package com.common.chart.bean.axis;

import java.util.List;

import com.common.chart.bean.AxisLine;
import com.common.chart.bean.axislabel.AxisLabel;
import com.util.StringUtil;

public class Axis {
	// 轴线数据类型
	protected String type;
	// 坐标轴两端留白
	protected String boundaryGap;
	// 轴线数据
	protected List<Object> datas;
	// 轴线单位
	protected String unit;
	// 轴线刻度数值标识
	protected AxisLabel axisLabel;
	// 轴线样式
	protected AxisLine axisLine;
	
	public Axis( List<Object> datas  , AxisLabel axisLabel) {
		this.type = "value";
		this.boundaryGap = StringUtil.encodeQuotationString( "0%" );
		this.datas = datas;
		this.axisLabel = axisLabel;
		this.axisLine = new AxisLine( 1.0 );
	}
	
	public Axis( List<Object> datas , Double xScaleWidth , AxisLabel axisLabel) {
		this.type = "category";
		this.boundaryGap = StringUtil.encodeQuotationString( "0%" );
		this.datas = datas;
		this.axisLabel = axisLabel;
		this.axisLine = new AxisLine( xScaleWidth );
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public String getBoundaryGap() {
		return boundaryGap;
	}
	public void setBoundaryGap(String boundaryGap) {
		this.boundaryGap = boundaryGap;
	}
	public List<Object> getDatas() {
		return datas;
	}
	public void setDatas(List<Object> datas) {
		this.datas = datas;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public AxisLabel getAxisLabel() {
		return axisLabel;
	}
	public void setAxisLabel(AxisLabel axisLabel) {
		this.axisLabel = axisLabel;
	}
	public AxisLine getAxisLine() {
		return axisLine;
	}
	public void setAxisLine(AxisLine axisLine) {
		this.axisLine = axisLine;
	}
	
}
