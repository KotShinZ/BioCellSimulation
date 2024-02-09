package com.shin.Simrations;

import com.shin.Area.StepUpdater;
import com.shin.Area.AreaArray;
import com.shin.Area.AreaComponents.Concrete.DecayComponent;
import com.shin.Area.AreaComponents.Concrete.DiffusionComponent;
import com.shin.Area.AreaComponents.Concrete.MigrateComponent;
import com.shin.Area.AreaComponents.Concrete.NeumannComponent;
import com.shin.Area.AreaComponents.Concrete.ProductionComponent;
import com.shin.Baselib.DataClass.Range3;
import com.shin.Baselib.DataClass.vector.Vector;
import com.shin.Baselib.DataClass.vector.Vector3Int;
import com.shin.SuportSim.*;
import com.shin.Area.ValueArea;

public class BigMigrate {
    final static int simTime = 300; // シュミレーション時間
    final static Vector3Int simSize = new Vector3Int(300, 300, 1); // シュミレーションサイズ

    final static double dt = 1;
    final static double dx = 1;

    final static String preName = "./Out/4/area_Sim1"; // パス
    final static String name = "areas_"; // パス
    final static String path = GetPath.Get(preName, simSize, simTime, name);

    public static void main(String[] args) {
        StepUpdater.now = new StepUpdater(dt, simTime); // dtを設定
        var areas = CreateAreas.CreateAreaArray(simSize, dx, path); // エリアを構築する
        AddComponent(areas); // エリアにコンポーネントを追加

        // areas.AreaMethod(a -> System.out.println(((Area)
        // a.components.get(0).parent).position.get()));

        StepUpdater.now.Start(areas); // シュミレーションを開始
        // areas.PrintHeatMap("./Out/4/heatmap_Sim1.png", 5, 0);
        // CallPython.call(GetPath.Get(preName, simSize, simTime, ""), preName);

        // areas.AreaMethod(a -> System.out.println(((Area) a).position.get()));
    }

    static void AddComponent(AreaArray areas) {
        areas.<ValueArea>AreaMethod(v -> v.set((double) 3));

        areas.AddComponent(new DiffusionComponent(0.25f));
        // 拡散させる

        /*
         * areas.AddComponent(new MigrateComponent(vec -> new Vector<Double>((double)
         * 0.25, (double) 0)),
         * new Range3(9, 2, 5, 5),
         * true, true);
         */
        areas.AddComponent(new DecayComponent(1), new Range3(130, 20, 70, 20), true, true);
        areas.AddComponent(new DecayComponent(1), new Range3(280, 170, 70, 20), true, true);

        areas.AddComponent(new ProductionComponent(10), new Range3(130, 20, 280, 230), true, true);
        areas.AddComponent(new ProductionComponent(10), new Range3(280, 170, 280, 230), true, true);

        areas.AddComponent(new MigrateComponent(new Vector((double) 0, -0.25)),
                new Range3(175, 125, 290, 10), true, true);

        areas.AddComponent(new NeumannComponent(), areas.range.Offset(-1, -1, 0), false, true);
        // 端にノイマン境界条件を与える

        // areas.SystemPrintComponent();

        // areas.GetAreasOutRange(areas.range.Offset(-1, -1, 0), true)
        // .AreaMethod(a -> System.out.println(a.position.get()));
    }
}
