package com.joinus.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	// 메인 페이지
	@GetMapping(value = "/")
	public String home(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
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
