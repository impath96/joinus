<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="../../include/header.jsp"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ce8d060125bcc89e0c25ee69f6b5c7b0&libraries=services"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"> </script>


<body> 
<!-- 정모 -->
<div class="container px-5">
	<div class="row g-5">
	
	<!-- 예약 정보 -->
	  <div class="col-md-5 col-lg-4 order-md-last">
        <h4 class="d-flex justify-content-between align-items-center mb-1">
          <span class="text-primary">예약 정보</span>
        </h4>
          <small class="text-mute">버튼을 누르면 자동으로 적용 됩니다.</small> 
       <c:forEach var = "vo" items="${rentalList }" varStatus="status">
        <ul class="list-group mb-3">
          <li class="list-group-item d-flex justify-content-between lh-sm" >
            <div>
            <input type="hidden" id="rental_no_${status.index }" name="rental_no_${status.index }" value="${vo.rentalPlacesVo.rental_places_no }">
              <h6 class="my-2">${vo.rentalPlacesVo.rental_date }</h6> <brㄴ>
              <h6 class="my-2">${vo.partnerPlacesVo.partner_place_name }</h6> <br>
            </div>
              <input type="button" id="${vo.rentalPlacesVo.rental_places_no }" name="rental_${status.index }" class="btn btn-secondary my-4" value='적용하기'>
          </li>
        </ul>
        </c:forEach>
      </div>
    <!-- 예약 정보 -->  
      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">일정 수정하기</h4>
        <form class="needs-validation" action ="" method="post">
          <div class="row g-3">
            <div class="col-12">
              <label for="firstName" class="form-label">제목</label>
              <input type="text" class="form-control" name="club_meeting_title" value="${meetingList[0].club_meeting_title}">
              <div class="invalid-feedback">
                Valid first name is required. 
              </div>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">날짜</label>
              <input type="date" class="form-control" id="club_meeting_date" value="${meetingList[0].club_meeting_date}">
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">정원</label>
              <input type="number" class="form-control" name="club_meeting_capacity" value="${meetingList[0].club_meeting_capacity}">
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">장소</label>
              	<div class="input-group">
              		<input type="text" class="form-control" id="club_meeting_location" value="${meetingList[0].club_meeting_location}">
            	</div>
            </div>
  
            <div class="col-12">
              <label for="address2" class="form-label">참가비 <span class="text-muted">(선택)</span></label>
              <input type="number" class="form-control" name="club_meeting_dues" value="${meetingList[0].club_meeting_dues}">
            </div>

          </div>

          <hr class="my-4">

          <h4 class="mb-3">Payment</h4>

          <div class="my-3">
            <div class="form-check">
              <input id="credit" name="paymentMethod" type="radio" class="form-check-input" checked="" required="">
              <label class="form-check-label" for="credit">Credit card</label>
            </div>
          </div>

          <div class="row gy-3">
            <div class="col-md-6">
              <label for="cc-name" class="form-label">계좌번호</label>
              <input type="text" class="form-control" id="cc-name" placeholder="">
              <small class="text-muted">계좌번호는 모임 회원에게만 보여요!</small>
            </div>

          </div>
          <hr class="my-4">
	
			<div class="margin">
			
			<div class="btn-group">
			<button type="submit" class="btn btn-secondary btn-flat"
				onclick ="location.href='${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/meeting/${meetingList[0].club_meeting_no}/modify';"
				>수정하기</button>
			</div>
			
			</div>

        </form>
      </div>
    </div>
</div>
<!-- 정모 -->

<script type="text/javascript">
 
$(function(){
	
	$("input[name^='rental']").on('click', function(e){
		
			//console.log($(this).attr('id')); 
			var rental_places_no = $(this).attr('id');
		
		$.ajax({
			
			url : '${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/meeting/rental_no/'+rental_places_no,
			type : 'GET',
			contentType : "application/json",
			success : function(data){
			//alert('갔다옴');
				//console.log(data);
				//console.log(data[0].partnerPlacesVo.partner_place_name);
				//console.log(data[0].rentalPlacesVo.rental_date);
				
				var rental_date = data[0].rentalPlacesVo.rental_date;
				var rental_place = data[0].partnerPlacesVo.partner_place_name;
				
				$('#club_meeting_date').attr('value',rental_date);
				$('#club_meeting_location').attr('value',rental_place);
				
				}
		});//ajax
	
	});//클릭
	
	
});//jquery


</script>

<%@ include file="../../include/footer.jsp"%>
