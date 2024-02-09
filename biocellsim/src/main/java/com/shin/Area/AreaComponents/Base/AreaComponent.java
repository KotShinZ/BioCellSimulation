package com.shin.Area.AreaComponents.Base;

import com.shin.Area.Area;
import com.shin.Baselib.Component.Component;

/** Areaに関するコンポーネント */
public abstract class AreaComponent extends Component {
    public Area area() {
        return (Area) parent;
    }

    /**
     * エリアの更新を行う
     * 
     * @param 他のエリア
     */
    public abstract void Update();

    @Override
    public AreaComponent clone() {
        try {
            return ((AreaComponent) super.clone());
        } catch (Exception e) {
            return null;
        }
    }
}
