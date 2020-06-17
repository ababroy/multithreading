package com.java.thread.concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Work
    implements Runnable
{
    String name;

    public Work ( String name )
    {
        this.name = name;
    }

    public void run ()
    {
        String tName = Thread.currentThread().getName();
        System.out.println( tName + " work started " + name + "=>" );
        try
        {
            Thread.sleep( 5000 );
        }
        catch ( InterruptedException e )
        {
            // TODO: handle exception
        }
        System.out.println( tName + " work finished " + name + "<=" );
    }
}

public class ThreadPoolDemo
{
    public static void main ( String[] args )
    {
        Work[] works = { new Work( "Stt release" ), new Work( "UST release" ), new Work( "UTP View release" ), new Work( "STT Build" ), new Work( "STT Testing" ) };

        ExecutorService pool = Executors.newFixedThreadPool( 3 );

        try
        {
            for ( Work work : works )
            {
                pool.submit( work );
            }
        }
        finally
        {
            pool.shutdown();
        }

    }
}
