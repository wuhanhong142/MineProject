package com.mine.project.netty.nio;

/**
 * @author wuhanhong
 * @date 2019-11-28
 * @descp
 */
public class TimeClient {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new TimeClientHandler("127.0.0.1", 9000), "TimeClient-" + i).start();
        }
    }
}
