package com.joinus.service;

import com.joinus.domain.MembersVo;

public interface MemberService {
	
	MembersVo 회원찾기(String member);
	void 회원가입();

}
