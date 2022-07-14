package com.joinus.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joinus.domain.MemberInterestsVo;
import com.joinus.domain.MembersVo;
import com.joinus.persistence.MemberDao;
import com.joinus.util.SHA256;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDao memberDao;

	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Override
	public MembersVo join(MembersVo member) throws NoSuchAlgorithmException {
		// 1) 먼저 회원을 등록
		//  -1) 만약 소셜 로그인 회원일 경우 비밀번호를 설정
		//  -2) 일반 이메일 회원가입 회원일 경우 이미지 URL, 회원가입 타입 설정(default.jpg, 'common')
		String password = "";
		if (member.getMember_signup_type() == null) {
			member.setMember_image("default.jpg");
			member.setMember_signup_type("common");
			password = SHA256.encrypt(member.getMember_pass());
			member.setMember_pass(password);			
		} else {
			password = SHA256.encrypt(UUID.randomUUID().toString());
			member.setMember_pass(password);
		}
		
		memberDao.insertMember(member);

		// 2) 등록된 회원 다시 꺼내오고(session에 회원번호 저장하기 위해서)
		MembersVo selectMember = memberDao.selectMemberByEmail(member.getMember_email());
		log.info("꺼내온 member : {}", selectMember);

		return selectMember;
	}

	@Override
	public MembersVo findMemberByEmail(String member_email) {
		log.info("전달받은 이메일 주소 : {}", member_email);
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
		log.info("회원 프로필 사진 변경 savedFileName : {}, member_no : {}", savedFileName, member_no);
		memberDao.updateImage(savedFileName, member_no);
	}

	// 이메일과 패스워드를 통해 로그인 처리
	@Override
	public MembersVo signIn(String email, String password) {

		// 패스워드를 SHA-256으로 변환
		String encryptedPassword = "";
		try {
			encryptedPassword = SHA256.encrypt(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("암호화된 비밀번호 : {}", encryptedPassword);
		MembersVo member = memberDao.selectMember(email, encryptedPassword);
			
		return member;
	}

	@Override
	public void addInterest(int member_no, int interest) {

		memberDao.insertMemberInterest(member_no, interest);
		
	}

	@Override
	public MembersVo resetPassword(int member_no, String newPassword) {
		
		// 새로 전달받은 비밀번호 암호화
		String encryptedPassword = "";
		try {
			encryptedPassword = SHA256.encrypt(newPassword);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MembersVo member = memberDao.updatePassword(member_no, encryptedPassword);
		
		return member;
	}

	@Override
	public void updateName(String memberName, int member_no) {
		memberDao.updateName(memberName, member_no);
		
	}

}
