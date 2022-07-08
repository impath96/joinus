package com.joinus.controller;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joinus.auth.OauthService;
import com.joinus.domain.MembersVo;
import com.joinus.service.MemberService;

@RequestMapping(value="/member/*")
@Controller
public class MemberController {

	@Inject
	MemberService memberService;
	
	@Inject
	OauthService oauthService;
	
	@Inject
	private JavaMailSender mailSender;

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	@Inject
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		
		model.addAttribute("membersVo",new MembersVo());
		return "/member/signup";

	}
	
	// http://localhost:8080/member/signup
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(Model model, @ModelAttribute("membersVo") @Valid MembersVo vo, BindingResult result) throws Exception {
		
		if(result.hasErrors()) {
			
			
			List<ObjectError> list = result.getAllErrors();
			
			for( ObjectError error : list) {
				
				System.out.println(error);
			}
			return "/member/signup";
		}
		
		log.info(" C: " + vo);
		memberService.회원가입(vo);
		
		log.info(" C: 회원가입 처리 완료");
		
		return "redirect:/login";
	}
	
	// 이메일 인증
	@ResponseBody
	@RequestMapping(value = "/emailAuth", method = RequestMethod.POST)
	public String emailAuth(String email) {		
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;

		/* 이메일 보내기 */
        String setFrom = "hd2080277@naver.com";
        String toMail = email;
        String title = "회원가입 인증 이메일 입니다.";
        String content = 
                "JoinUs 홈페이지를 방문해주셔서 감사합니다." +
                "<br><br>" + 
                "인증 번호는 " + checkNum + "입니다." + 
                "<br>" + 
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        
        try {
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return Integer.toString(checkNum);
 
	}
	
	// GET : /signin -> 로그인 페이지 출력
	// 단순 이메일 로그인을 했을 경우 해당 유저 정보를 세션에 저장
	@RequestMapping(value="/signin", method = RequestMethod.GET)
	public String signInPage() {
		return "/login";
	}
	
	// 일반적인 이메일 방식의 로그인
	@RequestMapping(value="/signin", method = RequestMethod.POST)
	public String signIn(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
		// 1) 입력받은 정보를 통해 실제 가입된 회원인지 확인
		MembersVo member = memberService.회원찾기(email);
		
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
		MembersVo resultMember = oauthService.oauthLogin(service, code);
		
		if(session.getAttribute("member") == null) {
			
		}
		
		System.out.println("c : " + resultMember);
		rattr.addFlashAttribute("member", resultMember);
		return "redirect:/member/requiredInfo";
	}
	
	@RequestMapping(value = "/requiredInfo")
	public String requiredInfo(@ModelAttribute("member") MembersVo member, Model model) {
		model.addAttribute("member", member);
		return "/member/requiredInfo";
	}
	
	@RequestMapping(value = "/mypage")
	public String mypage() {
		return "/member/mypage";
	}
	
	

}
