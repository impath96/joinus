<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<%@ include file="../include/header.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript">

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
			minDate : 0
	}
	
	$(function(){
		$("input[name='rental_date']").datepicker(config);
	});
	
	$(document).ready(function(){
		
		$(':button').attr('class','btn btn-primary');
		
		
		$('#subBtn').click(function(){
			alert('결제버튼클릭');
			
			if($('#rental_date').val() == ''){
				$('#rental_date').focus();
				return false;
			}
			if($('#rental_time').val() == ''){
				$('#rental_time').focus();
				return false;
			}
			if($('#memberCnt').val() == ''){
				$('#memberCnt').focus();
				return false;
			}
			
 			//alert("hidden price value : "+$('#totalPrice').val());
			
		});
		
	});


</script>

<div class="container-xxl py-5">
	<div class="container" style="color: black;">
		<div class="row g-5">
			<h1>${partnerPlace.partner_place_name }</h1>
			
			<div style="width: 70%;">
				<img style="width: 95%; max-height: 550px;" src="${PageContext.request.contextPath }/resources/upload/partner_place/${partnerPlace.partner_place_image}">
				<div style="margin-top: 3em;">
					<pre class="boardContent">${partnerPlace.partner_place_content }</pre>
				</div>
				
				<!-- 주소로 장소 표시 -->
				
			</div>
			
			<!-- 전달할 정보 : 회원고유번호(세션값 이용), 제휴 시설 고유번호, 시설 대여비용(이건 굳이 결제테이블에 안넘겨도 되지 않을까), 최종 결제금액, 예약하는 일자/시간 -->
			<div style="width: 30%; border: 1px solid #32C36C; padding-top: 1em; height: 70%;">
				<form name="fr" action="" method="post">
					<input type="hidden" name="partner_place_no" value="${partner_place_no }">
					<input type="hidden" name="partner_place_price" value="${partnerPlace.partner_place_price }">
				
					<div>
						<div style="font-size: x-large; float: left;">
<!-- 							<i class="fa fa-check-circle text-primary me-3"></i> -->
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
					예약인원 <input type="number" class="form-control" id="memberCnt" name="memberCnt" required>
					</div>
					
					<script type="text/javascript">
						var memberCnt = 0;
						var totalPrice = 0;
						$('#memberCnt').blur(function(){
							memberCnt = $('#memberCnt').val();
							totalPrice = memberCnt * ${partnerPlace.partner_place_price} * 2
// 							alert("totalPrice : "+ totalPrice);
							document.getElementById("seePrice").innerHTML = totalPrice.toLocaleString();	// 보여줄 땐 천단위마다 콤마
							$('#totalPrice').val(totalPrice);
							
						});
					</script>
					
					
					<!-- 일자 -->
					<div style="margin-bottom: 2em;">
					날짜 선택 (아래의 버튼을 클릭해주세요)
						<input class="form-control" id="rental_date" name="rental_date" autocomplete="off" readonly style="background-color: white;">
					</div>
					
					<!-- 시간 -->
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

					<!--  총 결제금액 -->
					<div>
					총 결제금액<br>
					<span id="seePrice"></span>
					<input type="hidden" id="totalPrice" name="totalPrice">
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
    
    
    
    
<%@ include file="../include/footer.jsp"%>