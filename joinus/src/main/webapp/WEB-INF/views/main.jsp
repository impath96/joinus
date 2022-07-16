<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
    
<%@ include file="include/header.jsp"%>
<link href="${PageContext.request.contextPath }/resources/css/ksm.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
});


</script>
<script type="text/javascript">

</script>

<body>
    
   <!-- Projects Start -->
    <div class="container-xxl py-5 ">
        <div class="container maincSize py-5">
            <div class="text-center mx-auto mb-5 wow fadeInUp py-5" data-wow-delay="0.1s" style="max-width: 600px;">
                <h6 class="mainP">함께하는, 보다 행복한 삶</h6>
            	<div ><img src="../resources/img/joinus.png" class="mainJoinus"></div><br>
                <h1 class="mb-4">지금 바로 시작해보세요.</h1><hr>
                <button class="btn btn-primary MainBtn m-5 py-3" onclick="location.href='${PageContext.requeset.contextPath }/club/new'"> 모임만들기 </button>
            </div><br><br>
          
    
                 
                    <!-- 로그인 O : 맞춤추천 / 인기모임 / 급상승모임 / 이달의 모임 - 로그인 X : 인기모임 / 급상승모임 / 이달의 모임 -->
                     <c:if test="${!empty my }">
						
                         	<h4 class="MainListTitle"> &#127969; 우리 지역에 있는 > </h4>
                         	<p class="MainSubTitle">우리 지역의 다양한 모임들을 구경해보세요!</p>
                      <hr>
                          <c:forEach var="my" items="${my}">
                         <div class="Main4" >
	                  <div class="rounded">
                        	<c:if test="${empty my.club_image}">
                        		<img class="img-fluid mainImg" src="../resources/img/joinus.jpg">
                        	</c:if>
                        <img class="img-fluid mainImg" src="${PageContext.requeset.contextPath }/resources/upload/clubs/${my.club_image}" alt="">
                        <div class="position-relative p-4 pt-0 mainSize">
                           <!--  <div class="service-icon">
                                <i class="fa fa-wind fa-3x"></i>
                            </div> -->
                            <h6 class="mainGr">${my.club_name }</h6>
                            <p>${my.club_content }</p>
                        </div>
                     <hr>    
                            <a class="small fw-medium textGnW" href="${PageContext.request.contextPath }/club/${my.club_no}">모임보러가기<i class="fa fa-arrow-right ms-2"></i></a>
                    </div>
                    </div>
                    </c:forEach>
                    <br>
                     </c:if>
                         
                         
                         
                         <div>
                         	<h4 class="MainListTitle"> &#128293; 지금 가장 인기있는 > </h4>
                         	<p class="MainSubTitle">회원들이 가장 많이 찜한 모임을 구경해보세요!</p>
                         </div>
                         <hr>
                          <c:forEach var="po" items="${popular }">
                         <div class="Main4" >
	                  <div class="rounded">
                        	<c:if test="${empty po.club_image}">
                        		<img class="img-fluid mainImg" src="../resources/img/joinus.jpg">
                        	</c:if>
                        <img class="img-fluid mainImg" src="${PageContext.requeset.contextPath }/resources/upload/clubs/${po.club_image}" alt="">
                        <div class="position-relative p-4 pt-0 mainSize">
                           <!--  <div class="service-icon">
                                <i class="fa fa-wind fa-3x"></i>
                            </div> -->
                            <h6 class="mainGr">${po.club_name }</h6>
                            <p>${po.club_content }</p>
                        </div>
 					<hr>
                            <a class="small fw-medium textGnW" href="${PageContext.request.contextPath }/club/${po.club_no}">모임보러가기<i class="fa fa-arrow-right ms-2"></i></a>
                    </div>
                    </div>
                    </c:forEach>
                    <br>

						<div>
                         	<h4 class="MainListTitle"> &#128588; 참여회원이 가장 많은 > </h4>
                         	<p class="MainSubTitle">가장 회원이 많은 모임을 구경해보세요!</p>
                         </div>
                         <hr>
                          <c:forEach var="nu" items="${Numerous }">
                         <div class="Main4" >
	                  <div class="rounded">
                        <img class="img-fluid mainImg" src="${PageContext.requeset.contextPath }/resources/upload/clubs/${nu.club_image}" alt="">
                        <div class="position-relative p-4 pt-0 mainSize">
                           <!--  <div class="service-icon">
                                <i class="fa fa-wind fa-3x"></i>
                            </div> -->
                            <h6 class="mainGr">${nu.club_name }</h6>
                            <p>${nu.club_content }</p>
                        </div>
                    <hr>
                            <a class="small fw-medium textGnW" href="${PageContext.request.contextPath }/club/${nu.club_no}">  모임보러가기<i class="fa fa-arrow-right ms-2"></i></a>
                    </div>
                    </div>
                    </c:forEach>
                    <br> 



   						 <div>
                         	<h4 class="MainListTitle"> &#10024; 가장 최근에 생긴 > </h4>
                         	<p class="MainSubTitle">방금 생긴 따끈따끈한 모임을 구경해보세요!</p>
                         </div>
                         <hr>
                          <c:forEach var="la" items="${latest }">
                         <div class="Main4" >
	                  <div class="rounded">
                        <img class="img-fluid mainImg" src="${PageContext.requeset.contextPath }/resources/upload/clubs/${la.club_image}" alt="">
                        <div class="position-relative p-4 pt-0 mainSize">
                           <!--  <div class="service-icon">
                                <i class="fa fa-wind fa-3x"></i>
                            </div> -->
                            <h6 class="mainGr">${la.club_name }</h6>
                            <p>${la.club_content }</p>
                        </div>
                    <hr>
                            <a class="small fw-medium textGnW" href="${PageContext.request.contextPath }/club/${la.club_no}">  모임보러가기<i class="fa fa-arrow-right ms-2"></i></a>
                    </div>
                    </div>
                    </c:forEach>
                    <br> 


                    </div>
                       </div>
    </body>
	

    
<%@ include file="include/footer.jsp"%>