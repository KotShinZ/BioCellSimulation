package com.shin.Baselib.DataClass.vector;

public class Vector3Int extends Vector3_base<Integer> {
    public Vector3Int() {
        super();
    }

    public Vector3Int(int num) {
        super(num);
    }

    public Vector3Int(int x, int y) {
        super(x, y);
    }

    public Vector3Int(int x, int y, int z) {
        super(x, y, z);
    }

    public Vector3 ToVector() {
        return new Vector3((Double) x().doubleValue(), (Double) y().doubleValue(), (Double) z().doubleValue());
    }
}
