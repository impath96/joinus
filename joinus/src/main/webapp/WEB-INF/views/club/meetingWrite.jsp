<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="../include/header.jsp"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ce8d060125bcc89e0c25ee69f6b5c7b0"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services"></script>

<body> 
<!-- 정모 -->
<div class="container px-5">
	<div class="row g-5">
      <div class="col-md-5 col-lg-4 order-md-last">
        <h4 class="d-flex justify-content-between align-items-center mb-3">
          <span class="text-primary">예약 장소</span>
          <span class="badge bg-primary rounded-pill">총 개수</span>
        </h4>
        <ul class="list-group mb-3">
          <li class="list-group-item d-flex justify-content-between lh-sm">
            <div>
              <h6 class="my-0">7/30</h6>
              <small class="text-muted">##장소 이름##</small> <br>
              <small class="text-muted">##장소 시간##</small>
            </div>
            <span class="text-muted">n명</span>
          </li>
          <li class="list-group-item d-flex justify-content-between lh-sm">
            <div>
              <h6 class="my-0">7/30</h6>
              <small class="text-muted">##장소 이름##</small> <br>
              <small class="text-muted">##장소 시간##</small>
            </div>
            <span class="text-muted">n명</span>
          </li>
        </ul>
      </div>
      
      
      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">일정 생성하기</h4>
        <form class="needs-validation" novalidate="">
          <div class="row g-3">
          
            <div class="col-12">
              <label for="firstName" class="form-label">제목</label>
              <input type="text" class="form-control" id="firstName" placeholder="8월 정기 모임" value="" required="">
              <div class="invalid-feedback">
                Valid first name is required.
              </div>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">날짜</label>
              <input type="date" class="form-control" id="address"  required="">
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">정원</label>
              <input type="number" class="form-control" id="address" required="">
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>
            
            <form action="" method="get">
            <div class="col-12">
              <label for="address" class="form-label">장소</label>
              	<div class="input-group">
              		<input type="text" class="form-control" id="address">
             		<button type="button" class="btn btn-secondary" id="search">검색하기</button>
            	</div>
            </div>
            </form>
            <!-- https://map.kakao.com/link/search/카카오 -->
       
       		<div id="map" style="width:500px;height:400px;" class="text-center"></div>
       		<script>
       		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
       		var options = { //지도를 생성할 때 필요한 기본 옵션
       			center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
       			level: 3 //지도의 레벨(확대, 축소 정도)
       		};

       		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
       		</script>
       
       
            <div class="col-12">
              <label for="address2" class="form-label">참가비 <span class="text-muted">(선택)</span></label>
              <input type="number" class="form-control" id="address2" placeholder="Apartment or suite">
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

$(document).ready(function(){
	
	
	$.ajax({
		
		
		
		
	})//ajax
	
	
});//jquery


</script>


<%@ include file="../include/footer.jsp"%>
