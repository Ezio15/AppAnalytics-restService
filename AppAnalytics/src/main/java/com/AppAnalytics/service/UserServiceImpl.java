package com.AppAnalytics.service;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.AppAnalytics.Mailer.SendMail;
import com.AppAnalytics.dao.MailerDao;
import com.AppAnalytics.dao.UserDao;
import com.AppAnalytics.models.TblMail;
import com.AppAnalytics.models.TblUserCredentials;
import com.AppAnalytics.models.TblUserProfile;
import com.AppAnalytics.utils.Constants;
import com.AppAnalytics.utils.HashGenerator;



public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDaoImpl;
	
	@Autowired
	private MailerDao mailDaoImpl;

	@Autowired
	private MailerService mailServiceImpl;

	@Autowired
	private JavaMailSender mailSender;

	Logger logger = Logger.getLogger(UserServiceImpl.class);

	public TblUserCredentials authUser(TblUserCredentials userCredentials) {
		TblUserCredentials userData = null;
		try {
			userData = userDaoImpl.authUser(userCredentials);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("UserServiceImpl : Exception While Authentication User By Email or User Name");
			
		}
		return userData;
	}

	public boolean isEmailExists(String email) {

		Boolean result = null;
		try {
			result = userDaoImpl.isEmailExists(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean registerUser(TblUserCredentials userCredentials) {

	try {	
		TblUserProfile userProfile = new TblUserProfile();
		userProfile.setFirstName(userCredentials.getFirstName());
		userProfile.setLastName(userCredentials.getLastName());
		userProfile.setGender(userCredentials.getGender());
		// Checking isAdmin in the Payload and Assigning Roles for the User's.
		/*
		 * if(userCredentials.getIsAdmin()) {
		 * userProfile.setRoleId(Constants.Roles.ADMIN); } else{
		 * userProfile.setRoleId(Constants.Roles.END_USER); }
		 */

		Integer userProfileId = userDaoImpl.saveUser(userProfile);

		userProfile.setRecInsUsrid(userProfileId);
		userDaoImpl.updateUser(userProfile);

		HashGenerator hashGenerator = new HashGenerator();
		userCredentials.setPassword(hashGenerator.hashpassword(userCredentials.getPassword()));
		userCredentials.setSalt(hashGenerator.getSalt());
		userCredentials.setUserId(userProfileId);
		userCredentials.setRecInsUsrid(userProfileId);
		/*userCredentials.setIsActive(true);
		userCredentials.setEmailVerified(false);
*/
		boolean result = userDaoImpl.registerUser(userCredentials);
		logger.info("User Registered Successfully. . ! Sending Email...");

		// Sending Email to User for Email Verification..
		SendMail sendMail = new SendMail(mailSender);
		sendMail.emailConfirmation(userCredentials);

		// Inserting Email Token into Database
		Timestamp expirationDate = new Timestamp(
				new Date().getTime() + Constants.MAIL_CONSTANTS.RESET_PERIOD * 24 * 60 * 60 * 1000);
		logger.info("Token====" + sendMail.getToken() + "Email" + userCredentials.getEmail());
		logger.info("expirationDate" + expirationDate + "userId" + userCredentials.getUserId());

		TblMail mail = new TblMail(sendMail.getToken(), userCredentials.getEmail(), true, expirationDate,
				userCredentials.getUserId());
		mail.setRecInsDt(new Date());
		return mailServiceImpl.insertMailToken(mail);
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return true;
	}

	/*
	 * This method is used to Activate the User by Checking the Token
	 */
	public void updateLink(TblMail mail)  {
		try {
			// Updating isLatest Column in Mail Table
			mail.setIsLatest(false);
			mailDaoImpl.updateActivationMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("UserServiceImpl : Exception While Updating Token in Email Table");
			
		}
	}
}
