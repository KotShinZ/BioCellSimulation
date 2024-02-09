package com.shin.Baselib.DataClass;

import java.util.LinkedList;

/** 数が制限されたリスト */
public class LimitedList<T> extends LinkedList<T> {

    private int size;

    public LimitedList(int size) {
        super();
        this.size = size;
    }

    public void CheckSize() {
        while (size() > size) {
            removeLast();
        }
    }

    @Override
    public boolean add(T e) {
        var b = super.add(e);
        CheckSize();
        return b;
    }

    @Override
    public void add(int index, T e) {
        super.add(index, e);
        CheckSize();
    }

    @Override
    public void addFirst(T e) {
        super.addFirst(e);
        CheckSize();
    }

    @Override
    public void addLast(T e) {
        super.addLast(e);
        CheckSize();
    }

    @Override
    public void push(T e) {
        super.push(e);
        CheckSize();
    }
}
