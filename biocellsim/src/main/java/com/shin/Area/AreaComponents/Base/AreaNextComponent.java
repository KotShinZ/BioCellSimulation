package com.shin.Area.AreaComponents.Base;

import com.shin.Area.Area;
import com.shin.Area.AreaArray;

/** 他のエリアと共にの更新を行う */
public abstract class AreaNextComponent extends AreaComponent {
    /**
     * エリアの更新を行う
     * 
     * @param 他のエリア
     */
    public void Update() {

        var array = (AreaArray) (area().holder);

        for (int i = 0; i < array.dim.ordinal(); i++) {
            var pos = area().position.get();
            var leftpos = pos.Add(i, (double) -1);
            var rightpos = pos.Add(i, (double) 1);

            Area left = array.GetArea(leftpos);
            Area right = array.GetArea(rightpos);

            NextUpdate(left, right, i);
        }
    }

    public abstract void NextUpdate(Area left, Area right, int dim);
}
