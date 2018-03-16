package com.jokers.common.netty;

import com.jokers.common.cache.NettyCachePool;
import com.jokers.common.context.Constants;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.TypeParameterMatcher;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * NettyServerHandler
 * Created by yuTong on 2018/3/16.
 */
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<Object> {
    private ExecutorService executorService;
    private BusinessHandler businessHandler;

    public NettyServerHandler(ExecutorService executorService, BusinessHandler businessHandler) {
        this.executorService = executorService;
        this.businessHandler = businessHandler;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object t) throws Exception {
        String msg = (String) t;
        if (msg.contains(Constants.COOKIE) || msg.contains(Constants.HTTP)
                || msg.contains(Constants.USER_AGENT) || msg.contains(Constants.SSH)) {
            channelHandlerContext.writeAndFlush(Constants.PROFANITY);
            return;
        }
        log.trace("NettyServerHandler get message", t);
        executorService.execute(() -> businessHandler.handler(channelHandlerContext, t));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage(), cause);
        NettyCachePool.getInstance().remove(ctx);
        ctx.close();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.trace("NettyServerHandler Close the channel ip is：{}", ctx.channel().remoteAddress());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.trace("NettyServerHandler channelActive ip is: {}", ctx.channel().remoteAddress());
    }
}
