package com.model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
	private final String name;
	private final String code;
	private final double marketValue;
	private List<PortfolioShareClass> shareClasses;

	public Portfolio(String name, String code, double marketValue) {
		this.name = name;
		this.code = code;
		this.marketValue = marketValue;
		this.shareClasses = new ArrayList<>();
	}

	public void addShareClass(PortfolioShareClass psc) {
		shareClasses.add(psc);
		psc.setParentPortfolio(this);
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public List<PortfolioShareClass> getShareClasses() {
		return shareClasses;
	}

}
