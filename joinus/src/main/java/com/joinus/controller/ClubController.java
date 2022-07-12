package com.joinus.controller;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubMeetingsVo;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.Criteria;
import com.joinus.domain.MeetingTotalBean;
import com.joinus.domain.MembersVo;
import com.joinus.domain.PageMaker;
import com.joinus.service.ClubService;

@Controller
@RequestMapping("/club/*")
public class ClubController {
	
	@Inject
	private ClubService service;
	
	private static final Logger log = LoggerFactory.getLogger(ClubController.class);
	

	//http://localhost:8088/club/clubList?page=1
	//http://localhost:8088/club/clubList?interest_no=2
	@RequestMapping(value="/clubList", method = RequestMethod.GET)
	public void clubList(@ModelAttribute("interest_no") String interest_no,
						Criteria cri, Model model,HttpSession session) {
		log.info("interest_no : "+interest_no);	
		if(session.getAttribute("member") != null) {
			MembersVo member = (MembersVo) session.getAttribute("member");
			log.info(member+"");
			}
		
		if(interest_no.isEmpty()) {
			model.addAttribute("clubList", service.clubList(cri));
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(service.totalCnt());
			log.info(pageMaker+"");
			model.addAttribute("pm", pageMaker);
			log.info("clubList() 호출");
			
		}else{
			model.addAttribute("clubList", service.clubList(Integer.parseInt(interest_no),cri));
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(service.totalCnt(Integer.parseInt(interest_no)));
			log.info(pageMaker+"");
			model.addAttribute("pm", pageMaker);
			log.info("clubList(no) 호출");
			
		}
		
	}
	
	
	//http://localhost:8088/club/1/clubMembers
	@RequestMapping(value="/{club_no}/clubMembers", method = RequestMethod.GET)
	public String clubMember(Model model, 
							@PathVariable("club_no") Integer club_no, HttpSession session) throws Exception{
		log.info("clubMember() 호출");		
		
		//비회원
		int result = 0;
		
		if(session.getAttribute("member") != null) {
			MembersVo member = (MembersVo) session.getAttribute("member");
			log.info(member+"");
			
			int member_no =member.getMember_no();
			result = service.checkClubRole(club_no, member_no);
			//result = 3 : 클럽 미가입 회원
			//result = 1 : 클럽 가입 회원
			//result = 2 : 클럽장
		}
		
		List<ClubsVo> clubInfo = service.clubInfo(club_no);
		
		log.info("result : "+result);
		model.addAttribute("clubInfo", clubInfo);
		model.addAttribute("clubMemberList",service.clubMemberListAll(club_no));
		model.addAttribute("result", result);
		
		return "/club/clubMembers";
	}
	
	
	//http://localhost:8088/club/1/clubMember/ban
	@RequestMapping(value="/{club_no}/clubMembers/ban", method=RequestMethod.GET)
	public String clubMemberBan(@ModelAttribute("member_no") Integer member_no,
			@PathVariable("club_no") Integer club_no,
			RedirectAttributes rttr) {
		
		log.info("ban 호출");
		
		service.clubBan(member_no, club_no);
		log.info("ban 완료");
		
		rttr.addFlashAttribute("check","BANOK");
		
		return "redirect:/club/{club_no}/clubMembers";
		
	}
	//클럽 모임장 권한 이동
	@RequestMapping(value="/{club_no}/clubMembers/auth", method=RequestMethod.GET)
	public String clubMemberAuth(@ModelAttribute("member_no") Integer member_no,
			@PathVariable("club_no") Integer club_no,RedirectAttributes rttr) {
		
		log.info("auth 호출");
		service.clubAuth(member_no, club_no);
		
		
		rttr.addFlashAttribute("check","AUTHOK");
		
		return "redirect:/club/{club_no}/clubMembers";
		
	}
	
	//클럽 나가기
	@RequestMapping(value="/{club_no}/leave", method=RequestMethod.GET)
	public String clubLeave(@PathVariable("club_no") Integer club_no,
								HttpSession session, RedirectAttributes rttr) {
		log.info("clubLeave()호출");
		MembersVo member = (MembersVo) session.getAttribute("member");
		log.info(member+"");
		
		int member_no =member.getMember_no();
		
		if(service.checkClubRole(club_no, member_no) == 2) {
			rttr.addFlashAttribute("check","LEAVENOT");
			return "redirect:/club/{club_no}/clubMembers";
			
		}else {
			service.clubLeave(member, club_no);
			rttr.addFlashAttribute("check","LEAVEOK");
			return "redirect:/club/{club_no}/clubMembers";
		}
	}
	
	//http://localhost:8088/club/1/modify
		@RequestMapping(value = "/{club_no}/modify", method=RequestMethod.GET)
		public String clubModifyGET(@PathVariable("club_no") Integer club_no,
				HttpSession session, RedirectAttributes rttr, Model model) {
			
			log.info("clubModifyGET 이동");
			
			List<ClubsVo> clubInfo = service.clubInfo(club_no);
			log.info("club_no : "+ club_no);
			
			model.addAttribute("clubInfo", clubInfo);
			
			return "/club/clubModify";
		}

		//http://localhost:8088/club/1/meetingWrite
		@RequestMapping(value="/{club_no}/meetingWrite", method = RequestMethod.GET)
		public String meetingWritegPOST(Model model,
				@PathVariable("club_no") Integer club_no, HttpSession session) {
			
			log.info("meetingWritePOST() 호출");
			
			//멤버정보 가져오기
			//MembersVo member = (MembersVo) session.getAttribute("member");
			//int member_no =member.getMember_no();
			
			int member_no = 124;
			
			//예약정보 불러오기
			List<MeetingTotalBean> rentalList = (List<MeetingTotalBean>)service.getRental(member_no);
			log.info(rentalList+"");
			List<ClubsVo> clubInfo = service.clubInfo(club_no);
			log.info(clubInfo+"");
			model.addAttribute("clubInfo", clubInfo);
			model.addAttribute("rentalList", rentalList);
			return "/club/meetingWrite";
			
		}
		
		//http://localhost:8088/club/1/meeting/new
		@RequestMapping(value="/{club_no}/meeting/new", method = RequestMethod.POST)
		public String clubMeetingGET(Model model, ClubMeetingsVo vo, RedirectAttributes rttr,
				@PathVariable("club_no") Integer club_no, HttpSession session) {
			log.info("clubMeeting new() 호출");
			
			//log.info(vo+"");
			
			service.createMeeting(vo);
			rttr.addFlashAttribute("check","MeetingNew");
			
			return "redirect:/club/{club_no}/clubMembers";
		}
	
	
	
	//================================================================================================
	
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
	public void boardWritePost(ClubBoardsVo vo) {
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
