<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<%@ include file="../include/header.jsp"%>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=688b30a5f6f90e57956268dc3f172b25&libraries=services"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript">
	$(document).ready(function(){
		$(':button').attr('class','btn btn-primary');
	});
</script>

<div class="container-xxl py-5">
	<div class="container" style="color: black;">
		<div class="row g-5">
			<h1 id="partnerPlaceName">${place.place_name } <small>(비제휴)</small></h1>
<%-- 			<a href="${PageContext.request.contextPath }/place/placeList" style="color: black;">
	            <small>더 많은 제휴시설 보러가기 <i class="fa fa-arrow-right ms-3"></i> 
	        </small></a> --%>
			
			<div style="width: 70%;">
				<img style="width: 95%; max-height: 550px;" src="${PageContext.request.contextPath }/resources/upload/place/${place.place_image}">
				<div style="margin-top: 3em;" class="font">
					<pre class="boardContent">${place.place_content }</pre>
				</div>
			<hr>
				<div id="partnerPlaceAddr" style="margin-bottom: 16px;" class="font">
					${place.place_address }
				</div>
				
				<!-- 주소로 장소 표시 -->
				<div class="map_wrap py-2" style="padding-right: 3em;">
					<div id="map" style="width:100%; height:400px;"></div>
				</div>
			</div>
			
			<div style="width: 30%; border: 1px solid #32C36C; padding-top: 1em; height: 70%;">
				<form name="fr" action="" method="post">
					<input type="hidden" name="partner_place_no" value="${place_no }">
					<input type="hidden" name="partner_place_name" value="${place.place_name }">
				 	<input type="hidden" name="partner_place_price" value="${place.place_price }">
				
					<div>
						<div style="font-size: x-large; float: left;">
							${place.place_name }
						</div>
 						<div style="color: #32C36C; text-align: right; margin-bottom: 2em;">
 							<span style="font-size: x-large;">
								<fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${place.place_price }" />
								<br>
							</span>
							<span style="color: #9B9B9B;"> /시간</span>
						</div>
					</div>
					
					<div style="margin-bottom: 2em;">
						<i class="fa fa-phone-alt me-3" aria-hidden="true"></i>${place.place_tel }
					</div>
					
					<div style="margin-bottom: 2em;">
						날짜 선택
						<select class="form-select" id="rental_time" disabled="disabled">
							<option value="">해당 시설에 문의해주세요.</option>
						</select>
					</div>
					
					<div style="margin-bottom: 2em;">
						시간 선택
						<select class="form-select" id="rental_time" disabled="disabled">
							<option value="">해당 시설에 문의해주세요.</option>
						</select>
					</div>
					
					<div class="payBtn">
						<input type="submit" class="btn btn-primary rounded-pill py-3 px-5" id="subBtn" value="비제휴 시설입니다." disabled="disabled">
					</div>
				</form>
			</div>
			
		</div>
	</div>
</div>

<script>
	var partnerPlaceAddr = document.getElementById("partnerPlaceAddr").innerText;
	var partnerPlaceName =  document.getElementById("partnerPlaceName").innerText;
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
	    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨
	};  
	
	//지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption);
	
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(partnerPlaceAddr, function(result, status) {

	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });

	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+partnerPlaceName+'</div>'
	        });
	        infowindow.open(map, marker);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	});    

</script>

<%@ include file="../include/footer.jsp"%>