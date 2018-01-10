package com.common.chart.bean;

public class Title {
	private String text;
	private String x;
	private String y;
	private String textStyle;
	
	
	public Title() {
		this.x = "10";
		this.y = "10";
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getTextStyle() {
		return textStyle;
	}
	public void setTextStyle(String textStyle) {
		this.textStyle = textStyle;
	}
	
}
