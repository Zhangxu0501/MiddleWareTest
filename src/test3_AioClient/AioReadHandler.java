package test3_AioClient;

/**
 * Created by zhangxu on 2017/4/27.
 */
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class AioReadHandler implements CompletionHandler<Integer,ByteBuffer> {
    private AsynchronousSocketChannel socket;

    public AioReadHandler(AsynchronousSocketChannel socket) {
        this.socket = socket;
    }


    private CharsetDecoder decoder = Charset.forName("GBK").newDecoder();



    @Override
    public void completed(Integer i, ByteBuffer buf) {


        if (i > 0) {
            buf.flip();
            try {

                System.out.println("收到" + socket.getRemoteAddress().toString() + "的消息:" + new String(buf.array()));

                if(buf.position()<=900)
                    buf.clear();
                else buf.compact();
            } catch (CharacterCodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            socket.read(buf, buf, this);
            //采取循环读的形式，即读完了一次马上调用读取方法，直到对方断线为止。
        } else if (i == -1) {
            System.out.println("没有读取到");
            try {
                System.out.println("对端断线:" + socket.getRemoteAddress().toString());
                buf = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.printStackTrace();
    }
}
