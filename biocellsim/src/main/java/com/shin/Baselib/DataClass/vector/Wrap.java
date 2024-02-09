package com.shin.Baselib.DataClass.vector;

import com.shin.Baselib.Interface.IInstanceCalcuratable;
import com.shin.Baselib.Interface.ICalcuratable;

public class Wrap<T extends Number> implements ICalcuratable<Wrap<T>>, Cloneable, IInstanceCalcuratable<Wrap<T>> {
    public T num;

    public Wrap(T num) {
        this.num = num;
    }

    public Wrap(int num) {
        this.num = (T) (Number) num;
    }

    public Wrap(float num) {
        this.num = (T) (Number) num;
    }

    public Wrap(double num) {
        this.num = (T) (Number) num;
    }

    public int intValue() {
        return num.intValue();
    }

    public long longValue() {
        return num.longValue();
    }

    public double doubleValue() {
        return num.doubleValue();
    }

    public byte floatValue() {
        return num.byteValue();
    }

    public <S extends Number> Wrap<S> Cast() {
        return new Wrap<>((S) (Object) (num.doubleValue()));
    }

    @Override
    public String toString() {
        return num.toString();
    }

    @Override
    public Wrap<T> Add(Wrap<T> t) {
        return new Wrap<>(doubleValue() + t.doubleValue());
    }

    @Override
    public Wrap<T> Sub(Wrap<T> t) {
        return new Wrap<>(doubleValue() - t.doubleValue());
    }

    @Override
    public Wrap<T> Mul(Wrap<T> t) {
        return new Wrap<>(doubleValue() * t.doubleValue());
    }

    @Override
    public Wrap<T> Div(Wrap<T> t) {
        return new Wrap<>(doubleValue() / t.doubleValue());
    }

    public Wrap<T> Add(T t) {
        return new Wrap<>(doubleValue() + t.doubleValue());
    }

    public Wrap<T> Sub(T t) {
        return new Wrap<>(doubleValue() - t.doubleValue());
    }

    public Wrap<T> Mul(T t) {
        return new Wrap<>(doubleValue() * t.doubleValue());
    }

    public Wrap<T> Div(T t) {
        return new Wrap<>(doubleValue() / t.doubleValue());
    }

    public Wrap<T> clone() {
        return new Wrap<>(num);
    }

    @Override
    public void AddIns(Wrap<T> t) {
        Double d = num.doubleValue() + t.doubleValue();
        num = (T) d;
    }

    @Override
    public void SubIns(Wrap<T> t) {
        Double d = num.doubleValue() - t.doubleValue();
        num = (T) d;
    }

    @Override
    public void MulIns(Wrap<T> t) {
        Double d = num.doubleValue() * t.doubleValue();
        num = (T) d;
    }

    @Override
    public void DivIns(Wrap<T> t) {
        Double d = num.doubleValue() / t.doubleValue();
        num = (T) d;
    }

    public void AddIns(T t) {
        Double d = num.doubleValue() + t.doubleValue();
        num = (T) d;
    }

    public void SubIns(T t) {
        Double d = num.doubleValue() - t.doubleValue();
        num = (T) d;
    }

    public void MulIns(T t) {
        Double d = num.doubleValue() * t.doubleValue();
        num = (T) d;
    }

    public void DivIns(T t) {
        Double d = num.doubleValue() / t.doubleValue();
        num = (T) d;
    }
}