package com.changname.Bean;

public class Film {

	private String title;
	private String extension;
	
	public Film(String title, String extension) {
		super();
		this.title = title;
		this.extension = extension;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		return "Film [title=" + title + ", extension=" + extension + "]";
	}
		
}
