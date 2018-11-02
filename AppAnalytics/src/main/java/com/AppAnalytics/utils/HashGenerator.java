/**
 * 
 */
package com.AppAnalytics.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * @author Vignesh
 * 
 */
public final class HashGenerator {
	
	private String saltStr;
	private byte[] salt = new byte[32];

	/*
	 * Hash the Password by Generating new salt
	 * */
	public String hashpassword(String base) {

		StringBuffer hexString = new StringBuffer();
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			salt = this.generateSalt();
			digest.update(salt);
			this.setSalt(Utils.byteToString(salt));
			
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
		return hexString.toString();
	}
	
	/*
	 * Hash the Password by using existing salt
	 * */
	public String hashpassword(String base,String salt) {

		StringBuffer hexString = new StringBuffer();
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			digest.update(Utils.stringToByteArr(salt));
			
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
		return hexString.toString();
	}

	/*
	 * To Generate the new random salt
	 * */
	public byte[] generateSalt() {
		SecureRandom sr;
		
		try {
			sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
			sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			
			e.printStackTrace();
		}
		return salt;       
	}
	
	/*
	 * Getter for salt string
	 * */
	public String getSalt() {
		return saltStr;
	}
	
	/*
	 * Setter for Salt String
	 * */
	public void setSalt(String salt) {
		this.saltStr = salt;
	}
}
