package com.shin.Baselib.DataClass.RemindParam;

import java.util.ArrayList;

/** 過去の更新を適用するオブジェクト */
public class Reminder {
    public static Reminder nowReminder = new Reminder();

    ArrayList<Remindable> remindables = new ArrayList<>();

    /** 更新を記録するように登録する */
    public void Add(Remindable remindable) {
        remindables.add(remindable);
    }

    /** 更新を記録する */
    public void Remind() {
        remindables.forEach(re -> re.Remind());
    }
}
