package com.joinus.persistence;


import com.joinus.domain.MembersVo;

public interface MemberDao {
	
	public MembersVo selectMember(String email);

	public void insertMember(MembersVo socialMember);
	
	public void signupMember(MembersVo vo);
	
}
