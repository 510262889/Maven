package com.common.chart.parent;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import com.util.StringUtil;
import com.util.XmlUtil;

/**
 * 带有坐标轴的图形
 */
@Component
public abstract class ChartHasAxis extends ChartsAnalysis {
	// 轴线刻度精度
	protected double accuracy = Math.pow( 10 , 4 );
    // 横轴单位
	protected String xUnit;
	 // Y轴单位
	protected String yUnit;
	// Y横轴数据
	protected List<Object> yDatas;
    // X横轴数据字段
	protected String xScale;
	// X横轴数据字段
	protected String yScale;
	// X横轴数据
	protected List<Object> xDatas;
    // 是否显示横轴
	protected boolean xScaleIsShow;
    // 是否自动调整X轴刻度
	protected boolean autoXScale;
    // 是否自动调整Y轴刻度
	protected boolean autoYScale;
    // 是否交换XY轴
	protected boolean changeXY;
    // 纵轴起点值,设置之后纵轴最大值自动设置为数据中的最大值
	protected int minY;
    // 横轴起点值,设置之后横轴最大值自动设置为数据中的最大值
	protected int minX;
    // 横轴刻度间距
	protected int interval;
    // 横轴显示值的旋转角度
	protected String rotate;
    // 坐标和坐标数值的显示距离
	protected double xMargin;
    // 横轴线的宽度
	protected double xScaleWidth;
    // 纵轴线的宽度
	protected double yScaleWidth;
	
	protected ChartHasAxis(){
		super();
	}
	
	@Override
	protected void analysisConfiguration(InputStream ips) {
		super.analysisConfiguration(ips);
        // 获取根路径元素
        Element rootElement = document.getRootElement();
        // 解析根路径元素
        rootElementAnalysis( rootElement );
	}
	
	/**
	 * 解析根路径元素
	 */
	private void rootElementAnalysis( Element rootElement ){
		this.xUnit = StringUtil.encodeQuotationString( XmlUtil.getElementAttrValue( rootElement , "xUnit" ) );
		this.yUnit = StringUtil.encodeQuotationString( XmlUtil.getElementAttrValue( rootElement , "yUnit" ) );
        this.xScale = XmlUtil.getElementAttrValue( rootElement , "xScale" );
        this.xScaleIsShow = Boolean.valueOf( XmlUtil.getElementAttrValue( rootElement , "xScaleIsShow" ) ) ;
        this.autoXScale = "".equals( XmlUtil.getElementAttrValue( rootElement , "autoXScale" ) ) ? false : true;
        this.autoYScale = "".equals( XmlUtil.getElementAttrValue( rootElement , "autoYScale" ) ) ? false : true;
        this.changeXY = "".equals( XmlUtil.getElementAttrValue( rootElement , "changeXY" ) ) ? false : true;
        this.minY = "".equals( XmlUtil.getElementAttrValue( rootElement , "minY" ) ) ? 0 : Integer.parseInt( XmlUtil.getElementAttrValue( rootElement , "minY" ) );
        this.minX = "".equals( XmlUtil.getElementAttrValue( rootElement , "minX" ) ) ? 0 : Integer.parseInt( XmlUtil.getElementAttrValue( rootElement , "minX" ) );
        this.interval = "".equals( XmlUtil.getElementAttrValue( rootElement , "interval" ) ) ? 0 : Integer.parseInt( XmlUtil.getElementAttrValue( rootElement , "interval" ) );
        this.rotate = XmlUtil.getElementAttrValue( rootElement , "rotate" );
        this.xMargin = "".equals( XmlUtil.getElementAttrValue( rootElement , "xMargin" ) ) ? 0 : Double.parseDouble( XmlUtil.getElementAttrValue( rootElement , "xMargin" ) );
        this.xScaleWidth = "".equals( XmlUtil.getElementAttrValue( rootElement , "xScaleWidth" ) ) ? 0 : Double.parseDouble( XmlUtil.getElementAttrValue( rootElement , "xScaleWidth" ) );
        this.yScaleWidth = "".equals( XmlUtil.getElementAttrValue( rootElement , "yScaleWidth" ) ) ? 0 : Double.parseDouble( XmlUtil.getElementAttrValue( rootElement , "yScaleWidth" ) );
	}
}
