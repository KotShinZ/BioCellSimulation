package com.shin.Area;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.shin.Area.AreaComponents.Base.AreaComponent;
import com.shin.Baselib.PassPrinter;
import com.shin.Baselib.DataClass.Dimention;
import com.shin.Baselib.DataClass.Range3;
import com.shin.Baselib.DataClass.vector.Vector;
import com.shin.Baselib.DataClass.vector.Vector3;
import com.shin.Baselib.DataClass.vector.Vector3Int;

/** Areaを配列として並べたもの */
public class AreaArray extends AreaList {
    public Vector3Int size = new Vector3Int(100, 100, 1);
    public Dimention dim;
    public Range3 range;
    public Vector3Int center;

    /** Areaたち */
    public Area[][][] areas;

    /** Areaを初期化する */
    public AreaArray(Vector3Int size) {
        // System.out.println(size);
        this.size = size;
        this.dim = GetDim(size);
        this.range = new Range3(size.x() - 1, size.y() - 1, size.z() - 1);
        this.center = new Vector3Int(size.x() / 2, size.y() / 2, 0);

        areas = new Area[size.x()][size.y()][size.z()];
    }

    /** Areaを初期化する */
    public AreaArray(Vector3Int size, Function<Vector3, Vector3> posFunc, Function<Vector3, ? extends Area> ctor) {
        this(size);

        for (int z = 0; z < size.z(); z++) {
            for (int y = 0; y < size.y(); y++) {
                for (int x = 0; x < size.x(); x++) {
                    var pos = new Vector3Int(x, y, z); // 配列上の位置
                    var ins = ctor.apply(posFunc.apply(pos.ToVector())); // 生成したエリア
                    Add(pos, ins); // エリアを配列上に追加
                }
            }
        }
    }

    public <T extends Area> void AreaArrayMethod(BiConsumer<Vector3Int, T> func) {
        for (int z = 0; z < size.z(); z++) {
            for (int y = 0; y < size.y(); y++) {
                for (int x = 0; x < size.x(); x++) {
                    var pos = new Vector3Int(x, y, z); // 配列上の位置
                    func.accept(pos, this.<T>GetArea(pos));
                }
            }
        }
    }

    public void Add(Vector3Int point, Area a) {
        areas[point.x()][point.y()][point.z()] = a;
        Add(a);
    }

    public void Add(Vector3Int point, Area a, boolean hasHolder) {
        areas[point.x()][point.y()][point.z()] = a;
        Add(a, hasHolder);
    }

    /** 一つのAreaを取得する */
    public <T extends Area> T GetArea(Vector<? extends Number> point) {
        return this.<T>GetArea(point.Get(0).intValue(), point.Get(1).intValue(), point.Get(2).intValue());
    }

    /** 一つのAreaを取得する */
    public <T extends Area> T GetArea(int x, int y, int z) {
        try {
            return (T) areas[x][y][z];
        } catch (Exception e) {
            return null;
        }
    }

    /** 隣のエリアを取得 */
    public Area[] GetNextArea(Area area, int i) {
        var pos = area.position.get();
        var leftpos = pos.Add(i, (double) -1);
        var rightpos = pos.Add(i, (double) 1);

        Area left = GetArea(leftpos);
        Area right = GetArea(rightpos);

        Area[] areas = { left, right };
        return areas;
    }

    /** すべての方向の隣のエリアを取得 */
    public ArrayList<Area> GetAllNextArea(Area area) {

        ArrayList<Area> areas = new ArrayList<Area>();

        for (int i = 0; i < dim.ordinal(); i++) {
            var a = GetNextArea(area, i);
            areas.add(a[0]);
            areas.add(a[1]);
        }

        return areas;
    }

    /** すべての方向の隣のエリアに対して関数を実行 */
    public void FuncAllNextArea(Area area, BiConsumer<Area[], Integer> func) {
        for (int i = 0; i < dim.ordinal(); i++) {
            var lr = GetNextArea(area, i);

            func.accept(lr, i);
        }
    }

    public Area GetCenterArea() {
        return GetArea(center);
    }

    /**
     * Areaの中身をファイルに書き込む
     * 
     * @param time どの時間の値としてファイルに書き込むか
     * @param obj  PassPrinterオブジェクト
     * @param str  書き込む文字
     */
    @Override
    public void AreasPrint2D(int time, PassPrinter obj) {
        AreaArrayMethod((pos, area) -> {
            obj.GetPW(time).print(area);
            if (pos.x() == size.x() - 1) {
                obj.GetPW(time).print("\n");
            } else {
                obj.GetPW(time).print(",");
            }
        });
    }

    /**
     * Areaの中身をプリントする
     * 
     * @param str プリントする文字列
     */
    @Override
    public void SystemPrintAreas() {
        AreaMethod((area) -> {
            System.out.print(area);
            if (area.position.get().y() == size.y() - 1) {
                System.out.print("\n");
            } else {
                System.out.print(",");
            }
        });
    }

