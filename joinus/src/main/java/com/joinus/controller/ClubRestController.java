package com.joinus.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joinus.domain.MeetingTotalBean;
import com.joinus.service.ClubService;

@RestController
@RequestMapping("/club/*")
public class ClubRestController {

	private static final Logger log = LoggerFactory.getLogger(ClubRestController.class);
	
	@Inject
	private ClubService service;
	
	@RequestMapping(value="/{club_no}/meeting/{rental_places_no}")
	public String getRentalREST(@PathVariable("club_no") int club_no,
								@PathVariable("rental_places_no") int rental_places_no) {
	
		List<MeetingTotalBean> rentalList = service.getRental(rental_places_no);
		
		log.info(rentalList+"");
		
	return "";	
	
	}
	
	
	
}
