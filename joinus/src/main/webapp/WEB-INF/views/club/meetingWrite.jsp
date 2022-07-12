<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="../include/header.jsp"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ce8d060125bcc89e0c25ee69f6b5c7b0&libraries=services"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"> </script>


<body> 
<!-- 정모 -->
<div class="container px-5">
	<div class="row g-5">
      <div class="col-md-5 col-lg-4 order-md-last">
        <h4 class="d-flex justify-content-between align-items-center mb-3">
          <span class="text-primary">예약 장소</span>
          <span class="badge bg-primary rounded-pill">총 개수</span>
        </h4>
       <c:forEach var = "vo" items="${rentalList }" varStatus="status">
        <ul class="list-group mb-3">
          <li class="list-group-item d-flex justify-content-between lh-sm" >
            <div>
            <input type="text" id="rental_no_${status.index}" value="${vo.rentalPlacesVo.rental_places_no }">
              <h6 class="my-0">${vo.rentalPlacesVo.rental_date }</h6>
              <small class="text-muted">${vo.partnerPlacesVo.partner_place_name }</small> <br>
              <input type="button" class="btn btn-secondary" value='적용하기'
              onclick="location.href='${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/meeting/${vo.rentalPlacesVo.rental_places_no }';"
              >
            </div>
          </li>
        </ul>
        </c:forEach>
      </div>
      
      
      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">일정 생성하기</h4>
        <form class="needs-validation" action ="${PageContext.request.contextPath }/club/${clubInfo[0].club_no}/meeting/new" method="post">
          <div class="row g-3">
          
            <div class="col-12">
              <label for="firstName" class="form-label">제목</label>
              <input type="text" class="form-control" name="club_meeting_title" value="" required="">
              <div class="invalid-feedback">
                Valid first name is required.
              </div>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">날짜</label>
              <input type="date" class="form-control" name="club_meeting_date"  required="">
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">정원</label>
              <input type="number" class="form-control" name="club_meeting_capacity" required="">
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>
            

            <div>
            <div class="col-12">
              <label for="address" class="form-label">장소</label>
              	<div class="input-group">
              		<input type="text" class="form-control" id="keyward" name="club_meeting_location">
             		<button type="button" class="btn btn-secondary" id ="search">검색하기</button>
            	</div>
            	<div>
            		<ul id="placesList"></ul>
            		<div id="pagination"></div>
            	</div>
            </div>
            <!-- https://map.kakao.com/link/search/카카오 -->
       
       		<div id="map" style="width:500px; height:400px;" class="text-center"></div>
       		</div>
      
            <div class="col-12">
              <label for="address2" class="form-label">참가비 <span class="text-muted">(선택)</span></label>
              <input type="number" class="form-control" name="club_meeting_dues" placeholder="Apartment or suite">
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
              <input type="text" class="form-control" id="cc-name" placeholder="" required="">
              <small class="text-muted">계좌번호는 모임 회원에게만 노출됩니다.</small>
              <div class="invalid-feedback">
                Name on card is required
              </div>
            </div>

          </div>
          <hr class="my-4">
          <button class="w-100 btn btn-primary btn-lg" type="submit">일정 생성하기</button>
        </form>
      </div>
    </div>
</div>
<!-- 정모 -->

<script type="text/javascript">

$(function(){
	//alert('jquery! check');
	
		//alert($('#rental_no'+${status.current}).attr('value'));
	
	//$('#rental').click(function(){
		//alert('rental 버튼 클릭');
		
		//$.ajax({
		//	url : '/club/rental?rental_places_no='+rental_no,
		//	type :'get',
		//	success : function(){
		//		alert('갔다옴');
		//	}
		//});//ajax
		
	//});//click
	
	
	
	
});//jquery


</script>

<%@ include file="../include/footer.jsp"%>
