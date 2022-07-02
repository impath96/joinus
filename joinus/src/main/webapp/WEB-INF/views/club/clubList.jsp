<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
 
<%@ include file="../include/header.jsp"%>
<body> 

		<!-- Testimonial Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                <h1 class="mb-4">이달의 모임</h1>
            </div>
            <div class="owl-carousel testimonial-carousel wow fadeInUp" data-wow-delay="0.1s">
                <div class="testimonial-item text-center">
                    <div class="testimonial-img position-relative">
                        <img class="img-fluid rounded-circle mx-auto mb-5" src="${PageContext.request.contextPath }/resources/img/testimonial-1.jpg">
                        <div class="btn-square bg-primary rounded-circle">
                            <i class="fa fa-quote-left text-white"></i>
                        </div>
                    </div>
                    <div class="testimonial-text text-center rounded p-4">
                        <p>Clita clita tempor justo dolor ipsum amet kasd amet duo justo duo duo labore sed sed. Magna ut diam sit et amet stet eos sed clita erat magna elitr erat sit sit erat at rebum justo sea clita.</p>
                        <h5 class="mb-1">Client Name</h5>
                        <span class="fst-italic">Profession</span>
                    </div>
                </div>
                <div class="testimonial-item text-center">
                    <div class="testimonial-img position-relative">
                        <img class="img-fluid rounded-circle mx-auto mb-5" src="${PageContext.request.contextPath }/resources/img/testimonial-2.jpg">
                        <div class="btn-square bg-primary rounded-circle">
                            <i class="fa fa-quote-left text-white"></i>
                        </div>
                    </div>
                    <div class="testimonial-text text-center rounded p-4">
                        <p>Clita clita tempor justo dolor ipsum amet kasd amet duo justo duo duo labore sed sed. Magna ut diam sit et amet stet eos sed clita erat magna elitr erat sit sit erat at rebum justo sea clita.</p>
                        <h5 class="mb-1">Client Name</h5>
                        <span class="fst-italic">Profession</span>
                    </div>
                </div>
                <div class="testimonial-item text-center">
                    <div class="testimonial-img position-relative">
                        <img class="img-fluid rounded-circle mx-auto mb-5" src="${PageContext.request.contextPath }/resources/img/testimonial-3.jpg">
                        <div class="btn-square bg-primary rounded-circle">
                            <i class="fa fa-quote-left text-white"></i>
                        </div>
                    </div>
                    <div class="testimonial-text text-center rounded p-4">
                        <p>Clita clita tempor justo dolor ipsum amet kasd amet duo justo duo duo labore sed sed. Magna ut diam sit et amet stet eos sed clita erat magna elitr erat sit sit erat at rebum justo sea clita.</p>
                        <h5 class="mb-1">Client Name</h5>
                        <span class="fst-italic">Profession</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Testimonial End -->
	
    <!-- Projects Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                <h6 class="text-primary">Our Projects</h6>
                <h1 class="mb-4">관심사</h1>
            </div>
            <div class="row mt-n2 wow fadeInUp" data-wow-delay="0.3s">
                <div class="col-12 text-center">
                    <ul class="list-inline mb-5" id="portfolio-flters">
                        <li class="mx-2 active"><a href="/club/clubList">전체</a></li>
                        <li class="mx-2"><a href="/club/clubList?interest_no=1">요리/제조</a></li>
                        <li class="mx-2"><a href="/club/clubList?interest_no=2">봉사활동</a></li>
                        <li class="mx-2"><a href="/club/clubList?interest_no=3">운동/스포츠</a></li>
                        <li class="mx-2"><a href="/club/clubList?interest_no=4">게임/오락</a></li>
                        <li class="mx-2"><a href="/club/clubList?interest_no=5">음악/악기</a></li>
                        <li class="mx-2"><a href="/club/clubList?interest_no=6">아웃도어/여행</a></li>
                        <li class="mx-2"><a href="/club/clubList?interest_no=7">외국/언어</a></li>
                        <li class="mx-2"><a href="/club/clubList?interest_no=8">문화/공연/축제</a></li>
                    </ul>
                </div>
            </div>
            
			          <div class="row g-4">
			    <c:forEach var = "vo" items="${clubList }">
                <div class="col-md-6 col-lg-4 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="service-item rounded overflow-hidden">
                    <!-- 클럽 대표 이미지 -->
                        <img class="img-fluid" src="${PageContext.request.contextPath }/resources/img/carousel-1.jpg" alt="">
                        <div class="position-relative p-4 pt-0">
                            <div class="service-icon">
                            <!-- 클럽관심사 아이콘  -->
                                <i class="${vo.interestVo.interest_icon } fa-3x"></i>
                            </div>
                            <h4 class="mb-3">${vo.clubVo.club_name }</h4>
                            <p>${vo.clubVo.club_content }</p>
                            <a class="small fw-medium" href="">클럽 상세 페이지<i class="fa fa-arrow-right ms-2"></i></a>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>            
        </div>
    </div>
    <!-- Projects End -->
     





<%@ include file="../include/footer.jsp"%>
