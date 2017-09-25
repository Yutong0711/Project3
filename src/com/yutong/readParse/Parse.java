package com.yutong.readParse;

public class Parse {

	public static void parse(String line) {

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

		// Handle the tags exceptions for INDI and FAM
		if (arguments.contains("INDI") || arguments.contains("FAM")) {
			if (LegalTags.checkTags(arguments)) {
				valid = "Y";
			} else {
				valid = "N";
			}
			System.out.println("<-- " + level + "|" + arguments + "|" + valid
					+ "|" + tag);
		} else {
			if (LegalTags.checkTags(tag)) {
				valid = "Y";
			} else {
				valid = "N";
			}
			System.out.println("<-- " + level + "|" + tag + "|" + valid + "|"
					+ arguments);
		}
	}
}
