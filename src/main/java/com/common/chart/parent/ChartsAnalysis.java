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

import com.exception.MyExpection;
import com.springMvc.dao.HibernateDao;
import com.util.StringUtil;
import com.util.XmlUtil;

@Component
public abstract class ChartsAnalysis {
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
     * 生成图形
     */
    protected abstract String createChart( HttpServletRequest request , Object... parms );
    
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
