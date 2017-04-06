package test2_NIO;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test
{
	public static void sop(Object o)
	{
		System.out.println(o);
	}
	public static void main(String[] args) throws Exception
	{
		ByteBuffer bf =ByteBuffer.allocate(1024);
		RandomAccessFile raf = new RandomAccessFile("/Users/zhangxu/Desktop/未命名文件夹/自传","rw");
		FileChannel fc = raf.getChannel();
		int len = fc.read(bf);
		while(len!=-1)
		{
			bf.flip();
			while(bf.hasRemaining())
				sop(bf.get());
			byte bs = bf.get();
			
			//sop(bs.length);
			bf.clear();
			len=fc.read(bf);
		}
		
	}

}
