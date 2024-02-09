package com.shin.Baselib.DataClass.vector;

public class Vector3_base<T extends Number> extends Vector<T> {

    public Vector3_base() {
        super(3);
    }

    public Vector3_base(T num) {
        this();
        setX(num);
        setY(num);
        setZ(num);
    }

    public Vector3_base(T x, T y) {
        this();
        setX(x);
        setY(y);
    }

    public Vector3_base(T x, T y, T z) {
        this();
        setX(x);
        setY(y);
        setZ(z);
    }

    public T x() {
        return nums.get(0).num;
    }

    public T y() {
        return nums.get(1).num;
    }

    public T z() {
        return nums.get(2).num;
    }

    public void setX(T t) {
        nums.get(0).num = t;
    }

    public void setY(T t) {
        nums.get(1).num = t;
    }

    public void setZ(T t) {
        nums.get(2).num = t;
    }
}
