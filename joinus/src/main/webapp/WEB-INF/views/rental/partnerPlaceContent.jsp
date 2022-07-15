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
			buttonText : "날짜 선택",
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


</script>

<div class="container-xxl py-5">
	<div class="container" style="color: black;">
		<div class="row g-5">
			<h1>${partnerPlace.partner_place_name }</h1>
			
			<div style="width: 70%;">
				<img style="width: 95%;" src="${PageContext.request.contextPath }/resources/upload/partner_place/${partnerPlace.partner_place_image}">
				<div style="margin-top: 3em;">
					<pre class="boardContent">${partnerPlace.partner_place_content }</pre>
				</div>
			</div>
			
			<!-- 전달할 정보 : 회원고유번호(세션값 이용), 제휴 시설 고유번호, 시설 대여비용, 최종 결제금액, 예약일자/시간 -->
			<div style="width: 30%; border: 1px solid #32C36C;">
				<form name="fr" action="" method="post">
					<input type="hidden" name="partner_place_no" value="${partner_place_no }">
					
				
					<div>
						<div style="font-size: x-large; float: left;">
							<i class="fa fa-check-circle text-primary me-3"></i>${partnerPlace.partner_place_name }
						</div>
						<div style="color: #32C36C; float: right;">
							<span style="font-size: x-large;">
								<fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${partnerPlace.partner_place_price }" /><br>
							</span>
							<span style="color: #9B9B9B; float: right;"> /시간</span>
						</div>
					</div>
					
					<div>
						<input name="rental_date" autocomplete="off" readonly>
					</div>
					<div>
						<select class="form-select" name="rental_time" multiple="multiple">
							<option value="1">10:00~12:00</option>
							<option value="2">12:00~14:00</option>
							<option value="3">14:00~16:00</option>
							<option value="4">16:00~18:00</option>
							<option value="5">18:00~20:00</option>
							<option value="6">20:00~22:00</option>
						</select>
					</div>
					
					<div>
					<!--  총 결제금액 -->
					</div>
				
				
				
				</form>
			</div>
			<!-- 전달할 정보 -->
			
			
			
		</div>
	</div>
</div>
    
    
    
    
<%@ include file="../include/footer.jsp"%>