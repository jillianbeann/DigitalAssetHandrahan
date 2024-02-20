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

//CSV parser class
public class CSVParser {
	public static Map<String, Portfolio> codeLookUp; 
	
	public static Map<String, Portfolio> getCodeMap() {
		return codeLookUp;
	}
	
	public static List<Portfolio> parsePortfolios(String filename) throws IOException, CsvValidationException {
		List<Portfolio> portfolios = new ArrayList<>();
		codeLookUp = new HashMap<>();
		
		try (CSVReader reader = new CSVReader(new FileReader(filename))) {
			String[] nextLine;
			// Pass the 1st row, contains column titles
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				// Process each row
				String code = nextLine[1];
				Portfolio portfolio = new Portfolio(nextLine[0], code,Double.parseDouble(nextLine[2].replace("$", "")));
				codeLookUp.put(code, portfolio);
//				portfolio.setName(nextLine[0]);
//				portfolio.setCode(nextLine[1]);
//				portfolio.setMarketValue(Double.parseDouble(nextLine[2].replace("$", "")));
				portfolios.add(portfolio);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Read CSV file and populate portfolio list
		return portfolios;
	}

	public static List<PortfolioShareClass> parsePortfolioShareClasses(String filename) throws IOException, CsvValidationException {
		List<PortfolioShareClass> shareClasses = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new FileReader(filename))) {
			String[] nextLine;
			// Pass the 1st row, contains column titles
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				// Process each row
				String parentPortfolioCode = nextLine[0];
				Portfolio parent = codeLookUp.get(parentPortfolioCode);
				PortfolioShareClass shareClass = new PortfolioShareClass(nextLine[1],nextLine[2],Double.parseDouble(nextLine[3].replace("%", "")));
				parent.addShareClass(shareClass);
//				shareClass.setParent(portfolios.nextLine[0]);
//				shareClass.setCode(nextLine[1]);
//				shareClass.setMarketValue(Double.parseDouble(nextLine[2].replace("$", "")));
				shareClasses.add(shareClass);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return shareClasses;
	}
}
