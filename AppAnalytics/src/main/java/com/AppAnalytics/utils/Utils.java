/**
 * 
 */
package com.AppAnalytics.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.Timer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.AppAnalytics.Mailer.SendMail;
/**
 * @author Vignesh
 *
 */
public class Utils {
	

final static PropertiesReader props = PropertiesReader.getInstance();

	static Logger logger = Logger.getLogger(Utils.class);


	public static byte[] stringToByteArr(String str) {
		return Base64.getDecoder().decode(str);
	}
	public static String byteToString(byte[] arr) {
		return Base64.getEncoder().encodeToString(arr);
	}
	/*

	//private final static PropertiesReader props = PropertiesReader.getInstance();

	private final static Logger logger = Logger.getLogger(Utils.class);

	


	

	

	

	public static void copyBean(Object dest, Object source) throws IllegalAccessException, InvocationTargetException {
		new BeanUtilsBean() {
			@Override
			public void copyProperty(Object dest, String name, Object value)
					throws IllegalAccessException, InvocationTargetException {
				if(value != null) {
					super.copyProperty(dest, name, value);
				}
			}
		}.copyProperties(dest, source);
	}

	public static String getRandomString(int length) {
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder( length );
		for( int i = 0; i < length; i++ ) {
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		}
		Random rand = new Random(); 
		int value = rand.nextInt(2);
		return sb.toString()+value;
	}


	public static boolean validateEmail(String email){

		// SaulinoGroup Email Format
		String emailFormat = "saulinogroup.com";

		String[] inputEmail = email.split("@");
		String  emailValidation = inputEmail[1].toLowerCase();
		if(emailValidation.equals(emailFormat)){
			return true;
		} else {
			return false;
		}
	}

	 This will convert given date to given Time Zone 
	public static Date convertTimeZone(Date date,String timeZone) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		TimeZone fromTimeZone = calendar.getTimeZone();

		TimeZone toTimeZone = TimeZone.getTimeZone(timeZone);
		calendar.setTimeZone(fromTimeZone);
		calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
		if (fromTimeZone.inDaylightTime(calendar.getTime())) {
			calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getDSTSavings() * -1);
		}

		calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
		calendar.setTimeZone(toTimeZone);
		if (toTimeZone.inDaylightTime(calendar.getTime())) {
			calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
		}

		return calendar.getTime();
	}

	public static Date convertStringToDate(String dateStr,String dateFormat) {
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			logger.error("ConvertStringToDate : Exception While Parsing Date");
			logger.error("Date :: " + dateStr + " Format :: " + dateFormat);
			e.printStackTrace();
		}
		return date;
	}

	public static String getMerchantEmail() {
		String mode = props.getMessage(Constants.Paypal.MODE);
		if(mode.equals(Constants.Paypal.SANDBOX_MODE)) {
			return props.getMessage(Constants.Paypal.MERCHANT_SANDBOX_EMAIL);
		} else {
			return props.getMessage(Constants.Paypal.MERCHANT_LIVE_EMAIL);
		}
	}

	public static HttpResponse HTTPPostCall(String url,HttpServletRequest request) {
		HttpResponse resp = null;
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			// You need to add this parameter to tell PayPal to verify
			params.add(new BasicNameValuePair(Constants.Paypal.CMD, Constants.Paypal.NO_VALIDATE)); 
			for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
				String name = e.nextElement();
				String value = request.getParameter(name);
				params.add(new BasicNameValuePair(name, value));
			}
			logger.info("Request Params :: " + params);
			post.setEntity(new UrlEncodedFormEntity(params));
			resp = client.execute(post);
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException While HTTP Post Call " , e);
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException While HTTP Post Call " , e);
		} catch (IOException e) {
			logger.error("IOException While HTTP Post Call " , e);
		}
		return resp;
	}

	public static String getIPNURL() {
		String mode = props.getMessage(Constants.Paypal.MODE);
		if(mode.equals(Constants.Paypal.SANDBOX_MODE)) {
			return props.getMessage(Constants.Paypal.SANDBOX_IPN_URL);
		} else {
			return props.getMessage(Constants.Paypal.LIVE_IPN_URL);
		}
	}
	
	public static void destroyTimerThread(Timer timer,String timerId) {
		
		try {
			logger.info("Closing Timer Thread with ID : " + timerId);
			timer.cancel();
			timer.purge();
		} catch (Exception e) {
			logger.error("Exception while destroying Timer Thread with ID " + timerId + "\n" + e);
		}
	}
*/
	public static String generateSHA256(String base) {
		if(base.trim().equals("") || base == null){
			return "";
		}else{
			MessageDigest md = null;
			base += UUID.randomUUID().toString();
			try {
				md = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			md.update(base.getBytes());
			return convertHexToString(md.digest());
		}
	}
	public static String convertHexToString(byte[] byteData){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}	
	
	public static String[] getReceiptmentEmail(String[] email) {
		String debug = props.getMessage("mail.debug");
		if(debug.trim().equalsIgnoreCase("true")) {
			logger.info("Mail Debug is True");
			String[] tMail={props.getMessage("debug.catchall")};
			return tMail;
		} else {
			logger.info("Mail Debug is False");
			return email;
		}
	}
}
