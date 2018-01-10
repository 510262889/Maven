package com.common.chart.bean;

import com.util.StringUtil;

public class Grid {
	private String y;
	private String x;
	private String y2;
	private String x2;
	private String width;
	private String heigth;
	private boolean containLabel;
	
	
	public Grid() {
		this.y = "60px";
		this.y2 = "30px";
		this.x = "60px";
		this.x2 = "60px";
		this.containLabel = true;
	}


	public String getY() {
		return y;
	}


	public void setY(String y) {
		this.y = y;
	}


	public String getX() {
		return x;
	}


	public void setX(String x) {
		this.x = x;
	}


	public String getY2() {
		return y2;
	}


	public void setY2(String y2) {
		this.y2 = y2;
	}


	public String getX2() {
		return x2;
	}


	public void setX2(String x2) {
		this.x2 = x2;
	}


	public String getWidth() {
		return StringUtil.empty( width , "undefined" );
	}


	public void setWidth(String width) {
		this.width = width;
	}


	public String getHeigth() {
		return StringUtil.empty( heigth , "undefined" );
	}


	public void setHeigth(String heigth) {
		this.heigth = heigth;
	}


	public boolean isContainLabel() {
		return containLabel;
	}


	public void setContainLabel(boolean containLabel) {
		this.containLabel = containLabel;
	}
}
