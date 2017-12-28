package com.util;

/**
 * 对象处理工具类
 * @author ykzhu
 */
public class ObjectUtil {
	/**
	 * 判断对象是否非空
	 * @param object
	 * @return
	 */
	public static boolean nonNull( Object object ){
		if( object == null ) return false; return true;
	}
	
	/**
	 * 判断对象是否为空
	 * @param object
	 * @return
	 */
	public static boolean isNull( Object object ){
		if( object == null ) return true; return false;
	}
}
