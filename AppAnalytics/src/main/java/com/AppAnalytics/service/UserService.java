package com.AppAnalytics.service;

import com.AppAnalytics.models.TblMail;
import com.AppAnalytics.models.TblUserCredentials;

public interface UserService {

	TblUserCredentials authUser(TblUserCredentials userCredentials);

	boolean isEmailExists(String email);

	boolean registerUser(TblUserCredentials userCredentials);

	void updateLink(TblMail mail);

}
