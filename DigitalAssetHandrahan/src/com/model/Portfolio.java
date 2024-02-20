package com.model;
import java.util.ArrayList;
import java.util.List;

// Portfolio entity
public class Portfolio {
	private String name;
	private String code;
	private double marketValue;
	private List<PortfolioShareClass> shareClasses;
	
	public Portfolio(String name, String code, double marketValue) {
        this.name = name;
        this.code = code;
        this.marketValue = marketValue;
        this.shareClasses = new ArrayList<>(0);
    }
	
	public void addShareClass(PortfolioShareClass psc) {
		shareClasses.add(psc);
		psc.setParentPortfolio(this);
	}
	
	
	public String getName() {
		return name;
	}
//	public void setName(String name) {
//		this.name = name;
//	}
	public String getCode() {
		return code;
	}
//	public void setCode(String code) {
//		this.code = code;
//	}
	public double getMarketValue() {
		return marketValue;
	}
//	public void setMarketValue(double marketValue) {
//		this.marketValue = marketValue;
//	}
//	
	public List<PortfolioShareClass> getShareClasses() {
		return shareClasses;
	}
	


}
