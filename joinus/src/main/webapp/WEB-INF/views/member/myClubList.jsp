<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>


<body>
	<!-- Contact Start -->
	<h2 class="text-center">내 모임 페이지</h2>
	<div class="container-xxl py-5">
		<div class="container">
		<div class="row g-4">
		<c:forEach var="myClub" items="${myClubList }">
			<div class="col-md-6 col-lg-4 wow fadeInUp" data-wow-delay="0.1s">
				<div class="service-item rounded overflow-hidden">
					<!-- 클럽 대표 이미지 -->
					<img
						src="${PageContext.requeset.contextPath }/resources/upload/clubs/${myClub.club_image}"
						class="w-100 py-auto">
					<div class="position-relative p-4 pt-0">
						<div class="service-icon">
							<!-- 클럽관심사 아이콘  -->
							<img
								src="${PageContext.requeset.contextPath }/resources/upload/interests/${myClub.interest_icon }"
								class="w-75 py-auto">
						</div>
						<h4 class="mb-3 py-2">${myClub.club_name }</h4>
						<p>${vo.clubsVo.club_content }</p>
						<a class="small fw-medium"
							href="${PageContext.request.contextPath }/club/${myClub.club_no}">클럽
							상세 페이지<i class="fa fa-arrow-right ms-2"></i>
						</a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
		</div>
	</div>
	

	<%@ include file="../include/footer.jsp"%>