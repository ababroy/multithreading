package com.java.thread.basic;

public class InterruptedDemo
{
    public static void main ( String[] args )
    {
        MyThread mt = new MyThread();
        Thread t1 = new Thread( mt, "child-thread" );
        t1.start();

        t1.interrupt();

        System.out.println( Thread.currentThread().getName() + " Thread Ends." );
    }
}

class MyThread
    implements Runnable
{
    public void run ()
    {

        try
        {
            for ( int i = 0; i < 6; i++ )
            {
                System.out.println( Thread.currentThread().getName() );

                Thread.sleep( 2000 );
            }
        }
        catch ( InterruptedException e )
        {
            System.err.println( Thread.currentThread().getName() + " got interrupted!!!" );
        }
    }
}
