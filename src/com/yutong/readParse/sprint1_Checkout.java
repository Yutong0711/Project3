package com.yutong.readParse;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class sprint1_Checkout {
    public static List<String> error = new ArrayList<>();
    //public static List<Family> familyList1;
    public static List<String> check_List(List<Indivdual> indivdualList, List<Family> familyList) {
        //familyList1 = familyList;
        for (Indivdual in : indivdualList) {
            death_After_Birth(in.Birthday, in.Death, in.Name);
            dates_Before_Current_Date(in.Birthday, in.ID);
            dates_Before_Current_Date(in.Death, in.ID);
            No_bigamy(in.ID, familyList);

            isValidDate(in.Birthday.toString().substring(4, 10)+ " " + in.Birthday.toString().substring(24,28));

        }
        for (Family fam : familyList) {
            Date husband_Date= null;
            Date wife_Date = null;
            Date husband_birth = null;
            Date wife_birth = null;
            for (int i = 0; i < indivdualList.size(); ++i) {
                if (fam.HusbandID.equals(indivdualList.get(i).ID)) {
                    husband_Date = indivdualList.get(i).Death;
                    husband_birth = indivdualList.get(i).Birthday;
                }
                if (fam.WifeID.equals(indivdualList.get(i).ID)) {
                    wife_Date = indivdualList.get(i).Death;
                    wife_birth = indivdualList.get(i).Birthday;
                }
            }
            marriage_Before_Death(husband_Date,fam.Married,fam.HusbandName);
            marriage_Before_Death(wife_Date,fam.Married,fam.WifeName);
            divorceBeforeDeath(fam.Married,fam.Divorced,fam.ID);
            dates_Before_Current_Date(fam.Married,fam.ID);
            dates_Before_Current_Date(fam.Divorced,fam.ID);
            birth_Before_Marriage(husband_birth, fam.Married,fam.ID);
            birth_Before_Marriage(wife_birth, fam.Married, fam.ID);
        }
        return error;
    }

    //do by xudong
    public static boolean death_After_Birth(Date birth, Date death, String name) {
        if (birth != null && death != null){
            if (birth.after(death)) {
                error.add(new String("death_before_Birth " + name ));
                return false;
            } return true;
        }
        return false;
    }

    public static boolean  marriage_Before_Death(Date date , Date marriage, String name) {
        if (date != null && date.before(marriage)) {
            error.add(new String("marriage_After_Death " + name ));
            return false;
        }
        return true;
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



    public static boolean divorceBeforeDeath (Date divorce, Date death, String fam_ID) {
        if(death!=null && divorce!=null && death.after(divorce)){
            error.add("ERROR: FAMILY: US06: "+ fam_ID +": divorce_Date "+ divorce +" before death" + death);
            return false;
        }
        return true;
    }




    //do by yutong
    // US01 Dates before current date for individuals
    public static boolean dates_Before_Current_Date (Date date, String id) {
        String res = "";
        Date current = new Date();
        if(date!= null && date.after(current)){
            error.add("ERROR: INDIVIDUAL: US01: "+ id+": Birthday "+ date +" occurs in the future.");
            res = "Wrong birthday";
            return false;
        }
        return true;
    }
    // US01 Dates before current date for families




    // US02 Birthday before marriage
    public static boolean birth_Before_Marriage (Date birth, Date marriage, String fam_ID) {
        if(birth!=null && marriage!=null && birth.after(marriage)){
            error.add("ERROR: FAMILY: US02: "+ fam_ID +": marriage_Date "+ marriage +" before birthday." + birth);
            return false;
        }
        return true;
    }
    public static boolean No_bigamy (String ID, List<Family> familyList1) {
        int count = 0;
        Date marrige1 = null;
        Date marrige2 = null;
        Date divorce1 = null;
        Date divorce2 = null;

        for (int i = 0; i < familyList1.size(); ++i) {
            if (ID.equals(familyList1.get(i).WifeID) || ID.equals(familyList1.get(i).HusbandID))  {
                marrige1 = familyList1.get(i).Married;
                divorce1 = familyList1.get(i).Divorced;
                count++;
            }
            if (ID.equals(familyList1.get(i).WifeID) || ID.equals(familyList1.get(i).HusbandID))  {
                if (count == 1 ) {
                    marrige2 = familyList1.get(i).Married;
                    divorce2 = familyList1.get(i).Divorced;
                    break;
                }
            }
        }
        if (count == 2) {
            error.add("ERROR: INDIVIDUAL: US11: " + ID + " is bigamy");
            return false;
        }
        return true;
    }



    public static boolean isValidDate(String date) {
        if (date == null)
            return false;
        //set the format to use as a constructor argument
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY");

        if (date.trim().length() != dateFormat.toPattern().length())
            return false;
        dateFormat.setLenient(false);
        try {
            //parse the date parameter
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            return false;
        }
        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        hashmap.put("JAN", 31);
        hashmap.put("FEB", 28);
        hashmap.put("MAR", 31);
        hashmap.put("APR", 30);
        hashmap.put("MAY", 31);
        hashmap.put("JUN", 30);
        hashmap.put("JULY", 31);
        hashmap.put("AUG", 31);
        hashmap.put("SEPT", 30);
        hashmap.put("OCT", 31);
        hashmap.put("NOV", 30);
        hashmap.put("DEC", 31);

        int given_year = Integer.parseInt(date.substring(7, 11));
        //System.out.println(given_year);
        String given_month = date.substring(3, 6);
        int given_date = Integer.parseInt(date.substring(0, 2));
        int max_date = hashmap.get(given_month);
        if(!given_month.equals("FEB"))
        {
            if (given_date <= max_date && given_date > 0) {
                return true;
            } else {
                error.add(new String("wrong data"));
                return false;

            }
        }
        else {
            if (given_year % 4 == 0) {
                if (given_date <= 29 && given_date > 0) {
                    return true;
                }
                else {
                    error.add(new String("wrong data"));
                    return false;
                }
            }
            else if (given_date <= max_date && given_date > 0) {
                return true;
            } else{
                error.add(new String("wrong data"));
                return false;
            }
        }
    }
    // you should choose your own story and implement your function
    // 1.public () {}
    // 2.public () {}
}
