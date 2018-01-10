package com.common.chart.bean.chart;

import com.util.StringUtil;

/**
 * @author ykzhu
 *
 * 折线图对象
 */
public class Area extends Chart {
	private String colorLine;
	private String colorArea;
	private String colorPoint;
	
	public Area(){
		colorPoint = "#33CC99";
		colorLine = "#33CC99";
		colorArea = "#33CC99";
	}

	public Area(String colorLine, String colorArea, String colorPoint) {
		this.colorLine = colorLine;
		this.colorArea = colorArea;
		this.colorPoint = colorPoint;
	}

	public String getColorLine() {
		return StringUtil.encodeQuotationString( colorLine );
	}

	public void setColorLine(String colorLine) {
		this.colorLine = colorLine;
	}

	public String getColorArea() {
		return StringUtil.encodeQuotationString( colorArea );
	}

	public void setColorArea(String colorArea) {
		this.colorArea = colorArea;
	}

	public String getColorPoint() {
		return StringUtil.encodeQuotationString( colorPoint );
	}

	public void setColorPoint(String colorPoint) {
		this.colorPoint = colorPoint;
	}

}
