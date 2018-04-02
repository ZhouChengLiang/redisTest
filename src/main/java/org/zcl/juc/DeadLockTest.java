package org.zcl.juc;
public class DeadLockTest {
    public static String lock1 = "lock1";
    public static String lock2 = "lock2";
    public static void main(String[] args){
        Thread a = new Thread(new Lock1());
        Thread b = new Thread(new Lock2());
        a.start();
        b.start();
    }
}
class Lock1 implements Runnable{
    @Override
    public void run(){
        try{
            while(true){
            	System.out.println(Thread.currentThread().getName()+" want to get lock1");
                synchronized(DeadLockTest.lock1){
                    System.out.println(Thread.currentThread().getName()+" get lock1");
                    Thread.sleep(100);
                    synchronized(DeadLockTest.lock2){
                        System.out.println(Thread.currentThread().getName()+" get lock2");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
class Lock2 implements Runnable{
    @Override
    public void run(){
        try{
            while(true){
            	System.out.println(Thread.currentThread().getName()+" want to get lock2");
                synchronized(DeadLockTest.lock2){
                    System.out.println(Thread.currentThread().getName()+" get lock2");
                    Thread.sleep(100);
                    synchronized(DeadLockTest.lock1){
                        System.out.println(Thread.currentThread().getName()+" get lock1");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}