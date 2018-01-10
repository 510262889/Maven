package com.common.chart.bean;

import java.util.List;

public class Legend {
	private String y;
	private List<String> data;
	
	public Legend() {this.y = "10";}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	
}
