package com.shin.Baselib.DataClass.vector;

public class Main {
    public static void main(String[] args) {
        Vector3Int vec = new Vector3Int();
        vec.setX(5);
        ;
        vec.MulIns(new Vector3Int(5, 1, 1));
        System.out.println(vec);
    }
}
