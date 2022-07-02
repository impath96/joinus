package com.joinus.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joinus.domain.MembersVo;

import lombok.Getter;

@Getter
@Service
@PropertySource("classpath:kakao.properties")
public class KakaoLogin extends SnsLogin {
	
	public static final String SERVICE = "kakao";
	
	@Value("${oauth.kakao.client_id}")
	private void setClientId(String clientId) {
		super.clientId = clientId;
	}
	@Value("${oauth.kakao.client_secret}")
	private void setClientSecret(String clientSecret) {
		super.clientSecret = clientSecret;
	}
	@Value("${oauth.kakao.authorization-server-uri}")
	private void setAuthorizationServerUrl(String authorizationServerUrl) {
		super.authorizationServerUrl = authorizationServerUrl;
	}
	@Value("${oauth.kakao.authentication-server-uri}")
	private void setAuthenticationServerUrl(String authenticationServerUrl) {
		super.authenticationServerUrl = authenticationServerUrl;
	}
	@Value("${oauth.kakao.api-server-uri}")
	private void setApiServerUrl(String apiServerUrl) {
		super.apiServerUrl = apiServerUrl;
	}
	@Value("${oauth.kakao.redirect_uri}")
	private void setRedirectUrl(String redirectUrl) {
		super.redirectUrl = redirectUrl;
	}
	
	
	@Override
	protected String parseAccessToken(ResponseEntity<String> tokenResponse) {
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoAuthToken authToken = null;
		try {
			authToken = objectMapper.readValue(tokenResponse.getBody(), KakaoAuthToken.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authToken.getAccess_token();
	}

	@Override
	protected MembersVo parseSocialMember(ResponseEntity<String> response) {
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
		} catch (JsonParseException e) {
			// json 파싱 오류시 예외 처리
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// json 매핑 오류시 예외 처리
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(kakaoProfile);
		/*
		 * SocialMember(id=2319171104,
		 * 				email=aksghcjswo@naver.com,
		 * 				nickname=aksghcjswo@naver.com, 
		 * 				password=null, 
		 * 				tel=null, 
		 * 				role=null, 
		 * 				type=kakao, 
		 * 				thumbnail_image_url=http://k.kakaocdn.net/dn/crtS8q/btrquP1oIw4/M6lV7eYmTqD1KkWDRal7GK/img_110x110.jpg, 
		 * 				regdate=null)
		 * */
		
		// 이메일, 이미지, 이름, 소셜로그인 유저 
		MembersVo socialMember = MembersVo.builder()
				.member_email(kakaoProfile.getKakao_account().getEmail())
				.member_image(kakaoProfile.getProperties().getThumbnail_image())
				.member_name(kakaoProfile.getProperties().getNickname())
				.member_type(SERVICE)
				.build();
		
		System.out.println("socialMember : " + socialMember.toString());
		return socialMember;
	}

}
