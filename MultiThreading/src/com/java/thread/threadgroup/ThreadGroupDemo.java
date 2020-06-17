package com.java.thread.threadgroup;

public class ThreadGroupDemo
{
    public static void main ( String[] args )
    {
        ThreadGroup system = Thread.currentThread().getThreadGroup().getParent();
        System.out.println( system.getName() );
        
        String systemsChild = Thread.currentThread().getThreadGroup().getName();
        System.out.println( systemsChild );
        
        Thread[] th = new Thread[system.activeCount()];
        printAllInsideSystemThreadGroup(system,th);
        
    }
    
    
    private static void printAllInsideSystemThreadGroup (ThreadGroup tg, Thread[] th)
    {
        tg.enumerate( th );
        for(Thread t : th)
        {
            System.out.println( t +" ... " + t.isDaemon() );
        }
    }
}
