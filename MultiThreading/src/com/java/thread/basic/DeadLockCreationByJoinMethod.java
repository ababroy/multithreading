package com.java.thread.basic;

public class DeadLockCreationByJoinMethod
{
    public static void main ( String[] args )
    {
        MyThread1.mt = Thread.currentThread();

        Thread t1 = new Thread( new MyThread1() );
        t1.start();
        try
        {
            t1.join(); //  Dead lock by main thread
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }

        for ( int i = 0; i < 6; i++ )
        {
            System.out.println( "Main Thread" );
        }
    }
}

class MyThread1
    implements Runnable
{
    static Thread mt;

    public void run ()
    {
        try
        {
            mt.join(); // Dead lock by child thread
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }

        for ( int i = 0; i < 6; i++ )
        {
            System.out.println( "Child Thread" );
        }
    }
}
