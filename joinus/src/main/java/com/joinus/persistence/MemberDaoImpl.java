package com.joinus.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.joinus.domain.MemberInterestsVo;
import com.joinus.domain.MembersVo;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	SqlSession sqlSession;
	private static final String NAMESPACE = "com.joinus.mapper.MemberMapper";
	
	private static final Logger log = LoggerFactory.getLogger(MemberDaoImpl.class);

	@Inject
	public MemberDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public MembersVo selectMemberByEmail(String member_email) {

		return sqlSession.selectOne(NAMESPACE+".selectMemberByEmail", member_email);
	}
	@Override
	public MembersVo selectMemberByNo(Integer member_no) {
		
		return sqlSession.selectOne(NAMESPACE+".selectMemberByNo", member_no);
	}

	@Override
	public void insertMember(MembersVo member) {

		sqlSession.insert(NAMESPACE+".insertMember", member);
	}

	@Override
	public void insertMemberInterest(MemberInterestsVo memberInterestVo) {
		log.info("insertMemberInterest 동작 수행");
		sqlSession.insert(NAMESPACE+".insertMemberInterest", memberInterestVo);
	}
	
	
	
	
	

}
