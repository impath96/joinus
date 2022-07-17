package com.joinus.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.joinus.domain.Criteria;
import com.joinus.domain.MembersVo;
import com.joinus.domain.MyClubDto;

public interface MemberService {
	
	public MembersVo join(MembersVo member) throws NoSuchAlgorithmException;
	public MembersVo findMemberByEmail(String member_email);
	public MembersVo findMemberByNo(int member_no);
	public void updateImage(String savedFileName, int member_no);
	public MembersVo signIn(String email, String password);
	public void addInterest(int member_no, int interest);
	public MembersVo resetPassword(int member_no, String newPassword);
	public void updateName(String memberName, int member_no);
	public List<MyClubDto> getMyClubList(int member_no);
	public List<MembersVo> findMemberAll(Criteria cri);
	public int getTotalCount();
	public void deleteMember(List<Integer> idList);

}
