package com.joinus.controller;





import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
<<<<<<< HEAD
=======
import javax.servlet.http.HttpServletResponse;
>>>>>>> 2cdc62da9acaf0daacde06f0c3d3be4409873b56
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardsVo;
import com.joinus.service.ClubService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


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
		
		session.setAttribute("member_no", 11);
		log.info("세션에 저장된 member_no : "+session.getAttribute("member_no"));
		
	}
	
	// 파일 X
	@RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
<<<<<<< HEAD
	public String boardWritePost(ClubBoardsVo vo, HttpServletRequest request) {
=======
	public String boardWritePost(ClubBoardsVo vo, @RequestParam("count") int count) {
>>>>>>> 2cdc62da9acaf0daacde06f0c3d3be4409873b56
		log.info(" boardWritePost() 호출 ");
		
		
		// 전달된 정보 저장(글쓰기 정보)
		log.info("글쓰기 정보 : "+vo);
		log.info("count : "+count);
		
		service.writeBoard(vo);
		log.info(" 글쓰기(파일X) 완료! ");
		
		int club_no = vo.getClub_no();
		
		return "redirect:/club/boardList?club_no="+club_no;
	}
	
	// 파일 O
	@RequestMapping(value = "/boardFileWrite", method = RequestMethod.POST)
	public String boardFileWritePost(ClubBoardsVo vo, @RequestParam("count") int count, HttpServletRequest request, MultipartFile file) {
		log.info(" boardFileWritePost() 호출 ");
		
		// 1) 파일 업로드
		// - 가상의 업로드 폴더 설정 upload 폴더 생성
		String path = request.getRealPath("/upload");
		log.info(" 파일 저장 경로 : "+path);
		
		
		log.info("파일명 : "+file.getOriginalFilename()); 
		
		// 업로드 파일 크기(10MB)
//		int maxSize = 10 * 1024 * 1024;
		
//		MultipartRequest multi = null;
		
//		try {
//			multi = new MultipartRequest(
//						request,
//						path,
//						maxSize,
//						"UTF-8",
//						new DefaultFileRenamePolicy()
//					);
//			
//			log.info(multi.getFilesystemName("file")); // 파일이름
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 전달된 정보 저장(글쓰기 정보)
		log.info("글쓰기 정보 : "+vo);
		log.info("count : "+count);
		log.info("request 파일 : "+request.getParameter("file"));
		log.info("request count : "+request.getParameter("count"));
//		log.info("club_no : "+vo.getClub_no());
		int club_no = vo.getClub_no();
		
		
		
		
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
