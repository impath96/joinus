package com.joinus.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubGradesVo;
import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.InterestDetailsVo;
import com.joinus.domain.InterestsVo;
import com.joinus.domain.MembersVo;
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

	
	
	
	//============= 모임 등록 ==========================================
		
		// http://localhost:8088/club/new  모임등록 페이지
		// http://localhost:8088/club/new?member_no=7
		@RequestMapping(value="/new", method = RequestMethod.GET)
		public String newClubGet(Model model, /* @ModelAttribute("member_no") int member_no, */ HttpSession session) {
				
			//회원넘버 세션으로 받을 시(넘겨주면 그걸로 받기)
			session.setAttribute("member_no", 7); //세션값 임의생성 
			int member_no =	(int)session.getAttribute("member_no");
				
			//회원 이름 출력
			MembersVo membervo = service.getMember(member_no);
			model.addAttribute("membervo", membervo);
			//회원 관심사 출력
			InterestsVo interestvo = service.getMemberInterest(member_no);
			model.addAttribute("interest", interestvo);
			
			return "/club/clubNew";
				 
	}
	
	
		// 상세관심사 ajax (등록페이지에 출력)
		// http://localhost:8088/club/getdetail
		@ResponseBody
		@RequestMapping(value="/getdetail", method = RequestMethod.GET)
		public List<InterestDetailsVo> test(@RequestParam("itemNum") int itemNum) {
			List<InterestDetailsVo> detailList = service.getDetailName(itemNum);
			return detailList;
	}
	
		
		@RequestMapping(value = "/new", method = RequestMethod.POST)
		public String createClubPost(@RequestParam("interest_detail_name") String detail,
				@RequestParam("member_no") int member_no, MultipartFile file,
				ClubsVo clubsvo,Model model,HttpSession session,HttpServletRequest request ) throws IOException {
		
				log.info("모임등록 호출");
			
			  //모임 사진등록 
				if(!file.isEmpty()) { 
				
				//가상업로드 폴더 설정
				ServletContext ctx =request.getServletContext();
				String realpath = ctx.getRealPath("/resources/upload/clubs");
				log.info("파일저장경로: " +realpath);
				
				//realpath 경로에 폴더 있는지 확인
				File savePath = new File(realpath);
				if(!savePath.exists()) {
					savePath.mkdirs();
				} //없다면 폴더 만듦
				
				
				//String FileName = file.getOriginalFilename();
				String savedFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				log.info("파일명: "+savedFileName);

				
				String fullpath = realpath;
				fullpath += File.separator + savedFileName;
				File saveFile = new File(fullpath);
				
				
				file.transferTo(saveFile);
				clubsvo.setClub_image(savedFileName);
				
				
				log.info("사진저장 완료");
				}
			  
				
				  //관심사번호 가져오기 
				  InterestDetailsVo interDetail = service.getInterestNo(detail);
				  //클럽정보 저장 + 넘버가져오기 
				  service.newClub(clubsvo); 
				  int club_no = (int)clubsvo.getClub_no();
				  model.addAttribute("club_no", club_no); 
				  //모임관심사 저장
				  service.newClubInterest(club_no,interDetail.getInterest_no(),interDetail.getInterest_detail_no()); 
				  //모임가입
				  ClubMembersVo members = new ClubMembersVo(); 
				  members.setClub_no(club_no);
				  members.setMember_no(member_no); 
				  members.setClub_member_role("admin"); //모임 첫생성은 관리자
				  service.join(members);
				  
				  model.addAttribute("member_no", member_no);
				 
				  return "redirect:/club/{club_no}";
		}
	
	
		// 모임상세페이지		
		// http://localhost:8088/club/	
		@RequestMapping(value = "/{club_no}", method = RequestMethod.GET)
		public String info(Model model,HttpSession session,
						@PathVariable("club_no") int club_no,
						@ModelAttribute("member_no") int member_no) {
				
				
				//모임정보
				ClubsVo clubvo = service.getClubInfo(club_no);
				model.addAttribute("clubvo", clubvo);
				
				//회원번호(세션)
				session.setAttribute("member_no", 44);
				model.addAttribute("member_no", (int)session.getAttribute("member_no"));
				
				//클럽회원정보
				List<ClubMembersVo> clubmemberVO = service.getClubMembers(club_no);
				model.addAttribute("clubmemberVO", clubmemberVO);
				
				//클럽별점정보
				List<ClubGradesVo> gradevo = service.getClubGrade(club_no);
				model.addAttribute("clubGrade", gradevo);
				
				//클럽별점 평균,참여자수
				model.addAttribute("gradeAvgCnt", service.getClubAvgCnt(club_no));   
								
				//모임관심사 정보로 관심사 가져오기
				
				return "/club/clubInfo";
			}
			
	
	
		// 모임가입 ajax (상세페이지에서 alert창 띄움)
		@ResponseBody
		@RequestMapping(value = "/join/{club_no}",method=RequestMethod.POST)
		public void joinClub(@PathVariable("club_no") int club_no, @ModelAttribute("member_no") int member_no) {
				
				ClubMembersVo members = new ClubMembersVo();
				members.setMember_no(member_no);
				members.setClub_no(club_no);
				members.setClub_member_role("common"); //상세페이지에서 가입하면 무조건 회원
				service.join(members);
				System.out.println("모임가입완료");
				
			}
			
		// 별점주기 ajax (상세페이지에서 클릭)
		@ResponseBody
		@RequestMapping(value = "/grade", method = RequestMethod.POST)
		public void clubGrade(@ModelAttribute ClubGradesVo vo) {
					service.clubGrade(vo);
					System.out.println("별점주기 완료");
			}
	
	
	
			
			
}
