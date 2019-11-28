package com.mine.project.netty.demo;

/**
 * @author wuhanhong
 * @date 2019-11-28
 * @descp
 */
public class TimeServer {
    public static void main(String[] args) {
        new TimeServerNetty(9001).bind();
    }
}
