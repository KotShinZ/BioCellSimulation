package com.shin.Baselib.DataClass.RemindParam;

import com.shin.Area.StepUpdater;

public class Main {
    public static void main(String[] args) {

        StepUpdater.now = new StepUpdater(1, 10);
        StepUpdater.now.Add(new SampleUpdatable());
        StepUpdater.now.Start();
    }
}