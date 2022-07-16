<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="../include/header.jsp"%>


<div class="container-xxl py-5">
	<div class="container">
		<div class="row g-5">

<!-- 			<div style="margin-bottom: 2em;"> -->
<!-- 				<button type="button" class="btn btn-primary" id="all">전체</button> -->
<!-- 				<button type="button" class="btn btn-primary" id="notice">공지사항</button> -->
<!-- 				<button type="button" class="btn btn-primary" id="free">자유글</button> -->
<!-- 				<button type="button" class="btn btn-primary" id="review">정모후기</button> -->
<!-- 			</div> -->
			
			<c:forEach var="partnerPlace" items="${partnerPlaceList }">
	            <div class="col-lg-4 col-md-6" onclick=" location.href='/rental/partnerPlaces/1'; " style="cursor: pointer;">
	                <div class="portfolio-img overflow-hidden">
	                    <img class="img-fluid" src="${PageContext.request.contextPath }/resources/upload/partner_place/${partnerPlace.partner_place_image}">
	                </div>
	                <div class="pt-3 placeListContent">
	                    <p class="text-primary mb-0">${partnerPlace.partner_place_address }</p>
	                    <hr class="text-primary w-25 my-2">
	                    <h5 class="lh-base">${partnerPlace.partner_place_name }</h5>
	                    <div>${partnerPlace.partner_place_price }</div>
	                </div>
	            </div>
            </c:forEach>
            
            <div class="col-lg-4 col-md-6" onclick=" location.href='/rental/partnerPlaces/1'; " style="cursor: pointer;">
                <div class="portfolio-img overflow-hidden">
                    <img class="img-fluid" src="${PageContext.request.contextPath }/resources/img/img-600x400-4.jpg" alt="">
                </div>
                <div class="pt-3 placeListContent">
                    <p class="text-primary mb-0">Hydropower Plants</p>
                    <hr class="text-primary w-25 my-2">
                    <h5 class="lh-base">We Are pioneers of solar & renewable energy industry</h5>
                </div>
            </div>
            <div class="col-lg-4 col-md-6" onclick=" location.href='/rental/partnerPlaces/1'; " style="cursor: pointer;">
                <div class="portfolio-img overflow-hidden">
                    <img class="img-fluid" src="${PageContext.request.contextPath }/resources/img/img-600x400-4.jpg" alt="">
                </div>
                <div class="pt-3 placeListContent">
                    <p class="text-primary mb-0">Hydropower Plants</p>
                    <hr class="text-primary w-25 my-2">
                    <h5 class="lh-base">We Are pioneers of solar & renewable energy industry</h5>
                </div>
            </div>
            <div class="col-lg-4 col-md-6" onclick=" location.href='/rental/partnerPlaces/1'; " style="cursor: pointer;">
                <div class="portfolio-img overflow-hidden">
                    <img class="img-fluid" src="${PageContext.request.contextPath }/resources/img/img-600x400-4.jpg" alt="">
                </div>
                <div class="pt-3 placeListContent">
                    <p class="text-primary mb-0">Hydropower Plants</p>
                    <hr class="text-primary w-25 my-2">
                    <h5 class="lh-base">We Are pioneers of solar & renewable energy industry</h5>
                </div>
            </div>
            <div class="col-lg-4 col-md-6" onclick=" location.href='/rental/partnerPlaces/1'; " style="cursor: pointer;">
                <div class="portfolio-img overflow-hidden">
                    <img class="img-fluid" src="${PageContext.request.contextPath }/resources/img/img-600x400-4.jpg" alt="">
                </div>
                <div class="pt-3 placeListContent">
                    <p class="text-primary mb-0">Hydropower Plants</p>
                    <hr class="text-primary w-25 my-2">
                    <h5 class="lh-base">We Are pioneers of solar & renewable energy industry</h5>
                </div>
            </div>
            <div class="col-lg-4 col-md-6" onclick=" location.href='/rental/partnerPlaces/1'; " style="cursor: pointer;">
                <div class="portfolio-img overflow-hidden">
                    <img class="img-fluid" src="${PageContext.request.contextPath }/resources/img/img-600x400-4.jpg" alt="">
                </div>
                <div class="pt-3 placeListContent">
                    <p class="text-primary mb-0">Hydropower Plants</p>
                    <hr class="text-primary w-25 my-2">
                    <h5 class="lh-base">We Are pioneers of solar & renewable energy industry</h5>
                </div>
            </div>
			
			
			
			
	
			
			
		</div>
	</div>
</div>
    
    
    
    
<%@ include file="../include/footer.jsp"%>