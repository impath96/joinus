package com.joinus.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.joinus.domain.PartnerPlacesVo;

@Repository
public class RentalDaoImpl implements RentalDao {
	
	@Inject
	private SqlSession sqlSession;
	
	static final String NAMESPACE = "com.joinus.mapper.RentalMapper";
	
	private static final Logger log = LoggerFactory.getLogger(RentalDaoImpl.class);

	@Override
	public PartnerPlacesVo getPartnerPlaceContent(int partner_place_no) {
		return sqlSession.selectOne(NAMESPACE+".getPlaceContent", partner_place_no);
	}

	@Override
	public List<PartnerPlacesVo> getAllPartnerPlaceList() {
		return sqlSession.selectList(NAMESPACE+".getAllPartnerPlaceList");
	}
	
	
	
}
