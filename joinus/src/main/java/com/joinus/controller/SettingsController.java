package com.joinus.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import com.joinus.domain.MembersVo;
import com.joinus.service.MemberService;

@RequestMapping("/settings/*")
@Controller
public class SettingsController {
	@Autowired
	MultipartResolver multipartResolver;
	
	MemberService memberService;
	
	private static final Logger log = LoggerFactory.getLogger(SettingsController.class);

	@Autowired
	public SettingsController(MemberService memberService) {
		this.memberService = memberService;
	}


	// 회원 계정 설정 페이지
	@GetMapping("member")
	public String settings(HttpSession session) {
		
		// 1) 세션에 회원 정보가 없으면 메인 페이지로 리다이랙트
		MembersVo member = (MembersVo)session.getAttribute("member");
		if(member == null) {
			return "redirect:/";
		}
		return "/member/settings";
	}
	
	@PostMapping("/profile")
	public String profile(@RequestParam("profile_image") MultipartFile file, Model model) {
		log.info("upload Post ... originalName={}", file.getOriginalFilename());
		log.info("upload Post ... size={}", file.getSize());
		log.info("upload Post ... contentType={}", file.getContentType());
		
		return "redirect:/settings/member";
		
	}
}
