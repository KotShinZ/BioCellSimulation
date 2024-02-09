package com.shin.Baselib.DataClass.RemindParam;

public class Double_Re extends Remindable<Double> {

    public Double_Re(Double t, int remindsNum) {
        super(t, remindsNum, Reminder.nowReminder);
        // TODO Auto-generated constructor stub
    }

    public Double_Re(Double t, int remindsNum, Reminder reminder) {
        super(t, remindsNum, reminder);
        // TODO Auto-generated constructor stub
    }

}
