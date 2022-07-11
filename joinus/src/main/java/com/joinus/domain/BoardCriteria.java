package com.joinus.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardCriteria {
	// 게시판 리스트 페이징 처리에 필요한 정보를 저장하는 객체
	
	private static final Logger log = LoggerFactory.getLogger(BoardCriteria.class);
	
	private int page;	// 페이지 시작번호
	private int perPageNum;	// 페이지 크기
	
	// 기본생성자 (1페이지 10개씩)
	public BoardCriteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <=0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	
	// mapper에서 사용될 메서드
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}
	
	
	public int getPage() {
		return page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	
	// alt shift s + s
	@Override
	public String toString() {
		return "BoardCriteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	

	
	
	
}
