package com.yutong.readParse;

import java.util.ArrayList;

public class Print {
        public static void print_arraylist_family(ArrayList<Family> tobedisplayed) {
            System.out.println(" ");
            String[] columnNames = {"ID", "MARRIED", "DIVORCED", "HUSBAND ID", "HUSBAND NAME", "WIFE ID", "WIFE NAME", "CHILDREN"};

            System.out.format("%5s%5s%5s%30s%10s%5s%2s%5s%5s%10s%5s%13s%5s%5s%3s%10s%5s", "|", columnNames[0], "|", columnNames[1], "|", columnNames[2], "|", columnNames[3], "|", columnNames[4], "|", columnNames[5], "|", columnNames[6], "|", columnNames[7], "|");
            System.out.println(" ");

            for (int i = 0; i <= 135; i++) {
                System.out.print("-");
            }
            System.out.println(" ");
            for (int i = 0; i < tobedisplayed.size(); i++) {
                Family obj = tobedisplayed.get(i);
                if (obj.Divorced != null) {
                    System.out.format("%5s%5s%5s%30s%10s%5s%5s%5s%5s%10s%5s%15s%5s%5s%5s%10s%5s", "|",
                            obj.ID, "|",
                            obj.Married == null ? null : obj.Married.toString().substring(0, 11), "|",
                            //obj.Married.toString().substring(0,11), "|",
                            obj.Divorced.toString().substring(0,11), "|",
                            obj.HusbandID, "|", obj.HusbandName, "|",
                            obj.WifeID, "|",
                            obj.WifeName, "|",
                            String.valueOf(obj.Children), "|");
                } else {
                    System.out.format("%5s%5s%5s%30s%10s%5s%5s%5s%5s%10s%5s%15s%5s%5s%5s%10s%5s", "|",
                            obj.ID, "|",
                            obj.Married == null ? null : obj.Married.toString().substring(0, 11), "|",
                            //obj.Married.toString().substring(0,11), "|",
                            obj.Divorced, "|", obj.HusbandID, "|",
                            obj.HusbandName, "|",
                            obj.WifeID, "|",
                            obj.WifeName, "|",
                            String.valueOf(obj.Children), "|");
                }
                System.out.println();
            }

            for (int i = 0; i <= 135; i++) {
                System.out.print("-");
            }
            System.out.println("");
        }

        public static void print_arraylist(ArrayList<Indivdual> tobedisplayed) {

            String[] columnNames = {"ID", "NAME", "GENDER","BIRTHDAY", "AGE", "ALIVE", "DEATH", "CHILD", "SPOUSE"};
            //  Indivdual longest=tobedisplayed.get(0);


            System.out.format("%5s %12s%10s %12s%7s  %6s%2s %10s%3s  %4s%4s %4s%1s %8s%5s %20s%28s %20s%28s", "|", columnNames[0], "|", columnNames[1], "|", columnNames[2], "|", columnNames[3], "|", columnNames[4], "|", columnNames[5], "|", columnNames[6], "|", columnNames[7], "|", columnNames[8], "|");
            System.out.println(" ");
            for (int i = 0; i <= 200; i++) {
                System.out.print("-");
            }
            System.out.println(" ");
            for (int i = 0; i < tobedisplayed.size(); i++) {

                Indivdual obj = tobedisplayed.get(i);
                if(obj.Death == null)
                {
                    obj.Alive=true;
                }

                System.out.format("%5s" +
                                "%1s%1s" +
                                "%16s%4s" +
                                "%5s%5s" + "%12s%2s" +
                                "%5s%5s" +
                                "%5s%2s" +
                                "%12s%2s" +
                                "%48s%1s" +
                                "%48s%1s", "|",
                        obj.ID, "|",
                        obj.Name, "|",
                        obj.Gender, "|",
                        obj.Birthday == null ? "NA" : obj.Birthday.toString().substring(4, 10) + " " + obj.Birthday.toString().substring(24, 28), "|",
                        obj.Age, "|",
                        obj.Alive, "|",
                        obj.Death == null ? "NA" : obj.Death.toString().substring(4, 10) + " " + obj.Death.toString().substring(24, 28), "|",
                        obj.Child.size() == 0 ? "NA" :String.valueOf(obj.Child) , "|",
                        obj.Spouse.size() == 0 ? "NA" :String.valueOf(obj.Spouse) , "|");
                System.out.println();
            }

            for (int i = 0; i <= 200; i++) {
                System.out.print("-");
            }
        }
    public static void print_Erroe(ArrayList<String> tobedisplayed) {
            for (int i = 0; i < tobedisplayed.size(); ++i) {
                System.out.println(tobedisplayed.get(i));
            }
    }
}
