package com.shin.Area.AreaComponents.Concrete;

import com.shin.Area.StepUpdater;
import com.shin.Area.ValueArea;
import com.shin.Area.AreaComponents.Base.AreaComponent;

public class ProductionComponent extends AreaComponent {
    double g;

    public ProductionComponent(double g) {
        super();
        this.g = g;
    }

    @Override
    public void Update() {
        ValueArea vArea = (ValueArea) area();
        var dt = StepUpdater.now.dt;
        vArea.addNext(g * dt);
    }
}
