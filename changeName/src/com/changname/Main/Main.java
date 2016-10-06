package com.changname.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	private static String[] EXTENSIONS = {"mp4","avi","mpeg","wmv","mov","rm","flv","dvd","mkv"}; 

	public static void main(String[] args) {
		Main m = new Main();
		
		
		
		
		//m.createExecution();
	}
	
	/**
	 * Method where execute all procecess
	 */
	public void createExecution() {
		String current = System.getProperty("user.dir");
		ArrayList<String> list = readAllFiles(current);
		HashMap<String, String> listCorrect = changeCorrectNames(list);
		changeTheCorrectName(listCorrect);
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public HashMap<String, String> changeCorrectNames(ArrayList<String> list) {
		String ext = "";
		String titleName = "";
		HashMap<String, String> listCorrect = new HashMap<String,String>();
		
		for (String temp : list) {
			ext = getExtensionFile(temp.toLowerCase());
			String oldName = temp;
			titleName = temp.toLowerCase();
			
			// list of splits "["
			titleName  = getSplitShut(titleName);

			// list of splits "DVDrip2
			titleName  = getSplitDVDrip(titleName);
			
			// list of splits "Xvid"
			titleName  = getSplitXvid(titleName);
			
			// list of splits "DVD"
			titleName  = getSplitDVD(titleName);
			
			// list of splits "("
			titleName  =  getSplitParenthesis(titleName);
			
			// // list of splits "BR"
			titleName  =  getSplitBR(titleName);
			
			// list of splits "BR"
			titleName  =  getSplitHD(titleName);
			
			// Delete points and add blank space
			titleName  =  getSplitPoint(titleName);
			
			// Delete the blank space
			titleName  =  getSplitLowBar(titleName);
			
			// Delete the low bars
			titleName  =  getSplitBlank(titleName);
			
			// Capital letter
			titleName  =  getCapital(titleName);
			
			// Add the correct name in the list
		    listCorrect.put(oldName,titleName+"."+ext);
		}
		return listCorrect;
	}
	
	/**
	 * 
	 * @param directory is a String
	 * @return Arraylist on the file names in the current directory.
	 */
	public ArrayList<String> readAllFiles(String directory) {
		ArrayList<String> titles = new ArrayList<String>();
		File f = new File(directory);

		if (f.exists()) {
			File[] files = f.listFiles();
			for (int x = 0; x < files.length; x++) {
				if (!files[x].isHidden() && files[x].isFile()){
						titles.add(files[x].getName());
				}
			}
		}
		return titles;
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public String getExtensionFile(String filename) {
		File f = new File(filename);
		String extension = "";
		if (f.isFile()) {
			int index = filename.lastIndexOf('.');
			if (index != -1) {
				extension = filename.substring(index + 1);
			}
		} 
		return extension;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public String getSplitPoint(String name) {
		String title = "";
		int index = name.lastIndexOf('.');
		if (index != -1) {
			name = name.substring(0, index);
			for (String point : name.split("\\.")) {
				title = title + " " + point;
			}
		}
		return title.substring(1);
	}
	
	/**
	 * 
	 * @param mapList
	 */
	public void changeTheCorrectName(HashMap<String, String> mapList) {
		for (String key : mapList.keySet()) {		
			File f1 = new File(key);
			File f2 = new File(mapList.get(key));
			
			if (f1.renameTo(f2)) {
				System.out.println(f1+" = SUCCESS with --> "+f2);
			} else {
				System.out.println(f1+" = FAILES with --> "+f2);
			}
		}
	}
	
	
	
	public String getCapital(String name) {
		String output = name.substring(0, 1).toUpperCase() + name.substring(1);
		return output;
	}

	public String getSplitShut(String name) {
		String change = name.split("\\[")[0];
		return change;
	}
	
	public String getSplitDVDrip(String name) {
		String change = name.split("dvdrip")[0];
		return change;
	}
	
	public String getSplitXvid(String name) {
		String change = name.split("xvid")[0];
		return change;
	}
	
	public String getSplitDVD(String name) {
		String change = name.split("dvd")[0];
		return change;
	}
	
	public String getSplitParenthesis(String name) {
		String change = name.split("\\(")[0];
		return change;
	}
	
	public String getSplitBR(String name) {
		String change = name.split("br")[0];
		return change;
	}
	
	public String getSplitHD(String name) {
		String change = name.split("hd")[0];
		return change;
	}
	
	public String getSplitBlank(String name) {
		String title = "";
		String[] change = name.split(" ");
		
		for (int i=0;i<change.length;i++) {
			title = title + " " + change[i];
		}
		
		return title.substring(1);
	}
	
	public String getSplitLowBar(String name) {
		String title = "";
		String[] change = name.split("_");
		
		for (int i=0;i<change.length;i++) {
			title = title + " " + change[i];
		}
		
		return title.substring(1);
	}
	
}
