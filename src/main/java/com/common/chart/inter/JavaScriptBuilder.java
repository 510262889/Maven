package com.common.chart.inter;

import java.util.List;

import com.common.chart.bean.Grid;
import com.common.chart.bean.Legend;
import com.common.chart.bean.Title;
import com.common.chart.bean.ToolTip;
import com.common.chart.bean.axis.Axis;

public interface JavaScriptBuilder {
	/**
	 * 初始化头部脚本
	 * @param 图形父ID
	 */
	void initHeadJavaScript( String id );
	
	/**
	 * 初始化标题部分脚本
	 * @param title
	 */
	void initTitleJavaScript( Title title );
	
	/**
	 * 初始化颜色部分脚本
	 */
	void initColorJavaScript( List<String> colors );
	
	/**
	 * 初始化悬浮提示部分脚本
	 */
	void initToolTipJavaScript( ToolTip toolTip );
	
	/**
	 * 初始化图例部分脚本
	 */
	void initLegendJavaScript( Legend legend );
	
	/**
	 * 初始化图形位置部分脚本
	 */
	void initGridJavaScript( Grid grid );
	
	/**
	 * 初始化轴线数据
	 */
	void initAxisJavaScript( Axis axis );
	
	/**
	 * 初始化无数据时的效果
	 */
	void initNoDataEffect();
	
	/**
	 * 初始化数据
	 */
	void initSeries();
	
	/**
	 * 初始底部脚本
	 */
	void initFootJavaScript();
}
