package com.shin.Baselib.DataClass;

import com.shin.Baselib.DataClass.vector.Vector3;
import com.shin.Baselib.DataClass.vector.Vector3_base;

public class Range3 implements Cloneable {
    public Range x;
    public Range y;
    public Range z;

    public Range3(double max) {
        x = new Range(max);
        y = new Range(max);
        z = new Range(max);
    }

    public Range3(double max, double min) {
        x = new Range(max, min);
        y = new Range(max, min);
        z = new Range(max, min);
    }

    public Range3(double max_x, double min_x, double max_y, double min_y, double max_z, double min_z) {
        x = new Range(max_x, min_x);
        y = new Range(max_y, min_y);
        z = new Range(max_z, min_z);
    }

    public Range3(double max_x, double min_x, double max_y, double min_y) {
        x = new Range(max_x, min_x);
        y = new Range(max_y, min_y);
        z = new Range(0, 0);
    }

    public Range3(double max_x, double max_y, double max_z) {
        x = new Range(max_x, 0);
        y = new Range(max_y, 0);
        z = new Range(max_z, 0);
    }

    public boolean IsInRange(Vector3_base<? extends Number> value, boolean equal) {
        return IsInRange(value, 0, equal);
    }

    public boolean IsInRange(Vector3_base<? extends Number> value, double offset, boolean equal) {
        return x.IsInRange(value.x().doubleValue(), offset, equal)
                && y.IsInRange(value.y().doubleValue(), offset, equal)
                && z.IsInRange(value.z().doubleValue(), offset, equal);
    }

    public Vector3 Clamp(Vector3_base<? extends Number> num) {
        var _x = x.Clamp(num.x().doubleValue());
        var _y = x.Clamp(num.y().doubleValue());
        var _z = x.Clamp(num.z().doubleValue());
        return new Vector3(_x, _y, _z);
    }

    @Override
    public Range3 clone() {
        Range3 c = null;
        try {
            c = (Range3) super.clone();
            c.x = (Range) this.x.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    /** RangeをFだけ大きくする */
    public Range3 Offset(double f) {
        var c = this.clone();

        c.x = this.x.Offset(f);
        c.y = this.y.Offset(f);
        c.z = this.z.Offset(f);

        return c;
    }

    /** Rangeをxyzだけ大きくする */
    public Range3 Offset(double x, double y, double z) {
        var c = this.clone();

        c.x = this.x.Offset(x);
        c.y = this.y.Offset(y);
        c.z = this.z.Offset(z);

        return c;
    }

    @Override
    public String toString() {
        return "x   " + x.toString() + "\ny   " + y.toString() + "\nz   " + z.toString();
    }
}
