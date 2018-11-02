package com.AppAnalytics.service;

import com.AppAnalytics.models.TblOauthAccessTokens;
import com.AppAnalytics.models.TblUserCredentials;

public interface AccessTokenService {

	TblOauthAccessTokens getAccessToken();

	TblOauthAccessTokens generateAccessToken(TblUserCredentials userData);

	void insertAccessToken(TblOauthAccessTokens accessToken);

}
