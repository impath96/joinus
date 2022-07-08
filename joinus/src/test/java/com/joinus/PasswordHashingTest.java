package com.joinus;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class PasswordHashingTest {
	
	//@Test
	public void hashTest() {
		
		String algorithm = "SHA-256";
		
		System.out.println("admin123 SHA-256 암호화 결과 : " + hashPassword(algorithm));
		
	}
	
	@Test
	public void parse() {
		String location = "부산시 사하구 감천동 ~~아파트 201";
		String[] part = location.split(" ");
		String savedLocation = part[0];
		for(int i = 1; i<3; i++) {
			savedLocation += " " + part[i];
		}
		System.out.println(savedLocation);
		
	}
	
	private String hashPassword(String hashAlgorithm) {
		String password = "admin123";
		String hashedPassword = "";
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(hashAlgorithm);
			messageDigest.update(password.getBytes());
			
			byte[] encPassword = messageDigest.digest();
			
			for(int i=0; i<encPassword.length; i++) {
				hashedPassword += Integer.toHexString(encPassword[i]&0xFF).toUpperCase();
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashedPassword;
	}

}
