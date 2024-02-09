package com.shin.Area;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

import com.shin.Baselib.PassPrinter;

public class AreaList {
    private ArrayList<Area> areaList = new ArrayList<>();

    public AreaList() {
    }

    public int Size() {
        return areaList.size();
    }

    public void Add(Area area) {
        area.holder = this;
        areaList.add(area);
    }

    public void Add(Area area, boolean hasHolder) {
        if (hasHolder)
            area.holder = this;
        areaList.add(area);
    }

    public <T extends Area> void AreaMethod(Consumer<T> func) {
        areaList.forEach(a -> func.accept((T) a));
    }

    /**
     * Areaの中身をファイルに書き込む
     * 
     * @param time どの時間の値としてファイルに書き込むか
     * @param obj  PassPrinterオブジェクト
     * @param str  書き込む文字
     */
    public void AreasPrint2D(int time, PassPrinter obj) {
        AreaMethod((area) -> {
            obj.GetPW(time).print(area);
            obj.GetPW(time).print(",");
        });
    }

    /**
     * Areaの中身をプリントする
     * 
     * @param str プリントする文字列
     */
    public void SystemPrintAreas() {
        AreaMethod((area) -> {
            System.out.print(area);
            System.out.print(",");
        });
    }

    /**
     * Areaの中身をファイルに書き込む
     * 
     * @param time どの時間の値としてファイルに書き込むか
     * @param obj  PassPrinterオブジェクト
     * @param str  書き込む文字
     */
    public void AreasPrint2D(int time, PassPrinter obj, Function<Area, String> str) {
        AreaMethod((area) -> {
            obj.GetPW(time).print(str);
            obj.GetPW(time).print(",");
        });
    }

    /**
     * Areaの中身をプリントする
     * 
     * @param str プリントする文字列
     */
    public void SystemPrintAreas(Function<Area, String> str) {
        AreaMethod((area) -> {
            System.out.print(str);
            System.out.print(",");
        });
    }

    /**
     * Areaの中身をプリントする
     * 
     * @param str プリントする文字列
     */
    public void SystemPrintAreasNum(Function<Area, Double> numFunc) {
        AreaMethod((area) -> {
            System.out.print(String.format("%.1d", numFunc.apply(area)));
            System.out.print(",");
        });
    }

    public void SystemPrintComponent() {
        AreaMethod(a -> {
            System.out.println(a.position.get());
            a.components.forEach(c -> System.out.print(c));
            System.out.println();
        });
    }

    public void SystemPrintPositions() {
        AreaMethod(a -> {
            System.out.println(a.position.get());
        });
    }
}
