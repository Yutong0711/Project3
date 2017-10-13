package com.yutong.readParse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public static boolean dates_Before_Current_Date (Date date) {
        Date current = new Date();
        if(date!= null && date.after(current)){
            return false;
        }
        return true;
    }
    public static boolean birth_Before_Marriage (Date birth, Date marriage) {
        if(birth!=null && marriage!=null && birth.after(marriage)){
            return false;
        }
        return true;
    }

    //do by Disha
    public static boolean validate1(String dateString) {
        SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy");
        try {
            df.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidDate(String dateString) {
        if (dateString == null || dateString.length() != "yyyyMMdd".length()) {
            return false;
        }

        int date;
        try {
            date = Integer.parseInt(dateString);
        } catch (NumberFormatException e) {
            return false;
        }

        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;

        // leap years calculation not valid before 1581
        boolean yearOk = (year >= 1581) && (year <= 2500);
        boolean monthOk = (month >= 1) && (month <= 12);
        //boolean dayOk = (day >= 1) && (day <= daysInMonth(year, month));

        return (true);
    }
    public static boolean validate(String dateToValidate) {

        String dateFormat = "dd mm yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            //if not valid, it will throw parseException
            Date date = sdf.parse(dateToValidate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }
    // you should choose your own story and implement your function
    // 1.public () {}
    // 2.public () {}
}
