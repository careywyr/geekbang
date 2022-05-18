package cn.leafw.gateway.handler;

import cn.leafw.gateway.filter.RequestFilter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/5/17
 */
public class HttpChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final List<String> proxyServer;

    public HttpChannelInitializer(List<String> proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        // HttpObjectAggregator 会将多个消息对象转变为单个 FullHttpRequest 或者 FullHttpResponse。
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        pipeline.addLast(new RequestFilter());
        pipeline.addLast(new GatewayHandler(this.proxyServer));
    }
}

