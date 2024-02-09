package com.shin.Baselib.Interface;

/**
 * 四則演算が出来る
 * 新しいインスタンスを生成する
 */
public interface ICalcuratable<T> {
    public T Add(T t);

    public T Sub(T t);

    public T Mul(T t);

    public T Div(T t);
}
