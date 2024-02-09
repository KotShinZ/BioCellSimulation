package com.shin.Area.AreaComponents.Base;

import com.shin.Area.Area;
import com.shin.Area.AreaArray;
import com.shin.Baselib.DataClass.vector.Vector;

public abstract class AreaNextVecComponent extends AreaComponent {
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
            Area left = array.GetArea(leftpos);
            NextUpdate(left, new Vector<Double>(array.dim.ordinal(), array.dim.ordinal() - 1 - i, (double) 1));

            var rightpos = pos.Add(i, (double) 1);
            Area right = array.GetArea(rightpos);
            NextUpdate(right, new Vector<Double>(array.dim.ordinal(), array.dim.ordinal() - 1 - i, (double) -1));
        }
    }

    public abstract void NextUpdate(Area other, Vector<Double> vec);
}
