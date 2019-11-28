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
public class TimeServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);

        String body = new String(req, StandardCharsets.UTF_8);
        System.out.println("the server receive message is : " + body);
        String responseBody = body + ":" + System.currentTimeMillis();
        System.out.println("the server response message is : " + responseBody);
        ByteBuf outBuf = Unpooled.copiedBuffer(responseBody.getBytes());
        ctx.write(outBuf);
    }
}
