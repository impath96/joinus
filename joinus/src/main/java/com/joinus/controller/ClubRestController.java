package com.joinus.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joinus.domain.MeetingTotalBean;
import com.joinus.service.ClubService;

@RestController
@RequestMapping("/club/*")
public class ClubRestController {

	private static final Logger log = LoggerFactory.getLogger(ClubRestController.class);
	
	@Inject
	private ClubService service;
	
	@RequestMapping(value="/{club_no}/rentalList", method=RequestMethod.GET)
	public ResponseEntity<List<MeetingTotalBean>> rentalAll(
			@PathVariable("club_no") Integer club_no,
			HttpSession session){
		
		log.info("rentalList -REST 호출");
		//멤버정보 가져오기
		//MembersVo member = (MembersVo) session.getAttribute("member");
		//int member_no =member.getMember_no();
		
		int member_no = 124;
		
		//예약정보 불러오기
		List<MeetingTotalBean> rentalList = (List<MeetingTotalBean>)service.getRental(member_no);
		log.info(rentalList+"");
		
		ResponseEntity<List<MeetingTotalBean>> entity = null;
		
		try {
			entity = new ResponseEntity<List<MeetingTotalBean>>(rentalList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	
	
	@RequestMapping(value="/{club_no}/meeting/rental_no/{rental_places_no}")
	public ResponseEntity<List<MeetingTotalBean>> getRentalREST(@PathVariable("club_no") int club_no,
								@PathVariable("rental_places_no") int rental_places_no) {
		log.info("rentalPlace -REST 호출");
		List<MeetingTotalBean> rentalList = service.getRentalREST(rental_places_no);
		
		log.info(rentalList+"");
		
		ResponseEntity<List<MeetingTotalBean>> entity = null;
		
		try {
			entity = new ResponseEntity<List<MeetingTotalBean>>(rentalList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		log.info(entity+"");
		
		return entity;
		
	
	}
	
	
	
}
