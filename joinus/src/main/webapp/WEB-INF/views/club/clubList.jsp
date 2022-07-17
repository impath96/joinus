<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
 
<%@ include file="../include/header.jsp"%>



<body> 

		<!-- Testimonial Start -->
		<br>
		<br>
		<br>
		<br>
		
    <div class="container-xxl py-5">
        <div class="container py-5">
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
	
	<!-- 소개 -->
		
	<div class="container-xxl py-5" style=" background-color: #EFEFEF;">
        <div class="container">
            <div class="row g-3">
                <div class="col-md-6 py-2 px-5 wow fadeIn" data-wow-delay="0.1s" style="visibility: visible; animation-delay: 0.1s; animation-name: fadeIn;">
                    <div class="d-flex align-items-center mb-4">
                        <h1 class="mb-0" data-toggle="counter-up">3453</h1>
                        <h2>명이 함께하고 있습니다!</h2>
                    </div>
                    <span> JoinUs는 경남권 전용 소모임 커뮤니티입니다. <br>
                    친구와 함께 다양한 취미생활에 도전해 보세요!
                    
					</span>
                </div>
				<!-- 모임 생성 -->   
	         <div class="text-center wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
	         	<h6 class="py-3">원하는 소모임이 없으신가요 ❓ </h6>
	         	<h6>새로운 모임을 만들어보세요 </h6> 
	         	<h2 class="py-3" > <a href="${PageContext.request.contextPath }/club/new" style="color: black;">
	            	모임 만들러 가기 <i class="fa fa-arrow-right ms-3"></i> 
	         	</a></h2>
	         </div>
	         <!-- 모임 생성 --> 


            </div>
        </div>
    </div>
	
	<!-- 소개 -->
	
    <!-- Projects Start -->
    <div class="container-xxl py-5">
        <div class="container">
        
         
        	<!-- 카테고리 -->
            <div class="row mt-n2 wow fadeInUp" data-wow-delay="0.3s">
                <div class="text-center py-5 border-dark">
                    <ul class="list-inline mb-3" id="portfolio-flters">
                        <li class="mx-2 active fw-bold"><a href="/club/clubList">전체</a></li>
                        <li class="mx-2 fw-bold"><a href="/club/clubList?interest_no=1">요리/제조</a></li>
                        <li class="mx-2 fw-bold"><a href="/club/clubList?interest_no=2">봉사활동</a></li>
                        <li class="mx-2 fw-bold"><a href="/club/clubList?interest_no=3">운동/스포츠</a></li>
                        <li class="mx-2 fw-bold"><a href="/club/clubList?interest_no=4">게임/오락</a></li>
                        <li class="mx-2 fw-bold"><a href="/club/clubList?interest_no=5">음악/악기</a></li>
                        <li class="mx-2 fw-bold"><a href="/club/clubList?interest_no=6">아웃도어/여행</a></li>
                        <li class="mx-2 fw-bold"><a href="/club/clubList?interest_no=7">외국/언어</a></li>
                        <li class="mx-2 fw-bold"><a href="/club/clubList?interest_no=8">문화/공연/축제</a></li>
                    </ul>
                </div>
					
				<c:set var="interest_no" value="${interest_no }" />
				<c:if test="${not empty interest_no}">
                	<div id="detail" class="rounded-2 py-3 mb-4" style="background-color: #EFEFEF;">
                	<h5 class="px-3"> ✔️ 좀 더 상세한 결과를 원하세요?  </h5>
                	</div>
                </c:if>	
                
            </div>
            <!-- 카테고리 -->
            
  
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
                            <h4 class="mt-3 py-2">${vo.clubsVo.club_name }</h4>
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
        			<li><a href="${PageContext.request.contextPath }/club/clubList?page=${pm.startPage-1}"><i class="fa-solid fa-angles-left px-3 py-3"></i></a></li>
        		</c:if>
        		<c:forEach var ="idx" begin ="${pm.startPage }" end="${pm.endPage }">
        			<li class="list-group-item" <c:out value="${pm.cri.page == idx? 'class=active':'' }"/>>
        			<a href = "${PageContext.request.contextPath }/club/clubList?page=${idx}" >${idx }</a>
        			</li>
        		</c:forEach>
        		
        		<c:if test="${pm.next && pm.endPage>0 }">
        			<li> <a href ="${PageContext.request.contextPath }/club/clubList?page=${pm.endPage+1}"><i class="fa-solid fa-angles-right px-3 py-3"></i></a></li>
        		</c:if>
        	
        	</ul>
        </div>
        
        
    </div>
    <!-- Projects End -->
     
<script type="text/javascript">
$(function(){
	
	var interest_no = ${interest_no};
	//alert(interest_no);
	
	
$.ajax({
		
		url :'${PageContext.request.contextPath}/club/clubList/'+interest_no,
		type : 'GET',
		dataType : "json",
		contentType : "application/json",
		success : function(data){
		console.log(data);
		console.log(data.interest_detail_name);
			
			$(data).each(function(idx,item){
				
				var tag = "<input type='button' class='btn py-2 px-lg-5' value='# "+item.interest_detail_name+"'>"
				
				$('#detail').append(tag);
				
			});//each
			
		}	
			
		
	});//ajax
	
//테이블에 요소 추가

	
/* 	var tag="<tr>";
	tag += "<td>"+item.bno+"</td>"
	tag += "<td><a href='/web/"+item.bno+"'>"+item.title+"</a></td>"
	tag += "<td>"+item.writer+"</td>"
	tag += "<td>"+new Date(item.regdate)+"</td>"
	tag += "<td>"+item.viewcnt+"</td>"
	tag += "</tr>";
	
	$('table').append(tag);
}); */
	
	
});


</script>




<%@ include file="../include/footer.jsp"%>
