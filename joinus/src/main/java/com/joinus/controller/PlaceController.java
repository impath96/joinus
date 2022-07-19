package com.joinus.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public void placeList(Model model) throws IOException {
		log.info(" placeList() 호출! ");
		
		JSONArray placeList = service.placeList();
		
		model.addAttribute("placeList", placeList);
	}

	

	  
	   
	
	
	
	
}
