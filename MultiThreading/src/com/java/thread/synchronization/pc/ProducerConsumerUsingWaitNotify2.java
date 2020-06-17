package com.java.thread.synchronization.pc;

import java.util.Stack;

public class ProducerConsumerUsingWaitNotify2
{

    public static void main ( String[] args )
    {
        Items2 items = new Items2( 10 );

        Thread pThread = new Thread( new Producer2( items ), "producer-thread" );
        pThread.start();

        Thread cThread = new Thread( new Consumer2( items ), "consumer-thread" );
        cThread.start();
    }

}

class Producer2
    implements Runnable
{
    Items2 items;

    Producer2 ( Items2 items )
    {
        this.items = items;
    }

    @Override
    public void run ()
    {
        items.producer();
    }

}

class Consumer2
    implements Runnable
{
    Items2 items;

    Consumer2 ( Items2 items )
    {
        this.items = items;
    }

    public void run ()
    {
        items.consumer();
    }
}

class Items2
{
    int maxItem;
    private int data=1;

    Items2 ( int maxItem )
    {
        this.maxItem = maxItem;
    }

    Stack< Integer > items = new Stack<>();

    private final Object lock = new Object();

    public void producer ()
    {
        synchronized ( lock )
        {
            while ( true )
            {
                if ( items.size() <= maxItem )
                {
                    System.out.println( Thread.currentThread().getName() + " = " + items.push( data++ ) );
                    try
                    {
                        Thread.sleep( 1000 );
                    }
                    catch ( InterruptedException ie )
                    {
                        ie.printStackTrace();
                    }
                    lock.notify();
                }
                else
                {
                    try
                    {
                        lock.wait();
                    }
                    catch ( Exception e )
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void consumer ()
    {
        synchronized ( lock )
        {
            while ( true )
            {
                if ( items.size() != 0 )
                {
                    System.out.println( Thread.currentThread().getName() + " = " + items.pop() );
                    try
                    {
                        Thread.sleep( 500 );
                    }
                    catch ( InterruptedException ie )
                    {
                        ie.printStackTrace();
                    }
                    lock.notify();
                }
                else
                {
                    try
                    {
                        lock.wait();
                    }
                    catch ( InterruptedException e )
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
