package com.joinus.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joinus.domain.LocationCityVo;
import com.joinus.domain.PlacesVo;
import com.joinus.persistence.PlaceDao;

@Service
public class PlaceServiceImpl implements PlaceService{
	
	private static final Logger log = LoggerFactory.getLogger(PlaceServiceImpl.class);

	@Inject
	private PlaceDao dao;
	
	// 시설 목록(비제휴)
	@Override
	public List<PlacesVo> getPlaceList() {
		return dao.getPlaceList();
	}

	// 부산 (구) 리스트
	@Override
	public List<LocationCityVo> getBusanGuList() {
		return dao.getBusanGuList();
	}

	// (구) 별 제휴시설리스트	
	@Override
	public List<PlacesVo> getCityPlaceList(String place_address) {
		return dao.getCityPlaceList(place_address);
	}
	// 제휴시설 상세보기
	@Override
	public PlacesVo getPlaceContent(int place_no) {
		return dao.getPlaceContent(place_no);
	}

	
/////////////////////////////////////크롤링//////////////////////////////
//	@Override
//	public JSONArray classList() {
//		
//		log.info("service : 원데이 클래스 목록 불러오기");
//
//		String url = "https://blog.naver.com/spacecloud";
//		Document doc = null;
//
//		try {
//			doc = Jsoup.connect(url).get();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		log.info(doc.toString());
//		
//		Elements class_title = doc.select(".sc-czvXZf dlesvJ > p");
//		Elements class_image = doc.select(".sc-kszsmc cOVJyJ > img");
//		Elements class_category = doc.select(".sc-cBIgVh gxRDfj > p");
//		Elements class_address = doc.select("p.sc-jHwEDs juVgEZ");
//		Elements class_price = doc.select("");
//		
//		JSONArray classList = new JSONArray();
//
//		for (int i = 0; i < class_title.size(); i++) {
//			JSONObject obj = new JSONObject();
//
//			obj.put("class_title", class_title.get(i).text());
//			obj.put("class_image", class_image.get(i).attr("data-original"));
//			obj.put("class_category", class_category.get(i).text());
//			obj.put("class_address", class_address.get(i).text());
//			obj.put("class_price", class_price.get(i).text());
//
//			log.info("obj: "+obj);
//			
//			classList.add(obj);
//		}
//		System.out.println("classList : " + classList);
//		
//		return classList;
//	}
/////////////////////////////////////크롤링//////////////////////////////

	

}
