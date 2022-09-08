package com.hundsun.thread;

public class MyThread extends Thread{

    private int count = 5;

    public MyThread(String name) {
        this.setName(name);
    }
    public MyThread() {
    }

    @Override
    synchronized public void run() {
        super.run();
        count--;
        System.out.println(count);
        //System.out.println(Thread.currentThread().getName());
    }
}
