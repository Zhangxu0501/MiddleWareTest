package test3_AioServer;



import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.security.spec.ECField;
import java.util.concurrent.CountDownLatch;

public class AioTcpServer implements Runnable{
    AsynchronousServerSocketChannel channel = null;
    
    public static void main(String... args) throws Exception{

    new Thread(new AioTcpServer()).run();

    }
    @Override
    public void run() {

        //latch用于防止线程关闭
        CountDownLatch latch = new CountDownLatch(1);
        try
        {
            channel = AsynchronousServerSocketChannel.open();
            channel.bind(new InetSocketAddress(20001));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        channel.accept(channel,new AioAcceptHandler());
        try
        {
            latch.await();
            latch.countDown();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

