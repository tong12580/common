package com.jokers.common.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * nettyServer
 * Created by yuTong on 2018/03/16.
 */
@Slf4j
public class NettyServer extends Thread {
    private static EventLoopGroup bossGroup = new NioEventLoopGroup();
    private static EventLoopGroup workerGroup = new NioEventLoopGroup();
    private static ExecutorService executorService;

    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public NettyServer(int port, ExecutorService executorService) {
        this.port = port;
        NettyServer.executorService = executorService;
    }


    @Override
    public void run() {
        log.info("Netty server start, port is : {}", port);
        ServerBootstrap b = new ServerBootstrap();
        b = b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new SocketChannelInitializer<SocketChannel>(null, new DemoNettyServerHandler(executorService)))
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            ChannelFuture future = b.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("NettyServer error {}", e);
        }
    }

    public void closeNettyService() {
        if (null != workerGroup && null != bossGroup) {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            executorService.shutdown();
        }
    }
}
