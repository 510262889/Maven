package com.common.chart.bean.chart.axis;

import java.util.ArrayList;
import java.util.List;

import com.common.chart.bean.chart.Chart;
import com.util.JsonUtil;
import com.util.StringUtil;

public class MarkLine extends Chart{
	private String name;
	private String color;
	private String width;
	// 'solid' | 'dotted' | 'dashed'
	private String type;
	private String yScale;
	
	public MarkLine(String name, String color, String width, String type, String yScale) {
		this.name = name;
		this.color = color;
		this.width = width;
		this.type = type;
		this.yScale = yScale;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getyScale() {
		return yScale;
	}
	public void setyScale(String yScale) {
		this.yScale = yScale;
	}
	/**
	 * 转换为markSeries脚本
	 * @param data
	 * @return
	 */
	public String toString( List<String> data ) {
		String javaScript =     "{"+
	             "                name : "+ StringUtil.encodeQuotationString( name ) + ","
	          +  "                symbol:'none',"
	          +  "                type : 'line',"
	          +  "                data : "+JsonUtil.toJson( data )+","
	          +  "                itemStyle:{normal: {lineStyle: {color :'#000'}}},"
	          +  "                markLine : {"
	          +  "                    itemStyle:{"
	          +  "                        	normal:{ color: "+StringUtil.encodeQuotationString( this.color )+", lineStyle:{type:"+ StringUtil.encodeQuotationString( this.type )+",width:"+ this.width +"}, label:{formatter:"+ StringUtil.encodeQuotationString( this.name )+" } }"
	          +  "                   		   },"
	          +  "                    data : ["
	          +  "                        {type : 'min', name: "+StringUtil.encodeQuotationString( this.name )+"}"
	          +  "                    ]"
	          +  "                },"
	          +  "            }";
			
		return javaScript;
	}
	
	
}
