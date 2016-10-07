package com.changname.Files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
	
	private ArrayList<String> parameters = null;
	private ArrayList<String> extensions = null;
		
	public ReadFile() {
		createContent(System.getProperty("user.dir")+"/changName.conf");
	}

	public void createContent(String archivo) {
        String line;
        FileReader f = null;
		
        try {
			f = new FileReader(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        BufferedReader b = new BufferedReader(f);
        
        try {
			if ((line = b.readLine())!=null) {
				extensions = new ArrayList<String>();
				
				for (String param: line.split(";")) {
					extensions.add(param);
				}
			}
			
			/* Dificult implentacion
			if ((line = b.readLine())!=null) {
				parameters = new ArrayList<String>();
				for (String param: line.split(";")) {
					parameters.add(param);
				}
			}*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			b.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public ArrayList<String> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}

	public ArrayList<String> getExtensions() {
		return extensions;
	}

	public void setExtensions(ArrayList<String> extensions) {
		this.extensions = extensions;
	}

 

}
