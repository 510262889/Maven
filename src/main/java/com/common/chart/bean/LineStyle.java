package com.common.chart.bean;

public class LineStyle {
	private String color;
	private Double xScaleWidth;
	
	public LineStyle() {
		color = "#999999";
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Double getxScaleWidth() {
		return xScaleWidth;
	}
	public void setxScaleWidth(Double xScaleWidth) {
		this.xScaleWidth = xScaleWidth;
	}
	
}