    /**
     * Areaの中身をファイルに書き込む
     * 
     * @param time どの時間の値としてファイルに書き込むか
     * @param obj  PassPrinterオブジェクト
     * @param str  書き込む文字
     */
    @Override
    public void AreasPrint2D(int time, PassPrinter obj, Function<Area, String> str) {
        AreaMethod((area) -> {
            obj.GetPW(time).print(str);
            if (area.position.get().y() == size.y() - 1) {
                obj.GetPW(time).print("\n");
            } else {
                obj.GetPW(time).print(",");
            }
        });
    }

    /**
     * Areaの中身をプリントする
     * 
     * @param str プリントする文字列
     */
    @Override
    public void SystemPrintAreas(Function<Area, String> str) {
        AreaMethod((area) -> {
            System.out.print(str.apply(area));
            if (area.position.get().y() == size.y() - 1) {
                System.out.print("\n");
            } else {
                System.out.print(",");
            }
        });
    }

    /**
     * Areaの中身をプリントする
     * 
     * @param str プリントする文字列
     */
    @Override
    public void SystemPrintAreasNum(Function<Area, Double> numFunc) {
        AreaMethod((area) -> {
            System.out.print(String.format("%.1d", numFunc.apply(area)));
            if (area.position.get().y() == size.y() - 1) {
                System.out.print("\n");
            } else {
                System.out.print(",");
            }
        });
    }

    /** サイズから次元を取得する */
    public Dimention GetDim(Vector3Int vector) {
        if (vector.x() == 1 && vector.y() == 1 && vector.z() == 1) {
            return Dimention._0D;
        } else if (vector.y() == 1 && vector.z() == 1) {
            return Dimention._1D;
        } else if (vector.z() == 1) {
            return Dimention._2D;
        } else
            return Dimention._3D;
    }

    /**
     * 指定した範囲のAreaを取得
     * 25~27の場合、
     * equal=true なら25,26,27
     * equal=false なら26
     * が取得される
     */
    public AreaArray GetAreasRange(Range3 range, boolean equal) {
        Vector3Int size;
        if (equal) {
            size = new Vector3Int(
                    (int) range.x.max - (int) range.x.min + 1,
                    (int) range.y.max - (int) range.y.min + 1,
                    (int) range.z.max - (int) range.z.min + 1);
        } else {
            size = new Vector3Int(
                    (int) range.x.max - (int) range.x.min - 1,
                    (int) range.y.max - (int) range.y.min - 1,
                    (int) range.z.max - (int) range.z.min - 1);
        }

        AreaArray gets = new AreaArray(size);

        if (equal) {
            for (int x = 0; x < size.x(); x++) {
                for (int y = 0; y < size.y(); y++) {
                    for (int z = 0; z < size.z(); z++) {
                        var target = areas[x + (int) range.x.min][y + (int) range.y.min][z
                                + (int) range.z.min];
                        gets.Add(new Vector3Int(x, y, z), target, false);
                    }
                }
            }
        } else {
            for (int x = 0; x < size.x(); x++) {
                for (int y = 0; y < size.y(); y++) {
                    for (int z = 0; z < size.z(); z++) {
                        var target = areas[x + (int) range.x.min + 1][y + (int) range.y.min + 1][z
                                + (int) range.z.min + 1];
                        gets.Add(new Vector3Int(x, y, z), target, false);
                    }
                }
            }
        }

        return gets;

    }

    /**
     * 指定した範囲以外のAreaを取得
     * 25~27の場合、
     * equal=trueなら25と27も含む
     * equal=falseなら25~27は含まない
     */
    public AreaList GetAreasOutRange(Range3 range, boolean equal) {
        AreaList list = new AreaList();

        AreaMethod(
                area -> {
                    if (!range.IsInRange(area.position.get(), equal)) {
                        list.Add(area, false);
                    }
                });

        return list;

    }

    public void AddComponent(AreaComponent com) {
        AddComponent(com, range, true, true);
    }

    /**
     * 各エリアにコンポーネントを追加する
     * 
     * @param com   追加するコンポーネント
     * @param range 追加する範囲
     * @param inout 範囲の外か中か (trueで中)
     * @param equal 範囲の端も含めるかどうか
     */
    public void AddComponent(AreaComponent com, Range3 range, boolean inout, boolean equal) {
        AreaArrayMethod((p, a) -> {
            if (range.IsInRange(p, equal)) {
                if (inout) {
                    a.AddComponent(com.clone());
                }
            } else {
                if (!inout)
                    a.AddComponent(com.clone());
            }
        });
    }

    /**
     * public void PrintHeatMap(String outPath, double max, double min) {
     * HeatMap.generateHeatMap(areas, a -> {
     * System.out.println(((ValueArea) a[0]).get());
     * return ((ValueArea) a[0]).get();
     * }, outPath, min, max);
     * }
     * 
     * public void PrintHeatMap(String outPath, double max, double min, Vector3Int
     * normal) {
     * HeatMap.generateHeatMap(areas, a -> ((ValueArea) a[0]).get(), outPath, min,
     * max);
     * }
     */
}
