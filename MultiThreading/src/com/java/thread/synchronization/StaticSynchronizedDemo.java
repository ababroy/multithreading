package com.java.thread.synchronization;

/**
 * 
 * @author AROY
 *
 */
public class StaticSynchronizedDemo
{
    public static void main ( String[] args )
    {
        // Even though Two separate threads calling two separate Objects, 
        // still we will get the regular output because of class level lock (static synchronized)
        Display d1 = new Display(); //<= object-1
        Thread t1 = new Thread( new MyRunnable( d1, "Som" ), "som-thread" );

        Display d2 = new Display();// <= Object-2
        Thread t2 = new Thread( new MyRunnable( d2, "Abhi" ), "abhi-thread" );

        t1.start();
        t2.start();

    }

}


class MyRunnable
    implements Runnable
{
    private Display display;

    private String name;

    MyRunnable ( Display display, String name )
    {
        this.display = display;
        this.name = name;
    }

    public void run ()
    {
        try
        {
            display.show( name );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
    }
}

class Display
{
    /**
     * 
     * @param name
     * @throws InterruptedException
     */
    static synchronized void show ( String name ) throws InterruptedException // Static makes it Class level lock rather object level lock
    {
        for ( int i = 0; i < 6; i++ )
        {
            System.out.print( "Hello  " );
            Thread.sleep( 2000 );
            System.out.println( name );
        }
        
    }
}
