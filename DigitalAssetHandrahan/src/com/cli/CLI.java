package com.cli;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import com.model.Portfolio;
import com.model.PortfolioShareClass;
import com.opencsv.exceptions.CsvValidationException;
import com.parser.CSVParser;

public class CLI {
	private List<Portfolio> portfolios;
	private List<PortfolioShareClass> shareClasses;

	public CLI() throws CsvValidationException {
		try {
			portfolios = CSVParser.parsePortfolios("Portfolio.CSV");
			shareClasses = CSVParser.parsePortfolioShareClasses("PortfolioShareClass.CSV");
			System.out.println("CSV files loaded successfully. Ready to continue.");
			// Start CLI prompt
			startPrompt();
		} catch (IOException e) {
			System.err.println("Error loading CSV files: " + e.getMessage());
		}
	}

	public void startPrompt() {
		// Display prompt and handle user input
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("> ");
			String input = scanner.nextLine().toLowerCase();
			if (input.equals("show share class")) {
				System.out.println("Code? ");
				input = scanner.nextLine();
				showShareClass(input);
			} else {
				processCommand(input);
			}
		}
	}

	private void processCommand(String command) {
		switch (command) {
		case "help":
			System.out.println("Available commands: COUNTS, LIST PORTFOLIOS, SHOW SHARE CLASS");
			break;
		case "counts":
			counts();
			break;
		case "list portfolios":
			listPortfolios();
			break;
		default:
			System.out.println("Unknown command. Type 'help' for available commands.");
		}
	}

	// Implement commands: COUNTS, LIST PORTFOLIOS, SHOW SHARE CLASS
	private void counts() {
		System.out.println(portfolios.size() + " Portfolios loaded.");
		System.out.println(shareClasses.size() + " Portfolio Share Classes loaded.");

	}

	private void listPortfolios() {
		for (Portfolio p : portfolios) {
			System.out.println(p.getName() + ", " + p.getShareClasses().size() + " Share Classes");
		}
	}

	private void showShareClass(String code) {
		Portfolio parent = CSVParser.getCodeMap().get(code); 
		for (PortfolioShareClass psc : parent.getShareClasses()) {
			System.out.println(parent.getName() + " " + psc.getName() + " " + psc.getCode() + " " + String.format("%.0f%%", psc.getBaseFee()));
		}

	}
}