package com.joinus.controller;





import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardsVo;
import com.joinus.service.ClubService;


@Controller
@RequestMapping("/club/*")
public class ClubController {
	
	@Inject
	private ClubService service;
	
	private static final Logger log = LoggerFactory.getLogger(ClubController.class);
	
	//http://localhost:8088/club/clubList
	//http://localhost:8088/club/clubList?interest_no=2
//	@RequestMapping(value="/clubList", method = RequestMethod.GET)
//	public void clubList(@ModelAttribute("interest_no") String interest_no, Model model) {
//		log.info("interest_no : "+interest_no);	
//		
//		if(interest_no.isEmpty()) {
//			model.addAttribute("clubList", service.clubList());
//			log.info("clubList() 호출");
//		}else{
//			model.addAttribute("clubList", service.clubList(Integer.parseInt(interest_no)));
//			log.info("clubList(no) 호출");
//		}
//	}
	
	//http://localhost:8088/club/clubMember?club_no=1
//	@RequestMapping(value="/clubMember", method = RequestMethod.GET)
//	public void clubMember(Model model, int club_no) {
//		log.info("clubMember() 호출");
//		
//		club_no = 1;
//		model.addAttribute("clubMemberList",service.clubMemberListAll(club_no));
//	}
	
	//http://localhost:8088/club/clubMeeting
//	@RequestMapping(value="/clubMeeting", method = RequestMethod.GET)
//	public void clubMeeting() {
//		log.info("clubMeeting() 호출");
//	}
	
	
	
	
	
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
	public String boardWritePost(ClubBoardsVo vo) {
		log.info(" boardWritePost() 호출 ");
		
		
		// 전달된 정보 저장(글쓰기 정보)
		log.info("글쓰기 정보 : "+vo);
//		log.info("club_no : "+vo.getClub_no());
		int club_no = vo.getClub_no();
		
		service.writeBoard(vo);
		log.info(" 글쓰기 완료! ");
		
		return "redirect:/club/boardList?club_no="+club_no;
	}
	
	
	// http://localhost:8088/club/boardList?club_no=1
	// http://localhost:8088/club/boardList?club_no=1&board_type_no=2
	// 게시글리스트
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public void boardListGet(@ModelAttribute("club_no") String club_no, @ModelAttribute("board_type_no") String board_type_no, Model model) {
		log.info(" boardListGet() 호출 ");
		log.info("club_no : "+club_no);
		log.info("board_type_no : "+board_type_no);
		
		model.addAttribute("club_no", club_no);
		
		if(board_type_no.isEmpty()) {
			model.addAttribute("boardList", service.getBoardListAll(Integer.parseInt(club_no)));
			
		} else {
			model.addAttribute("boardList", service.getBoardList(Integer.parseInt(club_no), Integer.parseInt(board_type_no)));
		}
	}

	
}
