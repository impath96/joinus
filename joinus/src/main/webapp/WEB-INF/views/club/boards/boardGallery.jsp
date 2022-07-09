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
            
        </div>
    </div>
    <!-- Projects End -->
    
<!--                 <div class="row mt-n2 wow fadeInUp" data-wow-delay="0.3s"> -->
<!--                 <div class="col-12 text-center"> -->
<!--                     <ul class="list-inline mb-5" id="portfolio-flters"> -->
<!--                         <li class="mx-2 active" data-filter="*">정보</li> -->
<!--                         <li class="mx-2" data-filter=".first">게시판</li> -->
<!--                         <li class="mx-2" data-filter=".second">사진첩</li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--             </div> -->
    
    
    
     
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
