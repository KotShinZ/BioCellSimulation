package com.shin.Baselib;

import java.util.Random;

public class Utill {
    /** 確立的にfloatをintにする */
    public static int GetIntProbability(float num) {
        int floor = (int) Math.floor(num); // 整数部分
        double random = num - floor - new Random().nextDouble(1);
        boolean b = random > 0;

        /**
         * if (num != 0) {
         * System.out.println(
         * String.valueOf(num) + ", "
         * + String.valueOf(ceil) + ", "
         * + String.valueOf(random) + ", "
         * + String.valueOf(b));
         * }
         */

        return floor + (b ? 1 : 0);
    }

    public static double Normalize(double value, double max, double min) {
        // 値が範囲[min, max]内にあることを保証
        value = Math.max(value, min);
        value = Math.min(value, max);

        // 値を0と1の間に正規化
        return (value - min) / (max - min);
    }
}
