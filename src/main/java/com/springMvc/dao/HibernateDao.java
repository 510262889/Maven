package com.springMvc.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDao {
	@Autowired  
	private SessionFactory sessionFactory;
	private Session getCurrentSession() {  
        return this.sessionFactory.getCurrentSession();  
    }
	
	public void selectAll(){
		SQLQuery query = getCurrentSession().createSQLQuery("SELECT * FROM WM_SYSTEM");
		query.addEntity( com.springMvc.bean.System.class );
		List list = query.list();
		
		System.out.println(list.size());
	}
}
