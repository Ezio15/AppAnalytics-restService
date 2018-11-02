package com.AppAnalytics.dao;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.AppAnalytics.models.TblMail;

public class MailerDaoImpl extends HibernateDaoSupport implements MailerDao {

	public void insertMailToken(TblMail mail) {
		try {
			getHibernateTemplate().save(mail);
		} catch (Exception e) {
			logger.error("Dao Exception : While Saving Mail Token -" + e);
			e.printStackTrace();
		}
	}

	public TblMail isTokenExists(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public TblMail updateToken(TblMail mail) {
		try {
			getHibernateTemplate().update(mail);
		} catch (Exception e) {
			logger.error("Dao Exception : While Updating Mail Token -" + e);
		}
		return null;
	}

	public boolean updateActivationMail(TblMail mailer) {
		boolean result = false;
		try {
			getHibernateTemplate().update(mailer);
			result = true;
		} catch (Exception e) {
			logger.error("Dao Exception : While Updating Activation Mail TOken  -" + e);
		}
		return result;
	}

}
