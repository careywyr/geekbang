package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.JSONObject;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/29
 */
@Slf4j
public class JsonEncoder extends MessageToMessageEncoder<RpcfxRequest> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcfxRequest rpcfxRequest, List<Object> list) throws Exception {
        log.info("============encode========");
        FullHttpRequest request = null;
        ByteBuf encodeBuf = Unpooled.copiedBuffer((CharSequence) JSONObject.toJSONString(rpcfxRequest), StandardCharsets.UTF_8);
        request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.POST, "", encodeBuf);
        HttpUtil.setContentLength(request, encodeBuf.readableBytes());
        //2. 填充header
        request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        request.headers().add(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
        request.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        list.add(request);
    }
}

