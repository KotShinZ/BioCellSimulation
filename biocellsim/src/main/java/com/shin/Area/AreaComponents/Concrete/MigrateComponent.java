package com.shin.Area.AreaComponents.Concrete;

import java.util.function.Function;

import com.shin.Area.Area;
import com.shin.Area.StepUpdater;
import com.shin.Area.ValueArea;
import com.shin.Area.AreaComponents.Base.AreaNextComponent;
import com.shin.Baselib.DataClass.vector.Vector;

public class MigrateComponent extends AreaNextComponent {
    Function<Vector<Double>, Vector<Double>> vec;
    public Vector<Double> vecLimit = new Vector<Double>(0, 0, 0);

    /**
     * 分子を移動させるコンポーネント
     * 
     * @param vecFunc 位置に対する速度を求める関数
     */
    public MigrateComponent(Function<Vector<Double>, Vector<Double>> vecFunc) {
        this.vec = vecFunc;
    }

    /**
     * 分子を移動させるコンポーネント
     * 
     * @param vecFunc 位置に対する速度を求める関数
     */
    public MigrateComponent(Vector<Double> vec) {
        this.vec = v -> vec;
    }

    @Override
    public void NextUpdate(Area left, Area right, int dim) {
        if (left == null || right == null)
            return;
        ValueArea vArea = (ValueArea) area(); // 自分のエリア
        ValueArea vleft = (ValueArea) left; // 他のエリア
        ValueArea vright = (ValueArea) right; // 他のエリア

        var dir = new Vector<>(3, dim, (double) 1);

        Double dot = vec.apply(area().position.get()).Dot(dir);// 内積
        // dot = dot >= 0 ? dot : 0; // 進む方向のみ値がある
        // Double dotLimit = dir.Dot(vecLimit); // 進行方向と同じなら1,逆なら-1,関係なしで0になる

        Double dt = StepUpdater.now.dt;
        Double dx = vArea.Distance(vleft); // 左側と値を交換するとする

        /*
         * if (dot != 0 && area().position.get().y() == 10 && false) {
         * System.out.println(area().position.get());
         * System.out.println(other.position.get());
         * System.out.println(vArea.get());
         * System.out.println(vOther.get());
         * System.out.println("__________________  ");
         * }
         */

        // System.out.println(vArea.position.get());
        // System.out.println(vOther.GetComponent(MigrateComponent.class));
        double diff = 0;
        if (vleft.GetComponent(MigrateComponent.class) != null) {
            diff += dot > 0 ? vleft.get() : -vArea.get();
        }
        if (vright.GetComponent(MigrateComponent.class) != null) {
            diff += dot > 0 ? -vArea.get() : vright.get();
        }
        /*
         * System.out.println(diff);
         * System.out.println(vArea.get());
         * System.out.print(vArea.position.get());
         * System.out.print("      ");
         * System.out.println(vleft.position.get());
         * 
         * System.out.println("\n");
         */

        dot = dot >= 0 ? dot : -dot; // 進む方向のみ値がある
        vArea.addNext(dot * diff * (dt / dx));

        var v = dot * diff * (dt / dx);
        /*
         * if (dot != 0) {
         * System.out.println(StepUpdater.now.nowTime);
         * System.out.println(v);
         * System.out.println(dot);
         * System.out.print(vArea.position.get());
         * System.out.print("      ");
         * System.out.println(vleft.position.get());
         * System.out.println(vleft.GetComponent(MigrateComponent.class));
         * System.out.println("\n");
         * }
         */

    }

}
