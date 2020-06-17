package com.java.thread.synchronization;

public class PrintHelloWorldByTwoThreads
{
    public static void main ( String[] args )
    {
        Task1 task = new Task1( 0, 10 );
        Thread t1 = new Thread( new HelloThread( task ) );
        t1.setName( "Hello Thread " );
        
        Thread t2 = new Thread( new WorldThread( task ) );
        t2.setName( "World Thread " );

        t1.start();
        t2.start();
    }
}

class Task1
{
    private final Object lock = new Object();

    private int start;

    private int max;

    Task1 ( int start, int max )
    {
        this.start = start;
        this.max = max;
    }

    public void printHello ()
    {
        synchronized ( lock )
        {
            while ( start < max )
            {
                if ( start % 2 == 0 )
                {
                    System.out.print( " [Hello " );
                    start++;
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

    public void printWorld ()
    {
        synchronized ( lock )
        {
            while ( start < max )
            {
                if ( start % 2 != 0 )
                {
                    System.out.print( "World] " );
                    start++;
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

class HelloThread
implements Runnable
{
Task1 task;

public HelloThread ( Task1 task )
{
    this.task = task;
}

@Override
public void run ()
{
    task.printHello();
}

}

class WorldThread
implements Runnable
{
Task1 task;

public WorldThread ( Task1 task )
{
    this.task = task;
}

@Override
public void run ()
{
    task.printWorld();
}

}

