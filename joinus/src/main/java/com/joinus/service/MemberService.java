package com.joinus.service;

import com.joinus.domain.MembersVo;

public interface MemberService {
	
	
	void 회원가입(MembersVo member, int interest_no);
	public MembersVo findMemberByEmail(String member_email);
	public MembersVo findMemberByNo(String member_no);

}
