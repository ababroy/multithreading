package com.java.thread.daemon;

public class DemonDemo
{
    public static void main ( String[] args )
    {
        MyThread mt = new MyThread();
        mt.setDaemon( true ); // It's a right place to set as Daemon
        mt.start();

        System.out.println( "Executed by " + Thread.currentThread().getName() + " thread: " );
    }
}

class MyThread
    extends Thread
{
    public void run ()
    {
        for ( int i = 0; i < 10; i++ )
        {
            System.out.println( Thread.currentThread().getName() + ", count:" + i ); // executed by child thread
            try
            {
                Thread.sleep( 1000 );
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
            }
        }
    }
}
