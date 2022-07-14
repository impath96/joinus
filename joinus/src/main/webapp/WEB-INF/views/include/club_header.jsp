<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">

<div class="col-12 text-center">
            <ul class="list-inline mb-5" id="portfolio-flters">
            <li class="mx-2"> <a href="${PageContext.request.contextPath}/club/${clubInfo[0].club_no}">모임정보</a></li>
            <li class="mx-2"> <a href="${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/boards">모임게시판</a></li>
            <li class="mx-2"> <a href="${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/gallery">사진첩</a></li>
            <li class="mx-2"> <a href="${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/clubMembers">모임관리</a></li>
            </ul>
</div>



    