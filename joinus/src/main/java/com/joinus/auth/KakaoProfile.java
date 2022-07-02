package com.joinus.auth;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class KakaoProfile {
	private Long id; // 회원번호, Long
	private String connected_at; // 서버에 연결 완료된 시간. UTC, Datetime
	private Properties properties; // 사용자 프로퍼티, JSON
	private KakaoAccount kakao_account; // 카카오 계정 정보
	private Map<String, Object> additional_properties = new HashMap<String, Object>();

	@Data
	public class Properties {

		private String nickname;
		private String profile_image;
		private String thumbnail_image;
		private Map<String, Object> additional_properties = new HashMap<>();

	}

	@Data
	public class KakaoAccount {

		private Boolean profile_nickname_needs_agreement;
		private Boolean profile_image_needs_agreement;
		private Profile profile;
		private Boolean has_email;
		private Boolean email_needs_agreement;
		private Boolean is_email_valid;
		private Boolean is_email_verified;
		private String email;
		private Map<String, Object> additional_properties = new HashMap<String, Object>();

		@Data
		public class Profile {

			private String nickname;
			private String thumbnail_image_url;
			private String profile_image_url;
			private Boolean is_default_image;
			private Map<String, Object> additional_properties = new HashMap<String, Object>();
		}
	}

}
