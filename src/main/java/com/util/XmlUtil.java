package com.util;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil {
	 public static Log log = LogFactory.getLog( XmlUtil.class );
	 /**
	  * 解析XML
	  * @param ips
	  */
	 public static void parserXml( InputStream ips ) {
	        SAXReader saxReader = new SAXReader();
	        try {
	            Document document = saxReader.read(ips);
	            List<Element> elements = document.getRootElement().elements();
	            for( Element element : elements ){
	            	
	            }
	        } catch (DocumentException e) {
	        	log.error( e.getStackTrace() );
	        }
	 }
	 
	 /**
	  * 获取XML元素属性
	  */
	 public static String getElementAttrValue( Element element , String key ){
		 return StringUtil.empty( element.attributeValue( key ) );
	 }
}
