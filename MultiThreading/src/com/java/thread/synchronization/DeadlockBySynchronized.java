package com.java.thread.synchronization;

public class DeadlockBySynchronized extends Thread
{
    A a = new A();
    B b = new B();
    
    public void run()
    {
        a.method1( b ); // child thread will execute this piece of code
    }
    void call ()
    {
        this.start(); // child thread will be responsible
        b.method2( a ); // main thread will be responsible
    }
    
    public static void main ( String[] args )
    {
        DeadlockBySynchronized obj = new DeadlockBySynchronized();
        obj.call();
    }

}

class A
{
    synchronized void method1 ( B b )
    {
        System.out.println( "Class-A -> method1() called" );
        try
        {
            Thread.sleep( 2000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        System.out.println( "method-1 calls b.last()..." );
        b.last();
    }

    synchronized void last ()
    {
        System.out.println( "Inside Object A last()" );
    }
}

class B 
{
    synchronized void method2 ( A a )
    {
        System.out.println( "Class-B -> method2() called" );
        try
        {
            Thread.sleep( 2000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        System.out.println( "method-2 calls a.last()..." );
        a.last();
    }

    synchronized void last ()
    {
        System.out.println( "Inside Object B last()" );
    }
}
