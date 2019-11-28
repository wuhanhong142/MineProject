package com.mine.project.netty.nio;

/**
 * @author wuhanhong
 * @date 2019-11-28
 * @descp
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 9000;
        MultiTimeServer timeServer = new MultiTimeServer(port);
        new Thread(timeServer, "MultiTimeServer-0001").start()  ;
    }
}
