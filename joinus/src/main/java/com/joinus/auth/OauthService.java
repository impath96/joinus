package com.joinus.auth;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinus.domain.MemberVo;
import com.joinus.persistence.MemberDao;

@Service
public class OauthService {

	@Inject
	SnsLoginFactory snsLoginFactory;
	
	@Inject
	MemberDao memberDao;
	
	public MemberVo oauthLogin(String service, String code) {

		// 1. 먼저 service 타입(kakao, google)에 따라 처리가 달라야 한다
		SnsLogin snsLogin = snsLoginFactory.getSnsLogin(service);		// 해당 oauth(kakao, google)타입에 따른 객체 제공
		
		// 2. 클라이언트로부터 전달받은 code를 통해 소셜로그인 회원 정보 획득
		// access_token이나 refresh 토큰을 가지고 세션, 쿠키를 통해 이미 로그인했던 사람일 경우 어떻게 처리할것인가??
		MemberVo socialLoginMember = snsLogin.requestSocialMember(code);
		
		// 소셜 로그인 토큰으로 일단 체크 -> 있으면 바로 로그인 완료?
		// 3. 특정 조건으로 해당 회원이 가입자인지 체크 -> 이메일이 UK이니까 이메일로 체크?
		System.out.println("select 하기 전");
		MemberVo findMember = memberDao.selectMember(socialLoginMember.getMember_email());
		System.out.println("select 하기 후");
		
		// 1) 만약 가입자가 아닐 경우 : 회원가입 후 로그인 처리
		if(findMember == null) {
			// 회원가입 처리 - 근데 우리는 아직 입력받을 정보가 더 남아있기 때문에 저장 X 
			// System.out.println("DB에 저장할 회원 정보 : " + findMember);
			// 비밀번호 입력이 not null 이기 때문에 임의의 비밀번호 발급 
			// socialLoginMember.setMember_pass("abcdabasc");
			// memberDao.insertMember(socialLoginMember);
			// return socialLoginMember;
			return null;
		}
		// 2) 가입자일 경우 : 로그인 처리
		return findMember;
	}
	
}
