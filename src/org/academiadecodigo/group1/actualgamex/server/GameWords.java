package org.academiadecodigo.group1.actualgamex.server;

public class GameWords {

    private static final String[] WORDS =
    {"elephant",
    "array",
    "genius",
    "sand",
    "bubble",
    "luis",
    "vando",
    "programming",
    "boat",
    "mouse",
    "computer",
    "banana",
    "peixeDeFrente"};

    public static String random() {
        return WORDS[(int) (Math.random() * WORDS.length)];
    }

}
