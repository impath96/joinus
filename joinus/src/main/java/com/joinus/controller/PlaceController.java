package com.joinus.controller;

import java.sql.Connection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
	
	private PlaceService service;
	
	final String hourplaceUrl = "https://hourplace.co.kr";
    org.jsoup.Connection conn = Jsoup.connect(hourplaceUrl);
//    Document document = conn.get();	
	
	@RequestMapping(value = "/placeList", method = RequestMethod.GET)
	public void placelist() {

		
		
	}
	
	
	
}
