package com.joinus.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.joinus.domain.ClubsVo;
import com.joinus.domain.MembersVo;
import com.joinus.service.MainService;

@Controller
public class HomeController {
	
	@Inject
	private MainService service;
	
	@GetMapping(value = "/")
	public String home(Locale locale, Model model, MembersVo members) {
		
		List<ClubsVo> vo1 = service.getMostPopularClub();
		model.addAttribute("popular", vo1);
		List<ClubsVo> vo2 = service.getMostRecentClub();
		model.addAttribute("latest", vo2);
		List<ClubsVo> vo3 = service.getMostNumerousClub();
		model.addAttribute("Numerous", vo3);
		
		members.setMember_location("부산진구");
		if(members != null) {
			List<ClubsVo> vo4 = service.getMyClubs(members.getMember_location());
			model.addAttribute("my", vo4);
		}
		
		return "main";
	}
	
	@GetMapping(value = "/signup")
	public String signup() {
		
		return "/member/signup";
	}
	
	// 로그아웃 처리
	@GetMapping(value = "/signout")
	public String signout(HttpSession session) {
		session.invalidate();
		return "/";
	}
			
			
	
	
	
}
