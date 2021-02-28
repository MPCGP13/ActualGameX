package org.academiadecodigo.group1.actualgamex.user;

import org.academiadecodigo.group1.actualgamex.graphics.Screen;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class UserTimer implements Runnable {

    private int seconds;
    private Screen screen;

    public UserTimer(int seconds, Screen screen){
        this.seconds = seconds;
        this.screen = screen;
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
            screen.getCounter().setText(seconds-- + "s");
        }
    }

}

