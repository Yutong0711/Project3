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
					indivduals.get(indivduals.size() - 1).Birthday = date;
				} else if (pre.equals("Death")) {
					indivduals.get(indivduals.size() - 1).Death = date;
				}
			}catch (ParseException ex){
				System.out.println("Exception " + ex);
			}
		} else if (tag.contains("BIRT")&& LegalTags.checkTags(tag)) {
			pre = "BIRT";
		} else if (tag.contains("DEAT")&& LegalTags.checkTags(tag)) {
			pre = "Death";
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
			Family family = new Family();
			family.ID = tag;
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
					families.get(families.size() - 1).Married = date;
				} else if (pre.equals("DIV")) {
					families.get(families.size() - 1).Divorced = date;
				}
			}catch (ParseException ex){
				System.out.println("Exception " + ex);
			}
		}
		else if (tag.contains("HUSB") && LegalTags.checkTags(tag)) {
			families.get(families.size() - 1).HusbandID = arguments;
			for (Indivdual p : Indivduals) {
				if (p.getID() == arguments) {
					families.get(families.size() - 1).HusbandName = p.Name;
				}
			}
		}
		else if (tag.contains("WIFE") && LegalTags.checkTags(tag)) {
			families.get(families.size() - 1).WifeID = arguments;
			for (Indivdual p : Indivduals) {
				if (p.getID() == arguments) {
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
