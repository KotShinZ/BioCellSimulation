package com.shin.Area;

import java.util.ArrayList;
import java.util.function.Function;

import com.shin.Area.AreaComponents.Base.AreaComponent;
import com.shin.Area.Interface.ITimeUpdatable;
import com.shin.Baselib.DataClass.Range3;
import com.shin.Baselib.DataClass.RemindParam.Reminder;
import com.shin.Baselib.DataClass.vector.Vector3;
import com.shin.Baselib.DataClass.vector.Vector3Int;

public class AreaArrayUpdatable extends AreaArray implements ITimeUpdatable {
    ArrayList<Class<? extends AreaComponent>> comlist = new ArrayList<>();

    public AreaArrayUpdatable(Vector3Int size) {
        super(size);
    }

    public AreaArrayUpdatable(Vector3Int size, Function<Vector3, ? extends Area> ctor) {
        super(size, x -> x, ctor);
    }

    public AreaArrayUpdatable(Vector3Int size, Function<Vector3, Vector3> posFunc,
            Function<Vector3, ? extends Area> ctor) {
        super(size, posFunc, ctor);
    }

    /** 全エリアをUpdate */
    @Override
    public void Update() {
        // System.out.println("____________________________________________________________________");

        // super.SystemPrintAreas();
        comlist.forEach(com -> {
            AreaMethod(a -> a.Update(com));
            Reminder.nowReminder.Remind();
            // System.out.println("_______________");
            // System.out.println(com);
            // super.SystemPrintAreas();

        }); // コンポーネントをひとつづつUpdate
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
        super.AddComponent(com, range, inout, equal);
        comlist.add(com.getClass()); // 加えられた順に記録
    }

}
