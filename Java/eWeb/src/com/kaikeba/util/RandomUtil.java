package com.kaikeba.util;

import java.util.Random;

public class RandomUtil {
    private static Random r = new Random();
    public static int getCode(){
        return r.nextInt(900000)+100000;
    };
}
