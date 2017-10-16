package com.yutong.readParse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parse {
    private static String pre = "";
    public static void parse(String line, List<Indivdual> indivduals, List<Family> families, List<String> error) {

        String level = "";
        String tag = "";
        String valid = " ";
        String arguments = "";
        int level_indice = 0;
        int tag_indice = 0;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                level_indice = i;
                break;
            }
        }
        for (int i = level_indice + 1; i < line.length(); i++) {

            if (i == line.length() - 1) {
                tag_indice = line.length() - 1;
                break;
            }

            if (line.charAt(i) == ' ') {
                tag_indice = i;
                break;
            } else {
                tag_indice = line.length() - 1;
            }

        }
        level = line.substring(0, level_indice);

        tag = line.substring(level_indice + 1, tag_indice + 1);

        if (tag.charAt(tag.length() - 1) == ' ') {
            tag = line.substring(level_indice + 1, tag_indice);
        }
        arguments = line.substring(tag_indice + 1, line.length());





        //finish by xudong YU
        if (arguments.contains("INDI") && LegalTags.checkTags(arguments)) {
            Indivdual indivdual = new Indivdual();
            indivdual.ID = tag;
            indivduals.add(indivdual);
        } else if (tag.contains("NAME") && LegalTags.checkTags(tag)) {
            indivduals.get(indivduals.size() - 1).Name = arguments;
        } else if (tag.contains("SEX") && LegalTags.checkTags(tag)) {
            indivduals.get(indivduals.size() - 1).Gender = arguments;
        } else if (tag.contains("DATE") && LegalTags.checkTags(tag)) {
            String[] age = arguments.split(" ");
            int age1 = Integer.parseInt(age[age.length - 1]);
            indivduals.get(indivduals.size() - 1).Age = 2017 - age1;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
            try{
                Date date = simpleDateFormat.parse(arguments);
                /*Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);*/
                if (pre.equals("BIRT")){
                    //Check if it is valid of Birth date
                    boolean res = sprint1_Checkout.dates_Before_Current_Date(date);
                    if(res){
                        indivduals.get(indivduals.size() - 1).Birthday = date;
                    }else {
                        String wrong_infor = "Invalid Date of Birth of "+indivduals.get(indivduals.size()-1).Name;
                        error.add(wrong_infor);
                        //System.out.println("Invalid Date of Birth of "+indivduals.get(indivduals.size()-1).Name);
                    }
                } else if (pre.equals("Death")) {
                    // checkout if it is valid of Death Date;
                    boolean res1 = sprint1_Checkout.dates_Before_Current_Date(date);
                    boolean res = sprint1_Checkout.death_After_Birth(indivduals.get(indivduals.size() - 1).Birthday, date);
                    if (res && res1) {
                        indivduals.get(indivduals.size() - 1).Death = date;
                    } else {
                        //System.out.println("invalid Date of Death of " + indivduals.get(indivduals.size() - 1).Name);
                        error.add(new String ("invalid Date of Death of " + indivduals.get(indivduals.size() - 1).Name));
                    }
                }
            }catch (ParseException ex){
                System.out.println("Exception " + ex);
            }
        } else if (tag.contains("BIRT")&& LegalTags.checkTags(tag)) {
            pre = "BIRT";
        } else if (tag.contains("DEAT")&& LegalTags.checkTags(tag)) {
            pre = "Death";
            //Do to checkout Death after birth;
            indivduals.get(indivduals.size() - 1).Alive = false;
        } else if (tag.contains("FAMC") && LegalTags.checkTags(tag)) {
            indivduals.get(indivduals.size() - 1).Child.add(arguments);
        } else if (tag.contains("FAMS") && LegalTags.checkTags(tag)) {
            indivduals.get(indivduals.size() - 1).Spouse.add(arguments);
        }


        if (arguments.contains("FAM") && LegalTags.checkTags(arguments)) {
            Family family = new Family();
            family.ID = tag;
            families.add(family);
        }
        else if (tag.contains("MARR")&& LegalTags.checkTags(tag)) {
            pre = "MARR";
        }
        //done by DISHA SAREEN
        else if(tag.contains("DATE")&& LegalTags.checkTags(tag)) {

            String phrase = line;
            String delims = "[ ]+";
            String[] tokens = phrase.split(delims);
            for (int i = 0; i < tokens.length; i++) { //System.out.print(tokens[i]);
                if (tokens[i].equals("DATE")) {
                    String[] newArray = Arrays.copyOfRange(tokens, i + 1, tokens.length);
                    //System.out.println(newArray[2]);
                    String dateHere = Arrays.toString(newArray);
                    //System.out.println(dateHere);
                    sprint1_Checkout.validate(dateHere);
                }

            }
        }

        else if (tag.contains("DATE") && LegalTags.checkTags(tag)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
            try{
                Date date = simpleDateFormat.parse(arguments);
                /*Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);*/
                if (pre.equals("MARR")){
                    //get husband date and wife date
                    Date husband_Birthday = null;
                    Date wife_Birthday = null;
                    for(int i = 0; i < indivduals.size(); ++i) {
                        if(indivduals.get(i).ID.equals(families.get(families.size() - 1).HusbandID)) {
                            husband_Birthday = indivduals.get(i).Birthday;
                            continue;
                        }
                        if(indivduals.get(i).ID.equals(families.get(families.size() - 1).WifeID)) {
                            wife_Birthday = indivduals.get(i).Birthday;
                            continue;
                        }
                    }
                    //checkout if it valid of Married
                    boolean res1 = sprint1_Checkout.dates_Before_Current_Date(date);
                    boolean res_husband_birth = sprint1_Checkout.birth_Before_Marriage(husband_Birthday,date);
                    boolean res_wife_birth = sprint1_Checkout.birth_Before_Marriage(wife_Birthday,date);
                    if (res_husband_birth&&res_wife_birth && res1) {
                        families.get(families.size() - 1).Married = date;
                    } else {
                        error.add(new String ("Invalid Marriage Data of " + families.get(families.size() - 1).HusbandName + " and " + families.get(families.size() - 1).WifeName));
                        //System.out.println("Invalid Marriage Data of " + families.get(families.size() - 1).HusbandName + " and " + families.get(families.size() - 1).WifeName);
                    }
                } else if (pre.equals("DIV")) {
                    Date husbandDeath = null;
					Date wifeDeath = null;
					for (int i = 0; i < Indivduals.size(); ++i) {
						if (Indivduals.get(i).ID.equals(families.get(families.size() - 1).HusbandID)) {
							husbandDeath = Indivduals.get(i).Death;
							continue;
						}
						if (Indivduals.get(i).ID.equals(families.get(families.size() - 1).WifeID)) {
							wifeDeath = Indivduals.get(i).Death;
							continue;
						}
					}
                    boolean res = sprint1_Checkout.dates_Before_Current_Date(date);
                    boolean res2 = sprint1_Checkout.marrigeBeforeDivorce(families.get(families.size() - 1).Married, families.get(families.size() - 1).Divorced);
                    if(res){
                    	if (res2) {
				if (husbandDeath != null) {
					if (sprint1_Checkout.divorceBeforeDeath(date, husbandDeath)) {
						divIsValid = true;
					} else {
						error.add(new String ("Divorce Date of " + families.get(families.size()-1).HusbandName + " and " + families.get(families.size()-1).WifeName) + "should be before the date of death of " + families.get(families.size()-1).HusbandName + ".");
						divIsValid = false;
					}
				}
				if (wifeDeath != null) {
					if (sprint1_Checkout.divorceBeforeDeath(date, wifeDeath) && divIsValid) {
						families.get(families.size() - 1).Divorced = date;
					} else error.add(new String ("Divorce Date of " + families.get(families.size()-1).HusbandName + " and " + families.get(families.size()-1).WifeName) + "should be before the date of death of " + families.get(families.size()-1).WifeName + ".");
				} else families.get(families.size() - 1).Divorced = date;
			} else error.add(new String ("Divorce Date of " + families.get(families.size()-1).HusbandName + " and " + families.get(families.size()-1).WifeName) + "should be after the date of their marraige date.");
                    } else error.add(new String ("Invalid Date of Divorce of "+ families.get(families.size()-1).HusbandName + " and "+families.get(families.size()-1).WifeName));
                }
            }catch (ParseException ex){
                System.out.println("Exception " + ex);
            }
        }
        else if (tag.contains("HUSB") && LegalTags.checkTags(tag)) {
            families.get(families.size() - 1).HusbandID = arguments;
            for (Indivdual p : indivduals) {
                if (p.getID().equals(arguments)) {
                    families.get(families.size() - 1).HusbandName = p.Name;
                }
            }
        }
        else if (tag.contains("WIFE") && LegalTags.checkTags(tag)) {
            families.get(families.size() - 1).WifeID = arguments;
            for (Indivdual p : indivduals) {
                if (p.getID().equals(arguments)) {
                    families.get(families.size() - 1).WifeName = p.Name;
                }
            }
        }

        else if (tag.contains("DIV")&& LegalTags.checkTags(tag)) {
            pre = "DIV";
        }
        else if (tag.contains("CHIL") && LegalTags.checkTags(tag)) {

            families.get(families.size() - 1).Children.add(arguments);

        }
    }

    /*public static void parseFamilies(String line, List<Family> families, List<Indivdual> Indivduals) {

        String level;
        String tag = "";
        String valid = " ";
        String arguments = "";
        int level_indice = 0;
        int tag_indice = 0;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                level_indice = i;
                break;
            }
        }

        for (int i = level_indice + 1; i < line.length(); i++) {

            if (i == line.length() - 1) {
                tag_indice = line.length() - 1;
                break;
            }

            if (line.charAt(i) == ' ') {
                tag_indice = i;
                break;
            } else {
                tag_indice = line.length() - 1;
            }

        }
        level = line.substring(0, level_indice);

        tag = line.substring(level_indice + 1, tag_indice + 1);

        if (tag.charAt(tag.length() - 1) == ' ') {
            tag = line.substring(level_indice + 1, tag_indice);
        }
        arguments = line.substring(tag_indice + 1, line.length());




        //Chenglin Wu
        if (arguments.contains("FAM") && LegalTags.checkTags(arguments)) {
            Family family = new Family();
            family.ID = tag;
            families.add(family);
        }
        else if (tag.contains("MARR")&& LegalTags.checkTags(tag)) {
            pre = "MARR";
        }
        //done by DISHA SAREEN
        else if(tag.contains("DATE")&& LegalTags.checkTags(tag)) {

            String phrase = line;
            String delims = "[ ]+";
            String[] tokens = phrase.split(delims);
            for (int i = 0; i < tokens.length; i++) { //System.out.print(tokens[i]);
                if (tokens[i].equals("DATE")) {
                    String[] newArray = Arrays.copyOfRange(tokens, i + 1, tokens.length);
                    //System.out.println(newArray[2]);
                    String dateHere = Arrays.toString(newArray);
                    //System.out.println(dateHere);
                    sprint1_Checkout.validate(dateHere);
                }

            }
        }

        else if (tag.contains("DATE") && LegalTags.checkTags(tag)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
            try{
                Date date = simpleDateFormat.parse(arguments);
                *//*Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);*//*
                if (pre.equals("MARR")){
                    //get husband date and wife date
                    Date husband_Birthday = null;
                    Date wife_Birthday = null;
                    for(int i = 0; i < Indivduals.size(); ++i) {
                        if(Indivduals.get(i).ID.equals(families.get(families.size() - 1).HusbandID)) {
                            husband_Birthday = Indivduals.get(i).Birthday;
                            continue;
                        }
                        if(Indivduals.get(i).ID.equals(families.get(families.size() - 1).WifeID)) {
                            wife_Birthday = Indivduals.get(i).Birthday;
                            continue;
                        }
                    }
                    //checkout if it valid of Married
                    boolean res1 = sprint1_Checkout.dates_Before_Current_Date(date);
                    boolean res_husband_birth = sprint1_Checkout.birth_Before_Marriage(husband_Birthday,date);
                    boolean res_wife_birth = sprint1_Checkout.birth_Before_Marriage(wife_Birthday,date);
                    if (res_husband_birth&&res_wife_birth && res1) {
                        families.get(families.size() - 1).Married = date;
                    } else {
                        System.out.println("Invalid Marriage Data of " + families.get(families.size() - 1).HusbandName + " and " + families.get(families.size() - 1).WifeName);
                    }
                } else if (pre.equals("DIV")) {
                    boolean res = sprint1_Checkout.dates_Before_Current_Date(date);
                    if(res){
                        families.get(families.size() - 1).Divorced = date;
                    }else {
                        System.out.println("Invalid Date of Divorce of "+ families.get(families.size()-1).HusbandName + " and "+families.get(families.size()-1).WifeName);
                    }
                }
            }catch (ParseException ex){
                System.out.println("Exception " + ex);
            }
        }
        else if (tag.contains("HUSB") && LegalTags.checkTags(tag)) {
            families.get(families.size() - 1).HusbandID = arguments;
            for (Indivdual p : Indivduals) {
                if (p.getID().equals(arguments)) {
                    families.get(families.size() - 1).HusbandName = p.Name;
                }
            }
        }
        else if (tag.contains("WIFE") && LegalTags.checkTags(tag)) {
            families.get(families.size() - 1).WifeID = arguments;
            for (Indivdual p : Indivduals) {
                if (p.getID().equals(arguments)) {
                    families.get(families.size() - 1).WifeName = p.Name;
                }
            }
        }

        else if (tag.contains("DIV")&& LegalTags.checkTags(tag)) {
            pre = "DIV";
        }
        else if (tag.contains("CHIL") && LegalTags.checkTags(tag)) {

            families.get(families.size() - 1).Children.add(arguments);

        }
    }*/
}
