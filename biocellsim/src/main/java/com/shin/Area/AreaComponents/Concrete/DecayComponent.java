package com.shin.Area.AreaComponents.Concrete;

import com.shin.Area.StepUpdater;
import com.shin.Area.ValueArea;
import com.shin.Area.AreaComponents.Base.AreaComponent;

/**
 * 分子の数を減らす
 * - k M[x] dt で減らす
 */
public class DecayComponent extends AreaComponent {

    double k;

    public DecayComponent(double k) {
        super();
        this.k = k;
    }

    @Override
    public void Update() {
        ValueArea vArea = (ValueArea) area();
        var pre = vArea.get();
        var dt = StepUpdater.now.dt;

        // System.out.println(-1 * k * pre * dt);
        vArea.addNext(-1 * k * pre * dt);
    }
}
