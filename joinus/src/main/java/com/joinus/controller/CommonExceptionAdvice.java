package com.joinus.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CommonExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	public String nullException(Exception e) {
		log.info("Exception() 호출");
		log.info(e.getMessage());
		return"error/commonException";
	}
}