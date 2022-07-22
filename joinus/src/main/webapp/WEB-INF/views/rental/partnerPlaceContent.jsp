<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<%@ include file="../include/header.jsp"%>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=688b30a5f6f90e57956268dc3f172b25&libraries=services"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript">
	var partner_place_no = ${partner_place_no};
	alert("partner_place_no : "+partner_place_no);

	// 캘린더
	var config = {
			dateFormat : 'yy-mm-dd',
			showOn : "button",
			buttonText : "선택",
			prevText : '이전 달',
			nextText : '다음 달',
			monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dayNames : ['일','월','화','수','목','금','토'],
			dayNamesShort : ['일','월','화','수','목','금','토'],
			dayNamesMin : ['일','월','화','수','목','금','토'],
			yearSuffix : '년',
			minDate : 1	// 오늘은 선택X 내일부터 일자선택가능
	}
	
	$(function(){
		$("input[name='rental_date']").datepicker(config);
	});
	
	$(document).ready(function(){
		
		$(':button').attr('class','btn btn-primary');
		
		$('#subBtn').click(function(){
			//alert('결제버튼클릭');
			
			if($('#rental_date').val() == ''){
				$('#rental_date').focus();
				return false;
			}
			if($('#rental_time_no').val() == ''){
				$('#rental_time_no').focus();
				return false;
			}
			if($('#memberCnt').val() == ''){
				$('#memberCnt').focus();
				return false;
			}
			if($('#memberCnt').val() > 20){
				alert('최대 20명까지 가능합니다.');
				$('#memberCnt').focus();
				return false;
			}
//  			alert("hidden price value : "+$('#payment_price').val());
 			
 			// 모임장만 결제가 가능
 			if(${checkClubAdmin == 0}){
 				alert('모임장만 결제가 가능합니다.');
 				return false;
 			}
			
			
		});
		
		// 시간 선택 시 예약된 정보 비교 및 막기
		// 시간 선택 시 말고 걍 일자 등록하고 나서 바로 갔다왔음 좋겠음
		// => 일자값이 공백이 아니면,,  안되네.. 걍 시간 선택 시 ㄱㄱ
		$('#rental_time_no').click(function(){
// 			alert('시간선택');
// 			$("select option[value*='1']").prop('disabled', true);
			$("select option[value*='3']").prop('disabled', true);
// 			var rental_date = $('#rental_date').val();
// 			alert("rental_date : "+rental_date);

			var timeList = [];
// 			console.log(timeList);
			
			$.ajax({
				url : '${PageContext.request.contextPath }/rental/partnerPlaces/'+${partner_place_no}+'/dateCheck',
				type : 'GET',
				async : false,	// 전역변수에 데이터 저장
				data : {'rental_date':$('#rental_date').val()},
				success : function(data){
// 					alert('갔다옴');
// 					$("select option[value*='2']").prop('disabled', true);
// 					console.log(data);
					timeList = data;
				}
			});
			
			console.log("해당 일자의 예약 시간대 Arr : "+timeList);
			
// 			for(var i=0; i<timeList.length; i++){
// 				console.log(timeList[i]);
// 			}
			
			$('#timeList').text(timeList);
			
			// select option 값 들고와서 ajax 리스트 데이터와 비교
			// select option value를 배열로 저장
			var select = document.getElementById("rental_time_no");
			var optionVal = [];
			
			for(var i=1; i<select.length; i++){
				// 0부터 시작하면 value = ""이 포함되므로 value값이 있는 1부터 시작
				console.log(select.options[i].value);
				optionVal.push(select.options[i].value);
			}
			
			console.log("optionValueArr : "+optionVal);
			
			console.log(timeList);
			console.log(optionVal);
			
			
		});	// 시간 선택 시 예약시간 막기
		
		
	});	// jQuery


</script>

<c:forEach var="rental" items="${rentalPlaceDate }">
	${rental.rental_date } / ${rental.rental_time_no }<br>
