package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

/**
 * netty客户端处理服务端返回的数据
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/29
 */
@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<RpcfxResponse> {

    private RpcfxResponse rpcfxResponse;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("==========channel active==========");
//        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.POST, "");
//        request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
//        request.headers().add(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
//        request.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
//        ctx.writeAndFlush(request);
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcfxResponse response) throws Exception {
        try {
            // 将 RpcResponse字符串 反序列化成 RpcResponse对象
            log.info("Netty client serializer : " + response.toString());
            rpcfxResponse = response;
        } finally{
            // 必须释放msg数据
            ReferenceCountUtil.release(response);

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public RpcfxResponse getRpcfxResponse() {
        return rpcfxResponse;
    }
}

