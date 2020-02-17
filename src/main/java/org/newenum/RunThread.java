package org.newenum;

import java.util.Observable;
import java.util.Observer;

public class RunThread extends Observable implements Runnable {
    
    
    // 此方法一经调用，立马可以通知观察者，在本例中是监听线程
    public void doBusiness() {
        if (true) {
            super.setChanged();
        }
        notifyObservers();
    }

    @Override
    public void run() {
        int c = 0;
        while (true) { // 模拟线程运行一段时间之后退出
            System.out.println("Runing- " + c + " ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                doBusiness();
                break;
            }
            c++;
            // 模拟抛出异常
            try {
                if (c == 4) {
                    String str = null;
                    str.length();// 此处将会抛出空指针异常
                }
            } catch (Exception e) {
                e.printStackTrace();
                doBusiness();// 在抛出异常时调用，通知观察者，让其重启线程
                break;// 异常抛出之后，一定要跳出循环，保证将线程送进地狱
            }
        }
    }

    public static void main(String[] args) {

        RunThread run = new RunThread();

        Listener listen = new Listener();

        run.addObserver(listen);

        new Thread(run).start();

        // run.doBusiness();

    }
}

class Listener implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("RunThread死机");
        RunThread run = new RunThread();
        run.addObserver(this);
        new Thread(run).start();
        System.out.println("RunThread重启");
    }
}

