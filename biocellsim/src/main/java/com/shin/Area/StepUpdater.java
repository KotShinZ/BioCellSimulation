package com.shin.Area;

import java.util.ArrayList;
import java.util.function.BiConsumer;

import com.shin.Area.Interface.ITimeUpdatable;
import com.shin.Baselib.DataClass.RemindParam.Double_Re;
import com.shin.Baselib.DataClass.RemindParam.Reminder;

/** 単位時間毎に更新を行う */
public class StepUpdater {
    public static StepUpdater now;

    public Reminder reminder;
    public ArrayList<ITimeUpdatable> updatables = new ArrayList<>();

    public BiConsumer<StepUpdater, ITimeUpdatable> onStep;
    public BiConsumer<StepUpdater, ITimeUpdatable> onEndStep;

    public Double_Re dt_Re = new Double_Re((double) 0, 3);

    public double nowTime = 0;
    public double dt = 1;
    public double endTime = 100;

    public double progressRate = 0.05; // 進捗を表示する間隔（デフォルトは5%）
    public double preProgress = 9999999;

    public StepUpdater() {
        reminder = Reminder.nowReminder;
    }

    public StepUpdater(double dt, double endTime) {
        this();
        this.dt = dt;
        this.endTime = endTime;
    }

    public StepUpdater(double dt, double endTime, int step_Re) {
        this();
        this.dt = dt;
        this.endTime = endTime;
        dt_Re = new Double_Re((double) 0, step_Re);
    }

    public StepUpdater(Reminder reminder) {
        this.reminder = reminder;
    }

    /** 更新するものを登録する */
    public void Add(ITimeUpdatable... updatable) {
        for (ITimeUpdatable iTimeUpdatable : updatable) {
            updatables.add(iTimeUpdatable);
        }
    }

    public void Start(ITimeUpdatable... updatable) {
        Add(updatable);
        Start();
    }

    /** 時間の更新をスタートする */
    public void Start() {
        System.out.println("シュミレーションを開始します");

        while (true) {
            Update();
            /*
             * var a = (AreaArray) updatables.get(0);
             * a.SystemPrintAreas();
             * a.AreaMethod(ar -> {
             * var v = (ValueArea) ar;
             * if (v.num.getNext() != null && v.num.getNext() != 0) {
             * System.out.println(v.num.getNext());
             * }
             * });
             * System.out.println();
             */
            reminder.Remind();

            dt_Re.set(dt); // ステップタイムの変更に対応
            nowTime += dt; // 時間を更新

            if (nowTime % (endTime * progressRate) < preProgress) {
                System.out.println(String.valueOf(nowTime) + "まで終了");
            }
            preProgress = nowTime % (endTime * progressRate);
            if (nowTime >= endTime) {
                break; // 時間を超えると終了
            }
        }

        System.out.println("シュミレーションが終了しました");
    }

    /** 毎回の更新 */
    public void Update() {
        for (ITimeUpdatable iTimeUpdatable : updatables) {
            if (onStep != null)
                onStep.accept(this, iTimeUpdatable); // 更新時の動作

            iTimeUpdatable.Update(); // 各オブジェクトを更新

            if (onEndStep != null)
                onEndStep.accept(this, iTimeUpdatable); // 更新終了時の動作
        }
    }
}