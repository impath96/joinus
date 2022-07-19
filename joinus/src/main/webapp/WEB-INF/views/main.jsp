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
        <div class="container"> 
        
        
        
        
            <div class="text-center mx-auto mb-5 py-5" >
                <h6 class="MainTextSub">함께하는, 보다 행복한 삶</h6>
            	<div ><img src="../resources/img/joinus.png" class="mainJoinus"></div><br>
                <h1 class="mb-4">지금 바로 시작해보세요.</h1><hr>
                <button class="btn btn-primary MainBtn m-5 py-3 joinClub joinbtn" onclick="location.href='${PageContext.requeset.contextPath }/club/new'"> 모임만들기 </button>
            </div><br><br>
          
          
          
     <div class="row g-4">
                 
                 
                 
                    <!-- 로그인 O : 맞춤추천 / 인기모임 / 급상승모임 / 이달의 모임 - 로그인 X : 인기모임 / 급상승모임 / 이달의 모임 -->
                    
                    
                    
                    <!-- 지역맞춤 -->
                     <c:if test="${!empty my }">
                     <div class="mainGroup">
            	  			<div class="wow fadeInUp marginB" data-wow-delay="0.1s">
                         	<h2> &#127969; 우리 지역에 있는 TOP 6 </h2>
                         	<p class="MainSubTitle">우리 지역의 다양한 모임들을 구경해보세요!</p><hr>
                          	</div>
				            <div class="row g-4">              
							<c:forEach var = "my" items="${my }">
				                <div class="col-md-6 col-lg-4 wow fadeInUp" data-wow-delay="0.1s">
				                    <div class="service-item rounded overflow-hidden Grrrr shadow">
				                        <img src="${PageContext.requeset.contextPath }/resources/upload/clubs/${my.club_image}" class="w-100 py-auto mainImg"
				                        onclick="location.href='${PageContext.request.contextPath }/club/${my.club_no}'">
				                    <!-- 클럽 대표 이미지 -->
						                    <c:if test="${empty my.club_image}">
						                    	<img src="../resources/img/joinus.jpg" class="w-100 py-auto"
						                    	onclick="location.href='${PageContext.request.contextPath }/club/${my.club_no}'">
						                    </c:if>
					                        <div class="position-relative p-4 pt-0">
					                            <h4 class="mb-3 py-2 MainTextTitle">${my.club_name }</h4>
					                           
					                            <p class="MainTextSub ">${my.club_content }</p>
					                        </div>
				                    </div>
				                </div>
				            </c:forEach>
				                </div>
                   </div>
                     </c:if>
                     
                     
                     <!-- 인기모임(찜) -->
            	  <div class="mainGroup">
            	  			<div class="wow fadeInUp marginB" data-wow-delay="0.1s">
                         	<h2> &#128293; 지금 가장 인기있는 TOP 6 </h2>
                         	<p class="MainSubTitle">회원들이 가장 많이 찜한 모임을 구경해보세요!</p><hr>
                          	</div>
				            <div class="row g-4">              
							<c:forEach var = "po" items="${popular }" begin="0" end="9">
				                <div class="col-md-6 col-lg-4 wow fadeInUp" data-wow-delay="0.1s">
				                    <div class="service-item rounded overflow-hidden Grrrr shadow">
				                        <img src="${PageContext.requeset.contextPath }/resources/upload/clubs/${po.club_image}" class="w-100 py-auto mainImg"
				                        onclick="location.href='${PageContext.request.contextPath }/club/${po.club_no}'">
				                    <!-- 클럽 대표 이미지 -->
						                    <c:if test="${empty po.club_image}">
						                    	<img src="../resources/img/joinus.jpg" class="w-100 py-auto"
						                    	onclick="location.href='${PageContext.request.contextPath }/club/${po.club_no}'">
						                    </c:if>
					                        <div class="position-relative p-4 pt-0">
					                            <h4 class="mb-3 py-2 MainTextTitle">${po.club_name }</h4>
					                           
					                            <p class="MainTextSub ">${po.club_content }</p>
					                        </div>
<%-- 						                <a class="small fw-medium textGnW" href="${PageContext.request.contextPath }/club/${po.club_no}">모임보러가기<i class="fa fa-arrow-right ms-2"></i></a>
 --%>					                        
				                    </div>
				                </div>
				            </c:forEach>
				                </div>
                   </div>
                    
			
					<!-- 회원많은모임 -->
				 <div class="mainGroup">
            	  			<div class="wow fadeInUp marginB" data-wow-delay="0.1s">
                         	<h2> &#128588; 참여회원이 가장 많은 TOP 6 </h2>
                         	<p class="MainSubTitle">가장 회원이 많은 모임을 구경해보세요!</p><hr>
                          	</div>
				            <div class="row g-4">              
							<c:forEach var = "nu" items="${Numerous }">
				                <div class="col-md-6 col-lg-4 wow fadeInUp" data-wow-delay="0.1s">
				                    <div class="service-item rounded overflow-hidden Grrrr shadow">
				                        <img src="${PageContext.requeset.contextPath }/resources/upload/clubs/${nu.club_image}" class="w-100 py-auto mainImg"
				                        onclick="location.href='${PageContext.request.contextPath }/club/${nu.club_no}'">
				                    <!-- 클럽 대표 이미지 -->
						                    <c:if test="${empty nu.club_image}">
						                    	<img src="../resources/img/joinus.jpg" class="w-100 py-auto"
						                    	onclick="location.href='${PageContext.request.contextPath }/club/${nu.club_no}'">
						                    </c:if>
					                        <div class="position-relative p-4 pt-0">
					                            <h4 class="mb-3 py-2 MainTextTitle">${nu.club_name }</h4>
					                           
					                            <p class="MainTextSub ">${nu.club_content }</p>
					                        </div>
				                    </div>
				                </div>
				            </c:forEach>
				                </div>
                   </div>
				
				
				
					<!-- 최근에 생성된 모임  -->
				 <div class="mainGroup">
	            	  			<div class="wow fadeInUp marginB" data-wow-delay="0.1s">
	                         	<h2>  &#10024; 최근에 생긴 모임 </h2>
	                         	<p class="MainSubTitle">방금 생긴 따끈따끈한 모임을 구경해보세요!</p><hr>
	                          	</div>
					            <div class="row g-4">              
								<c:forEach var = "la" items="${latest }">
					                <div class="col-md-6 col-lg-4 wow fadeInUp" data-wow-delay="0.1s">
					                    <div class="service-item rounded overflow-hidden Grrrr shadow">
					                        <img src="${PageContext.requeset.contextPath }/resources/upload/clubs/${la.club_image}" class="w-100 py-auto mainImg"
					                        onclick="location.href='${PageContext.request.contextPath }/club/${la.club_no}'">
					                    <!-- 클럽 대표 이미지 -->
							                    <c:if test="${empty la.club_image}">
							                    	<img src="../resources/img/joinus.jpg" class="w-100 py-auto"
							                    	onclick="location.href='${PageContext.request.contextPath }/club/${la.club_no}'">
							                    </c:if>
						                        <div class="position-relative p-4 pt-0">
						                            <h4 class="mb-3 py-2 MainTextTitle">${la.club_name }</h4>
						                           
						                            <p class="MainTextSub ">${la.club_content }</p>
						                        </div>
					                    </div>
					                </div>
					            </c:forEach>
					                </div>
	                   </div>



		</div>
		</div>
        </div>
    </body>
	

    
<%@ include file="include/footer.jsp"%>