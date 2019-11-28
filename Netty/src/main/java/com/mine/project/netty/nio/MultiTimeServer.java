package com.mine.project.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wuhanhong
 * @date 2019-11-28
 * @descp
 */
public class MultiTimeServer implements Runnable {
    private Selector selector;
    private ServerSocketChannel channel;
    private volatile boolean stop = false;

    public MultiTimeServer(int port) {
        try {
            selector = Selector.open();
            channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(port), 1024);
            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The Multi Time Server started in port : " + port);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1024);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey selectionKey = null;
                while (iterator.hasNext()) {
                    selectionKey = iterator.next();
                    iterator.remove();
                    handleInput(selectionKey);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isValid()) {
            if (selectionKey.isAcceptable()) {
                //处理新接入请求
                System.out.println("handle new connection");
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }

            if (selectionKey.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = socketChannel.read(buffer);
                if (readBytes > 0) {
                    buffer.flip();
                    byte[] datas = new byte[buffer.remaining()];
                    buffer.get(datas);
                    String body = new String(datas, StandardCharsets.UTF_8);
                    System.out.println("the server receive message is : " + body);
                    String responseBody = body + System.currentTimeMillis();
                    System.out.println("the server response message is : " + responseBody);
                    byte[] out = responseBody.getBytes();
                    ByteBuffer outByte = ByteBuffer.allocate(out.length);
                    outByte.put(out);
                    outByte.flip();
                    socketChannel.write(outByte);
                } else if (readBytes < 0) {
                    selectionKey.cancel();
                    socketChannel.close();
                } else {
                    System.out.println("empty message");
                }
            }
        }
    }
}
