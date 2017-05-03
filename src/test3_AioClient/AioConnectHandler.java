package test3_AioClient;


import test3_AioServer.AioReadHandler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * Created by zhangxu on 2017/4/27.
 */
public class AioConnectHandler implements CompletionHandler {
    private Integer content = 0;
    public AioConnectHandler(Integer value){
        this.content = value;
    }

    //

    /*
    public void completed(Void attachment,AsynchronousSocketChannel connector) {
        try {
            connector.write(ByteBuffer.wrap(String.valueOf(content).getBytes())).get();
            startRead(connector);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException ep) {
            ep.printStackTrace();
        }
    }

    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
        exc.printStackTrace();
    }
    */

    public void startRead(AsynchronousSocketChannel socket) {
        ByteBuffer clientBuffer = ByteBuffer.allocate(1024);
        socket.read(clientBuffer, clientBuffer, new AioReadHandler(socket));
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Object result, Object attachment) {
        AsynchronousSocketChannel connector=(AsynchronousSocketChannel)attachment;
        try {
            connector.write(ByteBuffer.wrap(String.valueOf(content).getBytes())).get();
            startRead(connector);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException ep) {
            ep.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        exc.printStackTrace();

    }
}