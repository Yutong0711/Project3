package com.yutong.readParse;

import java.util.ArrayList;

public class Print {
	public static void print_arraylist_family(ArrayList<Family> tobedisplayed) {
		String[] columnNames = { "ID", "MARRIED", "DIVORCED", "HUSBAND ID",
				"HUSBAND NAME", "WIFE ID", "WIFE NAME", "CHILDREN" };

		System.out
				.format("%5s %12s%11s %10s%4s %10s%4s %12s%11s %12s%8s %12s%11s %12s%8s %43s%30s",
						"|", columnNames[0], "|", columnNames[1], "|",
						columnNames[2], "|", columnNames[3], "|",
						columnNames[4], "|", columnNames[5], "|",
						columnNames[6], "|", columnNames[7], "|");
		System.out.println(" ");

		for (int i = 0; i <= 222; i++) {
			System.out.print("-");
		}
		System.out.println(" ");

		for (int i = 0; i < tobedisplayed.size(); i++) {
			Family obj = tobedisplayed.get(i);
			if (obj.getDivorced() != null) {
				System.out
						.format("%5s %1s%1s %12s%2s %12s%2s %1s%1s %16s%4s %1s%1s %16s%4s %72s%1s",
								"|",
								obj.getID(),
								"|",
								obj.getMarried() == null ? null : obj
										.getMarried().toString()
										.substring(0, 11), "|",
								obj.getDivorced().toString().substring(0, 11),
								"|", obj.getHusbandID(), "|", obj
										.getHusbandName(), "|",
								obj.getWifeID(), "|", obj.getWifeName(), "|",
								String.valueOf(obj.getChildren()), "|");
			} else {
				System.out
						.format("%5s %1s%1s %12s%2s %12s%2s %1s%1s %16s%4s %1s%1s %16s%4s %72s%1s",
								"|",
								obj.getID(),
								"|",
								obj.getMarried() == null ? null : obj
										.getMarried().toString()
										.substring(0, 11),
								"|",
								obj.getDivorced() == null ? null : obj
										.getDivorced().toString()
										.substring(0, 11), "|", obj
										.getHusbandID(), "|", obj
										.getHusbandName(), "|",
								obj.getWifeID(), "|", obj.getWifeName(), "|",
								String.valueOf(obj.getChildren()), "|");
			}
			System.out.println();
		}

		for (int i = 0; i <= 222; i++) {
			System.out.print("-");
		}
		System.out.print("\n");
	}

	public static void print_arraylist_indivdual(
			ArrayList<Indivdual> tobedisplayed) {

		String[] columnNames = { "ID", "NAME", "GENDER", "BIRTHDAY", "AGE",
				"ALIVE", "DEATH", "CHILD", "SPOUSE" };

		System.out
				.format("%5s %12s%10s %12s%7s  %6s%2s %10s%3s  %4s%4s %4s%1s %8s%5s %20s%28s %20s%28s",
						"|", columnNames[0], "|", columnNames[1], "|",
						columnNames[2], "|", columnNames[3], "|",
						columnNames[4], "|", columnNames[5], "|",
						columnNames[6], "|", columnNames[7], "|",
						columnNames[8], "|");
		System.out.println(" ");
		for (int i = 0; i <= 200; i++) {
			System.out.print("-");
		}
		System.out.println(" ");

		for (int i = 0; i < tobedisplayed.size(); i++) {
			Indivdual obj = tobedisplayed.get(i);
			if (obj.getDeath() == null) {
				obj.setAlive(true);
			}

			System.out
					.format("%5s" + "%1s%1s" + "%16s%4s" + "%5s%5s" + "%12s%2s"
							+ "%5s%5s" + "%5s%2s" + "%12s%2s" + "%48s%1s"
							+ "%48s%1s",
							"|",
							obj.getID(),
							"|",
							obj.getName(),
							"|",
							obj.getGender(),
							"|",
							obj.getBirthday() == null ? "NA" : obj
									.getBirthday().toString().substring(4, 10)
									+ " "
									+ obj.getBirthday().toString()
											.substring(24, 28),
							"|",
							obj.getAge(),
							"|",
							obj.isAlive(),
							"|",
							obj.getDeath() == null ? "NA" : obj.getDeath()
									.toString().substring(4, 10)
									+ " "
									+ obj.getDeath().toString()
											.substring(24, 28),
							"|",
							obj.getChild().size() == 0 ? "NA" : String
									.valueOf(obj.getChild()),
							"|",
							obj.getSpouse().size() == 0 ? "NA" : String
									.valueOf(obj.getSpouse()), "|");
			System.out.println();
		}

		for (int i = 0; i <= 200; i++) {
			System.out.print("-");
		}
		System.out.print("\n");
	}

	public static void print_Error(ArrayList<String> toBeDisplayed) {
		for (int i = 0; i < toBeDisplayed.size(); ++i) {
			System.out.println(toBeDisplayed.get(i));
		}
	}
}
