package com.yutong.readParse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parse {
	private static String pre = "";
	public static void parse(String line, List<Indivdual> indivduals) {

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
						System.out.println("Invalid Date of Birth of "+indivduals.get(indivduals.size()-1).Name);
					}
				} else if (pre.equals("Death")) {
					// checkout if it is valid of Death Date;
					boolean res1 = sprint1_Checkout.dates_Before_Current_Date(date);
					boolean res = sprint1_Checkout.death_After_Birth(indivduals.get(indivduals.size() - 1).Birthday, date);
					if (res && res1) {
						indivduals.get(indivduals.size() - 1).Death = date;
					} else {
						System.out.println("invalid Date of Death of " + indivduals.get(indivduals.size() - 1).Name);
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
		// Handle the tags exceptions for INDI and FAM
		/*f (arguments.contains("INDI") || arguments.contains("FAM")) {
			if (LegalTags.checkTags(arguments)) {
				valid = "Y";
				Indivdual indivdual = new Indivdual();


			} else {
				valid = "N";
			}
			System.out.println("<-- " + " " + level + " " + "|" + arguments + " " + "|" + valid + "|" + " " + tag);
		} else {
			if (LegalTags.checkTags(tag)) {
				valid = "Y";
			} else {
				valid = "N";
			}
			System.out.println("<-- " + " " + level + " " + "|" + tag + " " + "|" + valid + "|" + " " + arguments);
		}*/
	}

	public static void parseFamilies(String line, List<Family> families, List<Indivdual> Indivduals) {

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
			Family family = new Family(tag);
			//family.ID = tag;
			families.add(family);
		}
		else if (tag.contains("MARR")&& LegalTags.checkTags(tag)) {
			pre = "MARR";
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
				} 
        			else if (pre.equals("DIV")) {
					Date husbanDeath = null;
					Date wifeDeath = null;
					for (int i = 0; i < Indivduals.size(); ++i) {
						if (Indivduals.get(i).ID.equals(families.get(families.size() - 1).HusbandID)) {
							husbanDeath = Indivduals.get(i).Death;
							continue;
						}
						if (Indivduals.get(i).ID.equals(families.get(families.size() - 1).WifeID)) {
							wifeDeath = Indivduals.get(i).Death;
							continue;
						}
					}
          				boolean res = sprint1_Checkout.dates_Before_Current_Date(date);
					boolean res2 = sprint1_Checkout.marrigeBeforeDivorce(families.get(families.size() - 1).Married, families.get(families.size() - 1).Divorced);
					if (res) {
            					if (res2) {
            						if (husbanDeath != null) {
            							if (sprint1_Checkout.divorceBeforeDeath(date, husbanDeath)) {
								  families.get(families.size() - 1).Divorced = date;
            							} else System.out.println("Error! Divorce Date should be before the date of death of husband.");
            						} else families.get(families.size() - 1).Divorced = date;
							if (wifeDeath != null) {
								if (sprint1_Checkout.divorceBeforeDeath(date, wifeDeath)) {
									families.get(families.size() - 1).Divorced = date;
								} else System.out.println("Error! Divorce Date should be before the date of death of wife.");
						  	} else families.get(families.size() - 1).Divorced = date;
					  	} else System.out.println("Error! Divorce Date should be after Marriage Date.");
					}else System.out.println("Invalid Date of Divorce of "+ families.get(families.size()-1).HusbandName + " and "+families.get(families.size()-1).WifeName);
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
	}
}
