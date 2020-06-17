package com.java.thread.synchronization;

public class Print1To10UsingTwoThreads
{
    public static void main ( String[] args )
    {
        Task task = new Task( 0, 10 );
        Thread oddThread = new Thread( new OddThread( task ) );
        oddThread.setName( "Odd Thread " );
        Thread evenThread = new Thread( new EvenThread( task ) );
        evenThread.setName( "Even Thread " );
        
        oddThread.start();
        evenThread.start();
    }
}
class Task
{
    int start;

    int max;

    private final Object lock = new Object();

    Task ( int start, int max )
    {
        this.start = start;
        this.max = max;
    }

    public void printOdd ()
    {
        synchronized ( lock )
        {
            while ( start <= max )
            {
                if ( start % 2 != 0 )
                {
                    System.out.println( Thread.currentThread().getName() + " = " + start + " " );
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

    public void printEven ()
    {
        synchronized ( lock )
        {
            while ( start <= max )
            {
                if ( start % 2 == 0 )
                {
                    System.out.println( Thread.currentThread().getName() + " = " + start + " " );
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

class OddThread implements Runnable
{
    Task task;
    
    OddThread(Task task)
    {
        this.task = task;
    }
    @Override
    public void run ()
    {
        task.printOdd();
    }

}

class EvenThread implements Runnable
{
    Task task;
    
    EvenThread(Task task)
    {
        this.task = task;
    }
    @Override
    public void run ()
    {
        task.printEven();
    }

}


