<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
    
<%@ include file="include/header.jsp"%>


	<style>
		.maincSize {
			width: 1500px; 
		}
		.mainImg {
			width: 336px; height: 180px;
			margin-bottom: 10px;
		}
		.Main4 {
			width: 300px; height: 160px;
			display: inline-block;
			box-shadow: 0 0 45px rgb(0 0 0 / 8%);
			margin-right: 20px;
		}
		.margin0 {
			margin: 0px;
		}		
		.mainSize {
			 width: 300px; height: 120px;
			 background-color:transparent;
		}
		.mainGr {
			color: #32C36C;
		}
		.textGnW {
			padding: 6px;
			text-align: center;
		 	font-style: italic;
			color: white;
			background-color: #32C36C;
			border-radius: 10px;
			vertical-align: middle;
		}
		
		.MainListTitle {
			font-size: large;
			text-align: left;
			font-weight: 500;
			margin-top: 50px;
		}
		.MainBtn {
			width: 200px;
			box-shadow: 0 0 45px rgb(0 0 0 / 8%);
			margin-bottom: 70px;
		}
	</style>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
});


</script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">
function iamport(){

}
</script>

<body>
    
   <!-- Projects Start -->
    <div class="container-xxl py-5 ">
        <div class="container maincSize">
            <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                <h6 class="text-primary">함께하는, 보다 행복한 삶</h6>
                <h1 class="mb-4">JOINUS에서 시작해보세요.</h1>
                <button class="btn btn-primary MainBtn" onclick="location.href='${PageContext.requeset.contextPath }/club/new'"> 모임만들기 </button>
            </div><hr><br><br>
          
            <!-- <div class="row g-4 portfolio-container wow fadeInUp" data-wow-delay="0.5s">
                <div class="col-lg-4 col-md-6 portfolio-item first">
                    <div class="portfolio-img rounded overflow-hidden">
                        <img class="img-fluid" src="img/img-600x400-6.jpg" alt="">
                        <div class="portfolio-btn">
                            <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href="img/img-600x400-6.jpg" data-lightbox="portfolio"><i class="fa fa-eye"></i></a>
                            <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href=""><i class="fa fa-link"></i></a>
                        </div>
                    </div> -->
                    
                    
                    
                    <!-- 로그인 O : 맞춤추천 / 인기모임 / 급상승모임 / 이달의 모임 -->
                    <!-- 로그인 X : 인기모임 / 급상승모임 / 이달의 모임 -->
                    
                         
                         
                         
                         <div>
                         	<h4 class="MainListTitle"> &#128293; 지금 가장 인기있는 > </h4><br>
                         </div>
                         
                          <c:forEach var="po" items="${popular }">
                         <div class="Main4" >
	                  <div class="rounded overflow-hidden">
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
                            <a class="small fw-medium textGnW" href="${PageContext.request.contextPath }/club/${po.club_no}">  모임보러가기<i class="fa fa-arrow-right ms-2"></i></a>
                    </div>
                    </div>
                    </c:forEach>
                    <br>

						<div>
                         	<h4 class="MainListTitle"> &#128588; 참여회원이 가장 많은 > </h4><br>
                         </div>
                         
                          <c:forEach var="nu" items="${Numerous }">
                         <div class="Main4" >
	                  <div class="rounded overflow-hidden">
                        <img class="img-fluid mainImg" src="${PageContext.requeset.contextPath }/resources/upload/clubs/${nu.club_image}" alt="">
                        <div class="position-relative p-4 pt-0 mainSize">
                           <!--  <div class="service-icon">
                                <i class="fa fa-wind fa-3x"></i>
                            </div> -->
                            <h6 class="mainGr">${nu.club_name }</h6>
                            <p>${nu.club_content }</p>
                        </div>
                            <a class="small fw-medium textGnW" href="${PageContext.request.contextPath }/club/${nu.club_no}">  모임보러가기<i class="fa fa-arrow-right ms-2"></i></a>
                    </div>
                    </div>
                    </c:forEach>
                    <br>



   						 <div>
                         	<h4 class="MainListTitle"> &#10024; 가장 최근에 생긴 > </h4><br>
                         </div>
                         
                          <c:forEach var="la" items="${latest }">
                         <div class="Main4" >
	                  <div class="rounded overflow-hidden">
                        <img class="img-fluid mainImg" src="${PageContext.requeset.contextPath }/resources/upload/clubs/${la.club_image}" alt="">
                        <div class="position-relative p-4 pt-0 mainSize">
                           <!--  <div class="service-icon">
                                <i class="fa fa-wind fa-3x"></i>
                            </div> -->
                            <h6 class="mainGr">${la.club_name }</h6>
                            <p>${la.club_content }</p>
                        </div>
                            <a class="small fw-medium textGnW" href="${PageContext.request.contextPath }/club/${la.club_no}">  모임보러가기<i class="fa fa-arrow-right ms-2"></i></a>
                    </div>
                    </div>
                    </c:forEach>
                    <br>

  						  






                    </div>
      
                    
                       </div>
                       
                    
                    
                    
                   <!--  
                    <div class="pt-3">
                        <p class="text-primary mb-0">Wind Turbines</p>
                        <hr class="text-primary w-25 my-2">
                        <h5 class="lh-base">We Are pioneers of solar & renewable energy industry</h5>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 portfolio-item third">
                    <div class="portfolio-img rounded overflow-hidden">
                        <img class="img-fluid" src="img/img-600x400-4.jpg" alt="">
                        <div class="portfolio-btn">
                            <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href="img/img-600x400-4.jpg" data-lightbox="portfolio"><i class="fa fa-eye"></i></a>
                            <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href=""><i class="fa fa-link"></i></a>
                        </div>
                    </div>
                    <div class="pt-3">
                        <p class="text-primary mb-0">Hydropower Plants</p>
                        <hr class="text-primary w-25 my-2">
                        <h5 class="lh-base">We Are pioneers of solar & renewable energy industry</h5>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 portfolio-item first">
                    <div class="portfolio-img rounded overflow-hidden">
                        <img class="img-fluid" src="img/img-600x400-3.jpg" alt="">
                        <div class="portfolio-btn">
                            <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href="img/img-600x400-3.jpg" data-lightbox="portfolio"><i class="fa fa-eye"></i></a>
                            <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href=""><i class="fa fa-link"></i></a>
                        </div>
                    </div>
                    <div class="pt-3">
                        <p class="text-primary mb-0">Solar Panels</p>
                        <hr class="text-primary w-25 my-2">
                        <h5 class="lh-base">We Are pioneers of solar & renewable energy industry</h5>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 portfolio-item second">
                    <div class="portfolio-img rounded overflow-hidden">
                        <img class="img-fluid" src="img/img-600x400-2.jpg" alt="">
                        <div class="portfolio-btn">
                            <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href="img/img-600x400-2.jpg" data-lightbox="portfolio"><i class="fa fa-eye"></i></a>
                            <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href=""><i class="fa fa-link"></i></a>
                        </div>
                    </div>
                    <div class="pt-3">
                        <p class="text-primary mb-0">Wind Turbines</p>
                        <hr class="text-primary w-25 my-2">
                        <h5 class="lh-base">We Are pioneers of solar & renewable energy industry</h5>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 portfolio-item third">
                    <div class="portfolio-img rounded overflow-hidden">
                        <img class="img-fluid" src="img/img-600x400-1.jpg" alt="">
                        <div class="portfolio-btn">
                            <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href="img/img-600x400-1.jpg" data-lightbox="portfolio"><i class="fa fa-eye"></i></a>
                            <a class="btn btn-lg-square btn-outline-light rounded-circle mx-1" href=""><i class="fa fa-link"></i></a>
                        </div>
                    </div>
                    <div class="pt-3">
                        <p class="text-primary mb-0">Hydropower Plants</p>
                        <hr class="text-primary w-25 my-2">
                        <h5 class="lh-base">We Are pioneers of solar & renewable energy industry</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    Projects End -->
    </body>
	

    
<%@ include file="include/footer.jsp"%>