package com.joinus.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joinus.service.PlaceService;

@Controller
@RequestMapping("/place/*")
public class PlaceController {
	
	private static final Logger log = LoggerFactory.getLogger(PlaceController.class);
	
	@Inject
	private PlaceService service;
	
	
	@RequestMapping(value = "/placeList", method = RequestMethod.GET)
	public void placeList() throws IOException {
		log.info(" placeList() 호출! ");
		
//		String url = "https://sports.news.naver.com/kbaseball/index.nhn";
		String url = "https://www.spacecloud.kr/search?q=부산";
		log.info("url: "+url);
		
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.info(doc.toString());
		
//		Elements element = doc.select("div.home_news");
//		
//		String title = element.select("h2").text();
//		log.info("title: "+title);
//		
//		for(Element el : element.select("li")) {
//			log.info(el.text()); 
//		}
		
	}
	
	
//	@RequestMapping(value = "/placeList", method = RequestMethod.GET)
//	public void placeList() throws IOException {
//		log.info(" placeList() 호출! ");
//		
//		String url = "https://sports.news.naver.com/kfootball/index.nhn";
//		log.info("url: "+url);
//		
//		Document doc = Jsoup.connect(url).get();
//		
//		Elements element = doc.select("div.home_news");
//		
//		String title = element.select("h2").text();
//		log.info("title: "+title);
//		
//		for(Element el : element.select("li")) {
//			log.info(el.text()); 
//		}
//		
//	}

	

	  
	   
	
	
	
	
}
