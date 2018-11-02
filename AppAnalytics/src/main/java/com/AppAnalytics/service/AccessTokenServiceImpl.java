package com.AppAnalytics.service;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import com.AppAnalytics.controllers.UserController;
import com.AppAnalytics.models.TblOauthAccessTokens;
import com.AppAnalytics.models.TblUserCredentials;
import com.AppAnalytics.utils.Constants;
import com.AppAnalytics.utils.Utils;


public class AccessTokenServiceImpl implements AccessTokenService{

	private static final Logger logger = Logger.getLogger(AccessTokenServiceImpl.class);


	public TblOauthAccessTokens getAccessToken() {
		// TODO Auto-generated method stub
		return null;
	}

	public TblOauthAccessTokens generateAccessToken(TblUserCredentials userData) {
		TblOauthAccessTokens accessToken = new TblOauthAccessTokens();
		try {
			String clientId = "statementGames";
			String token = Utils.generateSHA256(userData.getUserName()+clientId);
			accessToken.setAccessToken(token);
			accessToken.setClientId(clientId);
			accessToken.setCredentialsId(userData.getCredentialsId());
			accessToken.setRecInsUsrid(userData.getUserId());
			Date targetTime = new Date();
			targetTime = DateUtils.addDays(targetTime, Constants.EXPIRATION_TIME);
			accessToken.setExpirationTime(targetTime);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("AccessTokenServiceImpl : Exception While Getting Generating AccessToken");
			
		}
		return accessToken;
	}

	public void insertAccessToken(TblOauthAccessTokens accessToken) {
		// TODO Auto-generated method stub
		
	}

	

}
