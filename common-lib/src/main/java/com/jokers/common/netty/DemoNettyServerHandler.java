package com.jokers.common.netty;

import com.jokers.common.cache.NettyCachePool;
import com.jokers.common.context.Constants;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * This is a demo about netty server
 * DemoNettyHandler
 * Created by yuTong on 2018/03/23.
 */
@Slf4j
public class DemoNettyServerHandler extends SimpleChannelInboundHandler<String> {

    private ExecutorService executorService;

    public DemoNettyServerHandler(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        if (msg.contains(Constants.COOKIE) || msg.contains(Constants.HTTP)
                || msg.contains(Constants.USER_AGENT) || msg.contains(Constants.SSH)) {
            ctx.writeAndFlush(Constants.PROFANITY);
            return;
        }
        log.info("DemoNettyHandler get a message {}", msg);
        log.info("ChannelHandlerContext id is {}", ctx.channel().id().asLongText());
        executorService.execute(() -> {
            log.info("Process this data");
            log.info("Save the channel to the cache, it may help you Two-way communication");
            NettyCachePool.getInstance().put(ctx.channel().id().asLongText(), ctx.channel());
            ctx.writeAndFlush("send you an apple");
        });
    }
}
