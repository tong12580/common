package com.jokers.common.netty;

import io.netty.channel.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * NettyChannelInitializer
 * Created by yuTong on 2018/03/23.
 */
public class SocketChannelInitializer<T extends Channel> extends ChannelInitializer<T> {

    private DemoNettyClientHandler demoNettyClientHandler;
    private DemoNettyServerHandler demoNettyServerHandler;

    public SocketChannelInitializer(DemoNettyClientHandler demoNettyClientHandler, DemoNettyServerHandler demoNettyServerHandler) {
        this.demoNettyClientHandler = demoNettyClientHandler;
        this.demoNettyServerHandler = demoNettyServerHandler;
    }

    @Override
    protected void initChannel(T ch) {
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new StringEncoder());
        if (null != demoNettyClientHandler) {
            ch.pipeline().addLast(demoNettyClientHandler);
        }
        if (null != demoNettyServerHandler) {
            ch.pipeline().addLast(demoNettyServerHandler);
        }
    }
}
