package com.jokers.common.netty;

import java.util.Timer;
import java.util.TimerTask;

/**
 * NettyClienTest
 * Created by yuTong on 2018/03/23.
 */
public class NettyClientTest {
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient(2222, "127.0.0.1");
        nettyClient.start();
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                nettyClient.closeNettyService();
            }
        }, 20000L);
    }
}
