package com.AppAnalytics.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.AppAnalytics.models.TblUserCredentials;
import com.AppAnalytics.models.TblUserProfile;






public class UserDaoImpl  extends HibernateDaoSupport  implements UserDao {

	public Boolean isEmailExists(String email)  {

		boolean result = false;
		try {
			DetachedCriteria cr = DetachedCriteria.forClass(TblUserCredentials.class);
			cr.setProjection(Projections.property("email"));
			cr.add(Restrictions.eq("email", email));
			List<TblUserCredentials> userList = (List<TblUserCredentials>) getHibernateTemplate().findByCriteria(cr);

			if (userList.isEmpty()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			logger.error("UserDaoImpl : Exception While Retrieving User By Email");
			
		}
		return result;
	}

	public Integer saveUser(TblUserProfile userProfile) {
		try {
			getHibernateTemplate().save(userProfile);
			return userProfile.getUserId();
		} catch (HibernateOptimisticLockingFailureException olfe) {
			logger.error("UserProfileDaoImpl : Exception While Inserting User Records in User Profile Table",olfe);
			
		} catch (Exception e) {
			logger.error("UserProfileDaoImpl : Exception While Inserting User Records in User Profile Table",e);
			
		}
		return userProfile.getUserId();
	}

	public void updateUser(TblUserProfile userProfile) {
		try {
			getHibernateTemplate().update(userProfile);
		} catch (HibernateOptimisticLockingFailureException olfe) {
			logger.error("UserDaoImpl : Exception While Updating User Records in UserCredentials Table");
			
		} catch (Exception e) {
			logger.error("UserDaoImpl : Exception While Updating User Records in UserCredentials Table");
			
		}

	}

	public boolean registerUser(TblUserCredentials userCredentials) {
		try {
			getHibernateTemplate().save(userCredentials);
		} catch (HibernateOptimisticLockingFailureException olfe) {
			logger.error("UserDaoImpl : Exception While Inserting User Records",olfe);
			
		} catch (Exception e) {
			logger.error("UserDaoImpl : Exception While Inserting User Records",e);
		
		}
		return true;
	}

	public TblUserCredentials authUser(TblUserCredentials userCredentials) {
		List<TblUserCredentials> userList = null;
		try {
			DetachedCriteria cr = DetachedCriteria.forClass(TblUserCredentials.class);
			cr.add(Restrictions.or(Restrictions.eq("userName", userCredentials.getUserName()),
					Restrictions.eq("email", userCredentials.getUserName())));

		 userList = (List<TblUserCredentials>) getHibernateTemplate().findByCriteria(cr);
			if (userList.isEmpty()) {
				return null;
			} else {
				return userList.get(0);
			}
		} catch (Exception e) {
			logger.error("UserDaoImpl : Exception While Retrieving User By UserName");
			
		}
		return userList.get(0);
	}

}
