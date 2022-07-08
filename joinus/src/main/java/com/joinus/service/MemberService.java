package com.joinus.service;

import com.joinus.domain.MembersVo;

public interface MemberService {
	
	
	public MembersVo 회원가입(MembersVo member, int interest_no);
	public MembersVo findMemberByEmail(String member_email);
	public MembersVo findMemberByNo(int member_no);
	public void updateImage(String savedFileName, int member_no);

}
