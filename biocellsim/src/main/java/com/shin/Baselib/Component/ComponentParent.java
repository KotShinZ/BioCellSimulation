package com.shin.Baselib.Component;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ComponentParent implements Cloneable {
    public ArrayList<Component> components = new ArrayList<>();

    /**
     * コンポーネント全体に関数を適用
     * 
     * @param clazz これを継承する物のみに関数を適用
     * @param func  適用する関数
     */
    protected <T extends Component> void ComponentUpdate(Class<T> clazz, Consumer<T> func) {

        components.forEach(com -> {
            if (com != null && clazz.isInstance(com)) {
                func.accept((T) com);
            }
        });
    }

    /** コンポーネントを追加する */
    public void AddComponent(Component com) {
        components.add(com);
        com.SetParent(this);
    }

    /**
     * コンポーネントを一つ見つける
     * 無いならnullを返す
     */
    public <T extends Component> T GetComponent(Class<T> clazz) {
        for (Component com : components) {
            if (clazz.isInstance(com)) {
                return (T) com;
            }
        }

        return null;
    }

    /** コンポーネントを複数見つける */
    public <T extends Component> ArrayList<T> GetComponents(Class<T> clazz) {
        ArrayList<T> list = new ArrayList<>();
        for (Component com : components) {
            if (clazz.isInstance(com)) {
                list.add((T) com);
            }
        }
        return list;
    }

    public ComponentParent clone() {
        try {
            ComponentParent c = (ComponentParent) super.clone();
            for (Component com : components) {
                Component newcom = com.clone();
                c.AddComponent(newcom);
            }
            return c;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
}
