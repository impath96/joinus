package com.joinus.controller;

import java.util.List;

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
	
	// 파라미터를 전달하고 싶을 때는 보내주는 주소와 받는 주소 모두 다 modelAttribute를 사용해야 함
	// ?뒤에 숫자는 모임고유번호(일단 임의로 주소줄에서 받아오기)
	// http://localhost:8088/club/boardWrite?club_no=1
	// 게시판글쓰기
	@RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
	public void boardWriteGet(@ModelAttribute("club_no") int club_no, HttpSession session) {
		log.info(" boardWriteGet() 호출 ");
		log.info(" club_no : "+club_no);
		
		session.setAttribute("member_no", 1);
		log.info("세션에 저장된 member_no : "+session.getAttribute("member_no"));
		
	}
	
	
	@RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
	public void boardWritePost(ClubBoardVo vo) {
		log.info(" boardWritePost() 호출 ");
		
		
		// 전달된 정보 저장(글쓰기 정보)
		log.info("글쓰기 정보 : "+vo);
		
		service.writeBoard(vo);
		log.info(" 글쓰기 완료! ");
		
//		return "redirect:/club/boardList";
	}
	
	
	// http://localhost:8088/club/boardList?club_no=1
	// 게시글리스트
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public void boardListGet(@ModelAttribute("club_no") int club_no) {
		log.info(" boardListGet() 호출 ");
		log.info("club_no : "+club_no);
		
//		List<ClubBoardVo> boardList = service.getBoardListAll(club_no);
//		log.info("boardList : "+boardList);
	}
	
}
