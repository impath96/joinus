package com.joinus.auth;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.joinus.domain.MembersVo;

public abstract class SnsLogin {

	@Autowired
	protected RestTemplate restTemplate;
	
	protected String authenticationServerUrl;
	protected String authorizationServerUrl;
	protected String apiServerUrl;
	protected String clientId;
	protected String clientSecret;
	protected String redirectUrl;
	

	// 인증 코드를 통해 소셜(구글, 카카오)로부터 회원 정보 획득
	public MembersVo requestSocialMember(String code) {

		// 1) 인증 코드로 access_token 찾기
		String accessToken = requestAccessToken(code);
		
		// 2) access_token을 통해 회원정보 요청
		return requestMemberInfo(accessToken);
	}

	// 받아온 access_token을 가지고 소셜에게 회원 정보 요청
	private MembersVo requestMemberInfo(String accessToken) {
		
		// 회원정보 요청 
		ResponseEntity<String> tokenResponse = restTemplate.exchange(apiServerUrl, HttpMethod.POST,
							makeUserInfoRequest(accessToken), String.class);
		
		// 응답 데이터를 통해 회원 정보 구체화
		return parseSocialMember(tokenResponse);
	}

	//access_token으로 소셜에게 회원 정보 요청
	private HttpEntity<MultiValueMap<String, String>> makeUserInfoRequest(String accessToken) {

		HttpHeaders requestHeader = new HttpHeaders();
		requestHeader.add("Authorization", "Bearer " + accessToken);
		requestHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		requestHeader.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
		
		return new HttpEntity<>(requestHeader);
	}

	// 인증 코드로 access_token 요청
	private String requestAccessToken(String code) {

		System.out.println("authServerUrl : "+authorizationServerUrl);
		ResponseEntity<String> tokenResponse = restTemplate.exchange(authorizationServerUrl, HttpMethod.POST,
				makeAccessTokenRequest(code), String.class);
		return parseAccessToken(tokenResponse);
	}

	// accessToken 요청을 보낼 Http 메시지 생성
	private HttpEntity<MultiValueMap<String, String>> makeAccessTokenRequest(String code) {
		System.out.println("access_token 요청 HTTP 메시지 생성");
		// HttpHeader 생성
		HttpHeaders requestHeader = new HttpHeaders();
		requestHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		requestHeader.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
		// HttpBody 생성 -> grant_type, client_id, client_secret, redirect_url, code
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
		requestBody.add("grant_type", "authorization_code"); // 고정값
		requestBody.add("code", code);						 // code
		requestBody.add("client_id", clientId); 		     // client_id
		requestBody.add("client_secret", clientSecret); 	 // client_secret 
		requestBody.add("redirect_uri", redirectUrl);  		 // redirect_url

		System.out.println("access_token 요청 HTTP 메시지 생성 완료");
		return new HttpEntity<>(requestBody, requestHeader);
	}

	// 받아온 access_token가 kakao, google 별로 서로 다르게 파싱 할 수도 있음
	abstract protected String parseAccessToken(ResponseEntity<String> tokenResponse);
	
	// access_token으로 받아온 데이터 형식이 다를 수도 있음.
	// 또는 받아오는 데이터가 다를수도 있음.
	abstract protected MembersVo parseSocialMember(ResponseEntity<String> response);
}
