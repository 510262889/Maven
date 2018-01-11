package com.common.chart.bean;

public class LineStyle {
	private String color;
	private Double scaleWidth;
	
	public LineStyle() {
		color = "#999999";
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Double getScaleWidth() {
		return scaleWidth;
	}
	public void setScaleWidth(Double scaleWidth) {
		this.scaleWidth = scaleWidth;
	}
	
}
