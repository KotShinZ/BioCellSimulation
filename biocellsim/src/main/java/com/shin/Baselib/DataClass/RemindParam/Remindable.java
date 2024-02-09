package com.shin.Baselib.DataClass.RemindParam;

import java.util.function.BiFunction;

import com.shin.Baselib.DataClass.LimitedList;
import com.shin.Baselib.Interface.ICalcuratable;

/** これを継承したものは過去の更新を記録できる */
public class Remindable<T> implements Cloneable {
    public LimitedList<T> reminds;
    public BiFunction<T, T, T> func;
    T next = null;

    public Remindable(T t, int remindsNum) {
        reminds = new LimitedList<>(remindsNum);
        for (int i = 0; i < remindsNum; i++) {
            reminds.add(t);
        }
        next = t;
    }

    public Remindable(T t, int remindsNum, Reminder reminder) {
        this(t, remindsNum);
        reminder.Add(this);
    }

    /**
     * 情報を記録する
     * 今の情報はnullとなる
     */
    public void Remind() {
        if (next != null) {
            reminds.push(next);
        } else {
            reminds.push(get());
        }
        next = get();
    }

    /** 現在の値を取得 */
    public T value() {
        return reminds.get(0);
    }

    /** 現在の値を取得 */
    public T get() {
        return reminds.get(0);
    }

    /** 現在の値を取得 */
    public T getNext() {
        if (next != null) {
            return next;
        } else {
            return null;
        }
    }

    /** 現在の値を取得 */
    public T get(int n) {
        return reminds.get(n);
    }

    /** 過去の値を取得 */
    public T prevalue() {
        return reminds.get(1);
    }

    /** 過去の過去の値を取得 */
    public T preprevalue() {
        return reminds.get(2);
    }

    /** 現在の値をセット */
    public void set(T t) {
        reminds.set(0, t);
        setNext(t);
    }

    /** 次の値をセット */
    public void setNext(T t) {
        next = t;
    }

    /** 次の値をセット */
    public void addNext(T t) {
        if (t instanceof Number) {
            var n = (Number) next;
            var t2 = (Number) t;
            Double d = n.doubleValue() + t2.doubleValue();
            next = (T) d;
        } else if (next instanceof ICalcuratable) {
            var n = (ICalcuratable) next;
            n.Add(t);
        } else {
            System.err.println("Can't add");
        }
    }

    /** 現在の値をセット */
    public void set(int index, T t) {

        if (index == 0) {
            set(t);
        } else {
            reminds.set(index, t);
        }
    }

    /**
     * 引き算を定義する
     * 
     * @param i 番号（引かれる側）
     * @param j 番号(引く側)
     * @return
     */
    private Double GetDiff(int i, int j) {
        return ((Number) reminds.get(i)).doubleValue() - ((Number) reminds.get(j)).doubleValue();
    }

    /**
     * n次微分を取得する
     * Δtは一定である前提
     * 
     * @param t 微分する変数
     * @param n 何次微分か
     * @return
     */
    public Double dndt(Remindable<? extends Number> t, int n) {
        Double dt = (Double) t.sub1();
        return dndt(dt, n);
    }

    /**
     * n次微分を取得する
     * Δtは一定である前提
     * 
     * @param t 微分する変数
     * @param n 何次微分か
     * @return
     */
    public Double dndt(Double dt, int n) {
        try {
            return subn(n) / Math.pow(dt, n);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 再帰的に引き算を行い、fn-fn-1を求める
     * 
     * @param n 何次微分か
     * @return
     */
    public Double subn(int n) {
        return subn(n, n);
    }

    /**
     * 再帰的に引き算を行い、fn-fn-1を求める
     * 
     * @param n   何次微分か
     * @param max 引き算の際に最大の番号
     * @return
     */
    public Double subn(int n, int max) {
        try {
            if (n <= 1) {
                return GetDiff(max - 1, max);
            }
            return subn(n - 1, max - 1) - subn(n - 1, max);
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public T sub1() {
        if (reminds.size() < 2)
            return null;
        try {
            return (T) (Object) GetDiff(0, 1);
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public T sub2() {
        if (reminds.size() < 2)
            return null;
        try {
            Double d1 = GetDiff(0, 1);
            Double d2 = GetDiff(1, 2);
            return (T) (Object) (d1 - d2);
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
