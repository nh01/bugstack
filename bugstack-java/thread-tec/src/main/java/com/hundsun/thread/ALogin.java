package com.hundsun.thread;

import com.hundsun.controller.LoginServlet;

public class ALogin extends Thread{
    @Override
    public void run() {
        super.run();
        LoginServlet.doPost("a", "aa");
    }
}
