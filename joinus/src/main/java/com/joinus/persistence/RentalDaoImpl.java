package com.joinus.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.joinus.domain.LocationCityVo;
import com.joinus.domain.PartnerPlacesVo;
import com.joinus.domain.PaymentsVo;
import com.joinus.domain.RentalPlacesVo;

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

	@Override
	public List<LocationCityVo> getBusanGuList() {
		return sqlSession.selectList(NAMESPACE+".getBusanGuList");
	}

	@Override
	public List<PartnerPlacesVo> getCityPartnerPlaceList(String partner_place_address) {
		return sqlSession.selectList(NAMESPACE+".getCityPartnerPlaceList", partner_place_address);
	}

	@Override
	public List<PartnerPlacesVo> getPartnerPlaceList(String partner_place_type, String partner_place_address) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("partner_place_type", partner_place_type);
		param.put("partner_place_address", partner_place_address);
		
		return sqlSession.selectList(NAMESPACE+".getPartnerPlaceList", param);
	}

	@Override
	public List<PartnerPlacesVo> getTypePartnerPlaceList(String partner_place_type) {
		return sqlSession.selectList(NAMESPACE+".getTypePartnerPlaceList", partner_place_type);
	}
	
	@Override
	public Integer pay(PaymentsVo vo) {
		return sqlSession.insert(NAMESPACE+".pay", vo);
	}

	@Override
	public void place(RentalPlacesVo vo) {
		sqlSession.insert(NAMESPACE+".place", vo);
	}
	
	
	
}
