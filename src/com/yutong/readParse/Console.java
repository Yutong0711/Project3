package com.yutong.readParse;
import java.util.*;

public class Console {
    public static void readLine(){
        Scanner input = new Scanner(System.in);
        String line;
        // Read the file line by line
        List <Indivdual> indivdualList = new ArrayList<Indivdual>();
        List<Family> familiesList = new ArrayList<>();
        List<String> error = new ArrayList<>();
        while(true){
            if(!(line = input.nextLine()).equals("quit")){
                line = Tools.replaceBlank(line);
                //System.out.println("--> " + line);
                Parse.parse(line, indivdualList,familiesList,error);
                //Parse.parseFamilies(line, familiesList, indivdualList);
            }else{
                break;
            }
        }

        //System.out.println("--> " + line);
        Print.print_arraylist_indivdual((ArrayList<Indivdual>) indivdualList);
        Print.print_arraylist_family((ArrayList<Family>) familiesList);
        Print.print_Error((ArrayList<String>) error);
    }
}
