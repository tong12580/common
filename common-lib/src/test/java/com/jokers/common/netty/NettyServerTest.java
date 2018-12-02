package com.jokers.common.netty;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NettyTestMain
 * Created by yuTong on 2018/03/23.
 */
public class NettyServerTest {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        int port = 2222;
        NettyServer nettyServer = new NettyServer(port, executorService);
        nettyServer.start();
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                nettyServer.closeNettyService();
            }
        }, 20000L);
    }
}
