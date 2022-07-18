package com.joinus.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joinus.domain.LocationCityVo;
import com.joinus.domain.MeetingTotalBean;
import com.joinus.domain.PartnerPlacesVo;
import com.joinus.service.RentalService;

@RestController
@RequestMapping("/rental/*")
public class RentalRestController {
	
	private static final Logger log = LoggerFactory.getLogger(RentalRestController.class);
	
	@Inject
	private RentalService rentalService;
	
	//${PageContext.request.contextPath}/rental/location/'+partner_place_address
	@RequestMapping(value = "/location/{partner_place_address}")
	public ResponseEntity<List<PartnerPlacesVo>> getCityPartnerPlaceREST(@PathVariable("partner_place_address") String partner_place_address){
		log.info(" getCityPartnerPlaceREST() 호출 ");
		log.info(partner_place_address);
		
		List<PartnerPlacesVo> cityPartnerPlaceList = rentalService.getCityPartnerPlaceList(partner_place_address);
		log.info(cityPartnerPlaceList+"");
		
		ResponseEntity<List<PartnerPlacesVo>> entity = null;
		
		try {
			entity = new ResponseEntity<List<PartnerPlacesVo>>(cityPartnerPlaceList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		log.info(entity+"");
		
		return entity;
	}
	
	
	
}
