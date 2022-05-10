package com.hhp.threadpool;

public class WorkThread extends Thread {

    @Override
    public void run() {
        System.out.println("Prepare to execute work thread...");
    }
}
