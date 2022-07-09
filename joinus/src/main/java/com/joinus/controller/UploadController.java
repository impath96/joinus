package com.joinus.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

@Controller
public class UploadController {


	  @Autowired MultipartResolver multipartResolver;
	  
	 //@Resource(name="uploadPath") private String uploadPath;
	  
	  private static final Logger log =
	  LoggerFactory.getLogger(UploadController.class);
	  
	  @GetMapping("/uploadImage") public void uploadFormGet() {
	  log.info("upload GET ...");
	  
	  }
	  
	  @PostMapping("/uploadImage") public String uploadFormPost(MultipartFile file,
	  Model model) throws IOException { log.info("upload Post ... originalName={}",
	  file.getOriginalFilename()); log.info("upload Post ... size={}",
	  file.getSize()); log.info("upload Post ... contentType={}",
	  file.getContentType());
	  
	  // 시스템에 저장된 파일명
	  String savedFileName = uploadFile(file);
	  model.addAttribute("savedFileName", savedFileName); return "/uploadImage";
	  
	  } private String uploadFile(MultipartFile file) throws IOException { String
	  savedFileName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
	// File target = new File(uploadPath, savedFileName); 
	  // 어느 위치(uploadPath)에 어떤 파일? 
	  // stream을 쉽게 해주는 기능을 스프링이 제공
	  //FileCopyUtils.copy(file.getBytes(),target);
	 
	  return savedFileName; }


}
