<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp"%>


<body>
	<!-- Contact Start -->
	<div class="container-xxl py-5">
		<div class="container">
			<div class="text-center mx-auto mb-5 wow fadeInUp"
				data-wow-delay="0.1s" style="max-width: 600px;">
				<h1 class="mb-4">마이 페이지</h1>
			</div>
			<div class="row g-4">
				<div class="col-md-12 col-lg-6 wow  fadeInUp" data-wow-delay="0.1s">
					<div class="service-item position-relative rounded overflow-hidden p-3 shadow">
						<div style="height:230px;">
							<div class="service-content d-flex flex-column">
								<h4 class="mb-2">${sessionScope.member.member_name }님 프로필</h4>
								<c:choose>
									<c:when test="${sessionScope.member.member_updatedate eq sessionScope.member.member_regdate}">
										<small>회원가입일 : ${sessionScope.member.member_regdate }</small>
									</c:when>
									<c:otherwise>
										<small>회원정보수정일 : ${sessionScope.member.member_updatedate}</small>
									</c:otherwise>
								</c:choose>
								
								
								<!-- <p>Stet stet justo dolor sed duo. Ut clita sea sit ipsum diam
									lorem diam.</p> -->
								<a class="small fw-medium bottom-right" href="/settings/member">프로필 수정하기<i
									class="fa fa-arrow-right ms-2"></i></a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-lg-6 wow  fadeInUp" data-wow-delay="0.1s">
					<div class="service-item position-relative rounded overflow-hidden px-3 py-2 shadow">
						<div style="height:250px;">
							<div class="service-content d-flex flex-column">
								<h4 class="mb-2">내 모임</h4>
								<div class="box-content">
									<c:forEach var="club" items="${clubList }">
									<a href="${pageContext.request.contextPath }/club/${club.club_no}" class="club_item d-flex align-items-center mb-1" style="color:currentColor;">
										<div class="club_name"> &#183; ${club.club_name }</div>
										<time class="finished_time" datetime="Mon Jul 04 2022 16:12:23 GMT+0900 (GMT+09:00)">20시간 전</time>
									</a>
									</c:forEach>
								</div>
								<a class="small fw-medium bottom-right" href="${pageContext.request.contextPath }/member/my-clublist">내 모임 전체보기<i
									class="fa fa-arrow-right ms-2"></i></a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-lg-6 wow  fadeInUp" data-wow-delay="0.1s">
					<div class="service-item position-relative rounded overflow-hidden px-3 py-2 shadow">
						<div style="height:250px;">
							<div class="service-content d-flex flex-column">
								<h4 class="mb-2">최근 본 모임</h4>
								<div class="box-content">
									<a href="" class="club_item d-flex align-items-center mb-1" style="color:currentColor;">
										<div class="club_name">안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요</div>
										<time class="finished_time" datetime="Mon Jul 04 2022 16:12:23 GMT+0900 (GMT+09:00)">20시간 전</time>
									</a>
									<a href="" class="club_item d-flex align-items-center mb-1" style="color:currentColor;">
										<div>aaaaaaaaaaaaaaaaaaaaaaaaaaaa</div>
										<time class="finished_time" datetime="Mon Jul 04 2022 16:12:23 GMT+0900 (GMT+09:00)">20시간 전</time>
									</a>
									<a href="" class="club_item d-flex align-items-center mb-1" style="color:currentColor;">
										<div>aaaaaaaaaaaaaaaaaaaaaaaaaaaa</div>
										<time class="finished_time" datetime="Mon Jul 04 2022 16:12:23 GMT+0900 (GMT+09:00)">20시간 전</time>
									</a>
									<a href="" class="club_item d-flex align-items-center mb-1" style="color:currentColor;">
										<div>aaaaaaaaaaaaaaaaaaaaaaaaaaaa</div>
										<time class="finished_time" datetime="Mon Jul 04 2022 16:12:23 GMT+0900 (GMT+09:00)">20시간 전</time>
									</a>
									<a href="" class="club_item d-flex align-items-center mb-1" style="color:currentColor;">
										<div>aaaaaaaaaaaaaaaaaaaaaaaaaaaa</div>
										<time class="finished_time" datetime="Mon Jul 04 2022 16:12:23 GMT+0900 (GMT+09:00)">20시간 전</time>
									</a>
								</div>
								<a class="small fw-medium bottom-right" href="">내 모임 전체보기<i
									class="fa fa-arrow-right ms-2"></i></a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-lg-6 wow  fadeInUp" data-wow-delay="0.1s">
					<div class="service-item position-relative rounded overflow-hidden px-3 py-2 shadow">
						<div style="height:250px;">
							<div class="service-content d-flex flex-column">
								<h4 class="mb-2">내가 만든 모임</h4>
								<div class="box-content">
									<c:forEach var="myClub" items="${myClubList }">
									<a href="${pageContext.request.contextPath }/club/${myClub.club_no}" class="club_item d-flex align-items-center mb-1" style="color:currentColor;">
										<div class="club_name"> &#183; ${myClub.club_name }</div>
										<time class="finished_time" datetime="Mon Jul 04 2022 16:12:23 GMT+0900 (GMT+09:00)">20시간 전</time>
									</a>
									</c:forEach>
								</div>
								<a class="small fw-medium bottom-right" href="/member/my-clublist">내 모임 전체보기<i
									class="fa fa-arrow-right ms-2"></i></a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-lg-6 wow  fadeInUp" data-wow-delay="0.1s">
					<div class="service-item position-relative rounded overflow-hidden px-3 py-2 shadow">
						<div style="height:250px;">
							<div class="service-content d-flex flex-column">
								<h4 class="mb-2">참석 예정 정모</h4>
								<div class="box-content">
									<a href="" class="club_item d-flex align-items-center mb-1" style="color:currentColor;">
										<div class="club_name">안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요</div>
										<time class="finished_time" datetime="Mon Jul 04 2022 16:12:23 GMT+0900 (GMT+09:00)">20시간 전</time>
									</a>
									<a href="" class="club_item d-flex align-items-center mb-1" style="color:currentColor;">
										<div>aaaaaaaaaaaaaaaaaaaaaaaaaaaa</div>
										<time class="finished_time" datetime="Mon Jul 04 2022 16:12:23 GMT+0900 (GMT+09:00)">20시간 전</time>
									</a>
									<a href="" class="club_item d-flex align-items-center mb-1" style="color:currentColor;">
										<div>aaaaaaaaaaaaaaaaaaaaaaaaaaaa</div>
										<time class="finished_time" datetime="Mon Jul 04 2022 16:12:23 GMT+0900 (GMT+09:00)">20시간 전</time>
									</a>
									<a href="" class="club_item d-flex align-items-center mb-1" style="color:currentColor;">
										<div>aaaaaaaaaaaaaaaaaaaaaaaaaaaa</div>
										<time class="finished_time" datetime="Mon Jul 04 2022 16:12:23 GMT+0900 (GMT+09:00)">20시간 전</time>
									</a>
									<a href="" class="club_item d-flex align-items-center mb-1" style="color:currentColor;">
										<div>aaaaaaaaaaaaaaaaaaaaaaaaaaaa</div>
										<time class="finished_time" datetime="Mon Jul 04 2022 16:12:23 GMT+0900 (GMT+09:00)">20시간 전</time>
									</a>
								</div>
								<a class="small fw-medium bottom-right" href="">내 모임 전체보기<i
									class="fa fa-arrow-right ms-2"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer.jsp"%>