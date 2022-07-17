package com.joinus.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joinus.domain.MembersVo;
import com.joinus.domain.PartnerPlacesVo;
import com.joinus.domain.PaymentsVo;
import com.joinus.domain.RentalPlacesVo;
import com.joinus.service.ClubService;
import com.joinus.service.RentalService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
@RequestMapping("/rental/*")
public class RentalController {
	
	@Inject
	private ClubService clubService;
	
	@Inject
	private RentalService rentalService;
	
	private static final Logger log = LoggerFactory.getLogger(ClubController.class);
	
	private IamportClient api;
	
	public RentalController() {
		// REST API 키와 REST API secret
		this.api = new IamportClient("4450940620010058",
				"cd721e413ac18a65fe657ac002c45a9427ca9cb46aa7cca4e0600788bf9c7b4d1de7fcb996d24ccd");
	}
	
	
	
	//================================================================================================
	
	
	// http://localhost:8088/rental/partnerPlaceList
	// 대관리스트
	@RequestMapping(value = "/partnerPlaceList", method = RequestMethod.GET)
	public void placeListGet(Model model) {
		log.info(" placeListGet() 호출 ");
		
		// 모임장의 정보에 장소가 있으면 구 를 중심(위치), 없으면 시 중심 (구는 이름이 겹치는 게 많아서)
		// 장소유형은 일단 전체로
		List<PartnerPlacesVo> partnerPlaceList = rentalService.getAllPartnerPlaceList();
		
		// 리스트에서 주소는 (시 구)까지만 보이게
		for(PartnerPlacesVo vo :partnerPlaceList) {
			String[] addressArr = vo.getPartner_place_address().split(" ");
			String address = addressArr[0].substring(0, 2) + " " + addressArr[1];
//			log.info(address + " / 시 이름 길이 : "+addressArr[0].length());
			vo.setPartner_place_address(address);
		}
//		log.info("주소 자른 결과 : "+partnerPlaceList);
		model.addAttribute("partnerPlaceList", partnerPlaceList);
	}
	
	// http://localhost:8088/rental/partnerPlaces/{partner_place_no}
	// http://localhost:8088/rental/partnerPlaces/1
	// 대관상세
	@RequestMapping(value = "/partnerPlaces/{partner_place_no}", method = RequestMethod.GET)
	public String partnerPlaceContentGet(@PathVariable("partner_place_no") int partner_place_no, Model model) {
		log.info(" partnerPlaceContentGet() 호출 ");
		
		model.addAttribute("partnerPlace", rentalService.getPartnerPlaceContent(partner_place_no));
		
		return "/rental/partnerPlaceContent";
	}
	
	

	@RequestMapping(value = "/partnerPlaces/{partner_place_no}", method = RequestMethod.POST)
	public String partnerPlaceContentPost(PartnerPlacesVo partnerplacevo ,PaymentsVo paymentvo, 
			RentalPlacesVo rentalplacevo,Model model, @RequestParam("rental_time") int rentaltime,
			HttpSession session,@RequestParam("totalPrice") int totalprice) {
		log.info(" partnerPlaceContentPost() 호출");
		
		String ppname = partnerplacevo.getPartner_place_name();
		model.addAttribute("ppname", ppname);
		model.addAttribute("totalp", totalprice);
		log.info("장소이름,결제금액: "+ppname+totalprice);
		
		MembersVo vo = (MembersVo)session.getAttribute("member");
		model.addAttribute("members", vo);
		model.addAttribute("rental_time", rentaltime);
		model.addAttribute("payment", paymentvo);
		log.info("결제정보: "+paymentvo);
		
		// 결제 후 예약정보저장
		
		return "/rental/payment";
		
	}
		
		@ResponseBody
		@RequestMapping(value="/verifyIamport/{imp_uid}")
		public IamportResponse<Payment> paymentByImpUid(
				Model model, Locale locale, HttpSession session
				, @PathVariable("imp_uid") String imp_uid ) throws IamportResponseException, IOException{	
				log.info("아임포트 결제 호출");
				return api.paymentByImpUid(imp_uid);
				
		}
		
		@ResponseBody
		@RequestMapping(value ="/partnerPlaces/{partner_place_no}/payment",method=RequestMethod.POST)
		public PaymentsVo payment( Model model, 
				@RequestParam("partner_place_price") int partner_place_price,
				@RequestParam("payment_price") int payment_price,
				@RequestParam("rental_time") int rental_time,
				@PathVariable("partner_place_no") int partner_place_no,
				RentalPlacesVo rentalplacevo, PaymentsVo paymentvo,HttpSession session) {
			log.info(" 결제 정보 저장시작 ");
			log.info(" 결제 정보 저장 호출 " + paymentvo);
			
			log.info("받아온 vo정보 . paymentvo : "+paymentvo);
			log.info("받아온 vo정보 . RentalPlacesVo : "+rentalplacevo);
			

			// 주문번호 생성 (날짜-place_no)
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar cal = Calendar.getInstance();
			java.util.Date date = cal.getTime();
		
			String rsNum = sdf.format(date)+"-"+partner_place_no;
			log.info("예약번호"+rsNum);
			
			paymentvo.setReservation_no(rsNum);
			MembersVo mvo = (MembersVo)session.getAttribute("member");
			paymentvo.setMember_no(mvo.getMember_no());
			paymentvo.setPartner_place_no(partner_place_no);
			paymentvo.setPartner_place_price(partner_place_price);
			paymentvo.setPayment_status(1);
			
			Integer pay = rentalService.pay(paymentvo);
			rentalplacevo.setPayment_no(paymentvo.getPayment_no());
			
			if(pay == 1) {
				log.info("결제 정보 저장 성공");
			}else {
				log.info("결제 정보 저장 실패..");
			}
			
			log.info("rentalplace 저장시작");
			//session 멤버정보처럼 클럽도 계속 넘겨서 받아와야함.. 일단 임의로 작성
			rentalplacevo.setClub_no(46);
			rentalplacevo.setMember_no(mvo.getMember_no());
			rentalplacevo.setRental_places_no(partner_place_no);
			rentalplacevo.setReservation_no(rsNum);
			rentalplacevo.setRental_time_no(rental_time);
			//rentalplacevo.setRental_date(paymentvo.getPayment_date()); 데이터 받아오는 것보다 바로 넣어버리는건??
			rentalplacevo.setRental_status(1);
			
			rentalService.place(rentalplacevo);
			log.info("rentalPlace 저장완료 : "+rentalplacevo);
			
			
			return paymentvo;
			
			//form으로 받은 정보들로 결제 후 결제완료 페이지 출력
			
		}
		
		
		
	
	
	
}
	


