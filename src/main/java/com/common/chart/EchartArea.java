package com.common.chart;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.common.chart.bean.axis.*;
import com.common.chart.bean.axislabel.XAxisLabel;
import com.common.chart.bean.axislabel.YAxisLabel;
import com.common.chart.bean.chart.axis.Area;
import com.common.chart.bean.Grid;
import com.common.chart.bean.Legend;
import com.common.chart.bean.Title;
import com.common.chart.bean.ToolTip;
import com.common.chart.parent.ChartHasAxis;
import com.exception.MyExpection;
import com.util.JsonUtil;
import com.util.ObjectUtil;
import com.util.StringUtil;
import com.util.XmlUtil;

/**
 * 
 * @author ykzhu
 *
 * 折线图生成对象
 */
@Component
public class EchartArea extends ChartHasAxis{
	private String DEFAULT_URL = "/WEB-INF/views/component/chart/chartdemo.xml";
    // 图形颜色
    private List<String> colors;
    // 图形集合
    private List<Area> areas;
    // 图例集合
    private List<String> legends;
    // 是否堆叠
    private boolean isStack = false;
    // 是否曲线平滑
    private boolean isSmooth = false;
    
	public EchartArea(){super(); this.resultJs = new StringBuffer(); }
	
	public EchartArea( String configUrl ){  this.DEFAULT_URL = configUrl; }
	
	/**
	 * 初始化参数,防止生成的数值错乱
	 */
	private void initParm(){
		this.resultJs = new StringBuffer();
		this.chartData = new HashMap<Object, List<Object>>();
    	this.resultJs = new StringBuffer();
    	this.colors = new ArrayList<String>();
    	this.areas = new ArrayList<Area>();
    	this.legends = new ArrayList<String>();
	}
	
	@Override
	public String createChart( HttpServletRequest request , Object... parms ) {
		initParm();
		// 获取XML文件
 		InputStream fileIps = request.getServletContext().getResourceAsStream( DEFAULT_URL );
 		// 解析XML文件
 		if( fileIps == null ) throw new MyExpection( "访问的配置文件路径不存在" );
 		// 解析配置
		analysisConfiguration( fileIps );
		// 初始化数据源
		initDataSource( areas , parms );
		
		// 初始化头部脚本
		initHeadJavaScript( this.id );
		
		// 实例标题对象
		Title title = new Title();
		title.setText( this.title );
		title.setTextStyle( this.titleStyle );
		// 初始化标题部分脚本
		initTitleJavaScript( title );
		
		// 初始化颜色部分脚本
		initColorJavaScript( colors );
		
		// 实例化悬浮提示对象
		ToolTip toolTip = new ToolTip();
		// 初始化提示部分脚本
		initToolTipJavaScript( toolTip );
		
		// 实例化图例对象
		Legend legend = new Legend();
		legend.setData( legends );
		// 初始化图例部分脚本
		initLegendJavaScript( legend );
		
		// 实例化表格对象,用于定义图形大小及偏移位置
		Grid grid = new Grid();
		grid.setWidth( this.width );
		grid.setHeigth( this.height );
		// 初始化图形位置部分脚本
		initGridJavaScript( grid );
		
		// 实例化轴线数值标记对象
		XAxisLabel xAxisLabel = new XAxisLabel( this.accuracy , this.interval , this.xMargin , this.rotate );
		// 实例化轴线对象
		Axis xAxis = new XAxis( xDatas , this.xScaleWidth, xAxisLabel , this.maxX , this.minX );
		// 初始化横轴线部分脚本
		initAxisJavaScript( xAxis );
		
		// 实例化轴线数值标记对象
		YAxisLabel yAxisLabel = new YAxisLabel( this.accuracy );
		// 实例化轴线对象
		Axis yAxis = new YAxis( yDatas , this.yScaleWidth, yAxisLabel , this.maxY , this.minY );
		// 初始化横轴线部分脚本
		initAxisJavaScript( yAxis );
		
		// 初始化无数据时的效果
		initNoDataEffect();
		
		// 初始化数据
		initSeries();
		
		// 初始底部脚本
		initFootJavaScript();
		
		return resultJs.toString();
	}

	@Override
	protected void analysisConfiguration( InputStream ips ) {
		super.analysisConfiguration( ips );
        // 获取根路径元素
        Element rootElement = document.getRootElement();
        this.isStack = Boolean.parseBoolean( XmlUtil.getElementAttrValue( rootElement , "isStack" ) );
        this.isSmooth = Boolean.parseBoolean( XmlUtil.getElementAttrValue( rootElement , "isSmooth" ) );
        // 获取chart标签
        for( Element secondElement : rootElement.elements() ){
        	if( "data".equals( secondElement.getName() ) ) {
        		for( Element element : secondElement.elements() ){
        			if( "dataDetail".equals( element.getName() ) ){
        				for( Element chartElement : element.elements() ){
        					if( "chart".equals( chartElement.getName() ) ){
        						Area area = new Area();
        						String colorArea = XmlUtil.getElementAttrValue( chartElement ,  "colorArea" );
        						String colorLine = XmlUtil.getElementAttrValue( chartElement ,  "colorLine" );
        						String colorPoint = XmlUtil.getElementAttrValue( chartElement ,  "colorPoint" );
        						String name = StringUtil.encodeQuotationString( XmlUtil.getElementAttrValue( chartElement ,  "name" ) );
        						String field = XmlUtil.getElementAttrValue( chartElement ,  "field" );
        						area.setColorLine( colorLine );
        						area.setColorArea( colorArea );
        						area.setColorPoint( colorPoint );
        						area.setField( field );
        						area.setName( name );
        						// 添加图形对象
        						areas.add( area );
        						// 添加折线颜色
        						colors.add( colorArea );
        						// 添加图例颜色
        						legends.add( name );
        					}
        				}
        			}
        		}
        	}
        }
	}

	/**
	 * 初始化数据
	 */
	public void initSeries() {
		resultJs.append( "           series: [ ");
		if( ObjectUtil.nonNull( areas ) ){
			int index = 0;
			for( Area area : areas ){
				resultJs.append( "           { ");
				resultJs.append( "              name:" + area.getName()  );
				resultJs.append( "              ,type:'line'"  );
				if( isStack ) resultJs.append( "              ,stack : '总量'"  );
				resultJs.append( "              ,data:"+ JsonUtil.toJson( chartData.get( area ) )  );
				resultJs.append( "              ,itemStyle:  {normal: {color:" + area.getColorPoint() + ",lineStyle:{color:" + area.getColorLine() + "}}}"  );
				if( isSmooth ){
					resultJs.append( "              ,smooth:true"  );
					resultJs.append( "              ,smoothMonotone:'x'"  );
				}
				if( !isStack ){
					resultJs.append( "              ,z:" + index );
					resultJs.append( "              ,zlevel:1"  );
				}
				resultJs.append( "           }, ");
				index++;
			}
		}
		resultJs.append( "           ] ");
	}
	
}
