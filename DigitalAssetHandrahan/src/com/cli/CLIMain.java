package com.cli;

import com.opencsv.exceptions.CsvValidationException;

public class CLIMain {

	public static void main(String[] args) {
		// Your main method implementation here
		CLI cli;
		try {
			cli = new CLI();
			cli.startPrompt();
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
