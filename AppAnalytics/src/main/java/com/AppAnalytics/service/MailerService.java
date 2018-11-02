/**
 * 
 */
package com.AppAnalytics.service;

import java.util.ArrayList;

import com.AppAnalytics.models.TblMail;


/**
 * @author Vignesh
 *
 */
public interface MailerService {

	boolean insertMailToken(TblMail mail) ;

	TblMail getUserMailByToken(String token) ;

	boolean updateMailToken(TblMail mail) ;

}
