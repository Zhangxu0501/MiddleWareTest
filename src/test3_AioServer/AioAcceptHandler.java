package test3_AioServer;

import test3_AioClient.*;
import test3_AioClient.AioReadHandler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.ServerSocketChannel;

public class AioAcceptHandler implements CompletionHandler<AsynchronousSocketChannel,AsynchronousServerSocketChannel> {

 
    public void startRead(AsynchronousSocketChannel socketChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        socketChannel.read(buffer,buffer,new AioReadHandler(socketChannel));
    }

    @Override
    public void completed(AsynchronousSocketChannel socketChannel, AsynchronousServerSocketChannel serverSocketChannel) {
        System.out.println("远程socket为："+socketChannel.toString());

        //循环监听
        serverSocketChannel.accept(serverSocketChannel,this);
        startRead(socketChannel);

    }



    @Override
    public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
        exc.printStackTrace();

    }





}