package com.yutong.readParse;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class mainMethod {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        PrintStream oldPrintStream = System.out;
        FileOutputStream bos = new FileOutputStream("output.txt");
        MultiOutputSteam multi = new MultiOutputSteam(new PrintStream(bos), oldPrintStream);
        System.setOut(new PrintStream(multi));
        Scanner sc = new Scanner(System.in);
        System.out.println("If you want to choose a file, please press 1; if you want to test in console, please press 2.");
        String choice = sc.nextLine();
        if(choice.equals("1")){
            try {
                Read.openFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else if(choice.equals("2")){
            System.out.println("Please enter quit when you complete inputing your lines:");
            Console.readLine();
        }
    }

}
