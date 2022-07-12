package com.joinus.controller;
import java.util.List;





import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joinus.domain.BoardCriteria;
import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubGradesVo;
import com.joinus.domain.ClubMeetingsVo;
import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.InterestDetailsVo;
import com.joinus.domain.InterestsVo;
import com.joinus.domain.MemberDipsVo;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.Criteria;
import com.joinus.domain.MembersVo;
import com.joinus.domain.PageMaker;
import com.joinus.service.Clubservice;


@Controller
@RequestMapping("/club/*")
public class ClubController {
	
	@Inject
	private Clubservice service;
	
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
	
	//http://localhost:8088/club/clubMeeting
//	@RequestMapping(value="/clubMeeting", method = RequestMethod.GET)
//	public void clubMeeting() {
//		log.info("clubMeeting() 호출");
//	}
	
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

	
	
	
	//================================================================================================
	
	// 파라미터를 전달하고 싶을 때는 보내주는 주소와 받는 주소 모두 다 modelAttribute를 사용해야 함
	// ?뒤에 숫자는 모임고유번호(일단 임의로 주소줄에서 받아오기)
	// http://localhost:8088/club/{club_no}/boards/new
	// http://localhost:8088/club/1/boards/new
	// 게시판글쓰기
	@RequestMapping(value = "/{club_no}/boards/new", method = RequestMethod.GET)
	public String boardWriteGet(@PathVariable("club_no") Integer club_no, HttpSession session) {
		log.info(" boardWriteGet() 호출 ");
		log.info(" club_no : "+club_no);
		
		session.setAttribute("member_no", 11);
		log.info("세션에 저장된 member_no : "+session.getAttribute("member_no"));
		
		return "/club/boards/boardWrite";
	}
	
	// 파일 X
	@RequestMapping(value = "/{club_no}/boards/new", method = RequestMethod.POST)
	public String boardWritePost(ClubBoardsVo vo) {
		log.info(" boardWritePost() 호출 ");
		
		
		// 전달된 정보 저장(글쓰기 정보)
		log.info("글쓰기 정보 : "+vo);
//		log.info("count : "+count);
		
		service.writeBoard(vo);
		log.info(" 글쓰기(파일X) 완료! ");
		
		int club_no = vo.getClub_no();
		
		return "redirect:/club/"+club_no+"/boards";
		//return "redirect:/club/boardList?club_no="+club_no;
	}
	
