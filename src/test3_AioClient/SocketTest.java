package test3_AioClient;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhangxu on 2017/4/27.
 */
public class SocketTest {
    public static void main(String [] args)throws Exception
    {
        Socket socket = new Socket("localhost",20001);
        OutputStream outputStreamut =socket.getOutputStream();
        PrintWriter pw = new PrintWriter(outputStreamut);
       
        for(int i =0;i<10000;i++)
        {
        	pw.println("abcdabcd"+i);
            pw.flush();
        }
        
    }
}
