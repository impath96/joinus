package com.joinus.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joinus.domain.ClubsVo;
import com.joinus.service.MainService;

@Controller
public class HomeController {
	
	@Inject
	private MainService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		List<ClubsVo> vo1 = service.getMostPopularClub();
		model.addAttribute("popular", vo1);
		List<ClubsVo> vo2 = service.getMostRecentClub();
		model.addAttribute("latest", vo2);
		List<ClubsVo> vo3 = service.getMostNumerousClub();
		model.addAttribute("Numerous", vo3);
		
		return "main";
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home2(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
