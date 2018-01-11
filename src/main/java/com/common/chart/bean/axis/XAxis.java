package com.common.chart.bean.axis;

import java.util.List;

import com.common.chart.bean.axislabel.XAxisLabel;

public class XAxis extends Axis{
	public XAxis(List<Object> datas, Double xScaleWidth , XAxisLabel axisLabel , String max , String min) { 
		super(datas, xScaleWidth , axisLabel , max , min); 
	}
}
