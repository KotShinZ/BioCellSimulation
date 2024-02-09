package com.shin.Baselib.DataClass;

import java.util.ArrayList;

public class Range implements Cloneable {

    enum RangeInEqual {
        equal,
        noEqual,
        leftOnly,
        RightOnly;
    }

    public double max;
    public double min;

    public Range() {
    }

    public Range(double max) {
        this.max = max;
    }

    public Range(double max, double min) {
        this.max = max;
        this.min = min;
        if (max < min)
            System.out.println("The Range is Wrongd !! " + "Range : max = " + String.format("%.1d", max)
                    + "   Range : min = " + String.format("%.1d", min));
    }

    public boolean IsInRange(double num, boolean equal) {
        return IsInRange(num, 0, equal);
    }

    public boolean IsInRange(double num, double offset, boolean equal) {
        return IsInRange(num, offset, equal ? RangeInEqual.equal : RangeInEqual.noEqual);

    }

    public boolean IsInRange(double num, double offset, RangeInEqual inEqual) {
        switch (inEqual) {
            case equal:
                /*
                 * System.out.println("~~~~~1");
                 * System.out.print(num);
                 * System.out.print("  ");
                 * System.out.print(max);
                 * System.out.print("  ");
                 * System.out.println(min);
                 * System.out.println(num <= max + offset);
                 * System.out.println(num >= min - offset);
                 * System.out.println(num <= max + offset && num >= min - offset);
                 * System.out.println("~~~~~");
                 */
                return num <= max + offset && num >= min - offset;
            case noEqual:
                return num < max + offset && num > min - offset;
            case leftOnly:
                return num <= max + offset && num > min - offset;
            case RightOnly:
                return num < max + offset && num >= min - offset;

            default:
                return false;
        }
    }

    public double Clamp(double num) {
        return clamp(num, min, max);
    }

    public int Clamp(int num) {
        return (int) clamp(num, min, max);
    }

    public double clamp(double num, double min, double max) {
        if (num < min) {
            return min;
        } else if (num > max) {
            return max;
        } else {
            return num;
        }
    }

    // minからmaxの間を等間隔で分ける
    public ArrayList<Double> IntervalList(double interval) {
        ArrayList<Double> gets = new ArrayList<>();

        for (double n = min; n < max; n += interval) {
            gets.add(n);
        }

        return gets;
    }

    @Override
    public Range clone() {
        Range b = null;
        try {
            b = (Range) super.clone(); // 親クラスのcloneメソッドを呼び出す
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public Range Offset(double f) {
        /** RangeをFだけ大きくする */
        var c = this.clone();
        c.max += f;
        c.min -= f;
        if (c.max < c.min) {
            var m = (c.max + c.min) / 2;
            c.max = m;
            c.min = m;
        }
        return c;
    }

    @Override
    public String toString() {
        return "min:" + String.valueOf(min) + " max:" + String.valueOf(max);
    }
}
