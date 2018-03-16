package com.jokers.common.netty;

import com.jokers.common.executor.ExecutorUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * netty
 * Created by yuTong on 2018/03/16.
 */
@Slf4j
public class NettyServer extends Thread {
    private static EventLoopGroup bossGroup = new NioEventLoopGroup();
    private static EventLoopGroup workerGroup = new NioEventLoopGroup();
    public static ExecutorService executorService = ExecutorUtil.newCachedThreadPool();

    private int port;
    private ChannelHandler channelHandler;
    private MessageToMessageDecoder decoder;
    private MessageToMessageDecoder encoder;

    public NettyServer(int port, ChannelHandler channelHandler) {
        this.port = port;
        this.channelHandler = channelHandler;
    }

    public NettyServer(int port, ChannelHandler channelHandler, MessageToMessageDecoder decoder) {
        this.port = port;
        this.channelHandler = channelHandler;
        this.decoder = decoder;
    }

    public NettyServer(int port, ChannelHandler channelHandler, MessageToMessageDecoder decoder, MessageToMessageDecoder encoder) {
        this.port = port;
        this.channelHandler = channelHandler;
        this.decoder = decoder;
        this.encoder = encoder;
    }


    @Override
    public void run() {
        log.info("Netty server start, port is : {}", port);
        ServerBootstrap b = new ServerBootstrap();
        b = b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        if (null != encoder) {
                            socketChannel.pipeline().addLast(encoder);
                        }
                        if (null != decoder) {
                            socketChannel.pipeline().addLast(decoder);
                        }
                        if (null != channelHandler) {
                            socketChannel.pipeline().addLast(channelHandler);
                        }
                    }
                })
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
