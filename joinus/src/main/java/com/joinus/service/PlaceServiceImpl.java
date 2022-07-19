package com.joinus.service;

import java.io.IOException;

import org.json.simple.JSONArray;
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
	public JSONArray placeList() {
		log.info(" hourplace 장소 목록 불러오기");
		
		String url = "https://hourplace.co.kr/search?keyword=부산";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements place_title = doc.select("");
		
		
		return null;
	}

}
