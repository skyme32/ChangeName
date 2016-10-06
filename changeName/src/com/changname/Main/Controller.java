package com.changname.Main;

import java.io.File;
import java.util.ArrayList;

import com.changname.Bean.Film;

public class Controller {
	
	private static String[] EXTENSIONS = {"mp4","avi","mpeg","wmv","mov","rm","flv","dvd","mkv"}; 

	public Controller() {
		createExecution();
	}

	/**
	 * Method where execute all procecess
	 */
	public void createExecution() {
		String current = System.getProperty("user.dir");
		
		
		
		ArrayList<Film> list = readAllFiles(current);
		for (Film temp : list) {
			System.out.println(temp);
		}

		// HashMap<String, String> listCorrect = changeCorrectNames(list);
		// changeTheCorrectName(listCorrect);
	}

	/**
	 * 
	 * @param directory
	 *            is a String
	 * @return Arraylist on the file names in the current directory.
	 */
	public ArrayList<Film> readAllFiles(String directory) {
		ArrayList<Film> films = new ArrayList<Film>();
		File f = new File(directory);
		String extension = "";
		String filename = "";

		// If the file exist
		if (f.exists()) {
			File[] files = f.listFiles();
			for (int x = 0; x < files.length; x++) {
				// Look if not hidden file, the file is a file and the file is a
				// movie
				if (!files[x].isHidden() && files[x].isFile()) {
					// Separate the name with the extension
					int index = files[x].getName().lastIndexOf('.');
					if (index != -1) {
						extension = files[x].getName().substring(index + 1).toLowerCase();
						filename = files[x].getName().substring(0, index).toLowerCase();
					}
					films.add(new Film(filename, extension));
				}
			}
		}
		return films;
	}

}
