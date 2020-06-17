package com.java.thread.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo
{
    public static void main ( String[] args )
    {
        Display display = new Display();

        MyReentrantLock lock1 = new MyReentrantLock( display, "Abhijit" );
        MyReentrantLock lock2 = new MyReentrantLock( display, "Som" );
        lock1.start();
        lock2.start();
    }
}

class MyReentrantLock
    extends Thread
{
    String name;

    Display display;

    public MyReentrantLock ( Display display, String name )
    {
        this.display = display;
        this.name = name;
    }

    public void run ()
    {
        display.wish( name );
    }
}

class Display
{
    Lock lock = new ReentrantLock();

    public void wish ( String name )
    {
        lock.lock();

        for ( int i = 0; i < 6; i++ )
        {
            System.out.print( "Hello " );

            try
            {
                Thread.sleep( 2000 );
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
            }

            System.out.println( name );
        }

        lock.unlock();
    }
}
