package com.yutong.readParse;

import java.io.IOException;

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
