package com.java.thread.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo
{
    public static void main ( String[] args )
    {
        Display1 display = new Display1();

        MyReentrantLock1 lock1 = new MyReentrantLock1( display, "Abhijit" );
        lock1.setName( "abhi-thread" );
        MyReentrantLock1 lock2 = new MyReentrantLock1( display, "Som" );
        lock2.setName( "som-thread" );

        lock1.start();
        lock2.start();
    }
}

class MyReentrantLock1
    extends Thread
{
    String name;

    Display1 display;

    public MyReentrantLock1 ( Display1 display, String name )
    {
        this.display = display;
        this.name = name;
    }

    public void run ()
    {
        try
        {
            display.wish( name );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
    }
}

class Display1
{
    Lock lock = new ReentrantLock();

    public void wish ( String name ) throws InterruptedException
    {
        do
        {
            if ( lock.tryLock( 5000, TimeUnit.MILLISECONDS ) )
            {
//                for ( int i = 0; i < 6; i++ )
//                {
                    System.out.print( "Hello " );

                    try
                    {
                        Thread.sleep( 20000 );
                    }
                    catch ( InterruptedException e )
                    {
                        e.printStackTrace();
                    }

                    System.out.println( name );
//                }
                lock.unlock();
                System.out.println( Thread.currentThread().getName() +" releasing the lock" );
                break;
            }
            else
            {
                System.out.println();
                System.out.println( Thread.currentThread().getName() + " - Didn't get a chance to execute if part, started to execute else part!!!" );
            }
        }
        while ( true );
        
        System.out.println( " Finished!!!" );

    }
}
