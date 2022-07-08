package com.joinus.auth;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.joinus.domain.MembersVo;

public abstract class AuthLogin {
	protected AuthInfo authInfo;
	protected String profileEndPoint;
	protected String accessTokenEndPoint;

	protected OAuth20Service oauthService;
	
	public AuthLogin(AuthInfo authInfo) {
		this.oauthService = new ServiceBuilder(authInfo.getClientId())
				.apiSecret(authInfo.getClientSecret())
				.callback(authInfo.getRedirectUrl())
				.build(authInfo.getApi20Instance());
		this.authInfo = authInfo;
		
	}

	//
	public OAuth2AccessToken getAccessToken(String code) throws IOException, InterruptedException, ExecutionException {

//		OAuthRequest request = new OAuthRequest(Verb.POST, oauthService.getApi().getAccessTokenEndpoint());
//		request.addHeader(OAuthConstants.HEADER, OAuthConstants.BASIC + ' '
//                + Base64.encode(String.format("%s:%s", oauthService.getApiKey(), oauthService.getApiSecret()).getBytes(Charset.forName("UTF-8"))));
//        request.addParameter(OAuthConstants.REDIRECT_URI, oauthService.getCallback());
//        request.addParameter(OAuthConstants.GRANT_TYPE, OAuthConstants.AUTHORIZATION_CODE);
//        
//      음... 카카오가 request를 파싱하는 방법이 다른건가? 왜 카카오만 안되지?
//			request.addParameter를 해줘야 정상적으로  동작
//		음...?
		return this.oauthService.getAccessToken(code);
	}

	public MembersVo getMemberInfo(String code, HttpSession session) throws InterruptedException, ExecutionException, IOException {

		// 1. code를 통해 accessToken 얻기
		OAuth2AccessToken accessToken = getAccessToken(code);

		// 2. accessToken을 통해 응답 반환
		Response response = requestMemberInfo(accessToken);

		return parseResponse(response);
	}

	// 이건 서로 다르게 구현해야할듯
	public abstract MembersVo parseResponse(Response response) throws JsonProcessingException, IOException;

	//
	public Response requestMemberInfo(OAuth2AccessToken accessToken)
			throws InterruptedException, ExecutionException, IOException {
		OAuthRequest request = new OAuthRequest(Verb.GET, getProfileEndPoint());
		oauthService.signRequest(accessToken, request);
		return oauthService.execute(request);
	}
	
	public abstract String getProfileEndPoint();

}
