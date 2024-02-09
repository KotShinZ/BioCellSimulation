package com.shin.Area;

import com.shin.Baselib.DataClass.RemindParam.Double_Re;
import com.shin.Baselib.DataClass.vector.Vector3;
import com.shin.Baselib.DataClass.vector.Vector3Int;

/**
 * 実数を一つ持つArea
 * 値は負にならない
 */
public class ValueArea extends Area {

    Double_Re num = new Double_Re(0.0, 3);

    public double get() {
        return num.get();
    }

    public double getNext() {
        return num.getNext();
    }

    public void setNext(double d) {
        var n = d >= 0 ? d : 0;
        num.setNext(n);
    }

    public void addNext(double d) {
        if (num.getNext() + d >= 0) {
            num.addNext(d);
        } else {
            num.setNext((double) 0);
        }

    }

    public void set(double d) {
        var n = d >= 0 ? d : 0;
        num.set(n);
    }

    public ValueArea(Vector3 position) {
        super(position);
    }

    public ValueArea(Vector3Int position) {
        super(position);
    }

    public String toString() {
        return String.format("%.1f", num.get(0));
    }

}
