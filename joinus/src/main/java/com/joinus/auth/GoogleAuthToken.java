package com.joinus.auth;

import lombok.Data;

@Data
public class GoogleAuthToken {

	private String access_token;
	private int expires_in;
	private String refresh_token;
	private String token_type;
	private String scope;
	private String id_token;
}
