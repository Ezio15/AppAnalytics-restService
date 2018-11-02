/**
 * 
 */
package com.AppAnalytics.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.AppAnalytics.dao.MailerDao;
import com.AppAnalytics.models.TblMail;

/**
 * @author Vignesh
 *
 */
public class MailerServiceImpl implements MailerService {

	@Autowired
	private MailerDao mailerDao;
	
	@Autowired
	private JavaMailSender mailSender1;

	private final static Logger logger = Logger.getLogger(MailerServiceImpl.class);

	public boolean insertMailToken(TblMail mail)  {
		try {
			mailerDao.insertMailToken(mail);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MailerServiceImpl : Exception While Inserting Activation EMail");
			
		}
		return true;
	}

	public TblMail getUserMailByToken(String token) {

		TblMail mail = null;
		try {
			mail = mailerDao.isTokenExists(token);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("MailerServiceImpl : Exception While Retrieving UserMail By Token");
			
		}
		return mail;
	}

	public boolean updateMailToken(TblMail mail)  {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			TblMail mailer = mailerDao.updateToken(mail);

			mailer.setExpirationDate(mail.getExpirationDate());
			mailer.setToken(mail.getToken());
			mailer.setIsLatest(mail.isIsLatest());

			Timestamp date = new Timestamp(new Date().getTime());
			mailer.setRecLastUpdDt(date);
			mailer.setRecLastUpdUsrid(mailer.getRecInsUsrid());
			mailer.setRecInsUsrid(mail.getRecInsUsrid());

			result = updateExtendingVerification(mailer);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MailerServiceImpl : Exception While Retrieving UserMail By Token");
			
		}
		return result;
	}

	private boolean updateExtendingVerification(TblMail mailer)  {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = mailerDao.updateActivationMail(mailer);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MailerServiceImpl : Exception While updating Mail Expiration Date");
		
		}
		return result;
	}


}
