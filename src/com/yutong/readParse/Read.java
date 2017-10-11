package com.yutong.readParse;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;




public class Read {
	public static void openFile() throws IOException {
		try {
			JFileChooser jfc = new JFileChooser(FileSystemView
					.getFileSystemView().getHomeDirectory());

			int returnValue = jfc.showOpenDialog(null);
			File selectedFile = null;
			String suffix = "";
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				selectedFile = jfc.getSelectedFile();
				String fileName = selectedFile.getName();
				suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			}
			if (selectedFile != null && suffix.equals("txt")) {
				FileInputStream fis = new FileInputStream(
						selectedFile.getAbsolutePath());

				BufferedReader br = new BufferedReader(new InputStreamReader(
						fis));
				String line;
				// Read the file line by line
				List <Indivdual> indivdualList = new ArrayList<>();
				List<Family> familiesList = new ArrayList<>();

				while ((line = br.readLine()) != null) {
					line = Tools.replaceBlank(line);
					//System.out.println("--> " + line);
					Parse.parse(line, indivdualList);
					Parse.parseFamilies(line, familiesList, indivdualList);

				}
				/*indivdualList.sort((Indivdual o1, Indivdual o2)->o1.getID().compareTo(o2.getID()));*/


				Collections.sort(indivdualList, new Comparator<Indivdual>() {
					@Override
					public int compare(Indivdual o1, Indivdual o2) {
						return o1.getID().compareTo(o2.getID());
					}

				});

				Print.print_arraylist((ArrayList<Indivdual>) indivdualList);
				Print.print_arraylist_family((ArrayList<Family>) familiesList);
				/*for (int i = 0; i < indivdualList.size() - 1; ++i) {
				    System.out.println(indivdualList.get(i).ID);
                }*/
				// Close the input stream
				br.close();
				fis.close();
			} else {
				System.out.println("Caution: This file does not exist or is not a txt file!!!");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Can NOT read the file!");
			e.printStackTrace();
		}

	}

}
