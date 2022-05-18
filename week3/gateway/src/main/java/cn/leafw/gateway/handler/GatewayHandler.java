package cn.leafw.gateway.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 处理转发到后端服务
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/5/17
 */
@Slf4j
public class GatewayHandler extends ChannelInboundHandlerAdapter {

    private final List<String> proxyServer;

    public GatewayHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
    }

    /**
     * 这个地方要做的就是根据路由规则将请求转发到后端server上
     * @param ctx ctx
     * @param msg msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        FullHttpRequest fullRequest = (FullHttpRequest) msg;
        String uri = fullRequest.uri();
        System.out.println("uri = " + uri);

        // 随机路由到一个服务器上
        int size = proxyServer.size();
        Random random = new Random(System.currentTimeMillis());
        String url = proxyServer.get(random.nextInt(size));

        CloseableHttpAsyncClient httpClient = AsyncHttpClient.getHttpClient();
        final HttpGet httpGet = new HttpGet(url);
        httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                doRequest(ctx, httpResponse, httpGet);
            }

            @Override
            public void failed(Exception e) {
                httpGet.abort();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }

    private void doRequest(final ChannelHandlerContext ctx, HttpResponse httpResponse, HttpGet httpGet) {
        FullHttpResponse response = null;
        try {
            byte[] body = new byte[0];
            try {
                body = EntityUtils.toByteArray(httpResponse.getEntity());
            } catch (IOException e) {
                httpGet.abort();
                e.printStackTrace();
            }
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(httpResponse.getFirstHeader("Content-Length").getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ctx.writeAndFlush(response);
        }
    }
}

