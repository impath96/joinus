package com.joinus.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joinus.service.Clubservice;

@RestController
@RequestMapping("/club")
public class ClubRestController {

	private static final Logger log = LoggerFactory.getLogger(ClubRestController.class);

	@Inject
	private Clubservice service;
	
	
	
	
	
}
