package com.shin.Area.AreaComponents.Concrete;

import com.shin.Area.ValueArea;
import com.shin.Area.AreaComponents.Base.AreaComponent;

/** 境界条件を適用し、値を常に固定する */
public class DirichletComponent extends AreaComponent {

    public double num;

    public DirichletComponent(double num) {
        this.num = num;
    }

    @Override
    public void Update() {
        ValueArea vArea = (ValueArea) area();
        vArea.set(num);
    }
}
