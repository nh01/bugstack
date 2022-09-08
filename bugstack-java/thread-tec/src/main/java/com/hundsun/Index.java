package com.hundsun;

import com.hundsun.thread.ALogin;
import com.hundsun.thread.BLogin;
import com.hundsun.thread.MyThread;

public class Index {

    public static void main(String[] args) {
//        Thread thread = new MyThread("tom");
//        MyThread myThread = new MyThread();
//        Thread thread = new Thread(myThread, "tom");
//        Thread thread2 = new Thread(myThread, "tom2");
//        Thread thread3 = new Thread(myThread, "tom3");
//        Thread thread4 = new Thread(myThread, "tom4");
//        thread.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        Thread thread1 = new MyThread();
//        Thread thread5 = new MyThread();
//        Thread thread6 = new MyThread();
//        thread1.start();
//        thread5.start();
//        thread6.start();
//        System.out.println(Thread.currentThread().getName());
        ALogin a = new ALogin();
        a.start();
        BLogin b = new BLogin();
        b.start();
    }
}
