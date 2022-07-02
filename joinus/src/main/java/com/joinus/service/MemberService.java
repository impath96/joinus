package com.joinus.service;

import com.joinus.domain.MemberVo;

public interface MemberService {
	
	MemberVo 회원찾기(String member);
	void 회원가입();

}
