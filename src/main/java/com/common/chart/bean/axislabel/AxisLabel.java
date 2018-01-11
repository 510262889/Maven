package com.common.chart.bean.axislabel;

public class AxisLabel {
	// 是否显示轴线
	protected boolean show;
	// 悬浮提示
	protected String formatter;
	// 轴线每个单元格间隔数量
	protected Double interval;
	// 轴线和坐标显示数值的距离
	protected Double margin;
	// 轴线显示值的旋转角度
	protected String rotate;

	public AxisLabel( double accuracy ) {
		this.show = true;
		this.formatter = "function (value, index) { return Math.round(value * "+accuracy+") /"+accuracy+"; }"; // 保证显示Y轴刻度的小数点精度
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	public Double getInterval() {
		return interval;
	}
	public void setInterval(Double interval) {
		this.interval = interval;
	}
	public Double getMargin() {
		return margin;
	}
	public void setMargin(Double margin) {
		this.margin = margin;
	}
	public String getRotate() {
		return rotate;
	}
	public void setRotate(String rotate) {
		this.rotate = rotate;
	}
	
}
