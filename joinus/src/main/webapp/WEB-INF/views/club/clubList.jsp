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
        	<!-- 카테고리 -->
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
            <!-- 카테고리 -->
         <!-- 모임 생성 -->   
         <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
         	<a href="${PageContext.request.contextPath }/club/new">
            	<h3 class="mb-4">모임 만들기 <i class="fa fa-arrow-right ms-3"></i> </h3>
         	</a>
         </div>
         <!-- 모임 생성 -->   
		<div class="row g-4">
			<c:forEach var = "vo" items="${clubList }">
                <div class="col-md-6 col-lg-4 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="service-item rounded overflow-hidden">
                    <!-- 클럽 대표 이미지 -->
                        <img src="${PageContext.requeset.contextPath }/resources/upload/clubs/${vo.clubsVo.club_image}" class="w-100 py-auto">
                        <div class="position-relative p-4 pt-0">
                            <div class="service-icon">
                            <!-- 클럽관심사 아이콘  -->
                                <img src="${PageContext.requeset.contextPath }/resources/upload/interests/${vo.interestsVo.interest_icon }" class="w-100 py-auto">
                            </div>
                            <h4 class="mb-3 py-2">${vo.clubsVo.club_name }</h4>
                            <p>${vo.clubsVo.club_content }</p>
                            <a class="small fw-medium" href="${PageContext.request.contextPath }/club/${vo.clubsVo.club_no}">클럽 상세 페이지<i class="fa fa-arrow-right ms-2"></i></a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>            
        </div>
        <br>
        <div class="d-flex justify-content-center py-3">
        	<ul class="list-group list-group-horizontal">
        		<c:if test="${pm.prev }">
        			<li><a href="${PageContext.request.contextPath }/club/clubList?page=${pm.startPage-1}">&laquo;</a></li>
        		</c:if>
        		<c:forEach var ="idx" begin ="${pm.startPage }" end="${pm.endPage }">
        			<li class="list-group-item" <c:out value="${pm.cri.page == idx? 'class=active':'' }"/>>
        			<a href = "${PageContext.request.contextPath }/club/clubList?page=${idx}" >${idx }</a>
        			</li>
        		</c:forEach>
        		
        		<c:if test="${pm.next && pm.endPage>0 }">
        			<li> <a href ="${PageContext.request.contextPath }/club/clubList?page=${pm.endPage+1}">&raquo;</a></li>
        		</c:if>
        	
        	</ul>
        </div>
        
        
        
        
    </div>
    <!-- Projects End -->
     





<%@ include file="../include/footer.jsp"%>
