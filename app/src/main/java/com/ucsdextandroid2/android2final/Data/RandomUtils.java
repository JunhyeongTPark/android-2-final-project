package com.ucsdextandroid2.android2final.Data;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class RandomUtils {
    public static int getRandomNumberInRange(){
        Random random = new Random();
        int value = random.nextInt(999);

        return value;
    }

}
