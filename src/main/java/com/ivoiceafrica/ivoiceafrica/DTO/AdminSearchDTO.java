package com.ivoiceafrica.ivoiceafrica.DTO;

public class AdminSearchDTO {

	private String searchOption;
	private String searchValue;
	
	public String getSearchOption() {
		return searchOption;
	}
	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	@Override
	public String toString() {
		return "AdminSearchDTO [searchOption=" + searchOption + ", searchValue=" + searchValue + "]";
	}
	
}
