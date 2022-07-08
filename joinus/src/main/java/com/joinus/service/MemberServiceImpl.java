package com.joinus.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joinus.domain.MemberInterestsVo;
import com.joinus.domain.MembersVo;
import com.joinus.persistence.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDao memberDao;
	
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Override
	public MembersVo 회원가입(MembersVo member, int interest_no) {
		// 1) 먼저 회원을 등록
		memberDao.insertMember(member);
		// 2) 등록된 회원 다시 꺼내오고(session에 회원번호 저장하기 위해서)
		MembersVo selectMember = memberDao.selectMemberByEmail(member.getMember_email());
		log.info("꺼내온 member : {}", selectMember);
		// 3) 해당 회원번호를 회원관심사 테이블에 사용해서 회원관심사 추가
		MemberInterestsVo memberInterestVo = new MemberInterestsVo();
		memberInterestVo.setInterest_no(interest_no);
		memberInterestVo.setMember_no(selectMember.getMember_no());
		memberDao.insertMemberInterest(memberInterestVo);
		return selectMember;
	}

	@Override
	public MembersVo findMemberByEmail(String member_email) {
		log.info("전달받은 이메일 주소 : {}", member_email );
		MembersVo findMember = memberDao.selectMemberByEmail(member_email);
		log.info("이메일을 통해 회원 정보 조회 : {}", findMember);
		return findMember;
	}
	@Override
	public MembersVo findMemberByNo(int member_no) {
		
		MembersVo findMember = memberDao.selectMemberByNo(member_no);
		
		return findMember;
	}

	@Override
	public void updateImage(String savedFileName, int member_no) {
		log.info("회원 프로필 사진 변경 savedFileName : {}, member_no : {}", savedFileName,member_no);
		memberDao.updateImage(savedFileName,member_no);
	}

}
