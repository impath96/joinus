package com.joinus.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joinus.domain.PartnerPlacesVo;
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
	private ClubService service;
	
	@Inject
	private RentalService rentalService;
	
	private static final Logger log = LoggerFactory.getLogger(ClubController.class);
	
	
	// 결제(정보 받는 페이지, 안에서 ajax)
	// http://localhost:8088/rental/payment
	@RequestMapping(value ="/payment",method=RequestMethod.GET)
	public void pay() {
			
		//form으로 받은 정보들로 결제 후 결제완료 페이지 출력
		
	}
	
	@ResponseBody
	@RequestMapping(value ="/paypay",method=RequestMethod.GET)
	public String pay2(@ModelAttribute("amount") int price ) {
		
		String res = "두번째 에이젝스 데이터왔다감";
		log.info(res + price);
		return res;
		
		//form으로 받은 정보들로 결제 후 결제완료 페이지 출력
		
	}
	
	
	private IamportClient api;
	
	public RentalController() {
		// REST API 키와 REST API secret
		this.api = new IamportClient("4450940620010058",
				"cd721e413ac18a65fe657ac002c45a9427ca9cb46aa7cca4e0600788bf9c7b4d1de7fcb996d24ccd");
	}
	
	@ResponseBody
	@RequestMapping(value="/verifyIamport/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(
			Model model, Locale locale, HttpSession session
			, @PathVariable("imp_uid") String imp_uid ) throws IamportResponseException, IOException{	
			log.info("결제 다녀감");
			return api.paymentByImpUid(imp_uid);
			
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
	
	// 예약
	@RequestMapping(value = "/partnerPlaces/{partner_place_no}", method = RequestMethod.POST)
	public void partnerPlaceContentPost(@PathVariable("partner_place_no") int partner_place_no) {
		log.info(" partnerPlaceContentPost() 호출");
		
		// 결제
		
		// 결제 후 예약정보저장
		// 주문번호 생성한다고 가정(이걸로 예약할 때 이미 예약된 시간또는 날짜는 선택하지 못하도록 제어)
		
	}
	
	
	
	
}
	


