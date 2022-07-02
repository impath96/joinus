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
@PropertySource("classpath:google.properties")
public class GoogleLogin extends SnsLogin{
	
	public static final String SERVICE = "google";
	
	// client_id, client_secret, authServerUrl, apiserverUrl 설정
	@Value("${oauth.google.client_id}")
	private void setClientId(String clientId) {
		super.clientId = clientId;
	}
	@Value("${oauth.google.client_secret}")
	private void setClientSecret(String clientSecret) {
		super.clientSecret = clientSecret;
	}
	@Value("${oauth.google.authorization-server-uri}")
	private void setAuthorizationServerUrl(String authorizationServerUrl) {
		super.authorizationServerUrl = authorizationServerUrl;
	}
	@Value("${oauth.google.authentication-server-uri}")
	private void setAuthenticationServerUrl(String authenticationServerUrl) {
		super.authenticationServerUrl = authenticationServerUrl;
	}
	@Value("${oauth.google.api-server-uri}")
	private void setApiServerUrl(String apiServerUrl) {
		super.apiServerUrl = apiServerUrl;
	}
	
	@Value("${oauth.google.redirect_uri}")
	private void setRedirectUrl(String redirectUrl) {
		super.redirectUrl = redirectUrl;
	}

	@Override
	protected String parseAccessToken(ResponseEntity<String> tokenResponse) {
		ObjectMapper objectMapper = new ObjectMapper();
		 GoogleAuthToken authToken = null;
		try {
			authToken = objectMapper.readValue(tokenResponse.getBody(), GoogleAuthToken.class);
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
		System.out.println("파싱 완료");
		return authToken.getAccess_token();
		
	}

	@Override
	protected MembersVo parseSocialMember(ResponseEntity<String> response) {
		ObjectMapper objectMapper = new ObjectMapper();
		GoogleProfile googleProfile = null;
		try {
			googleProfile = objectMapper.readValue(response.getBody(), GoogleProfile.class);
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
		MembersVo socialMember = MembersVo.builder()
				.member_email(googleProfile.getEmail())
				.member_name(googleProfile.getName())
				.member_type(SERVICE)
				.member_image(googleProfile.getPicture())
				.build();
		
		return socialMember;
	}


}
