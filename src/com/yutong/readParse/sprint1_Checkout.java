package com.yutong.readParse;

import java.util.Date;
import java.util.List;

public class sprint1_Checkout {
    //do by xudong
    public static boolean death_After_Birth(Date birth, Date death) {
        return birth.before(death);
    }
    public static boolean marriage_After_Birth(Date husband_Date, Date wife_Date, Date marriage) {
        return husband_Date != null && wife_Date != null && husband_Date.before(marriage) && wife_Date.before(marriage);
    }


    // do by chenglin
    public static boolean marrigeBeforeDivorce (Date marriage, Date Divorce) {
        if (Divorce != null) {
            return marriage.before(Divorce);
        } else return true;
    }
    public static boolean divorceBeforeDeath (Date divorce, Date death) {
        if (death != null)
            return divorce.before(death);
        else return true;
    }

    //do by yutong
    public boolean dates_Before_Current_Date () {
        return true;
    }
    public boolean birth_Before_Marriage () {
        return true;
    }

    //do by Disha
    // you should choose your own story and implement your function
    // 1.public () {}
    // 2.public () {}
}
