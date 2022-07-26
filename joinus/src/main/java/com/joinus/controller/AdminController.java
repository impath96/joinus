package com.joinus.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joinus.domain.Criteria;
import com.joinus.domain.MembersVo;
import com.joinus.domain.PageMaker;
import com.joinus.service.ClubService;
import com.joinus.service.MemberService;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	ClubService clubService;
	
	// 관리자 페이지 출력
	@GetMapping("")
	public String admin(Model model) {

		return "/admin/admin";
	}
	
	// 관리자 - 회원 목록 페이지 출력
	@GetMapping("/members")
	public String members(Criteria cri, Model model) {
		
		cri.setPerPageNum(15);
		List<MembersVo> members = memberService.findMemberAll(cri);
		model.addAttribute("members", members);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setDisplayPageNum(4);
		pageMaker.setTotalCount(memberService.getTotalCount());
		log.info("pageMaker : {}", pageMaker);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/admin/memberList";
	}
	
	// 회원 삭제
	@PostMapping("/members/delete")
	public String delete(@RequestParam(value="member_no") List<String> checkedMemberNo) {
		
		// String 값들을 Integer 타입으로 변환
		List<Integer> idList = checkedMemberNo.stream()
											  .map(id->Integer.parseInt(id))
											  .collect(Collectors.toList());
		
		memberService.deleteMember(idList);
		return "";
	}
	
	// 관리자 - 모임 목록 페이지 출력
	@GetMapping("/clubs")
	public String clubs(Criteria cri, Model model) {
		cri.setPerPageNum(15);
		model.addAttribute("clubList", clubService.getClubListForAdmin(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(clubService.totalCnt());
		log.info(pageMaker+"");
		model.addAttribute("pageMaker", pageMaker);
		log.info("clubList() 호출");
		
		return "/admin/clubList";
	}
	
	
	
}
