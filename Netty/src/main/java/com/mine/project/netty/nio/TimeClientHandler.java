package com.mine.project.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wuhanhong
 * @date 2019-11-28
 * @descp
 */
public class TimeClientHandler implements Runnable {
    private String ip;
    private int port;
    private Selector selector;
    private SocketChannel channel;
    private volatile boolean stop = false;
    public TimeClientHandler(String ip, int port) {
        this.ip = ip;
        this.port = port;

        try {
            selector = Selector.open();
            channel = SocketChannel.open();
            channel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            exitSystem();
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            exitSystem();
        }

        while (!stop) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey selectionKey;
                while (iterator.hasNext()) {
                    selectionKey = iterator.next();
                    iterator.remove();
                    try {
                        handleClientInput(selectionKey);
                    } catch (IOException e) {
                        e.printStackTrace();
                        selectionKey.cancel();
                        if (selectionKey.channel() != null) {
                            selectionKey.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                exitSystem();
            }
        }
    }

    private void exitSystem() {
        System.exit(1);
    }

    private void handleClientInput(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isValid()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            if (selectionKey.isConnectable()) {
                if (socketChannel.finishConnect()) {
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    doWrite(socketChannel);
                }else {
                    exitSystem();
                }
            }

            if (selectionKey.isReadable()) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = socketChannel.read(buffer);
                if (readBytes > 0) {
                    buffer.flip();
                    byte[] datas = new byte[buffer.remaining()];
                    buffer.get(datas);
                    String body = new String(datas, StandardCharsets.UTF_8);
                    System.out.println(Thread.currentThread().getName() + " - the client receive message is : " + body);
                } else if (readBytes < 0) {
                    selectionKey.cancel();
                    selectionKey.channel().close();
                } else {
                    System.out.println("empty message");
                }
            }
        }
    }

    private void doWrite(SocketChannel socketChannel) throws IOException {

        String messageBody = "This is Client Message";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(messageBody.getBytes());
        buffer.flip();
        socketChannel.write(buffer);

        if (buffer.hasRemaining()) {
            System.out.println("Send Success");
        }
    }

    private void doConnect() throws IOException {
        if (channel.connect(new InetSocketAddress(this.ip, this.port))) {
            channel.register(selector, SelectionKey.OP_READ);
            doWrite(channel);
        } else {
            channel.register(selector, SelectionKey.OP_CONNECT);
        }
    }
}
