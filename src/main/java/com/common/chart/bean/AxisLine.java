package com.common.chart.bean;

public class AxisLine {
	private boolean show;
	private LineStyle lineStyle;
	
	public AxisLine( Double scaleWidth ) { 
		show = true; 
		lineStyle = new LineStyle();
		lineStyle.setScaleWidth( scaleWidth );
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public LineStyle getLineStyle() {
		return lineStyle;
	}
	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}
	
}
