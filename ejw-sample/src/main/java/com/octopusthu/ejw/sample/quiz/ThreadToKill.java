package com.octopusthu.ejw.sample.quiz;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadToKill extends Thread {
    private final AtomicBoolean running = new AtomicBoolean(false);
    private int interval;

    public ThreadToKill(int sleepInterval) {
        this.interval = sleepInterval;
    }

    public void stopMe() {
        this.running.set(false);
    }

    @Override
    public void run() {
        this.running.set(true);
        while (this.running.get()) {
            System.out.println("Doing something...");
            try {
                Thread.sleep(this.interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Stopped.");
    }

    public static void main(String[] args) {
        ThreadToKill thread = new ThreadToKill(1000);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stopMe();
    }
}
