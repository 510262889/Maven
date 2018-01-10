package com.common.chart.bean;

import com.util.StringUtil;

public class ToolTip {
	private String trigger;
	
	
	public ToolTip() {
		this.trigger = "axis";
	}

	public String getTrigger() {
		return StringUtil.encodeQuotationString( trigger );
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
	
}
