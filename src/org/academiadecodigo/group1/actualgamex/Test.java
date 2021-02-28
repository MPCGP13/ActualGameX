package org.academiadecodigo.group1.actualgamex;

import org.academiadecodigo.group1.actualgamex.graphics.Screen;
import org.academiadecodigo.group1.actualgamex.graphics.UserGraphics;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {

        Screen screen = new Screen("Fake Dudu");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        screen.start();


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        screen.vote();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //screen.win();


    }

}
