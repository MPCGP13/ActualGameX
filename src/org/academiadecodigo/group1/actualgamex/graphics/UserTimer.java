package org.academiadecodigo.group1.actualgamex.graphics;

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

    /**
     * Method that creates a Timer Task each second of the count down
     */
    public void run() {
        Timer timer = new Timer();
        timer.schedule(new Counter(), 5, 1000);

        try {
            TimeUnit.SECONDS.sleep(seconds + 1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.cancel();
    }

    /**
     * Extends from TimerTask and prints each second of the count down
     */
    private class Counter extends TimerTask{

        public Counter() { }

        @Override
        public void run() {
            screen.getCounter().setText(seconds-- + "s");
        }
    }

}

