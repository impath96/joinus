<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp"%>

<div class="container-xxl py-5">
	<div class="container">
		<div class="row g-5">
			<div class="row g-4">

				<a href="${PageContext.request.contextPath}/rental/partnerPlaceList?type=음악연습실">음악연습실</a><br>
				<a href="${PageContext.request.contextPath}/rental/partnerPlaceList?type=공유주방">공유주방</a><br>
				<a href="${PageContext.request.contextPath}/rental/partnerPlaceList?type=스터디룸">스터디룸</a><br>
				<a href="${PageContext.request.contextPath}/rental/partnerPlaceList?type=운동시설">운동시설</a><br>
				<a href="${PageContext.request.contextPath}/rental/partnerPlaceList?type=카페">카페</a><br>
			
			</div>
		</div>
	</div>
</div>

	
<%@ include file="../include/footer.jsp"%>