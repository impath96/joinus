package com.joinus.auth;

import lombok.Data;

@Data
public class GoogleProfile {

	private String sub; // 동일인 식별 정보(고유 해시값)
	private String name; // 전체 이름
	private String given_name; // 이름
	private String family_name; // 성
	private String picture; // 프로필 사진 URL
	private String email; // 메일 주소
	private String email_verified; // 이메일 인증 여부
	private String locale; // 언어

}
/*
 * "sub": "101950499491445146817", 
 * "name": "minho kim",
 * "given_name": "minho",
 * "family_name": "kim",
 * "picture": "https://lh3.googleusercontent.com/a/AATXAJwpwVIlMi37KxM6ofYeoYqCHVGv8T-KEtU2hU3V\u003ds96-c",
 * "email": "impath96@gmail.com", 
 * "email_verified": true, 
 * "locale": "ko"
 */
