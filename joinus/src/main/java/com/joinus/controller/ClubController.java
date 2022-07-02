package com.joinus.controller;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joinus.service.ClubService;

@Controller
@RequestMapping("/club/*")
public class ClubController {
	
	@Inject
	private ClubService service;
	
	private static final Logger log = LoggerFactory.getLogger(ClubController.class);
	
	//http://localhost:8088/club/clubList
	//http://localhost:8088/club/clubList?interest_no=2
	@RequestMapping(value="/clubList", method = RequestMethod.GET)
	public void clubList(@ModelAttribute("interest_no") String interest_no, Model model) {
		log.info("interest_no : "+interest_no);	
		
		if(interest_no.isBlank()) {
			model.addAttribute("clubList", service.clubList());
			log.info("clubList() 호출");
		}else{
			model.addAttribute("clubList", service.clubList(Integer.parseInt(interest_no)));
			log.info("clubList(no) 호출");
		}
	}
	
	//http://localhost:8088/club/clubMember?club_no=1
	@RequestMapping(value="/clubMember", method = RequestMethod.GET)
	public void clubMember(Model model, int club_no) {
		log.info("clubMember() 호출");
		
		club_no = 1;
		model.addAttribute("clubMemberList",service.clubMemberListAll(club_no));
	}
	
	//http://localhost:8088/club/clubMeeting
	@RequestMapping(value="/clubMeeting", method = RequestMethod.GET)
	public void clubMeeting() {
		log.info("clubMeeting() 호출");
	}
	
	
	
	
	
}
