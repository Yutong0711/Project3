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
                            obj.Married.toString().substring(0,11), "|",
                            obj.Divorced.toString().substring(0,11), "|",
                            obj.HusbandID, "|", obj.HusbandName, "|",
                            obj.WifeID, "|",
                            obj.WifeName, "|",
                            String.valueOf(obj.Children), "|");
                } else {
                    System.out.format("%5s%5s%5s%30s%10s%5s%5s%5s%5s%10s%5s%15s%5s%5s%5s%10s%5s", "|",
                            obj.ID, "|",
                            obj.Married.toString().substring(0,11), "|",
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
        }

        public static void print_arraylist(ArrayList<Indivdual> tobedisplayed) {

            String[] columnNames = {"ID", "NAME", "GENDER", "AGE", "ALIVE", "DEATH", "CHILD", "SPOUSE"};
            //  Indivdual longest=tobedisplayed.get(0);


            System.out.format("%5s%5s%5s%30s%10s%5s%4s%5s%5s%10s%5s%15s%5s%5s%5s%10s%5s", "|", columnNames[0], "|", columnNames[1], "|", columnNames[2], "|", columnNames[3], "|", columnNames[4], "|", columnNames[5], "|", columnNames[6], "|", columnNames[7], "|");
            System.out.println(" ");
            for (int i = 0; i <= 135; i++) {
                System.out.print("-");
            }
            System.out.println(" ");
            for (int i = 0; i < tobedisplayed.size(); i++) {

                Indivdual obj = tobedisplayed.get(i);
                if(obj.Death == null)
                {
                    obj.Alive=true;
                }

                System.out.format("%5s%5s%5s%30s%10s%5s%5s%5s%5s%10s%5s%15s%5s%5s%5s%10s%5s", "|",
                        obj.ID, "|",
                        obj.Name, "|",
                        obj.Gender, "|",
                        obj.Age, "|",
                        obj.Alive, "|",
                        obj.Death == null ? "null" : obj.Death.toString().substring(0, 11), "|",
                        String.valueOf(obj.Child), "|",
                        String.valueOf(obj.Spouse), "|");
                System.out.println();
            }

            for (int i = 0; i <= 135; i++) {
                System.out.print("-");
            }
        }
}
