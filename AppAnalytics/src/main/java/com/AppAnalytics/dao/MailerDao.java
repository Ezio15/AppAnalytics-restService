package com.AppAnalytics.dao;

import com.AppAnalytics.models.TblMail;

public interface MailerDao {

	void insertMailToken(TblMail mail);

	TblMail isTokenExists(String token);

	TblMail updateToken(TblMail mail);

	boolean updateActivationMail(TblMail mailer);

}
