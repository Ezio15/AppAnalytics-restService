package com.AppAnalytics.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.AppAnalytics.models.TblMail;
import com.AppAnalytics.models.TblOauthAccessTokens;
import com.AppAnalytics.models.TblUserCredentials;
import com.AppAnalytics.service.AccessTokenService;
import com.AppAnalytics.service.MailerService;
import com.AppAnalytics.service.UserService;
import com.AppAnalytics.utils.Constants;
import com.AppAnalytics.utils.HashGenerator;



@RestController
@RequestMapping("/api/user")
public class UserController {
	
	
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userServiceObj;
	
	@Autowired
	private AccessTokenService accessTokenServiceObj;
	
	@Autowired
	private MailerService mailSeriviceObj;

	@RequestMapping(value = "/auth", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> authUser(@RequestBody TblUserCredentials userCredentials) {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			// Recaptcha code
			//String gRecaptchaResponse = userCredentials.getgRecaptchaResponse();
		//	boolean verify = userService.verify(gRecaptchaResponse);
			//if (verify) {
				if (userCredentials.getUserName() != null && userCredentials.getPassword() != null) {
					userCredentials.setEmail(userCredentials.getUserName());
					TblUserCredentials userData = userServiceObj.authUser(userCredentials);
					if (userData != null) {
						if (userData.isIsActive()) {
							HashGenerator hashGenerator = new HashGenerator();
							if (hashGenerator.hashpassword(userCredentials.getPassword(), userData.getSalt())
									.equals(userData.getPassword())) {
								if (userData.isEmailVerified()) {
									logger.info("User " + userCredentials.getUserName() + " is logged in Successfully");

									TblOauthAccessTokens accessToken = accessTokenServiceObj.generateAccessToken(userData);
									accessTokenServiceObj.insertAccessToken(accessToken);

									responseMap.put(Constants.MESSAGE, Constants.SUCCESS);
									responseMap.put(Constants.ACCESS_TOKEN, accessToken.getAccessToken());
									responseMap.put("email", userData.getEmail());
								} else {
									logger.info("Email Verification Pending..!");
									responseMap.put(Constants.MESSAGE,
											Constants.USER_SERVICE_MSG.EMAIL_VERIFY_PENDING);
								}
							} else {
								logger.info("Password Incorrect");
								responseMap.put(Constants.MESSAGE,
										Constants.USER_SERVICE_MSG.PASSWORD_INCORRECT);
							}
						} else {
							logger.info("Username is In Active");
							responseMap.put(Constants.MESSAGE, Constants.USER_SERVICE_MSG.IN_ACTIVE_USER);
						}
					} else {
						logger.info("Username or Email Not Exists");
						responseMap.put(Constants.MESSAGE, Constants.USER_SERVICE_MSG.INCORRECT_UN_EMAIL);
					}
				} else {
					logger.info("Username or Email Not Exists");
					responseMap.put(Constants.MESSAGE,Constants.USER_SERVICE_MSG.INFO_MISSING);
				}
			/*} else {
				logger.info("Recaptcha Informaion Missing");
				responseMap.put(Constants.STATUS_CODE, Constants.Response.RECAPTCHA_MISSING);
				responseMap.put(Constants.MESSAGE, props.getMessage(Constants.Response.RECAPTCHA_MISSING));
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.put(Constants.MESSAGE, e.toString().split(":")[1]);
		}
		return responseMap;
	}

	/*
	 * This API is used to SignUp new User
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> registerUser(@RequestBody TblUserCredentials userCredentials) {

		Map<String, Object> responseMap = new HashMap<String,Object>();
		try {
logger.info("hii");
			if (userCredentials.getUserName() != null && userCredentials.getEmail() != null
					&& userCredentials.getPassword() != null && userCredentials.getFirstName() != null
					&& userCredentials.getLastName() != null && userCredentials.isTermsOfUse() != null
					&& userCredentials.getIsAdmin() != null) {
				if (userCredentials.getUserName().length() >= Constants.USERNAME_LEN) {
					if (!userServiceObj.isEmailExists(userCredentials.getEmail())) {
						boolean result = userServiceObj.registerUser(userCredentials);
						if (result) {
							if (userCredentials.getToken() != null) {
								TblMail mail = mailSeriviceObj.getUserMailByToken(userCredentials.getToken());
								userServiceObj.updateLink(mail);
					}
							responseMap.put(Constants.MESSAGE,Constants.SUCCESS);
						} else {
							responseMap.put(Constants.MESSAGE, Constants.USER_SERVICE_MSG.INSERTION_FAILED);
						}
					} else {
						logger.info("User Email Already Exists..!");
						responseMap.put(Constants.MESSAGE, Constants.USER_SERVICE_MSG.EMAIL_ALREADY_EXIST);
					}
				} else {
					logger.info("Username should have atleast 3 Letters");
					responseMap.put(Constants.MESSAGE, Constants.USER_SERVICE_MSG.USERNAME_INVALID_LENGTH);
				}
			} else {
				logger.info("Necessary Information Missing..!");
				responseMap.put(Constants.MESSAGE, Constants.USER_SERVICE_MSG.INFO_MISSING);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.put(Constants.MESSAGE, e.toString().split(":")[1]);
		}
		return responseMap;
	}

}
