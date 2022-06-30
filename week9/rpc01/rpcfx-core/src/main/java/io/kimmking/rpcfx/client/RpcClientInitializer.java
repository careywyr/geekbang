package io.kimmking.rpcfx.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/29
 */
public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new HttpClientCodec());
        socketChannel.pipeline().addLast(new HttpObjectAggregator(512 * 1024));

    }
}

