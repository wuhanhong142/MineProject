package com.mine.project.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;

/**
 * @author wuhanhong
 * @date 2019-11-28
 * @descp
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);

        String body = new String(req, StandardCharsets.UTF_8);
        System.out.println("the client receive message is : " + body);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] req = "TimeClientHandler client message".getBytes();
        ByteBuf byteBuf = Unpooled.buffer(req.length);
        byteBuf.writeBytes(req);
        ctx.writeAndFlush(byteBuf);
    }

}
