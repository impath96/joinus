package com.joinus.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joinus.auth.OauthService;
import com.joinus.domain.MemberVo;
import com.joinus.service.MemberService;

@RequestMapping(value="/member/*")
@Controller
public class MemberController {

	@Inject
	MemberService memberService;
	
	@Inject
	OauthService oauthService;

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	@Inject
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {

		return "/member/signup";

	}
	
	// GET : /signin -> 로그인 페이지 출력
	// 단순 이메일 로그인을 했을 경우 해당 유저 정보를 세션에 저장
	@RequestMapping(value="/signin", method = RequestMethod.GET)
	public String signInPage() {
		return "/member/signin";
	}
	
	// 일반적인 이메일 방식의 로그인
	@RequestMapping(value="/signin", method = RequestMethod.POST)
	public String signIn(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
		// 1) 입력받은 정보를 통해 실제 가입된 회원인지 확인
		MemberVo member = memberService.회원찾기(email);
		
		// 만약 member가 null -> 이메일이나 비밀번호가 틀렸다는 의미 
		if(member == null) {
			model.addAttribute("error", "로그인 실패");
			return "/login";
		}
		
		// 존재하는 회원일 경우 session에 해당 회원정보 저장
		session.setAttribute("member", member);
		
		// 그리고 메인 페이지로 이동 - 하지만 지금은 일단 마이페이지로
		return "redirect:/member/mypage";
	}
	
	// redirect용?
	@RequestMapping(value="/oauth/{service}", method = {RequestMethod.GET, RequestMethod.POST})
	public String oauthSignIn(@PathVariable("service") String service, @RequestParam("code") String code, RedirectAttributes rattr, HttpSession session) {
		
		// 1) 이미 가입되어져 있는 회원일 경우 바로 로그인 처리
		// 2) 만약 가입되어있지 않은 회원일 경우 추가 정보 입력 처리
		MemberVo resultMember = oauthService.oauthLogin(service, code);
		
		if(session.getAttribute("member") == null) {
			
		}
		
		System.out.println("c : " + resultMember);
		rattr.addFlashAttribute("member", resultMember);
		return "redirect:/member/requiredInfo";
	}
	
	@RequestMapping(value = "/requiredInfo")
	public String requiredInfo(@ModelAttribute("member") MemberVo member, Model model) {
		model.addAttribute("member", member);
		return "/member/requiredInfo";
	}
	
	@RequestMapping(value = "/mypage")
	public String mypage() {
		return "/member/mypage";
	}
	
	

}
