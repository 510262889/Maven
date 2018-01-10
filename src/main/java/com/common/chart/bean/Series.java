package com.common.chart.bean;

public class Series {
	private String name;
	private String type;
	private String data;
	private String itemStyle;
	
	public Series() {
		itemStyle = "{normal: {areaStyle: {type: 'default'}}}";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getItemStyle() {
		return itemStyle;
	}
	
	public void setItemStyle(String itemStyle) {
		this.itemStyle = itemStyle;
	}
	
}
