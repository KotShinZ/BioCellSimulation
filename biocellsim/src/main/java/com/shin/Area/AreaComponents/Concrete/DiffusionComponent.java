package com.shin.Area.AreaComponents.Concrete;

import com.shin.Area.Area;
import com.shin.Area.StepUpdater;
import com.shin.Area.ValueArea;
import com.shin.Area.AreaComponents.Base.AreaNextComponent;

/** 値を拡散させる */
public class DiffusionComponent extends AreaNextComponent {

    public float D = 0.1f;

    public DiffusionComponent() {
    }

    public DiffusionComponent(float D) {
        this.D = D;
    }

    @Override
    public void NextUpdate(Area left, Area right, int dim) {
        if (left == null || right == null)
            return;
        ValueArea vArea = (ValueArea) area(); // 自分のエリア
        ValueArea vleft = (ValueArea) left; // 他のエリア
        ValueArea vright = (ValueArea) right; // 他のエリア

        Double dt = StepUpdater.now.dt;
        Double dx_2 = vArea.Distance(vleft) * vArea.Distance(vright);
        // Double dx_2 = (double) 1;

        Double rapU = (vleft.get() + vright.get() - 2 * vArea.get()) / dx_2;
        Double dudt = D * rapU; // du/dt = DΔu
        vArea.addNext(dudt * dt); // 自分の数を変更 f(t+1) = f(t) + dt * du/dt
    }
}
