package com.joinus.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PlaceServiceImpl implements PlaceService{
	
	private static final Logger log = LoggerFactory.getLogger(PlaceServiceImpl.class);

	@Override
	public void placeList(String url) {
		String city = "부산";
		String placeurl = "https://www.spacecloud.kr/search?q="+city;
		Document doc = null;
		log.info("url: "+placeurl);
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("div.info_area");		
		
	
		
	}

	
	
	
	
//	@Override
//	public JSONArray placeList() {
//		log.info(" spacecloud 장소 목록 불러오기");
//		
//		String city = "부산";
//		String url = "https://www.spacecloud.kr/search?q="+city;
//		Document doc = null;
//		log.info("url: "+url);
//		
//		try {
//			doc = Jsoup.connect(url).get();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		Elements place_title = doc.select("div.info_area");
//		log.info("place_title: "+place_title);
//		
//		String title = place_title.select("h3.tit_space").text();
//		
//		System.out.println(title);
//		
//		JSONArray placeList = new JSONArray();
//		
//		for (int i = 0; i < place_title.size(); i++) {
//			JSONObject jobj = new JSONObject();
//
//			jobj.put("place_title", place_title.get(i).text());
//			placeList.add(jobj);
//		}
//		log.info("placeList: "+placeList);
//		return placeList;
//	}
	
	
	
	
	
	

}
