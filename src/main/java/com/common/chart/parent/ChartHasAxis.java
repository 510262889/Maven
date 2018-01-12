package com.common.chart.parent;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.common.chart.bean.AxisLine;
import com.common.chart.bean.Grid;
import com.common.chart.bean.axis.Axis;
import com.common.chart.bean.axis.XAxis;
import com.common.chart.bean.axis.YAxis;
import com.common.chart.bean.axislabel.AxisLabel;
import com.common.chart.bean.chart.Chart;
import com.common.chart.inter.*;
import com.util.JsonUtil;
import com.util.StringUtil;
import com.util.XmlUtil;

/**
 * 带有坐标轴的图形
 */
@Component
public abstract class ChartHasAxis extends ChartsAnalysis {
	// 轴线刻度精度
	protected double accuracy = Math.pow( 10 , 4 );
	// 数据源
	protected List<Map> listData = new ArrayList<Map>();
    // 横轴单位
	protected String xUnit;
	 // Y轴单位
	protected String yUnit;
	// Y横轴数据
	protected List<Object> yDatas;
    // X横轴数据字段
	protected String xScale;
	// Y横轴数据字段
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
	protected String minY;
    // 横轴起点值,设置之后横轴最大值自动设置为数据中的最大值
	protected String minX;
	// 纵轴最大值
	protected String maxY;
	// 横轴最大值
	protected String maxX;
    // 横轴刻度间距
	protected double interval;
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
		this.maxX = Constant.UNDEFINED;
        this.maxY = Constant.UNDEFINED;
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
        this.changeXY = Boolean.parseBoolean( XmlUtil.getElementAttrValue( rootElement , "changeXY" ) );
        this.minY = "".equals( XmlUtil.getElementAttrValue( rootElement , "minY" ) ) ? Constant.UNDEFINED : XmlUtil.getElementAttrValue( rootElement , "minY" );
        this.minX = "".equals( XmlUtil.getElementAttrValue( rootElement , "minX" ) ) ? Constant.UNDEFINED : XmlUtil.getElementAttrValue( rootElement , "minX" );
        this.interval = "".equals( XmlUtil.getElementAttrValue( rootElement , "interval" ) ) ? 0 : Integer.parseInt( XmlUtil.getElementAttrValue( rootElement , "interval" ) );
        this.rotate = XmlUtil.getElementAttrValue( rootElement , "rotate" );
        this.xMargin = "".equals( XmlUtil.getElementAttrValue( rootElement , "xMargin" ) ) ? 0 : Double.parseDouble( XmlUtil.getElementAttrValue( rootElement , "xMargin" ) );
        this.xScaleWidth = "".equals( XmlUtil.getElementAttrValue( rootElement , "xScaleWidth" ) ) ? 0 : Double.parseDouble( XmlUtil.getElementAttrValue( rootElement , "xScaleWidth" ) );
        this.yScaleWidth = "".equals( XmlUtil.getElementAttrValue( rootElement , "yScaleWidth" ) ) ? 0 : Double.parseDouble( XmlUtil.getElementAttrValue( rootElement , "yScaleWidth" ) );
        this.width = StringUtil.encodeQuotationString( XmlUtil.getElementAttrValue( rootElement , "width" ) );
        this.height = StringUtil.encodeQuotationString( XmlUtil.getElementAttrValue( rootElement , "height" ) );
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void initDataSource( List< ? extends Chart > chartList , Object... parms ){
		// 获取到的数据
		listData = ( List<Map> ) hibernateDao.queryToMap( this.dataSource, parms );
		// 横轴数据
		List<Object> listXScale = new ArrayList<Object>();
		// 纵轴数据
		List<Object> listYScale = new ArrayList<Object>();
		// 最大Y值和最大X值只在X或是Y为数值类型时可以计算
		boolean xOk = true;
		boolean yok = true;
		Double maxY = 0.0;
		Double maxX = 0.0;
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
		if( xOk && this.autoXScale ){
			Map<String, String> mapX = scaleAdjust( Double.parseDouble( this.minX ) , maxX );
			this.maxX = mapX.get( "max" );
			this.minX = mapX.get( "min" );
		}else{
			this.maxX = Constant.UNDEFINED;
			this.minX = Constant.UNDEFINED;
		}
		
		if( yok && this.autoYScale ){
			Map<String, String> mapY = scaleAdjust( Double.parseDouble( this.minY ) , maxY );
			this.maxY = mapY.get( "max" );
			this.minY = mapY.get( "min" );
		}else {
			this.maxY = Constant.UNDEFINED;
			this.minY = Constant.UNDEFINED;
		}
		
		for( Chart chart : chartList ){
			chartData.put( chart , new ArrayList<Object>() );
			for (Map map : listData ) {
				chartData.get( chart ).add(  map.get( chart.getField() ) + "" );
			}
		}
		this.xDatas = listXScale;
		this.yDatas = listYScale;
		
	}
	
	/**
	 * 初始化图形位置部分脚本
	 */
	public void initGridJavaScript( Grid grid ){
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
	public void initAxisJavaScript( Axis axis ){
		String data = JsonUtil.toJson( axis.getDatas() );
		if( axis instanceof XAxis ){
			XAxis xAxis = ( XAxis ) axis;
			xAxis.setUnit( xUnit );
			AxisLabel axisLabel = xAxis.getAxisLabel();
			AxisLine axisLine = xAxis.getAxisLine();
 			if( this.changeXY ) resultJs.append( "           yAxis : [ ");
 			else resultJs.append( "           xAxis : [ ");
			resultJs.append( "   				{");
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
			
			if( this.changeXY ) resultJs.append( "           xAxis : [ ");
 			else resultJs.append( "           yAxis : [ ");
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
     * 调整刻度
     * @param vMin 数据中的最小值
     * @param vMax 数据中的最大值
     */
	protected Map<String,String> scaleAdjust( double vMin , double vMax ){
		Map<String, String> map = new HashMap<String, String>();
        /*差值除以10，作为刻度差值的参考值*/
		double vDiff = Math.abs(vMax-vMin)/10;
		double vStep = 10;
        if( vDiff > 0 ){
            vStep = 0.0001;
            while( vStep < vDiff ) vStep *=10;
            if( vDiff < vStep/2 ) vStep *=0.5;
        }
        /*调整最大值、最小值*/
        vMax = vMax - (vMax % vStep) + vStep;
        vMin = vMin - (vMin % vStep);
        /*格式化*/
        vMax = Math.round( vMax * 10000 ) / 10000;
        vMin = Math.round( vMin * 10000 ) / 10000;
        map.put( "max" , vMax + "" );
        map.put( "min" , vMin + "" ); 
        return map;
    }
}
