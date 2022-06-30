package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

/**
 * netty客户端处理服务端返回的数据
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/29
 */
@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

    private RpcfxResponse rpcfxResponse;

    private CountDownLatch countDownLatch;

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("==========channel active==========");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpResponse response) throws Exception {
        log.info("channelRead0");
        ByteBuf content = response.content();
        int len = content.readableBytes();
        byte[] arr = new byte[len];
        content.getBytes(0, arr);
        this.rpcfxResponse = JSON.parseObject(new String(arr, CharsetUtil.UTF_8), RpcfxResponse.class);
        log.info("服务端返回数据: {}", new String(arr, CharsetUtil.UTF_8));
        countDownLatch.countDown();

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

