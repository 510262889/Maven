package com.common.chart.bean.axislabel;


public class XAxisLabel extends AxisLabel{
	public XAxisLabel( double accuracy ) {
		super( accuracy );
		super.interval = 5.0;
		super.margin = 8.0;
		super.rotate = "0";
	}
	
	public XAxisLabel( double accuracy , double interval , double margin , String rotate ) {
		super( accuracy );
		super.interval = interval;
		super.margin = margin;
		super.rotate = rotate;
	}
}
