package com.shin.SuportSim;

import java.util.function.Function;

import com.shin.Area.ValueArea;
import com.shin.Baselib.PassPrinter;
import com.shin.Area.StepUpdater;
import com.shin.Area.AreaArray;
import com.shin.Area.AreaArrayUpdatable;
import com.shin.Baselib.DataClass.vector.Vector3;
import com.shin.Baselib.DataClass.vector.Vector3Int;

public class CreateAreas {

    static PassPrinter passPrinter;
    static double sum = 0;

    /**
     * エリアを構築する関数
     * 
     * @param size    エリアの大きさ
     * @param outPath 出力データのpath (例 : ./Out/3/area_100_150t_production/areas_)
     * @return 構築したエリア
     */
    public static AreaArrayUpdatable CreateAreaArray(Vector3Int size, String outPath) {
        return CreateAreaArray(size, 1, outPath);
    }

    public static AreaArrayUpdatable CreateAreaArray(Vector3Int size, double dx, String outPath) {
        return CreateAreaArray(size, outPath, x -> x.Mul(dx).Cast(Vector3::new));
    }

    public static AreaArrayUpdatable CreateAreaArray(Vector3Int size, String outPath,
            Function<Vector3, Vector3> posFunc) {
        AreaArrayUpdatable areas = new AreaArrayUpdatable(size, posFunc, ValueArea::new); // Areaを構築
        passPrinter = new PassPrinter(outPath);

        StepUpdater.now.onEndStep = (t, obj) -> {
            if (obj instanceof AreaArray) {
                AreaArray a = (AreaArray) obj;
                a.AreasPrint2D((int) t.nowTime, passPrinter);

                // a.GetAreasRange(areas.range.Offset(-1, -1, 0),
                /// true).AreaMethod(ar -> sum += ((ValueArea) ar).num.get());
                // System.out.println(sum);
                sum = 0;

                // System.out.println("______________________________________________");
                // a.SystemPrintAreas(aw -> String.format("%.1f", ((ValueArea) aw).num.get()));
                // System.out.println("_______________");
                // a.SystemPrintAreas(aw -> String.format("%.1f", ((ValueArea)
                // aw).num.getNext()));
                // System.out.println("______________________________________________");
                // System.out.println();
            }
        }; // エリアをプリントする

        return areas;
    }
}
