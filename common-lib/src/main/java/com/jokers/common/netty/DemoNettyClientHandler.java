package com.jokers.common.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author yuton
 * @version 1.0
 * @description
 * @since 2017/4/25 16:57
 */
@Slf4j
public class DemoNettyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String o) throws Exception {
        log.info("收到服务器发来的信息{}", o);
        log.info("请输入您要传输的话语，并以回车结束");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String text = buffer.readLine();
        channelHandlerContext.writeAndFlush(text);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush("ping");
    }
}
