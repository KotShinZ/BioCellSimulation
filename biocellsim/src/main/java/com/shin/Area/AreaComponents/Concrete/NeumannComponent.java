package com.shin.Area.AreaComponents.Concrete;

import com.shin.Area.Area;
import com.shin.Area.ValueArea;
import com.shin.Area.AreaComponents.Base.AreaNextComponent;

/**
 * ノイマン条件を適用する
 * 
 * 濃度勾配を0にする
 * 値が拡散されなくなる
 */
public class NeumannComponent extends AreaNextComponent {

    @Override
    public void NextUpdate(Area left, Area right, int dim) {
        ValueArea vArea = (ValueArea) area(); // 自分のエリア
        ValueArea vleft = (ValueArea) left; // 他のエリア
        ValueArea vright = (ValueArea) right; // 他のエリア

        if (left != null && right != null)
            return;

        if (left != null) {
            Neumann(vArea, vleft);
        } else if (right != null) {
            Neumann(vArea, vright);
        }
    }

    void Neumann(ValueArea area, ValueArea target) {
        area.setNext(target.getNext());
        area.set(target.get());
    }

}