	// 파일 O
	@RequestMapping(value = "/{club_no}/boards/fileNew", method = RequestMethod.POST)
	public String boardFileWritePost(ClubBoardsVo vo, HttpServletRequest request, MultipartFile file) {
		log.info(" boardFileWritePost() 호출 ");
		
		// 1) 파일 업로드
		// - 가상의 업로드 폴더 설정 upload 폴더 생성
		ServletContext ctx = request.getServletContext();
		String realPath = ctx.getRealPath("/resources/upload/boards");
		log.info(" 파일 저장 경로 : "+realPath);
		
		// realPath 경로에 파일업로드하기 위한 폴더가 있는지 확인
		File savePath = new File(realPath);
		if(!savePath.exists()) {
			savePath.mkdirs(); // 없으면 경로에 폴더를 만들어줌
		}
		
		
//		String fileName = file.getOriginalFilename();
		String savedFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		log.info("파일명 : "+savedFileName);
		
		String fullPath = realPath;
		
//		realPath += File.separator + fileName;
//		File savefile = new File(realPath);
		
		fullPath += File.separator + savedFileName;
		File saveFile = new File(fullPath);
		
		// 경로+파일명
//		log.info("@@@@@@@@@최종 fullPath : "+fullPath);
		
		// 파일 저장
		try {
			
			file.transferTo(saveFile);
			
			// 썸네일
			File thumbnailFile = new File(realPath, "sm_"+savedFileName);
			BufferedImage bo_image = ImageIO.read(saveFile);
			BufferedImage bt_image = new BufferedImage(200, 150, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = bt_image.createGraphics();
			graphic.drawImage(bo_image, 0, 0, 200, 150, null);
			ImageIO.write(bt_image, "jpg", thumbnailFile);
			
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		vo.setClub_board_image(savedFileName);
		// 전달된 정보 저장(글쓰기 정보)
//		log.info("글쓰기 정보 : "+vo);
		
		service.writeBoard(vo);
		log.info(" 글쓰기(파일O) 완료! ");
		
		int club_no = vo.getClub_no();
		
		return "redirect:/club/"+club_no+"/boards";
		//return "redirect:/club/boardList?club_no="+club_no;
	}
	
	
	// http://localhost:8088/club/{club_no}/boards
	// http://localhost:8088/club/1/boards
	// 게시글리스트
	@RequestMapping(value = "/{club_no}/boards", method = RequestMethod.GET)
	public String boardListAllGet(@PathVariable("club_no") Integer club_no, Model model) {
		log.info(" boardListAllGet() 호출 ");
		log.info("club_no : "+club_no);
		
		
		List<BoardTotalBean> boardList = service.getBoardListAll(club_no);
		log.info("@@@@@@@@@@@@"+boardList.get(16)+"");
		
		
		model.addAttribute("club_no", club_no);
		
		model.addAttribute("boardList", service.getBoardListAll(club_no));
		
		return "/club/boards/boardList";
			
	}
	
	// http://localhost:8088/club/{club_no}/boards/type/{board_type_no}
	// http://localhost:8088/club/1/boards/type/1
	// 게시글리스트(게시글유형별)
	@RequestMapping(value = "/{club_no}/boards/type/{board_type_no}", method = RequestMethod.GET)
	public String boardListTypeGet(@PathVariable("club_no") Integer club_no, @PathVariable("board_type_no") Integer board_type_no, Model model) {
		log.info(" boardListTypeGet() 호출 ");
		log.info("club_no : "+club_no);
		log.info("board_type_no : "+board_type_no);
		
		model.addAttribute("club_no", club_no);
		
		model.addAttribute("boardList", service.getBoardList(club_no, board_type_no));
		
		return "/club/boards/boardList";
	}
	
	// http://localhost:8088/club/{club_no}/gallery
	// http://localhost:8088/club/1/gallery
	// 갤러리 게시판
	@RequestMapping(value = "/{club_no}/gallery", method = RequestMethod.GET)
	public String galleryBoardGet(@PathVariable("club_no") Integer club_no, Model model) {
		log.info(" galleryBoardGet() 호출 ");
		//log.info("club_no : "+club_no);	// view에서 ${club_no} 로 호출가능
		
		model.addAttribute("imageList", service.getBoardImageList(club_no));
		
		return "/club/boards/boardGallery";
	}
	
	
	/// http://localhost:8088/club/{club_no}/boards/{club_board_no}
	/// http://localhost:8088/club/1/boards/1
	// 게시글 상세보기
	@RequestMapping(value = "/{club_no}/boards/{club_board_no}", method = RequestMethod.GET)
	public String boardContentGet(@PathVariable("club_no") Integer club_no, @PathVariable("club_board_no") Integer club_board_no, Model model) {
		log.info(" boardContentGet() 호출 ");
		log.info(service.getBoardContent(club_board_no)+"");
		
		model.addAttribute("vo", service.getBoardContent(club_board_no));
		
		return "/club/boards/boardContent";
	}
	
	// http://localhost:8088/club/{club_no}/boards/{club_board_no}/modify
	// http://localhost:8088/club/1/boards/17/modify
	// 게시글 수정
	@RequestMapping(value = "/{club_no}/boards/{club_board_no}/modify", method = RequestMethod.GET)
	public String modifyBoardGet(@PathVariable("club_no") Integer club_no, @PathVariable("club_board_no") Integer club_board_no, Model model) {
		log.info(" modifyBoardGet() 호출 ");
		log.info("club_no : "+club_no);
		log.info("club_board_no : "+club_board_no);
	
		model.addAttribute("board", service.getBoardContent(club_board_no));
	
		return "/club/boards/boardModify";
	}
	
	@RequestMapping(value = "/{club_no}/boards/{club_board_no}/modify", method = RequestMethod.POST)
	public String modifyBoardPost(ClubBoardsVo vo) {
		log.info(" modifyBoardPost() 호출 ");
		int club_board_no = vo.getClub_board_no();
//		log.info("club_board_no : "+club_board_no);
//		log.info("vo : "+vo);
		
		service.modifyBoardContent(vo);
//		
		return "redirect:/club/"+vo.getClub_no()+"/boards";
		
	}
	
	// http://localhost:8088/club/{club_no}/boards/{club_board_no}/delete
	// http://localhost:8088/club/1/boards/26/delete
	// 게시글 삭제
	@RequestMapping(value = "/{club_no}/boards/{club_board_no}/delete", method = RequestMethod.POST)
	public String deleteBoardPost(@PathVariable("club_no") Integer club_no, @PathVariable("club_board_no") Integer club_board_no) {
		log.info(" deleteBoardPost() 호출 ");
		log.info("club_no : "+club_no);
		
		service.deleteBoard(club_board_no);
		
		return "redirect:/club/"+club_no+"/boards";
	}
	
	// http://localhost:8088/club/${club_no}/boards/${club_board_no}/comment
	// http://localhost:8088/club/1/boards/${club_board_no}/comment
	// 댓글 등록
	
	
	

	
	
	
	//============= 모임 등록 ==========================================
		
		// http://localhost:8088/club/new  모임등록 페이지
		// http://localhost:8088/club/new?member_no=7
		@RequestMapping(value="/new", method = RequestMethod.GET)
		public String newClubGet(Model model, /* @ModelAttribute("membervo") MembersVo membervo, */HttpSession session) {
				
			//세션(임의)
			session.setAttribute("member_no", 7); 
			int member_no =	(int)session.getAttribute("member_no");
			//회원정보출력(merge후 생략)
			MembersVo membervo = service.getMember(member_no);
			model.addAttribute("membervo", membervo);
			
			//회원 관심사 출력
			InterestsVo interestvo = service.getMemberInterest(membervo.getMember_no());
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
	
	
		// 모임 제목 중복체크 ajax 
		@ResponseBody
		@RequestMapping(value="/check_clubname", method = RequestMethod.GET)
		public int checkClubName(@RequestParam("club_name") String club_name) {
				
			ClubsVo vo=service.checkClubName(club_name);
				int result = 0;
		        if(vo != null) { result =  1; } //중복
		        else if(vo == null){ result =  0; } //없음
		        return result;
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
				String realpath = ctx.getRealPath("/resources/upload/clubs/");
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
		// http://localhost:8088/club/5
		@RequestMapping(value = "/{club_no}", method = RequestMethod.GET)
		public String info(Model model,HttpSession session, @PathVariable("club_no") int club_no) {
												//@ModelAttribute("membervo") MembersVo membervo
			
				//모임정보
				ClubsVo clubvo = service.getClubInfo(club_no);
				model.addAttribute("clubvo", clubvo);
				
				//회원정보(임의)
				session.setAttribute("member_no", 7);
				int m = (int)session.getAttribute("member_no");
				model.addAttribute("member_no",m );
				log.info("회원넘버: "+m);

				//모임회원 리스트
				List<ClubMembersVo> clubmemberList = service.getClubMembers(club_no);
				model.addAttribute("clubmemebrList", clubmemberList);
				//방문한 모임회원
				int result = 0; 
				ClubMembersVo clubmembersvo = service.getClubMemberNo(club_no,m); //membervo.getMember_no();
				  if(clubmembersvo != null) { 
					  result = clubmembersvo.getMember_no(); 
				  }else if(clubmembersvo == null) { 
					  result = 0; }
				model.addAttribute("clubmember", result);
				log.info("모임회원일시 회원번호, 아닐시 0: "+result);
				
				//별점정보
				List<ClubGradesVo> gradevo = service.getClubGrade(club_no);
				model.addAttribute("clubGrade", gradevo);
				//별점참여
				int result2 = 0;
				Integer graded = service.getGradeinfo(club_no,m);
				  if(graded != null) { 
					  result2 = graded; 
				  }else if(graded == null) { 
					  result2 = 0; }
				model.addAttribute("graded", result2);
				log.info("별점 참여시 회원번호, 미참여시 0: "+result2);
				
				//클럽별점 평균,참여자수
				model.addAttribute("gradeAvgCnt", service.getClubAvgCnt(club_no));   
								
				//모임관심사 정보로 관심사 가져오기
				String interDetail = service.getClubInterestDName(club_no);
				model.addAttribute("interDetail", interDetail);   
				log.info("모임 상세관심사"+interDetail);
				
				//찜 여부 확인
				model.addAttribute("dipMember", service.dip(clubvo.getClub_no()));   
				
				//정모리스트 
				List<ClubMeetingsVo> meetings = service.getMeetings(club_no);
				//게시글(사진빼오기)
				List<ClubBoardsVo> boards = service.getBoards(club_no);
				
				
				return "/club/clubInfo";
			}
			
	
	
		// 모임가입 ajax (상세페이지에서 alert창 띄움)
		@ResponseBody
		@RequestMapping(value = "/{club_no}",method=RequestMethod.POST)
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
		@RequestMapping(value = "/{club_no}/grade", method = RequestMethod.POST)
		public void clubGrade(@ModelAttribute ClubGradesVo vo) {
					service.clubGrade(vo);
					System.out.println("별점주기 완료");
			}
		
		// 찜하기 ajax (상세페이지에서 클릭)
		@ResponseBody
		@RequestMapping(value = "/{clubvo.club_no}/dip", method = RequestMethod.POST)
		public void clubDip(@ModelAttribute MemberDipsVo vo) {
			service.clubDip(vo.getMember_no(),vo.getClub_no());
			System.out.println("찜하기 완료");
			
		}
		// 찜취소 ajax (상세페이지에서 클릭)
		@ResponseBody
		@RequestMapping(value = "/{clubvo.club_no}/dipX", method = RequestMethod.POST)
		public void clubDipX(@ModelAttribute MemberDipsVo vo) {
			service.dipX(vo.getMember_no(),vo.getClub_no());
			System.out.println("찜 취소완료");
			
		}
		
		
		
		// 대관 결제 ajax
		
		
	
	
	
			
			
}
