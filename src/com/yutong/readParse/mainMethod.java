package com.yutong.readParse;
import java.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



class Indivdual {
    String ID;
    String Name;
    String Gender;
    Date Birthday;
    int Age;
    boolean Alive;
    Date Death;
    List<String> Child = new ArrayList<>();
    List<String> Spouse = new ArrayList<>();

    public Indivdual() {
        super();
    }

    public Indivdual(String ID) {
        super();

        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
}


class Family {
    String ID;
    Date Married;
    Date Divorced;
    String HusbandID;
    String HusbandName;
    String WifeID;
    String WifeName;
    List<String> Children = new ArrayList<>();
    public Family() {
        super();
    }
    public Family (String ID) {
        super();
        this.ID = ID;
    }
    public String getID() {
        return ID;
    }
}

public class mainMethod {
    public static void print_arraylist_family(ArrayList<Family>  tobedisplayed)
    {

        String[] columnNames={"ID","MARRIED","DIVORCED","HUSBAND ID","HUSBAND NAME","WIFE ID","WIFE NAME","CHILDREN"};

        System.out.format("%5s%5s%5s%30s%10s%5s%2s%5s%5s%10s%5s%13s%5s%5s%3s%10s%5s","|",columnNames[0],"|",columnNames[1],"|",columnNames[2],"|",columnNames[3],"|",columnNames[4],"|",columnNames[5],"|",columnNames[6],"|",columnNames[7],"|");
        System.out.println(" ");

        for(int i=0;i<=135;i++)
        {System.out.print("-");}
        System.out.println(" ");
        for(int i=0;i<tobedisplayed.size();i++)
        {Family obj=tobedisplayed.get(i);
            System.out.format("%5s%5s%5s%30s%10s%5s%5s%5s%5s%10s%5s%15s%5s%5s%5s%10s%5s","|",obj.ID,"|",obj.HusbandName,"|",obj.gender,"|",obj.age,"|",obj.alive,"|",obj.death,"|",obj.child,"|",obj.spouse,"|");
            System.out.println();
        }

        for(int i=0;i<=135;i++)
        {System.out.print("-");}
    }
    public static void print_arraylist(ArrayList<Indivdual>  tobedisplayed)
    {

        String[]columnNames={"ID","NAME","GENDER","AGE","ALIVE","DEATH","CHILD","SPOUSE"};

        System.out.format("%5s%5s%5s%30s%10s%5s%4s%5s%5s%10s%5s%15s%5s%5s%5s%10s%5s","|",columnNames[0],"|",columnNames[1],"|",columnNames[2],"|",columnNames[3],"|",columnNames[4],"|",columnNames[5],"|",columnNames[6],"|",columnNames[7],"|");
        System.out.println(" ");
        for(int i=0;i<=135;i++)
        {System.out.print("-");}
        System.out.println(" ");
        for(int i=0;i<tobedisplayed.size();i++)
        {Indivdual obj=tobedisplayed.get(i);
            System.out.format("%5s%5s%5s%30s%10s%5s%5s%5s%5s%10s%5s%15s%5s%5s%5s%10s%5s","|",obj.ID,"|",obj.Name,"|",obj.Gender,"|",obj.Age,"|",obj.Alive,"|",obj.Death,"|",obj.Child,"|",obj.Spouse,"|");
            System.out.println();
        }

        for(int i=0;i<=135;i++)
        {System.out.print("-");
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            Read.openFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
