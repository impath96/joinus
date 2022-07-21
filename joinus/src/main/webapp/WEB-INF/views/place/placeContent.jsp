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
			<h1 id="partnerPlaceName">${place.place_name }</h1>
			
			<div style="width: 70%;">
				<img style="width: 95%; max-height: 550px;" src="${PageContext.request.contextPath }/resources/upload/place/${place.place_image}">
				<div style="margin-top: 3em;">
					<pre class="boardContent">${place.place_content }</pre>
				</div>
				
				<hr>
				
				<div style="margin-bottom: 16px;">
					<i class="fa fa-phone-alt me-3" aria-hidden="true"></i>${place.place_tel }
				</div>
				<div id="partnerPlaceAddr" style="margin-bottom: 16px;">
					${place.place_address }
				</div>
				
				<!-- 주소로 장소 표시 -->
				<div class="map_wrap py-2" style="padding-right: 3em;">
					<div id="map" style="width:100%; height:400px;"></div>
				</div>
				
			</div>
			
			<!-- 전달할 정보 : 회원고유번호(세션값 이용), 제휴 시설 고유번호, 시설 대여비용(이건 굳이 결제테이블에 안넘겨도 되지 않을까), 최종 결제금액, 예약하는 일자/시간 -->
			<div style="width: 30%; border: 1px solid #32C36C; padding-top: 1em; height: 70%;">
				<form name="fr" action="" method="post">
					<input type="hidden" name="partner_place_no" value="${place_no }">
					<input type="hidden" name="partner_place_name" value="${place.place_name }">
				<%-- 	<input type="hidden" name="partner_place_price" value="${place.place_price }"> --%>
				
					<div>
						<div style="font-size: x-large; float: left;">
<!-- 							<i class="fa fa-check-circle text-primary me-3"></i> -->
							${place.place_name }
						</div>
						<div style="color: #32C36C; text-align: right; margin-bottom: 2em;">
<%-- 							<span style="font-size: x-large;">
								<fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${place.place_price }" />
								<br>
							</span>
							<span style="color: #9B9B9B;"> /시간</span> --%>
						</div>
					</div>
					
<!-- 		
					<div>
					예약인원 <input type="number" class="form-control" id="memberCnt" name="memberCnt" required>
					</div>
					
					일자
					<div style="margin-bottom: 2em;">
						날짜 선택 (아래의 버튼을 클릭해주세요)
						<input class="form-control" id="rental_date" name="rental_date" autocomplete="off" readonly style="background-color: white;">
					</div>
					
					시간
					<div style="margin-bottom: 2em;">
						시간 선택
						<select class="form-select" id="rental_time" name="rental_time">
							<option value="">시간을 선택해주세요.</option>
							<option value="1">10:00~12:00</option>
							<option value="2">12:00~14:00</option>
							<option value="3">14:00~16:00</option>
							<option value="4">16:00~18:00</option>
							<option value="5">18:00~20:00</option>
							<option value="6">20:00~22:00</option>
						</select>
					</div>

					 총 결제금액
					<div>
						총 결제금액<br>
						<span id="seePrice"></span>
						<input type="hidden" id="totalPrice" name="totalPrice">
					</div>
					
					<div class="payBtn">
						<input type="submit" class="btn btn-primary rounded-pill py-3 px-5" id="subBtn" value="결제하기">
					</div>
				
-->
				</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>