package com.hundsun.thread;

import com.hundsun.controller.LoginServlet;

public class BLogin extends Thread{

    @Override
    public void run() {
        super.run();
        LoginServlet.doPost("b", "bb");
    }
}
