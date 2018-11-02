package com.AppAnalytics.dao;

import com.AppAnalytics.models.TblUserCredentials;
import com.AppAnalytics.models.TblUserProfile;

public interface UserDao {

	Boolean isEmailExists(String email);

	Integer saveUser(TblUserProfile userProfile);

	void updateUser(TblUserProfile userProfile);

	boolean registerUser(TblUserCredentials userCredentials);

	TblUserCredentials authUser(TblUserCredentials userCredentials);

}
