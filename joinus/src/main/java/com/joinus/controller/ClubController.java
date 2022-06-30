package com.joinus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/club/*")
public class ClubController {
	
	private static final Logger log = LoggerFactory.getLogger(ClubController.class);
	
	// http://localhost:8088/club/write
	// 게시판글쓰기
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void writeGet() {
		log.info(" writeGet() 호출 ");
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public void writePost() {
		log.info(" writePost() 호출 ");
	}
	
	
	// http://localhost:8088/club/list
	// 게시글리스트
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listGet() {
		log.info(" listGet() 호출 ");
	}
	
}
