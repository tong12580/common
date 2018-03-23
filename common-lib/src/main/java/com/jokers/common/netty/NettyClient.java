package com.jokers.common.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * NettyClient
 * Created by yuTong on 2018/03/23.
 */
@Slf4j
public class NettyClient {
    private int port;
    private String host;
    private static EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    public NettyClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .group(eventLoopGroup)
                .remoteAddress(host, port)
                .handler(new SocketChannelInitializer<SocketChannel>(new DemoNettyClientHandler(), null));
        ChannelFuture future;
        try {
            future = bootstrap.connect(host, port).sync();
            if (future.isSuccess()) {
                SocketChannel socketChannel = (SocketChannel) future.channel();
                System.out.println("----------------connect server success----------------" + socketChannel.localAddress());
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("NettyClient error {}", e);
        }
    }

    public void closeNettyService() {
        if (null != eventLoopGroup) {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
