package com.common.chart.parent;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.chart.bean.Legend;
import com.common.chart.bean.Title;
import com.common.chart.bean.ToolTip;
import com.common.chart.bean.chart.Chart;
import com.common.chart.inter.JavaScriptBuilder;
import com.exception.MyExpection;
import com.springMvc.dao.HibernateDao;
import com.util.JsonUtil;
import com.util.ObjectUtil;
import com.util.StringUtil;
import com.util.XmlUtil;

@Component
public abstract class ChartsAnalysis implements JavaScriptBuilder{
	public static Log log = LogFactory.getLog( ChartsAnalysis.class );
	// 配置文件对象
	protected Document document;
	// 数据库操作对象,用于获取数据
	@Autowired
	protected HibernateDao hibernateDao;
	// 生成图形的数据
	protected Map<Object,List<Object>> chartData;
	// 解析之后生成的最终脚本
	protected StringBuffer resultJs;
	// 图形ID
    protected String id;
    // 图形高度
    protected String height;
    // 图形宽度
    protected String width;
    // 数据源Json
    protected String dataSource;
    // 标题
    protected String title;
    // 标题样式
    protected String titleStyle;
    
    protected ChartsAnalysis(){
    	chartData = new HashMap<Object, List<Object>>();
    	resultJs = new StringBuffer();
    }

    //  解析文件配置
    protected void analysisConfiguration( InputStream ips ){
    	SAXReader saxReader = new SAXReader();
        try {
        	// 获取配置文件
        	document = saxReader.read( ips );
            // 获取根路径元素
            Element rootElement = document.getRootElement();
            // 解析根路径元素
            rootElementAnalysis( rootElement );
            
            List<Element> elements = document.getRootElement().elements();
            for( Element element : elements ){
            	if( "titleStyle".equals( element.getName() ) ) titleStyleAnalysis( element );
            	if( "data".equals( element.getName() ) ){
            		for( Element dataElement : element.elements() ){
            			if( "dataSource".equals( dataElement.getName() ) ) dataSourceAnalysis( dataElement ) ;
            		}
            	}
            }
        } catch ( DocumentException e ) {
        	log.error( e.getStackTrace() );
        	e.printStackTrace();
        }
    }
    
    /**
     * 解析根目录属性
     */
    private void rootElementAnalysis( Element rootElement ){
    	this.id = XmlUtil.getElementAttrValue( rootElement , "id" );
    	this.title = StringUtil.encodeJavascriptString( XmlUtil.getElementAttrValue( rootElement , "title" ) );
        if( StringUtil.isBlank( this.id ) ) throw new MyExpection("缺少图形id的定义");
    }

    /**
     * 初始化数据源
     */
    protected abstract void initDataSource( List< ? extends Chart > chartList , Object... parms );
    
    /**
     * 生成图形
     */
    protected abstract String createChart( HttpServletRequest request , Object... parms );
    
    /**
	 * 初始化头部脚本
	 * @param 图形父ID
	 */
	public void initHeadJavaScript( String id ){
		resultJs.append( " <script> " );
		resultJs.append( "   $(function(){" );
		resultJs.append( " 		var myChart = echarts.init( document.getElementById('"+id+"') ); " );
		resultJs.append( "  	var option = { " );
	}
	
	/**
	 * 初始化标题部分脚本
	 * @param title
	 */
	public void initTitleJavaScript( Title title ){
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
	public void initColorJavaScript( List<String> colors ){
		String colorStr = JsonUtil.toJson( colors );
		resultJs.append( "     		 color:"+colorStr+", ");
	}
    
	/**
	 * 初始化悬浮提示部分脚本
	 */
	public void initToolTipJavaScript( ToolTip toolTip ){
		resultJs.append( "     		 tooltip : { trigger:"+toolTip.getTrigger()+"}, " );
	}
	
	/**
	 * 初始化图例部分脚本
	 */
	public void initLegendJavaScript( Legend legend ){
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
	 * 初始化无数据时的效果
	 */
	public void initNoDataEffect() {
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
	 * 初始底部脚本
	 */
	public void initFootJavaScript(){
		resultJs.append( "		 }; ");
		resultJs.append( " 		myChart.setOption( option ); ");
		resultJs.append( " })" );
		resultJs.append( " </script>" );
	}
	/**
     * 解析图形标题
     */
    private void titleStyleAnalysis( Element titleStyle ){
    	String color = StringUtil.encodeQuotationString( XmlUtil.getElementAttrValue( titleStyle , "color" ) );
    	String fontStyle = StringUtil.encodeQuotationString( XmlUtil.getElementAttrValue( titleStyle , "fontStyle" ) );
    	String fontWeight = XmlUtil.getElementAttrValue( titleStyle , "fontWeight" );
    	String fontFamily = StringUtil.encodeQuotationString( XmlUtil.getElementAttrValue( titleStyle , "fontFamily" ) );
    	String fontSize = XmlUtil.getElementAttrValue( titleStyle , "fontSize" );
    	this.titleStyle = "{"
    					+ "color:"+color
    					+ ",fontStyle:"+fontStyle
    					+ ",fontWeight:"+fontWeight
    					+ ",fontFamily:"+fontFamily
    					+ ",fontSize:"+fontSize
    					+ "}";
    }
    
    /**
     * 解析数据源
     */
    private void dataSourceAnalysis( Element dataSource ){
    	this.dataSource = dataSource.getText();
    }

}
