package com.hundsun.controller;

public class LoginServlet {
    private static String username;
    private static String password;

    synchronized public static void doPost(String user, String pass) {
        try {
            username = user;
            if (username.equals("a")) {
                Thread.sleep(5000);
            }
            password = pass;
            System.out.println("username=" +username + ";password=" + password);
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
}