</c:forEach>
<div id="timeList">
</div>
<div class="container-xxl py-5">
	<div class="container" style="color: black;">
		<div class="row g-5">
			<h1 id="partnerPlaceName">${partnerPlace.partner_place_name }</h1>
			
			<div style="width: 70%;">
				<img style="width: 95%; max-height: 550px;" src="${PageContext.request.contextPath }/resources/upload/partner_place/${partnerPlace.partner_place_image}">
				<div style="margin-top: 3em;">
					<pre class="boardContent">${partnerPlace.partner_place_content }</pre>
				</div>
				
				<hr class="partnerPlaceContentHr">
				
				<div style="margin-bottom: 16px;">
					<i class="fa fa-phone-alt me-3" aria-hidden="true"></i>${partnerPlace.partner_place_tel }
				</div>
				<div id="partnerPlaceAddr" style="margin-bottom: 16px;">
					${partnerPlace.partner_place_address }
				</div>
				
				<!-- 주소로 장소 표시 -->
				<div class="map_wrap py-2" style="padding-right: 3em;">
					<div id="map" style="width:100%; height:400px;"></div>
				</div>
				
			</div>
			
			<!-- 전달할 정보 : 회원고유번호(세션값 이용), 제휴 시설 고유번호, 시설 대여비용(이건 굳이 결제테이블에 안넘겨도 되지 않을까), 최종 결제금액, 예약하는 일자/시간 -->
			<div style="width: 30%; border: 1px solid #32C36C; padding-top: 1em; height: 70%;">
				<form name="fr" action="" method="post">
					<input type="hidden" name="partner_place_no" value="${partner_place_no }">
					<input type="hidden" name="partner_place_name" value="${partnerPlace.partner_place_name }">
					<input type="hidden" name="partner_place_price" value="${partnerPlace.partner_place_price }">
				
					<div>
						<div style="font-size: x-large; float: left;">
							${partnerPlace.partner_place_name }
						</div>
						<div style="color: #32C36C; text-align: right; margin-bottom: 2em;">
							<span style="font-size: x-large;">
								<fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${partnerPlace.partner_place_price }" />
								<br>
							</span>
							<span style="color: #9B9B9B;"> /시간</span>
						</div>
					</div>
					
					
					<div>
					예약인원 <input type="number" class="form-control" id="memberCnt" name="memberCnt" max="20" required style="color: black;">
					</div>
					
					<script type="text/javascript">
						var memberCnt = 0;
						var totalPrice = 0;
						$('#memberCnt').blur(function(){
							memberCnt = $('#memberCnt').val();
							totalPrice = memberCnt * ${partnerPlace.partner_place_price} * 2
// 							alert("totalPrice : "+ totalPrice);
							document.getElementById("seePrice").innerHTML = totalPrice.toLocaleString();	// 보여줄 땐 천단위마다 콤마
							$('#payment_price').val(totalPrice);
							
						});
					</script>
					
					
					<!-- 일자 -->
					<div style="margin-bottom: 2em;">
						날짜 선택 (아래의 버튼을 클릭해주세요)
						<input class="form-control" id="rental_date" name="rental_date" autocomplete="off" readonly style="background-color: white; color: black;">
					</div>
					
					<!-- 시간 -->
					<div style="margin-bottom: 2em;">
						시간 선택
						<select class="form-select" id="rental_time_no" name="rental_time_no" style="color: black;">
							<option value="">시간을 선택해주세요.</option>
							<option value="1"
								<c:if test="${test =='1' }">
									disabled
								</c:if>
							>10:00~12:00</option>
							<option value="2">12:00~14:00</option>
							<option value="3">14:00~16:00</option>
							<option value="4">16:00~18:00</option>
							<option value="5">18:00~20:00</option>
							<option value="6">20:00~22:00</option>
						</select>
					</div>

					<!--  총 결제금액 -->
					<div>
						총 결제금액<br>
						<span id="seePrice"></span>
						<input type="hidden" id="payment_price" name="payment_price">
					</div>
					
					<div class="payBtn">
						<input type="submit" class="btn btn-primary rounded-pill py-3 px-5" id="subBtn" value="결제하기">
					</div>
				
				</form>
			</div>
			<!-- 전달할 정보 -->
			
			
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