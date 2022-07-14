package com.joinus.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joinus.auth.AuthInfo;
import com.joinus.auth.AuthLogin;
import com.joinus.auth.GoogleLogin;
import com.joinus.auth.KakaoLogin;
import com.joinus.domain.InterestsVo;
import com.joinus.domain.MembersVo;
import com.joinus.service.InterestService;
import com.joinus.service.MemberService;

@RequestMapping(value="/member/*")
@Controller
public class MemberController {

/*	에러떠서 주석처리ㅜㅜ
	
	@Autowired
	MemberService memberService;
	
	@Autowired 
	 private AuthInfo kakaoAuthInfo;
	 
	 @Autowired 
	 private AuthInfo googleAuthInfo;
	
	@Autowired
	InterestService interestService;

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	// 회원가입 페이지 이동
	@GetMapping("signup")
	public String signup(Model model, HttpSession session) {
		
		log.info("회원가입 페이지 이동  @@@@@@@@@@@@@@@@@@");
		
		String googleAuthUrl = kakaoAuthInfo.getRedirectUrl();
		String kakaoAuthUrl = googleAuthInfo.getRedirectUrl();
		
		model.addAttribute("googleUrl", googleAuthUrl);
		model.addAttribute("kakaoUrl", kakaoAuthUrl);
		return "/member/signup";

	}
	
	// 회원가입 처리
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(HttpSession session 
							, @RequestParam("location_name") String location_name
							, @RequestParam("member_tel") String member_tel
							, @RequestParam("interest") int interest_no) {
		log.info("회원가입 처리 동작 실행 @@@@@@@@@@@@@@");
		log.info("session에 저장되어 있는 member : {}", session.getAttribute("member"));
		log.info("추가로 전달받은 정보 : {}", interest_no);
		MembersVo sessionMember = (MembersVo)session.getAttribute("member");
		
		// 나중에 여기서 저장하기 전에 임의의 비밀번호도 설정해야한다.
		MembersVo signUpMember = MembersVo.builder()
				.member_email(sessionMember.getMember_email())
				.member_image(sessionMember.getMember_image())
				.member_name(sessionMember.getMember_name())
				.member_signup_type(sessionMember.getMember_signup_type())
				.member_tel(member_tel)
				.build();
		
		log.info("DB에 저장할 회원 정보 : {}", signUpMember);
		// 그러면 회원을 먼저 저장하고 해당 회원의 회원번호를 들고와서 회원관심사 테이블에 관심사를 등록?
		// 1) 회원가입 처리
		signUpMember.setMember_pass("asd123asc");
		memberService.회원가입(signUpMember, interest_no);
		
		return "redirect:/member/mypage";
		
	}
	
	// 마이 페이지로 이동
	@GetMapping("/mypage")
	public String mypage() {
		
		return "/member/mypage";
	}
	
	// 추가 정보 입력 페이지로 이동
	@GetMapping(value = "/signup/more-info")
	public String moreInfo(Model model, @ModelAttribute("member") MembersVo member, HttpSession session) {
		
		log.info("member : {}", member);
		// 관심사 정보도 넘겨줘야 한다.
		List<InterestsVo> interestList = interestService.selectInterestAll();
		log.info("관심사 목록 : {}", interestList);
		
		model.addAttribute("interestList", interestList);
		model.addAttribute("member", member);
		session.setAttribute("member", member);
		return "/member/moreInfo";

	}
	
	// 로그인 페이지로 이동
	// 단순 이메일 로그인을 했을 경우 해당 유저 정보를 세션에 저장
	@GetMapping(value="/signin")
	public String signInPage(Model model) {
		String googleAuthUrl = kakaoAuthInfo.getRedirectUrl();
		String kakaoAuthUrl = googleAuthInfo.getRedirectUrl();
		
		model.addAttribute("googleUrl", googleAuthUrl);
		model.addAttribute("kakaoUrl", kakaoAuthUrl);
		return "/member/signin";
	}
	
	// 로그인 동작 처리
	// 일반적인 이메일 방식의 로그인
	@RequestMapping(value="/signin", method = RequestMethod.POST)
	public String signIn(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
		// 1) 입력받은 정보를 통해 실제 가입된 회원인지 확인
		// 회원찾기에서 비밀번호 암호화까지 다 하자.
		MembersVo findMember = null;
		
		//memberService.회원찾기(email, password);
		
		// 만약 member가 null -> 이메일이나 비밀번호가 틀렸다는 의미 
		if(findMember == null) {
			model.addAttribute("error", "로그인 실패");
			return "/login";
		}
		
		// 존재하는 회원일 경우 session에 해당 회원정보 저장
		session.setAttribute("member", findMember);
		
		// 그리고 메인 페이지로 이동 - 하지만 지금은 일단 마이페이지로
		return "redirect:/member/mypage";
	}
	
	// 소셜 로그인 처리
	@GetMapping(value = "/oauth/{service}")
	public String oauthSignIn(@PathVariable("service") String service, @RequestParam("code") String code, Model model,
			HttpSession session, RedirectAttributes rattr) throws IOException, InterruptedException, ExecutionException {
		log.info("소셜 로그인 동작 처리");
		// 1) code를 통해 인증 서버에 access_token 발급 요청
		// 2) 요청 받은 access_token으로 사용자 정보 요청
		// 3) 전달받은 사용자 정보를 통해 필요한 데이터 추출
		// 4) 이메일을 통해 회원가입이 되어있는 사용자인지 판단
		// -1) 회원가입이 되어있지 않은 사용자일 경우 기존 사용자 정보를 포함하여 추가 정보 입력 페이지로 이동
		// -2) 회원가입이 되어있는 사용자일 경우 바로 mypage로 이동

		// 참고 1) 로그인완료, 회원가입 완료시 사용자 정보를 session에 저장
		// 2) 로그인완료, 회원가입 완료시 access_token 정보를 cookie에 저장??
		// 3) 만약 access_token값이 있거나 session 정보가 있다면 바로 로그인처리
		// 4) 로그아웃을 했더라고 기존 사용자일 경우 바로 로그인 처리
		AuthInfo authInfo = null;
		AuthLogin authLogin = null;
		
		if(session.getAttribute("access_token") != null) {
			// 바로 로그인 처리 - 이미 회원가입유저라는 의미 
			// 그런데 이제 access_token 만료 기간이 종료되었을 때도 고려해야하는데??
			return "redirect:/member/mypage";
			
		}
		
		// 리팩토링 될거 같은데?
		if(service.equalsIgnoreCase("kakao")) {
			authInfo = kakaoAuthInfo;
			log.info("kakao 객체");
			authLogin = new KakaoLogin(authInfo);
		}else {
			authInfo = googleAuthInfo;
			log.info("google 객체");
			authLogin = new GoogleLogin(authInfo);
		}
		// 
		// code를 통해 회원정보 획득
		MembersVo member = authLogin.getMemberInfo(code, session);
		log.info("code로 얻은 회원 정보 : {}", member);
		// 전달받은 사용자 정보를 통해 가입자인지 아닌지 판단
		
		log.info("회원 이메일 주소 : {}", member.getMember_email());
		MembersVo findMember = memberService.findMemberByEmail(member.getMember_email());
		log.info("DB에서 찾은 정보 : {}", findMember);
		
		
		// 만약 가입자가 아닐 경우 응답받은 사용자 정보를 가지고 추가정보입력 페이지로 이동
		if(findMember == null) {
			log.info(" RedirectAttributes에 넣을 member = {}", member);
			rattr.addFlashAttribute("member", member);
			return "redirect:/member/signup/more-info";
		}
		
		session.setAttribute("member", findMember);
		
		return "redirect:/member/mypage";
	}
	
	// 비밀번호 암호화 로직 구현 - SHA-256
	private void encryptPassword(String hashAlgorithm, String password) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(hashAlgorithm);
			messageDigest.update(password.getBytes());
			
			byte[] encPassword = messageDigest.digest();
			
			// 암호화된 바이트 데이터를 16진수 형태로 변환
			String hashedPassword = "";
			
			for(int i=0; i<encPassword.length; i++) {
				hashedPassword += Integer.toHexString(encPassword[i]&0xFF).toUpperCase();
			}
			log.info("비밀번호 암호화 완료(password) : ", hashedPassword);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	 
	*/
}
