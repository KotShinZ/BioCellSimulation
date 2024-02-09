package com.shin.Baselib.DataClass.vector;

public class Vector3 extends Vector3_base<Double> {
    public Vector3() {
        super();
    }

    public Vector3(double num) {
        super(num);
    }

    public Vector3(double x, double y) {
        super(x, y);
    }

    public Vector3(double x, double y, double z) {
        super(x, y, z);
    }

    public Vector3Int ToVectorInt() {
        return new Vector3Int(x().intValue(), y().intValue(), z().intValue());
    }

}
