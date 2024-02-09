package com.shin.Baselib.Interface;

/**
 * 四則演算が出来る
 * そのインスタンスに対して計算を行う
 */
public interface IInstanceCalcuratable<T> {
    public void AddIns(T t);

    public void SubIns(T t);

    public void MulIns(T t);

    public void DivIns(T t);
}
