package com.joinus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joinus.domain.Criteria;
import com.joinus.domain.MembersVo;
import com.joinus.domain.PageMaker;
import com.joinus.service.MemberService;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	MemberService memberService;
	
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
		pageMaker.setDisplayPageNum(3);
		pageMaker.setTotalCount(memberService.getTotalCount());
		log.info("pageMaker : {}", pageMaker);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/admin/memberList";
	}
	
	
	
}
