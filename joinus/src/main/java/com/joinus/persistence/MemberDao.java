package com.joinus.persistence;


import com.joinus.domain.MemberInterestVo;
import com.joinus.domain.MembersVo;

public interface MemberDao {
	
	public MembersVo selectMemberByEmail(String member_email);
	public MembersVo selectMemberByNo(Integer member_no);

	public void insertMember(MembersVo member);

	public void insertMemberInterest(MemberInterestVo memberInterestVo);
	
}
