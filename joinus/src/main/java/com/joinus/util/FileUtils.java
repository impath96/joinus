package com.joinus.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	// uploadPath : 
	public static String uploadFile(String uploadPath, MultipartFile file) throws IOException {
		// UUID_파일명 형식
		String savedFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		// 경로는 어떤걸로 하지??
		File target = new File(uploadPath, savedFileName); // 어느 경로 : realPath, 어떤 파일 : savedFileName

		// DB가 아닌 현재 웹 서버에 저장
		// DB에는 전체 경로명 + 파일명(UUID_파일명) 형식으로 전달
		FileCopyUtils.copy(file.getBytes(), target); // 내용을 파일에 쓰는 동작
		
		return savedFileName;
	}

//  현재 내가 업로드해야할 경로 반환
//	public static String getCurrentUploadPath(String uploadRootPath) {
//		
//		return "";
//	}
//
//	private static String makeDir(String uploadRootPath, String... paths) {
//		
//		return "";
//		
//	}
	
	
}
