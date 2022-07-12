<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
 
<%@ include file="../../include/header.jsp"%>
<body> 

	
    <!-- Projects Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="row mt-n2 wow fadeInUp" data-wow-delay="0.3s">
                <div class="col-12 text-center">
                    <ul class="list-inline mb-5" id="portfolio-flters">
                        <li class="mx-2 active"><a href="">정보</a></li>
                        <li class="mx-2"><a href="/club/${club_no}/boards">게시판</a></li>
                        <li class="mx-2"><a href="/club/${club_no }/gallery">사진첩</a></li>
                    </ul>
                </div>
            </div>
            
            
		    <div class="row g-4 portfolio-container wow fadeInUp" data-wow-delay="0.5s">
		    
		    
		    
			    <div class="col-lg-4 col-md-6 portfolio-item first">
			        <div class="portfolio-img rounded overflow-hidden">
			            <img class="img-fluid" src="${PageContext.request.contextPath }/resources/img/img-600x400-6.jpg" alt="">
			            <div class="portfolio-btn">
			                <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href="img/img-600x400-6.jpg" data-lightbox="portfolio"><i class="fa fa-eye"></i></a>
			                <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href=""><i class="fa fa-link"></i></a>
			            </div>
			        </div>
			    </div>
			    <div class="col-lg-4 col-md-6 portfolio-item first">
			        <div class="portfolio-img rounded overflow-hidden">
			            <img class="img-fluid" src="${PageContext.request.contextPath }/resources/img/img-600x400-6.jpg" alt="">
			            <div class="portfolio-btn">
			                <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href="img/img-600x400-6.jpg" data-lightbox="portfolio"><i class="fa fa-eye"></i></a>
			                <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href=""><i class="fa fa-link"></i></a>
			            </div>
			        </div>
			    </div>
			    <div class="col-lg-4 col-md-6 portfolio-item first">
			        <div class="portfolio-img rounded overflow-hidden">
			            <img class="img-fluid" src="${PageContext.request.contextPath }/resources/img/img-600x400-6.jpg" alt="">
			            <div class="portfolio-btn">
			                <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href="img/img-600x400-6.jpg" data-lightbox="portfolio"><i class="fa fa-eye"></i></a>
			                <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href=""><i class="fa fa-link"></i></a>
			            </div>
			        </div>
			    </div>
			    
			    <c:forEach var="image" items="${imageList }">
			    	<!-- 이미지 크기 600*400이 템플릿기본 -->
<!-- 	                <div class="col-md-6 col-lg-3 wow fadeIn" data-wow-delay="0.1s"> -->
<%-- 						<a href="/club/${club_no }/boards/${image.club_board_no}"><img src="${PageContext.request.contextPath }/resources/upload/boards/sm_${image.club_board_image}"></a> --%>
<!-- 	                </div> -->
	                
	                <div class="col-lg-4 col-md-6 portfolio-item first">
			        <div class="portfolio-img rounded overflow-hidden">
			            <img class="img-fluid" src="${PageContext.request.contextPath }/resources/upload/boards/${image.club_board_image}">
			            <div class="portfolio-btn">
			                <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href="img/img-600x400-6.jpg" data-lightbox="portfolio"><i class="fa fa-eye"></i></a>
			                <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href=""><i class="fa fa-link"></i></a>
			            </div>
			        </div>
			    </div>
                </c:forEach>
			    
			    
			    
		    </div>
            
        </div>
    </div>
    <!-- Projects End -->
    
    
    
    
    
    
    
    
    
     
	<!-- Feature Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="row g-5">
            	
            	<c:forEach var="image" items="${imageList }">
	                <div class="col-md-6 col-lg-3 wow fadeIn" data-wow-delay="0.1s">
						<a href="/club/${club_no }/boards/${image.club_board_no}"><img src="${PageContext.request.contextPath }/resources/upload/boards/sm_${image.club_board_image}"></a>
	                </div>
                </c:forEach>
                
                
            </div>
        </div>
    </div>
    <!-- Feature Start -->




<%@ include file="../../include/footer.jsp"%>
