package test1_线程和锁;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test
{

	public static void main(String[] args)
	{
		
		Resource r = new Resource();
		producer p = new producer(r);
		consumer c =new consumer(r);
		
		Thread t1=new Thread(p);
		Thread t2=new Thread(c);
		t1.start();
		t2.start();
		
	}

}
class Resource 
{
	public static int res=0;
	Lock lock = new ReentrantLock();
	Condition consumer =lock.newCondition();
	Condition producer =lock.newCondition();
	public  void add()
	{
		lock.lock();
		res+=1;
		System.out.println("生产了地"+res+"个商品");
		consumer.signalAll();
		lock.unlock();
	}
	public  void consume() throws Exception
	{
		lock.lock();
		while(res<=0)
			try
		{
				consumer.await();
		}
				catch(Exception e)
		{
					
		}
		System.out.println("消费了地"+res+"个商品");
		res-=1;
		lock.unlock();
		
	}
}

class producer implements Runnable
{
	public Resource res=null;
	public producer(Resource res)
	{
		this.res=res;
	}
	public void run ()
	{
		while(true)
		{
			try
			{
				res.consume();
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class consumer implements Runnable
{
	public Resource res=null;
	public consumer(Resource res)
	{
		this.res=res;
	}
	public void run ()
	{
		while(true)
		{
			res.add();
		}
		
	}
}
