package com.joinus.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.joinus.domain.MembersVo;
import com.joinus.persistence.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDao MemberDao;
	
	@Override
	public MembersVo 회원찾기(String member) {

		return MemberDao.selectMember(member);
	}

	@Override
	public void 회원가입(MembersVo vo) {
		
		MemberDao.signupMember(vo);
	}
	
	

}
