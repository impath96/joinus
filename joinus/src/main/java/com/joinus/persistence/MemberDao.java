package com.joinus.persistence;


import com.joinus.domain.MembersVo;

public interface MemberDao {
	
	public MembersVo selectMemberByEmail(String member_email);
	public MembersVo selectMemberByNo(Integer member_no);

	public void insertMember(MembersVo member);

	public void updateImage(String savedFileName, int member_no);
	public MembersVo selectMember(String email, String password);
	public void insertMemberInterest(int member_no, int interest);
	public MembersVo updatePassword(int member_no, String encryptedPassword);
	public void updateName(String memberName, int member_no);
	
}
