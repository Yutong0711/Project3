package com.yutong.readParse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class sprint1_Checkout {
    public static List<String> error = new ArrayList<>();
    public static List<String> check_List(List<Indivdual> indivdualList, List<Family> familyList) {
        for (Indivdual in : indivdualList) {
            death_After_Birth(in.Birthday, in.Death, in.Name);
        }
        for (Family fam : familyList) {
            Date husband_Date= null;
            Date wife_Date = null;
            for (int i = 0; i < indivdualList.size(); ++i) {
                if (fam.HusbandID.equals(indivdualList.get(i).ID)) {
                     husband_Date = indivdualList.get(i).Birthday;
                }
                if (fam.WifeID.equals(indivdualList.get(i).ID)) {
                    wife_Date = indivdualList.get(i).Birthday;
                }
            }
            marriage_After_Birth(husband_Date, wife_Date,fam.Married,fam.HusbandName,fam.WifeName);
            divorceBeforeDeath(fam.Married,fam.Divorced);
        }
        return error;
    }

    //do by xudong
    public static boolean death_After_Birth(Date birth, Date death, String name) {
        if (birth != null && death != null){
            if (birth.after(death)) {
                error.add(new String("wrong infor " + name ));
                return false;
            } return true;
        }
        return false;
    }
    public static String  marriage_After_Birth(Date husband_Date, Date wife_Date, Date marriage, String husband_Name, String wife_Name) {
        String res  = "11";
        if (husband_Date.before(marriage) && wife_Date.before(marriage)) {
            res = "00";
        }

        if (husband_Date.after(marriage)){
            error.add(new String ("wrong infor of husband_Date" + husband_Name));
            res = "10";
        }
        if (wife_Date.after(marriage)) {
            error.add(new String("wrong infor of wife_data") + wife_Name);
            res = "01";
        }
        return res;
    }


    // do by chenglin
    public static boolean marrigeBeforeDivorce (Date marriage, Date divorce) {
        if (marriage != null && divorce != null){
            if (marriage.after(divorce)) {
                error.add(new String("wrong infor "  ));
                return false;
            } return true;
        }
        return false;
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
