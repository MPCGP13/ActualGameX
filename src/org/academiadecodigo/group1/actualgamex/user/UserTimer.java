package org.academiadecodigo.group1.actualgamex.user;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class UserTimer implements Runnable {

    private int seconds;

    public UserTimer(int seconds){
        this.seconds = seconds;
    }

    public void run() {

        Timer timer = new Timer();
        timer.schedule(new Counter(seconds), 5, 1000);

        try {
            TimeUnit.SECONDS.sleep(seconds+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();


    }

    private class Counter extends TimerTask{

        private int seconds;

        public Counter(int seconds) {
            this.seconds = seconds;
        }


        @Override
        public void run() {
            System.out.println(seconds--);
        }
    }

}

