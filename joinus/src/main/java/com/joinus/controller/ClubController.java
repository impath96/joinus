package com.joinus.controller;
import java.util.List;





import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

import com.joinus.domain.BoardCommentsVo;
import com.joinus.domain.BoardCriteria;
import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.Criteria;
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
	// 게시글 상세보기 (모임가입한 사람만 확인가능)
	@RequestMapping(value = "/{club_no}/boards/{club_board_no}", method = RequestMethod.GET)
	public String boardContentGet(@PathVariable("club_no") Integer club_no, @PathVariable("club_board_no") Integer club_board_no, 
			Model model, HttpSession session) {
		log.info(" boardContentGet() 호출 ");
		log.info(service.getBoardContent(club_board_no)+"");
		
		model.addAttribute("vo", service.getBoardContent(club_board_no));
		
		int commentCnt = service.getCommentCnt(club_board_no);
		model.addAttribute("commentCnt", commentCnt);
		
		// 해당글의 댓글이 있는지 체크(댓글리스트 가져오기)
		if(commentCnt > 0) {
			model.addAttribute("commentList", service.getCommentList(club_board_no));
		}
		
		model.addAttribute("likeCnt", service.getLikeCnt(club_board_no));
		
		// 좋아요 체크
		session.setAttribute("member_no", 11);
		int member_no = (int) session.getAttribute("member_no");
		// checkLike - 1:좋아요O / 0:좋아요X
		model.addAttribute("checkLike", service.checkLike(club_board_no, member_no));
		
		// 좋아요 멤버리스트
		model.addAttribute("likeList", service.getLikeList(club_board_no));
		
		
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
	
	// http://localhost:8088/club/{club_no}/boards/{club_board_no}/comment
	// http://localhost:8088/club/1/boards/24/comment
	// 댓글 등록
	@ResponseBody
	@RequestMapping(value = "/{club_no}/boards/{club_board_no}/comment", method = RequestMethod.POST)
	public void writeCommentPost(@PathVariable("club_no") Integer club_no, @PathVariable("club_board_no") Integer club_board_no, HttpSession session, BoardCommentsVo vo) {
		log.info(" writeCommentPost() 호출 ");
		
		// 전달받은 데이터
		log.info("commentVo : "+vo);
		
		// 작성자(member_no) 세션에서 꺼내쓰기(게시판 글쓰기 실행시키고 와야함)
		session.setAttribute("member_no", 11);
		int member_no = (int) session.getAttribute("member_no");
		vo.setMember_no(member_no);
		log.info("member추가 vo : "+vo);
		
		service.writeComment(vo);
		service.updateCommentCnt(club_board_no);
		log.info(" 댓글등록 완료! ");
		
		
	}
	
	// http://localhost:8088/club/{club_no}/boards/{club_board_no}/comment/modify?board_comment_no="+board_comment_no
	// 댓글 수정
	@ResponseBody
	@RequestMapping(value = "/{club_no}/boards/{club_board_no}/comment/modify", method = RequestMethod.POST)
	public void updateCommentPost(@PathVariable("club_no") int club_no, @PathVariable("club_board_no") int club_board_no, 
			@RequestParam("board_comment_no") int board_comment_no, BoardCommentsVo vo) {
		log.info(" updateCommentPost() 호출 ");
		log.info("board_comment_no : "+board_comment_no);
		log.info("boardCommentsVo : "+vo);
		
		service.updateComment(vo);
		log.info(" 댓글수정 완료! ");
	}
	
	// http://localhost:8088/club/{club_no}/boards/{club_board_no}/comment/delete?board_comment_no="+board_comment_no
	// 댓글 삭제
	@ResponseBody
	@RequestMapping(value = "/{club_no}/boards/{club_board_no}/comment/delete", method = RequestMethod.POST)
	public void deleteCommentPost(@PathVariable("club_no") int club_no, @PathVariable("club_board_no") int club_board_no,
			@RequestParam("board_comment_no") int board_comment_no) {
		log.info(" deleteCommentPost() 호출 ");
		
		service.deleteComment(board_comment_no);
		service.decreaseCommentCnt(club_board_no);
		log.info(" 댓글삭제 완료! ");
		
	}
	
	// http://localhost:8088/club/{club_no}/boards/{club_board_no}/likeDown
	// 좋아요 취소
	@ResponseBody
	@RequestMapping(value = "/{club_no}/boards/{club_board_no}/likeDown", method = RequestMethod.POST)
	public void cancleLikePost(@PathVariable("club_no") int club_no, @PathVariable("club_board_no") int club_board_no) {
		log.info(" cancleLikePost() 호출 ");
	}
	
	// http://localhost:8088/club/{club_no}/boards/{club_board_no}/likeUp
	// 좋아요 등록
	@ResponseBody
	@RequestMapping(value = "/{club_no}/boards/{club_board_no}/likeUp", method = RequestMethod.POST)
	public void LikePost(@PathVariable("club_no") int club_no, @PathVariable("club_board_no") int club_board_no) {
		log.info(" LikePost() 호출 ");
	}

	
}
