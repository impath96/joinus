package com.joinus.controller;

import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joinus.service.ClubService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
@RequestMapping("/rental/*")
public class RentalController {
	
	@Inject
	private ClubService service;
	
	
	
	private static final Logger log = LoggerFactory.getLogger(ClubController.class);
	
	// 결제(정보 받는 페이지, 안에서 ajax)
	// http://localhost:8088/rental/pay
	@RequestMapping(value ="/payment",method=RequestMethod.GET)
	public void pay() {
		
		//form으로 받은 정보들로 결제 후 결제완료 페이지 출력
		
	}
	
	
	private IamportClient api;
	
	public void HomeController() {
		// REST API 키와 REST API secret
		this.api = new IamportClient("4450940620010058","cd721e413ac18a65fe657ac002c45a9427ca9cb46aa7cca4e0600788bf9c7b4d1de7fcb996d24ccd");
	}
	
	@ResponseBody
	@RequestMapping(value="/verifyIamport/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(
			Model model, Locale locale, HttpSession session
			, @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException{	
			return api.paymentByImpUid(imp_uid);
			
	}
	
	
	//================================================================================================
	
	
	// http://localhost:8088/rental/placeList
	@RequestMapping(value = "/placeList", method = RequestMethod.GET)
	public void placeListGet() {
		log.info(" placeListGet() 호출 ");
	}
	
	
	
	
}
	


