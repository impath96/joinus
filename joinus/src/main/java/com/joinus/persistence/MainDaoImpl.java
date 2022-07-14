package com.joinus.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joinus.domain.ClubsVo;
import com.joinus.domain.InterestsVo;

@Repository
public class MainDaoImpl implements MainDao{
	
	@Autowired
	SqlSession sqlSession;
	
	private static final String NAMESPACE ="com.joinus.mapper.MainMapper";
	
	@Override
	public List<ClubsVo> getMostPopularClub() {
		return sqlSession.selectList(NAMESPACE+".getMostPopularClub");
	}

	@Override
	public List<ClubsVo> getMostRecentClub() {
		return sqlSession.selectList(NAMESPACE+".getMostRecentClub");

	}
	@Override
	public List<ClubsVo> getMostNumerousClub() {
		return sqlSession.selectList(NAMESPACE+".getMostNumerousClub");

	}

	@Override
	public List<ClubsVo> getMyClubs(String location) {
		return sqlSession.selectList(NAMESPACE+".getMyClubs", location);
	}

	

}
