package com.joinus.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joinus.service.ClubService;

@RestController
@RequestMapping("/club/*")
public class KakaoApi {

	private static final Logger log = LoggerFactory.getLogger(KakaoApi.class);
	
	@Inject
	private ClubService service;
	
	
	@RequestMapping(value="/searchMap", method=RequestMethod.GET)
	public ResponseEntity<String> searchLocation(){
		
		
		
		return null;
	}
	
	
	
	
}
