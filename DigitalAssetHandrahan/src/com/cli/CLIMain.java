package com.cli;

import com.opencsv.exceptions.CsvValidationException;

public class CLIMain {

	public static void main(String[] args) {
		CLI cli;
		try {
			cli = new CLI();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		}

	}

}
