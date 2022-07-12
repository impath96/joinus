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
			    <c:forEach var="image" items="${imageList }">
			    	<!-- 이미지 크기 355.99*237.32이 템플릿기본 -->
	                <div class="col-lg-4 col-md-6 portfolio-item first">
				        <div class="portfolio-img rounded overflow-hidden">
				            <img style="width: 355.99px; height: 237.32px;" class="img-fluid" src="${PageContext.request.contextPath }/resources/upload/boards/${image.club_board_image}">
				            <div class="portfolio-btn">
				                <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href="/club/${club_no }/boards/${image.club_board_no}"><i class="fa fa-link"></i></a>
				            </div>
				        </div>
			    	</div>
                </c:forEach>
		    </div>
            
        </div>
    </div>
    <!-- Projects End -->
    
    
<%@ include file="../../include/footer.jsp"%>
