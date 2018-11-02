package com.AppAnalytics.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.AppAnalytics.models.TblDimAndMet;
import com.AppAnalytics.models.TblMetrics;
import com.AppAnalytics.utils.HibernateCfg;

public class DimensionsAndMetricsDaoImpl  extends HibernateDaoSupport implements DimensionsAndMetricsDao {

	@Autowired
	HibernateCfg hibernateCfgObj;
	
	

	public List<TblDimAndMet> getDimensionsAndMetrics() {
		/*Session session;
		Transaction tx = null ;
		List<TblDimAndMet> list = new ArrayList<TblDimAndMet>();
		try {
			session = hibernateCfgObj.cofig();
			tx = session.beginTransaction();
			 Query query = session.createQuery("SELECT dimension from tbl_dim_met_fields");
			 list = query.list();
			 tx.commit();
			
			System.out.println(list);
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		return list;
		*/ 
	
		List<TblDimAndMet> result = new ArrayList<TblDimAndMet>();
		try{
		DetachedCriteria cr = DetachedCriteria.forClass(TblDimAndMet.class);
		cr.setProjection(Projections.property("metrics"));
		//cr.add(Restrictions.eq("id",1 ));


		

		result = (List<TblDimAndMet>) getHibernateTemplate().findByCriteria(cr);
		System.out.println(result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
		}

	

	public boolean createDimentionAndMetrics(TblMetrics tblMetrics) {
		boolean result = false;
		try {
			getHibernateTemplate().save(tblMetrics);
			result = true;
		}catch (Exception e) {
			logger.error("Hibernate Dao:Exception Occurred while Inserting Metrics data into");
		e.printStackTrace();
		}
		return result;
	}


	}
