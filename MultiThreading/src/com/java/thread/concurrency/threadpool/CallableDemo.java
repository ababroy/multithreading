package com.java.thread.concurrency.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo
{
    public static void main ( String[] args )
    {
        CallJob cj = new CallJob();
        MyCallable[] mc = { new MyCallable( cj ), new MyCallable( cj ), new MyCallable( cj ), new MyCallable( cj ), new MyCallable( cj ), new MyCallable( cj ) };

        ExecutorService service = Executors.newFixedThreadPool( 2 );

        try
        {
            for ( MyCallable m : mc )
            {
                Future< Double > future = service.submit( m );
                try
                {
//                    if ( future.isDone() )
//                    {
                        System.out.println( Thread.currentThread().getName() + " returned value: " + future.get() );
//                    }
                }
                catch ( InterruptedException | ExecutionException e )
                {
                    e.printStackTrace();
                }
            }
        }
        finally
        {
            service.shutdown();
        }
    }
}

class MyCallable
    implements Callable< Double >
{
    CallJob callJob;

    public MyCallable ( CallJob callJob )
    {
        this.callJob = callJob;
    }

    public Double call () throws Exception
    {
        String tName = Thread.currentThread().getName();
        System.out.println( tName + " started " );
        double i = callJob.job();
        Thread.sleep( 3000 );
        System.out.println( tName + " returned " );
        return i;
    }
}

class CallJob
{
    double job ()
    {
        return Math.random();
    }
}
