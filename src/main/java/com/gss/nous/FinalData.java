package com.gss.nous;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


public class FinalData {

	private String productId;
	private String productName;
	private List<String> priceList=new ArrayList<String>();
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<String> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<String> priceList) {
		this.priceList = priceList;
	}
	public void addPrice(String priceString) {
		if(priceString!=null||"".equals(priceString)) {
			this.priceList.add(priceString);
		}
	}
	 public String toString() {
	      return this.productId + "," + this.productName +","+
	 this.priceList.toString().replace("[", "").replace("]", "")+"\r\n"
	 .replace("[", "").replace("]", "");
	   } 
}
