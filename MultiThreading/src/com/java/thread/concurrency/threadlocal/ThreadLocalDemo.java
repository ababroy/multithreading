package com.java.thread.concurrency.threadlocal;

public class ThreadLocalDemo
{
    public static void main ( String[] args )
    {
        MyThread mt1 = new MyThread( "t1" );
        MyThread mt2 = new MyThread( "t2" );
        MyThread mt3 = new MyThread( "t3" );
        MyThread mt4 = new MyThread( "t4" );
        
        mt1.start();
        mt2.start();
        mt3.start();
        mt4.start();
        
        
    }
}

class MyThread
    extends Thread
{
    static int count = 0;

    public MyThread ( String name )
    {
        super( name );
    }

    private static ThreadLocal< ? > tl = new ThreadLocal< Object >()
    {
        public Integer initialValue ()
        {
            return count++;
        }
    };

    public void run ()
    {
        System.out.println( Thread.currentThread().getName() + " executing with count number: " + tl.get() );
    }
}
