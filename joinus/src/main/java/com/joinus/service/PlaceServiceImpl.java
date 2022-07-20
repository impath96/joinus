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

	
	
//	@Override
//	public JSONArray placeList() {
//		
//		log.info("service : 숙소목록불러오기");
//
////		int pagenum = 1;
////		String url = "https://shareit.kr/search/venue?area=&date=&eventType=&venueType=&minPrice=0&maxPrice=-1&minPeople=0&maxPeople=-1&equip=&amenity=&approve=true&realtime=true&order=recent&page="+pagenum+"&keyword=%EB%B6%80%EC%82%B0";
//		String url = "https://shareit.kr/search/venue?area=59&date=&eventType=&venueType=&minPrice=0&maxPrice=-1&minPeople=0&maxPeople=-1&equip=&amenity=&approve=true&realtime=true&order=recent&page=1&keyword=";
//
//		Document doc = null;
//
//		try {
//			doc = Jsoup.connect(url).get();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		log.info(doc.toString());
//		
//		Elements place_title = doc.select(".sc-czvXZf dlesvJ > p"); //시설명
//		Elements place_img = doc.select(".sc-kszsmc cOVJyJ > img");    //시설사진
//		Elements place_category = doc.select(".sc-cBIgVh gxRDfj > p");   //시설분류
//		Elements place_address = doc.select("p.sc-jHwEDs juVgEZ");  //시설주소
//		
//		// JSON 형태 정보 저장
//		JSONArray placeList = new JSONArray();
//
//		for (int i = 0; i < place_title.size(); i++) {
//			// JSONObject에 키:값 형태로 데이터 저장
//			JSONObject obj = new JSONObject();
//
//			obj.put("place_title", place_title.get(i).text());
//			obj.put("place_img", place_img.get(i).attr("data-original"));
//			obj.put("place_category", place_category.get(i).text());
//			obj.put("place_address", place_address.get(i).text());
//
//			log.info("obj: "+obj);
//			
//			placeList.add(obj);
//		}
//		System.out.println("placeList : " + placeList);
//		
//		return placeList;
//	}


	
	

}
