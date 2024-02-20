package com.cli;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.Map;

import com.model.Portfolio;
import com.model.PortfolioShareClass;
import com.opencsv.exceptions.CsvValidationException;
import com.parser.CSVParser;

public class CLI {
	private Map<String, Portfolio> portfolios;
	private List<PortfolioShareClass> shareClasses;

	public CLI() throws CsvValidationException {
		try {
			portfolios = CSVParser.parsePortfolios("Portfolio.CSV");
			shareClasses = CSVParser.parsePortfolioShareClasses("PortfolioShareClass.CSV", portfolios);
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
			String input = scanner.nextLine();
			if (input.equals("SHOW SHARE CLASS")) {
				System.out.print("Code? ");
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
		case "COUNTS":
			counts();
			break;
		case "LIST PORTFOLIOS":
			listPortfolios();
			break;
		default:
			System.out.println("Unknown command. Type 'help' for available commands.");
		}
	}

	// Implement commands: COUNTS, LIST PORTFOLIOS, SHOW SHARE CLASS
	private void counts() {
		int pSize = portfolios.size();
		int sSize = shareClasses.size();
		if (pSize == 1) {
			System.out.println(pSize + "Portfolio loaded.");
		} else {
			System.out.println(pSize + " Portfolios loaded.");
		}
		if (sSize == 1) {
			System.out.println(sSize + "Portfolio Share Class loaded.");
		} else {
			System.out.println(sSize + " Portfolio Share Classes loaded.");
		}

	}

	private void listPortfolios() {
		for (Portfolio p : portfolios.values()) {
			int size = p.getShareClasses().size();
			System.out.print(p.getName() + ", " + size);
			if (size == 1) {
				System.out.println(" Share Class");
			} else {
				System.out.println(" Share Classes");
			}
		}
	}

	private void showShareClass(String code) {
		Portfolio parent = portfolios.get(code);
		if (parent == null) {
			System.out.println("Portfolio not found. Try a valid code. Codes are case sensitive.");
		} else {
			for (PortfolioShareClass psc : parent.getShareClasses()) {
				System.out.print(parent.getName() + " " + psc.getName() + " " + psc.getCode() + " ");
				double fee = psc.getBaseFee();
				if (fee % 1 == 0) {
					System.out.println(String.format("%.0f%%", fee));
				} else {
					System.out.println(String.format("%.2f%%", fee));
				}

			}
		}

	}
}