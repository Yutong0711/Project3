package com.yutong.readParse;
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
	public Indivdual (String ID) {
		super();

		this.ID = ID;
	}
	public String getID () {
		return ID;
	}
}


class Family {
	String ID;
	Date Married;
	Date Divorced;
	String HuabandID;
	String HusbandName;
	String WIfeID;
	String WIfeName;
	String[] Children;
}

public class mainMethod {

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
