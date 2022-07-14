<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/club_header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body> 

<!-- 클럽 정보 -->
<div class="container px-4 py-5">
	<form action ="${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/modify" method ="post" enctype="multipart/form-data">
	<div class="row featurette py-5">
		<div class="col-md-7 order-md-2">
		<input type="text" class="form-control border-1 py-3 md-3" style="height: 55px;" value = "${clubInfo[0].club_name}" name="club_name">
		<br>
		<textarea class="form-control border-1" style="resize: none;" rows="15" name="club_content">${clubInfo[0].club_content}</textarea>
      	</div>
	    <div class="col-md-5 order-md-1">
	    	<img src="${PageContext.requeset.contextPath }/resources/upload/clubs/${clubInfo[0].club_image}" class="w-100 py-auto">
	    	<br>
	    	<input type="file" class="btn btn-secondary btn-default float-right py-2 my-4" name="file">
	    </div>
    </div>
    <!-- 클럽정보 수정하기 -->
    <div class="text-end col-md-6 ms-auto">
    	<button class="btn btn-primary rounded-pill py-3 px-5" type="submit">수정하기</button>
    </div>
    </form>
    <!-- 클럽정보 수정하기 -->
</div>
<!-- 클럽 정보 -->


<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"> </script>

<%@ include file="../include/footer.jsp"%>
