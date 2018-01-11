package com.common.chart.bean.axis;

import java.util.List;

import com.common.chart.bean.axis.Axis;
import com.common.chart.bean.axislabel.YAxisLabel;

public class YAxis extends Axis {
	public YAxis(List<Object> datas , Double yScaleWidth ,  YAxisLabel axisLabel , String max , String min ) { super(datas, yScaleWidth , axisLabel , max , min); }
}
