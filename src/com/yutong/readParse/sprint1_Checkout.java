package com.yutong.readParse;

import java.util.Date;
import java.util.List;

public class sprint1_Checkout {
    //do by xudong
    public static boolean death_After_Birth(Date birth, Date death) {
        if (birth.before(death)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean marriage_After_Birth(Date husband_Date, Date wife_Date, Date marriage) {
        if (husband_Date != null && wife_Date != null && husband_Date.before(marriage) && wife_Date.before(marriage)) {
            return true;
        } else {
            return false;
        }
    }


    // do by chenglin
    public boolean marrige_Before_Divorce() {
        return true;
    }
    public boolean divorce_Before_Death() {
        return true;
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
