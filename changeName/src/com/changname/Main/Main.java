package com.changname.Main;

public class Main {

	public static void main(String[] args) {

		if (args.length == 0) {
			new Controller();
		} else {
			new Controller(args[0]);
		}
	}
}
