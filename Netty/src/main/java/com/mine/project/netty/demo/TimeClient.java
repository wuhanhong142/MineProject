package com.mine.project.netty.demo;

/**
 * @author wuhanhong
 * @date 2019-11-28
 * @descp
 */
public class TimeClient {
    public static void main(String[] args) {
        new TimeClientNetty().connect("127.0.0.1", 9001);
    }
}
