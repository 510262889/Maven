package com.common.chart;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.common.chart.bean.axis.*;
import com.common.chart.bean.axislabel.AxisLabel;
import com.common.chart.bean.axislabel.XAxisLabel;
import com.common.chart.bean.axislabel.YAxisLabel;
import com.common.chart.bean.AxisLine;
import com.common.chart.bean.Constant;
import com.common.chart.bean.Grid;
import com.common.chart.bean.Legend;
import com.common.chart.bean.Title;
import com.common.chart.bean.ToolTip;
import com.common.chart.bean.chart.Area;
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
		initDataSource( parms );
		
		// 初始化头部脚本
		initHeadJavaScript( this.id );
		
		// 实例标题对象
		Title title = new Title();
		title.setText( this.title );
		title.setTextStyle( this.titleStyle );
		// 初始化标题部分脚本
		initTitleJavaScript( title );
		
		// 初始化颜色部分脚本
		initColorJavaScript();
		
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
        						String name = StringUtil.encodeQuotationString( XmlUtil.getElementAttrValue( chartElement ,  "name" ) );
        						String field = XmlUtil.getElementAttrValue( chartElement ,  "field" );
        						area.setColorLine( colorLine );
        						area.setColorArea( colorArea );
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initDataSource( Object... parms ){
		// 获取到的数据
		List<Map> listData = ( List<Map> ) hibernateDao.queryToMap( this.dataSource, parms );
		// 横轴数据
		List<Object> listXScale = new ArrayList<Object>();
		// 纵轴数据
		List<Object> listYScale = new ArrayList<Object>();
		// 最大Y值和最大X值只在X或是Y为数值类型时可以计算
		boolean xOk = true;
		boolean yok = true;
		Double maxY = 0.0;
		Double maxX = 0.0;
		Double minY = 0.0;
		Double minX = 0.0;
		int index = 0;
		for (Map map : listData ) {
			// Object tempXScale = map.get( xScale );
			listXScale.add( map.get( xScale ) );
			listYScale.add( ( String ) map.get( yScale ) );
			// 计算最大Y值
			try {
				double currentY = Double.parseDouble( ( String )map.get( yScale ) );
				if( index == 0 ) {
					maxY = currentY;
				}else{
					if( maxY < currentY ) maxY = currentY;
				}
			} catch (Exception e) {
				yok = false;
			}
			
			// 计算最大X值
			try {
				double currentX = Double.parseDouble( ( String )map.get( xScale ) );
				if( index == 0 ) {
					maxX = currentX;
				}else{
					if( maxX < currentX ) maxX = currentX;
				}
			} catch (Exception e) {
				xOk = false;
			}
						
			index++;
		}
		// 计算X和Y的数据范围
		if( xOk ){
			Map<String, String> mapX = scaleAdjust( Double.parseDouble( this.minX ) , maxX );
			this.maxX = mapX.get( "max" );
			this.minX = mapX.get( "min" );
		}else{
			this.maxX = Constant.UNDEFINED;
			this.minX = Constant.UNDEFINED;
		}
		
		if( yok ){
			Map<String, String> mapY = scaleAdjust( Double.parseDouble( this.minY ) , maxY );
			this.maxY = mapY.get( "max" );
			this.minY = mapY.get( "min" );
		}else {
			this.maxY = Constant.UNDEFINED;
			this.minY = Constant.UNDEFINED;
		}
		
		for( Area area : areas ){
			chartData.put( area , new ArrayList<Object>() );
			for (Map map : listData ) {
				chartData.get( area ).add(  map.get( area.getField() ) + "" );
			}
		}
		this.xDatas = listXScale;
		this.yDatas = listYScale;
		
	}
	
	/**
	 * 初始化头部脚本
	 * @param 图形父ID
	 */
	private void initHeadJavaScript( String id ){
		resultJs.append( " <script> " );
		resultJs.append( "   $(function(){" );
		resultJs.append( " 		var myChart = echarts.init( document.getElementById('"+id+"') ); " );
		resultJs.append( "  	var option = { " );
	}
	
	/**
	 * 初始化标题部分脚本
	 * @param title
	 */
	private void initTitleJavaScript( Title title ){
		resultJs.append( "     		 title: { ");
		resultJs.append( "      		   text: '" + title.getText() + "', " );
		resultJs.append( "       		   x : " + title.getX() + ", " );
		resultJs.append( "       		   y : " + title.getY() + ", " );
		resultJs.append( "       		   textStyle: " + title.getTextStyle() + " " );
		resultJs.append( "     		 }, ");
	}
	
	/**
	 * 初始化颜色部分脚本
	 */
	private void initColorJavaScript(){
		String colorStr = JsonUtil.toJson( colors );
		resultJs.append( "     		 color:"+colorStr+", ");
	}
	
	/**
	 * 初始化悬浮提示部分脚本
	 */
	private void initToolTipJavaScript( ToolTip toolTip ){
		resultJs.append( "     		 tooltip : { trigger:"+toolTip.getTrigger()+"}, " );
	}
	
	/**
	 * 初始化图例部分脚本
	 */
	private void initLegendJavaScript( Legend legend ){
		String legendStr = "[";
		if( ObjectUtil.nonNull( legend.getData() ) ){
			for( int a = 0 ; a < legend.getData().size() ; a++ ){
				String currentColor = legend.getData().get( a );
				if( a == 0 ) legendStr += currentColor;
				else legendStr += "," + currentColor;
			}
		}
		legendStr += "]";
		resultJs.append( "     		 legend: { " );
		resultJs.append( "     	   		  y:"+legend.getY()+", " );
		resultJs.append( "       	      data: "+legendStr+" " );
		resultJs.append( "      	 }, " );
	}
	
	/**
	 * 初始化图形位置部分脚本
	 */
	private void initGridJavaScript( Grid grid ){
		resultJs.append( "     		 grid: { ");
		resultJs.append( "        		  y:'"+grid.getY()+"', ");
		resultJs.append( "        		  x: '"+grid.getX()+"', ");
		resultJs.append( "        		  x2: '"+grid.getX2()+"', ");
		resultJs.append( "          	  y2: '"+grid.getY2()+"', ");
		resultJs.append( "          	  width:"+grid.getWidth()+", ");
		resultJs.append( "                height:"+grid.getHeigth()+", ");
		resultJs.append( "                containLabel: "+grid.isContainLabel()+" ");
		resultJs.append( "           }, ");
	}
	
	/**
	 * 初始化轴线数据
	 */
	private void initAxisJavaScript( Axis axis ){
		String data = JsonUtil.toJson( axis.getDatas() );
		if( axis instanceof XAxis ){
			XAxis xAxis = ( XAxis ) axis;
			xAxis.setUnit( xUnit );
			AxisLabel axisLabel = xAxis.getAxisLabel();
			AxisLine axisLine = xAxis.getAxisLine();
 			resultJs.append( "           xAxis : [{ ");
			resultJs.append( "                     type : '" + xAxis.getType() + "',");
			resultJs.append( "                     boundaryGap : " + xAxis.getBoundaryGap() + ",");
			resultJs.append( "                     min : "+xAxis.getMin()+",");
			resultJs.append( "                     max : "+xAxis.getMax()+",");
			resultJs.append( "                     data : "+data+",");
			resultJs.append( "                     name : "+xAxis.getUnit()+",");
			resultJs.append( "                     axisLabel : {show:"+axisLabel.isShow()+",interval:"+axisLabel.getInterval()+" , margin:"+axisLabel.getMargin()+",rotate:"+axisLabel.getRotate()+"},");
			resultJs.append( "                     axisLine:{");
			resultJs.append( "                     			  show:"+axisLine.isShow()+",");
			resultJs.append( "                     			  lineStyle:{");
			resultJs.append( "                     			    color:'"+axisLine.getLineStyle().getColor()+"',");
			resultJs.append( "                     			    width:"+axisLine.getLineStyle().getScaleWidth());
			resultJs.append( "                     			  }");
			resultJs.append( "                     			}");
			resultJs.append( "                    }],");
			return;
		}
		
		if( axis instanceof YAxis ){
			YAxis yAxis = ( YAxis ) axis;
			yAxis.setUnit( yUnit );
			AxisLabel axisLabel = yAxis.getAxisLabel();
			AxisLine axisLine = yAxis.getAxisLine();
			
			resultJs.append( "yAxis:[");
			resultJs.append( "    {");
			resultJs.append( "        type : '"+yAxis.getType()+"',");
			resultJs.append( "        boundaryGap : "+yAxis.getBoundaryGap()+",");
			resultJs.append( "        min : "+yAxis.getMin()+",");
			resultJs.append( "        max : "+yAxis.getMax()+",");
			resultJs.append( "        name : "+yAxis.getUnit()+",");
			resultJs.append( "        axisLabel : {show:"+axisLabel.isShow()+",formatter:"+axisLabel.getFormatter()+"},");
			resultJs.append( "        axisLine:{");
			resultJs.append( "            show:"+axisLine.isShow()+",");
			resultJs.append( "            lineStyle:{");
			resultJs.append( "                color:'"+axisLine.getLineStyle().getColor()+"',");
			resultJs.append( "                width:"+axisLine.getLineStyle().getScaleWidth()+"");
			resultJs.append( "            }");
			resultJs.append( "        }");
			resultJs.append( "    }");
			resultJs.append( "],");
			return;
		}
	}
	
	/**
	 * 初始化无数据时的效果
	 */
	private void initNoDataEffect() {
		resultJs.append( "           noDataLoadingOption: { ");
		resultJs.append( "         			  text: '暂无数据', ");
		resultJs.append( "           		  effect: 'bubble', ");
		resultJs.append( "           		  effectOption: { ");
		resultJs.append( "             			     effect: { ");
		resultJs.append( "                  	     		n: 0 ");
		resultJs.append( "          	   			   } ");
		resultJs.append( "       	 		  } ");
		resultJs.append( "      	  }, ");
	}
	
	/**
	 * 初始化数据
	 */
	private void initSeries() {
		resultJs.append( "           series: [ ");
		if( ObjectUtil.nonNull( areas ) ){
			int index = 0;
			for( Area area : areas ){
				resultJs.append( "           { ");
				resultJs.append( "              name:" + area.getName()  );
				resultJs.append( "              ,type:'line'"  );
				resultJs.append( "              ,data:"+ JsonUtil.toJson( chartData.get( area ) )  );
				resultJs.append( "              ,itemStyle:  {normal: {color:" + area.getColorPoint() + ",lineStyle:{color:" + area.getColorLine() + "}}}"  );
				resultJs.append( "              ,smooth:true"  );
				resultJs.append( "              ,smoothMonotone:'x'"  );
				resultJs.append( "              ,z:" + index );
				resultJs.append( "              ,zlevel:1"  );
				resultJs.append( "           }, ");
				index++;
			}
		}
		resultJs.append( "           ] ");
	}
	
	/**
	 * 初始底部脚本
	 */
	private void initFootJavaScript(){
		resultJs.append( "		 }; ");
		resultJs.append( " 		myChart.setOption( option ); ");
		resultJs.append( " })" );
		resultJs.append( " </script>" );
	}
}
