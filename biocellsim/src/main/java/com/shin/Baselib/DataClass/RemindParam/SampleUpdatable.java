package com.shin.Baselib.DataClass.RemindParam;

import com.shin.Area.StepUpdater;
import com.shin.Area.Interface.ITimeUpdatable;

public class SampleUpdatable implements ITimeUpdatable {
    Double_Re num = new Double_Re((double) 0, 4);

    @Override
    public void Update() {
        var nowT = StepUpdater.now.nowTime;
        num.set(nowT * nowT);
        System.out.println(num.get());
        System.out.println(num.dndt(StepUpdater.now.dt, 2));
        System.out.println("\n");
    }

}
