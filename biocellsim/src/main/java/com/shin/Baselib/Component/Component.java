package com.shin.Baselib.Component;

public class Component implements Cloneable {
    /** このコンポーネントを持つオブジェクト */
    public ComponentParent parent;

    public Component() {
        parent = new ComponentParent();
    }

    public Component(ComponentParent parent) {
        this.parent = parent;
    }

    public void SetParent(ComponentParent parent) {
        this.parent = parent;
    }

    public Component clone() {
        try {
            return (Component) (super.clone());
        } catch (Exception e) {
            return null;
        }
    }
}
