package com.changname.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.changname.Bean.Film;
import com.changname.Files.ReadFile;

public class Controller {

	private ArrayList<String> EXTENSIONS;
	private static String[] PARAMETERS_SPLIT = { "\\[", "dvdrip", "xvid", "dvd", "\\(", "\\{", "2016", "2017", "2018",
			"screener" };

	public Controller() {
		createExecution(System.getProperty("user.dir"));
	}

	public Controller(String path) {
		createExecution(path);
	}

	/**
	 * Method where execute all procecess
	 */
	public void createExecution(String path) {
		ReadFile read = new ReadFile();
		getExt(read.getExtensions());
		ArrayList<Film> list = readAll(path);
		HashMap<Film, Film> listCorrect = changeCorrectNames(list);
		changeTheCorrectName(listCorrect);
	}

	/**
	 * 
	 * @param directory
	 *            is a String
	 * @return Arraylist on the file names in the current directory.
	 */
	public ArrayList<Film> readAll(String directory) {
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
						extension = files[x].getName().substring(index + 1);
						filename = files[x].getName().substring(0, index);
					}
					if (isCorrectExtension(extension.toLowerCase())) {
						films.add(new Film(filename, extension));
					}
				}
			}
		}
		return films;
	}
	
	/**
	 * 
	 * @param mapList
	 */
	public void changeTheCorrectName(HashMap<Film, Film> mapList) {
		for (Film key : mapList.keySet()) {		
			File f1 = new File(key.getTitle()+"."+key.getExtension());
			File f2 = new File(mapList.get(key).getTitle()+"."+mapList.get(key).getExtension());
			
			if (f1.renameTo(f2)) {
				System.out.println(f1+" = SUCCESS with --> "+f2);
			} else {
				System.out.println(f1+" = FAILED with --> "+f2);
			}
		}
	}

	/**
	 * Add defaults extensions
	 * 
	 * @param extensions
	 * @return
	 */
	private void getExt(ArrayList<String> extensions) {
		String[] defaults = { "mp4", "avi", "mpeg", "wmv", "mov", "rm", "flv", "dvd", "mkv" };
		EXTENSIONS = new ArrayList<String>();

		if (extensions == null || extensions.isEmpty()) {
			extensions = new ArrayList<String>();
			for (String ext : defaults) {
				extensions.add(ext);
			}
		}
		EXTENSIONS = extensions;
	}

	/**
	 * 
	 * @param extFile
	 * @return
	 */
	public Boolean isCorrectExtension(String extFile) {
		Boolean flagExtension = false;

		for (String ext : EXTENSIONS) {
			if (ext.equals(extFile) && !ext.isEmpty()) {
				flagExtension = true;
			}
		}
		return flagExtension;
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public HashMap<Film, Film> changeCorrectNames(ArrayList<Film> list) {
		String ext = "";
		String titleName = "";
		HashMap<Film, Film> listCorrect = new HashMap<Film, Film>();

		for (Film temp : list) {
			Film oldName = temp;
			ext = temp.getExtension().toLowerCase();
			titleName = temp.getTitle().toLowerCase();

			// list of splits "["
			titleName = getSplitParameters(titleName);

			// Delete points and add blank space
			titleName = getSplitPoint(titleName);

			// Delete the blank space
			titleName = getSplitLowBar(titleName);

			// Delete the low bars
			titleName = getSplitBlank(titleName);

			// Capital letter
			titleName = getCapital(titleName);

			// Add the correct name in the list
			listCorrect.put(oldName, new Film(titleName, ext));
		}
		return listCorrect;
	}

	/**
	 * 
	 * @param directory
	 *            is a String
	 * @return Arraylist on the file names in the current directory.
	 */
	public ArrayList<String> readAllFiles(String directory) {
		ArrayList<String> titles = new ArrayList<String>();
		File f = new File(directory);

		if (f.exists()) {
			File[] files = f.listFiles();
			for (int x = 0; x < files.length; x++) {
				if (!files[x].isHidden() && files[x].isFile()) {
					titles.add(files[x].getName());
				}
			}
		}
		return titles;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public String getSplitParameters(String name) {
		String title = name;

		for (String param : PARAMETERS_SPLIT) {
			title = title.split(param.toString())[0];
		}

		return title;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public String getSplitPoint(String name) {
		String title = name;
		String finald = "";

		if (title.contains(".")) {
			int index = title.lastIndexOf('.');
			if (index != -1) {
				title = title.substring(0, index);
				for (String point : name.split("\\.")) {
					finald = finald + " " + point;
				}
			}
			title = finald.substring(1);
		}

		return title;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public String getCapital(String name) {
		String output = name.substring(0, 1).toUpperCase() + name.substring(1);
		return output;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public String getSplitBlank(String name) {
		String title = "";
		String[] change = name.split(" ");

		for (int i = 0; i < change.length; i++) {
			title = title + " " + change[i];
		}

		return title.substring(1);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public String getSplitLowBar(String name) {
		String title = "";
		String[] change = name.split("_");

		for (int i = 0; i < change.length; i++) {
			title = title + " " + change[i];
		}

		return title.substring(1);
	}

}
