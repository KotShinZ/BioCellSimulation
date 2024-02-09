package com.shin.Area;

import com.shin.Area.AreaComponents.Base.AreaComponent;
import com.shin.Baselib.Component.ComponentParent;
import com.shin.Baselib.DataClass.RemindParam.Remindable;
import com.shin.Baselib.DataClass.RemindParam.Reminder;
import com.shin.Baselib.DataClass.vector.Vector3;
import com.shin.Baselib.DataClass.vector.Vector3Int;

/**
 * エリアの基本クラス
 * /workspaces/BioCellSimulation/biocellsim/src/main/java/com/shin/bases/AreaBase/AreaComponents
 * * 位置を持つ
 * コンポーネントをもつ
 * 他のエリアと干渉を行いあう
 * 微分可能なパラメータをもつためにReminderを保持する
 */
public class Area extends ComponentParent {

    public Remindable<Vector3> position = new Remindable<Vector3>(new Vector3(), 1);
    // 現在のエリアの位置。配列上の位置ではない

    protected Reminder reminder;
    public AreaList holder;

    public Area(Vector3 position) {
        this.position.set(position);
    }

    public Area(Vector3 position, Reminder reminder) {
        this(position);
        this.reminder = reminder;
    }

    public Area(Vector3Int position) {
        this.position.set(position.ToVector());
    }

    public void AllUpdate() {
        super.ComponentUpdate(AreaComponent.class, com -> com.Update()); // すべてのコンポーネントを実行
    }

    public <T extends AreaComponent> void Update(Class<T> clazz) {
        super.ComponentUpdate(clazz, com -> com.Update());
    }

    /** エリア同士の距離を求める */
    public double Distance(Area other) {
        return position.get().Sub(other.position.get()).Magnitude();
    }
}
