package com.springMvc.dao;


import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.util.ObjectUtil;

@Repository
public class HibernateDao {
	@Autowired  
	private SessionFactory sessionFactory;
	private Session getCurrentSession() {  
        return this.sessionFactory.getCurrentSession();  
    }
	
	public List selectAll(){
		SQLQuery query = getCurrentSession().createSQLQuery("SELECT * FROM WM_SYSTEM");
		query.addEntity( com.springMvc.bean.System.class );
		List list = query.list();
		return list;
	}
	
	/**
	 * 查询并获取List对象
	 */
	public List<?> queryToList( String sql , Object... parms ){
		SQLQuery query = getCurrentSession().createSQLQuery( sql );
		if( ObjectUtil.nonNull( parms ) ){
			for( int i = 0 ; i < parms.length ; i++ ){
				query.setParameter( i , parms[i] );
			}
		}
		return query.list();
	}
	
	/**
	 * 查询并获取List对象
	 */
	public List<?> queryToList( String sql , Map<String, Object> parms ){
		SQLQuery query = getCurrentSession().createSQLQuery( sql );
		if( ObjectUtil.nonNull( parms ) ){
			for( String parm : parms.keySet() ){
				query.setParameter( parm , parms.get( parm ) );
			}
		}
		return query.list();
	}
	
	/**
	 * 查询并获取List对象
	 */
	public List<?> queryToMap( String sql , Object... parms ){
		SQLQuery query = getCurrentSession().createSQLQuery( sql );
		if( ObjectUtil.nonNull( parms ) ){
			for( int i = 0 ; i < parms.length ; i++ ){
				query.setParameter( i , parms[i] );
			}
		}
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	/**
	 * 查询并获取Map对象
	 */
	public List<?> queryToMap( String sql , Map<String, Object> parms ){
		SQLQuery query = getCurrentSession().createSQLQuery( sql );
		if( ObjectUtil.nonNull( parms ) ){
			for( String parm : parms.keySet() ){
				query.setParameter( parm , parms.get( parm ) );
			}
		}
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
}
