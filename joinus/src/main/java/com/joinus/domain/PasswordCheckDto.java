package com.joinus.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.Data;

@Data
public class PasswordCheckDto {

	private String currentPassword;
	private String newPassword;
	private String newPasswordConfirm;

	// 전달받은 패스워드와 매개변수로 전달받은 패스워드(실제 사용자꺼)가 동일한가?
	public boolean equalsPassword(String password) {
		String encryptedPass = encryptPassword("SHA-256", currentPassword);
		if (encryptedPass.equals(password)) {
			return true;
		}
		return false;
	}

	// 전달받은 새로운 비밀번호와 재확인용 새 비밀번호가 동일한가?
	public boolean equalsNewPasswordAndConfirm() {
		if (newPassword.equals(newPasswordConfirm)) {
			return true;
		}
		return false;
	}

	// 비밀번호 암호화 로직 구현 - SHA-256
	private String encryptPassword(String hashAlgorithm, String password) {
		MessageDigest messageDigest = null;
		String hashedPassword = "";
		try {
			messageDigest = MessageDigest.getInstance(hashAlgorithm);
			messageDigest.update(password.getBytes());

			byte[] encPassword = messageDigest.digest();

			// 암호화된 바이트 데이터를 16진수 형태로 변환

			for (int i = 0; i < encPassword.length; i++) {
				hashedPassword += Integer.toHexString(encPassword[i] & 0xFF).toUpperCase();
			}

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashedPassword;
	}

}
