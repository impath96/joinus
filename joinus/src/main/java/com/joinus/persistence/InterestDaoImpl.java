package com.joinus.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joinus.domain.InterestVo;

@Repository
public class InterestDaoImpl implements InterestDao{
	
	@Autowired
	SqlSession sqlSession;
	
	private static final String NAMESPACE ="com.joinus.mapper.InterestMapper";
	@Override
	public List<InterestVo> selectInterestAll() {
		
		return sqlSession.selectList(NAMESPACE+".selectInterestAll");
	}

}
