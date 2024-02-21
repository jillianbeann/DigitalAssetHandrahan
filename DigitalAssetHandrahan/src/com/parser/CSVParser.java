package com.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Portfolio;
import com.model.PortfolioShareClass;

public class CSVParser {

	/**
	 * Parses the given file by row, creates Portfolio entities with respective attributes, adds entity to map
	 * @param filename - Portfolio.CSV
	 * @return Map with Portfolio code Keys mapping to respective Portfolio Objects
	 * @throws IOException
	 * @throws CsvValidationException
	 */
	public static Map<String, Portfolio> parsePortfolios(String filename) throws IOException, CsvValidationException {
		// List<Portfolio> portfolios = new ArrayList<>();
		Map<String, Portfolio> portfolios = new HashMap<>();

		try (CSVReader reader = new CSVReader(new FileReader(filename))) {
			String[] nextLine;
			// Pass the 1st row, contains column titles
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				// Process each row
				String code = nextLine[1];
				Portfolio portfolio = new Portfolio(nextLine[0], code,
						Double.parseDouble(nextLine[2].replace("$", "")));
				portfolios.put(code, portfolio);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return portfolios;
	}

	/**
	 * Parses the given file by row, creates PortfolioShareClass entities with respective attributes, sets parent Portfolio using Map lookup, adds entity to list
	 * @param filename - PortfolioShareClass.CSV
	 * @param codeLookUp - Map of Portfilio codes to respective portfolios
	 * @return List of PortfolioShareClass entities with attributes and parent Portfolios set
	 * @throws IOException
	 * @throws CsvValidationException
	 */
	public static List<PortfolioShareClass> parsePortfolioShareClasses(String filename,
			Map<String, Portfolio> codeLookUp) throws IOException, CsvValidationException {
		List<PortfolioShareClass> shareClasses = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new FileReader(filename))) {
			String[] nextLine;
			// Pass the 1st row, contains column titles
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				// Process each row
				String parentPortfolioCode = nextLine[0];
				Portfolio parent = codeLookUp.get(parentPortfolioCode);
				PortfolioShareClass shareClass = new PortfolioShareClass(nextLine[1], nextLine[2],
						Double.parseDouble(nextLine[3].replace("%", "")));
				parent.addShareClass(shareClass);
				shareClasses.add(shareClass);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return shareClasses;
	}
}
