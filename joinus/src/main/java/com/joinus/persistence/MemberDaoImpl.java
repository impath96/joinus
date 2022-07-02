package com.joinus.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.joinus.domain.MemberVo;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	SqlSession sqlSession;
	
	@Inject
	public MemberDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	private static final Logger log = LoggerFactory.getLogger(MemberDaoImpl.class);
	private static final String NAMESPACE = "com.joinus.mapper.MemberMapper";
	
	@Override
	public MemberVo selectMember(String email) {

		return sqlSession.selectOne(NAMESPACE+".selectMember", email);
	}

	@Override
	public void insertMember(MemberVo socialMember) {

		sqlSession.insert(NAMESPACE+".insertMember", socialMember);
	}
	
	
	
	
	

}
