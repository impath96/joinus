package com.joinus.auth;

public class SnsLoginFactory {
	
	private final KakaoLogin kakaoLogin;
	private final GoogleLogin googleLogin;
	
	public SnsLoginFactory(KakaoLogin kakaoLogin, GoogleLogin googleLogin) {
		this.kakaoLogin = kakaoLogin;
		this.googleLogin = googleLogin;
	}
	
	public SnsLogin getSnsLogin(String service) {
		if(service.equals(KakaoLogin.SERVICE)) {
			return kakaoLogin;
		}
		if(service.equals(GoogleLogin.SERVICE)) {
			return googleLogin;
		}
		return null;
	}

	
}
