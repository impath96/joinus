<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
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
			
			<!-- 모서리 각진 버전 -->
<%-- 			<c:forEach var="partnerPlace" items="${partnerPlaceList }"> --%>
<!-- 	            <div class="col-lg-4 col-md-6" onclick=" location.href='/rental/partnerPlaces/1'; " style="cursor: pointer;"> -->
<!-- 	                <div class="portfolio-img overflow-hidden"> -->
<%-- 	                    <img class="img-fluid" src="${PageContext.request.contextPath }/resources/upload/partner_place/${partnerPlace.partner_place_image}"> --%>
<!-- 	                </div> -->
<!-- 	                <div class="pt-3 placeListContent"> -->
<%-- 	                    <p class="text-primary mb-0">${partnerPlace.partner_place_address }</p> --%>
<!-- 	                    <hr class="text-primary w-25 my-2"> -->
<%-- 	                    <h5 class="lh-base">${partnerPlace.partner_place_name }</h5> --%>
<%-- 	                    <div>${partnerPlace.partner_place_price }</div> --%>
<!-- 	                </div> -->
<!-- 	            </div> -->
<%--             </c:forEach> --%>

			<div class="row g-4">
				<c:forEach var="partnerPlace" items="${partnerPlaceList }">
	                <div class="col-md-6 col-lg-4 wow fadeInUp" data-wow-delay="0.1s" onclick=" location.href='/rental/partnerPlaces/${partnerPlace.partner_place_no}'; " style="cursor: pointer;">
	                    <div class="service-item rounded overflow-hidden shadow" style="height: auto;">
	                    <!-- 클럽 대표 이미지 -->
	                        <img src="${PageContext.requeset.contextPath }/resources/upload/partner_place/${partnerPlace.partner_place_image}" class="w-100 py-auto" style="height: 225px;">
	                        <div class="position-relative p-4 pt-0">
	                            <h4 class="mb-3 py-2">${partnerPlace.partner_place_name }</h4>
	                            <div>
		                            <span>${partnerPlace.partner_place_address }</span>
		                            <div style="float: right;"><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${partnerPlace.partner_place_price }" /><small> 원/시간</small></div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </c:forEach>
        	</div>       
			
			
			
		</div>
	</div>
</div>
    
    
    
    
<%@ include file="../include/footer.jsp"%>