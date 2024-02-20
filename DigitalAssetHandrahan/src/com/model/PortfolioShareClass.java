package com.model;

//Portfolio Share Class entity
public class PortfolioShareClass {
	private Portfolio parentPortfolio;
	private String name;
	private String code;
	private double baseFee;
	
	
	public PortfolioShareClass(String name, String code, double baseFee) {
        this.name = name;
        this.code = code;
        this.baseFee = baseFee;
    }
	
	public Portfolio getParentPortfolio() {
		return parentPortfolio;
	}
	
	public void setParentPortfolio(Portfolio parent) {
		this.parentPortfolio  = parent;
	}

}