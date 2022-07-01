package com.joinus.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joinus.domain.ClubBoardVo;
import com.joinus.service.ClubService;

@Controller
@RequestMapping("/club/*")
public class ClubController {
	
	private static final Logger log = LoggerFactory.getLogger(ClubController.class);
	
	// 서비스 객체 주입
	@Inject
	private ClubService service;
	
	// ?뒤에 숫자는 모임고유번호(일단 임의로 주소줄에서 받아오기)
	// http://localhost:8088/club/write?club_no=1
	// 게시판글쓰기
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void writeGet(@ModelAttribute("club_no") int club_no) {
		log.info(" writeGet() 호출 ");
		
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePost(ClubBoardVo vo) {
		log.info(" writePost() 호출 ");
		
		// 전달된 정보 저장(글쓰기 정보)
		log.info("글쓰기 정보 : "+vo);
		
		service.boardWrite(vo);
		log.info(" 글쓰기 완료! ");
		
		return "redirect:/club/list";
	}
	
	
	// http://localhost:8088/club/list
	// 게시글리스트
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listGet() {
		log.info(" listGet() 호출 ");
	}
	
}
