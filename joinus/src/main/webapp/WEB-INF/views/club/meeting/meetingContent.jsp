<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../include/header.jsp"%>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ce8d060125bcc89e0c25ee69f6b5c7b0&libraries=services"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"> </script>


<body> 
<!-- 정모 -->
<div class="container px-5 py-5">
	<div class="row g-5 py-5">
	
		<!-- 참가인원 -->
	  <div class="col-md-5 col-lg-4 order-md-last">
        <h4 class="d-flex justify-content-between align-items-center mb-1">
          <span class="text-primary py-5">🌟 현재 참가인원</span>
        </h4>
       <c:forEach var = "vo" items="${meetingMember }" varStatus="status">
        <ul class="list-group mb-3">
          <li class="list-group-item d-flex justify-content-between lh-sm" >
              <h6 class="my-2">${vo.membersVo.member_name }</h6> <br>
          </li>
        </ul>
        </c:forEach>
      </div>
      <!-- 참가인원 -->
      
      <div class="col-md-7 col-lg-8">
        <form class="needs-validation" action ="" role ="form">
        <h4 class="mb-5">${meetingList[0].club_meeting_title}</h4>
          <div class="row g-3">
            <div class="col-12">
              <label for="address" class="form-label">🗓️ 날짜</label>
              <input type="datetime" class="form-control" id="club_meeting_date" value="${meetingList[0].club_meeting_date}" disabled="disabled">
            </div>
         
            
            <div class="col-12">
              <label for="address" class="form-label">🙋 정원</label>
              <input type="number" class="form-control" name="club_meeting_capacity" value="${meetingList[0].club_meeting_capacity}" disabled="disabled">
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">🏩 장소</label>
              	<div class="input-group">
              		<input type="text" class="form-control" id="club_meeting_location" value="${meetingList[0].club_meeting_location}" disabled="disabled">
              		<br>
              		<input type="text" class="form-control" id="club_meeting_address" value="${meetingList[0].club_meeting_address}" disabled="disabled">
            	</div>
            </div>
            
			<div>
				<p style="margin-top:-12px">
				    <em class="link">
				        <a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
				            혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요. 
				        </a>
				    </em>
				</p>
				<div id="map" style="width:100%;height:350px;"></div>
				
			</div> 
            <div class="col-12">
              <label for="address2" class="form-label">참가비 <span class="text-muted">(선택)</span></label>
              <input type="number" class="form-control" name="club_meeting_dues" value="${meetingList[0].club_meeting_dues}" disabled="disabled">
            </div>

          </div>

          <hr class="my-4">

          <h4 class="mb-3">추가 공지사항</h4>

          <div class="row gy-3">
            <div class="col-md-6">
              <input type="text" class="form-control" id="club_meeting_content" placeholder="" required="">
              <div class="invalid-feedback">
                Name on card is required
              </div>
            </div>
          </div>


     </form>
          <hr class="my-4">
	
			<div class="margin">
			
			<div class="btn-group">
			<button type="submit" class="btn btn-secondary btn-flat" id ="modify">수정하기</button>
			</div>
			
			<div class="btn-group">
			<button type="submit" class="btn btn-danger btn-flat" id ="delete">삭제하기</button>
			</div>
			
			
			<c:set var="meetingStatus" value="${meetingStatus }"/>
			
			<c:if test="${meetingStatus eq '모집중'}">
			<div class="btn-group">
			<button type="submit" class="btn btn-success btn-flat" id ="colse">마감하기</button>
			</div>
			</c:if>
			
			<c:if test="${meetingStatus eq '마감'}">
			<div class="btn-group">
			<button type="submit" class="btn btn-success btn-flat" id ="colse">오픈하기</button>
			</div>
			</c:if>
			
			</div>


      </div>
    </div>
</div>
<!-- 정모 -->

			<script>
			
$(function(){
	
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		var location = $('input#club_meeting_location').val();
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
	$.ajax({
		
		url : '/club/${club_no}/meeting/${club_meeting_no}/address',
		type : 'GET',
		success : function(data){
			console.log(data);
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(data, function(result, status) {
		
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
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+location+'</div>'
		        });
		        infowindow.open(map, marker);
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords); 
		        
		    } //if
		  });//search 
		}	
	});//ajax
	
	var formObj = $('form[role="form"]')
	
	$('#modify').click(function(){

		formObj.attr("action", "/club/${clubInfo[0].club_no}/meeting/${meetingList[0].club_meeting_no}/modify");
		formObj.submit();
	});
	
	$('#delete').click(function(){
		formObj.attr("action", "/club/${clubInfo[0].club_no}/meeting/${meetingList[0].club_meeting_no}/delete");
		formObj.submit();
	});
	
	$('#close').click(function(){
		formObj.attr("action", "/club/${clubInfo[0].club_no}/meeting/${meetingList[0].club_meeting_no}/close");
		formObj.submit();
	});
	
	$('#reopen').click(function(){
		formObj.attr("action", "/club/${clubInfo[0].club_no}/meeting/${meetingList[0].club_meeting_no}/reopen");
		formObj.submit();
	});
	
	
	
});//jquery


var check ='${check}';
if(check == "UPStatus"){
	alert('정모가 마감되었습니다.');
}





</script>



<%@ include file="../../include/footer.jsp"%>
