package com.joinus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joinus.domain.MembersVo;
import com.joinus.service.MemberService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	MemberService memberService;
	
	@GetMapping("")
	public String admin(Model model) {
		List<MembersVo> members = memberService.findMemberAll();
		model.addAttribute("members", members);
		return "/admin/admin";
	}
}
