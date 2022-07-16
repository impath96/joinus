<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/club_header.jsp"%>
<link href="${PageContext.request.contextPath }/resources/css/ksm.css" rel="stylesheet">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ce8d060125bcc89e0c25ee69f6b5c7b0&libraries=services"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript">
	
		 $(document).ready(function(){
	
			 // 모임가입
			$('#joinClub').click(function(){
				
				var member_no = ${member_no};
						
				if( member_no == null ){
					alert('회원만 모임에 가입할 수 있습니다');
					return false
				}else if(member_no != null){
					
				
				 if(confirm("모임에 가입하시겠어요?") == true){
					
					$.ajax({
						url:'${pageContext.request.contextPath}/club/${clubvo.club_no}',
						type:'POST',
						data: {member_no},
						dataType: 'json',
						success: function(){
						},
						fail: function(data){
				                alert('failed');
							location.reload();

				        }

				        });
							alert(' 저희 모임에 오신걸 환영합니다! ');
							location.reload();
				    
				}else{
				        return false;
				    } 
				}				

				});
		
			
				// 별점
				$('#rate1,#rate2,#rate3,#rate4,#rate5').click(function(){
					
						var rate = $(this).val();
						
					 if(confirm(" 우리 모임에 "+rate+"점을 주시겠어요? ") == true){
						
						$.ajax({
							
							url:'${pageContext.request.contextPath}/club/${clubvo.club_no}/grade',
							type:'POST',
							data: {
								'club_no' : '${clubvo.club_no}',
								'member_no' : '${member_no}',
								'club_grade_rate' : rate
								},
							dataType: 'json'
						});
							alert('참여해주셔서 감사합니다');
							location.reload();
					 }else{
					        return false;
					    } 
					 
				});
			
		
				// 찜
				$('#Dip').click(function(){
					
					if(confirm(" 우리모임을 찜하시겠어요? ") == true){
						
						$.ajax({
							
							url:'${pageContext.request.contextPath}/club/${clubvo.club_no}/dip',
							type:'POST',
							data: {
								'club_no' : '${clubvo.club_no}',
								'member_no' : '${member_no}'
								},
							dataType: 'json'
							
						});
							alert('우리 모임을 찜하셨습니다');
							location.reload();
					 }else{
					        return false;
					    } 
				});
				
				
				// 찜취소
				$('#DDone').click(function(){
					
					if(confirm(" 찜하기를 취소하시겠어요? ") == true){
						
						$.ajax({
							
							url:'${pageContext.request.contextPath}/club/${clubvo.club_no}/dipX',
							type:'POST',
							data: {
								'club_no' : '${clubvo.club_no}',
								'member_no' : '${member_no}'
								},
							dataType: 'json'
							
						});
							alert('찜하기가 취소되었습니다');
							location.reload();
					 }else{
					        return false;
					    } 
				
				 });
				
				
		 });
	

</script>




<body>
    
    
 <div class="container">
	    <div class="container-xxl py-5">
    
	
	
    <!-- About Start  모임 설명 -->
    	<!-- 	<div class="container-fluid bg-light overflow-hidden my-5 px-lg-0"> -->
    	  <div class="container">
                  <div class="text-center" >
                    <div class="datail_vertical">
                        <h6 class="text-primary" >${interDetail }</h6>
                        <h1 class="mb-4" id="club_name">${clubvo.club_name }
						
					<c:if test="${!empty member_no} ">
						<!-- 찜기능 -->	
                        <c:if test="${empty dipMember }">
	                        <img src="../resources/img/heart.png" id="Dip">
                       </c:if>
                       <c:forEach var="dip" items="${dipMember}">
                        <c:if test="${dip eq member_no }">
	                        <img src="../resources/img/heartt.png"  id="DDone">
                        </c:if>
                       </c:forEach>
					</c:if>	
                        </h1>
                        
				</div>
                        
                        <hr>
                          <div class="container about px-lg-0" >
                         <p id="club_capa"><i class="fa fa-check-circle text-primary me-3" ></i>정원:${clubvo.club_capacity } 명</p>
                    <c:if test="${not empty clubvo.club_image }">
					   	 <div class="col-12 " id="imgDiv" >
							<img src="${PageContext.requeset.contextPath }/resources/upload/clubs/${clubvo.club_image}" id="club_image">
						</div>
					</c:if>
                       
					  <div id="detailcss">
         	               <h4 id="club_content" style="white-space: pre-wrap;">${clubvo.club_content }</h4>
         	               </div>
					</div>
					
					<!--  모임멤버면 별점, 별점 후 평균값 / 멤버가 아니면 가입하기 버튼 /  --> 
					<div id="datailbtn"> 
					 <!-- 회원인 경우만 출력 -->
					<c:if test="${!empty member_no}">
					 <!-- 모임X 벤 당한 회원은 가입버튼X -->
					<c:if test="${ graded eq '0' &&  clubmember  eq '0' }">
								<div class="btn btn-primary rounded-pill py-3 px-5 mt-3" id="joinClub">가입하기</div>
					</c:if>
					 <!-- 모임O / 별점X  -->
                	<c:if test="${ graded eq '0' &&  clubmember eq member_no}">
						<div id="grade">
							<span class="text-bold">우리 모임의 별점을 선택해주세요</span>
		             		<form class="mb-3" name="myform" id="myform" method="post">
							<fieldset>
							<input type="radio" name="reviewStar" value="5" id="rate1"><label for="rate1">★</label>
							<input type="radio" name="reviewStar" value="4" id="rate2"><label for="rate2">★</label>
							<input type="radio" name="reviewStar" value="3" id="rate3"><label for="rate3">★</label>
							<input type="radio" name="reviewStar" value="2" id="rate4"><label for="rate4">★</label>
							<input type="radio" name="reviewStar" value="1" id="rate5"><label for="rate5">★</label>
							</fieldset>
							</form>
						 </div>
					</c:if>
					
					 <!-- 모임O / 별점O -->
              		<c:if test="${ graded ne '0' && clubmember ne '0'}">
						<h4 class="text-primary">우리모임의 평균 별점은?</h4> 
							<h3> ${gradeAvgCnt[0].avg} 점 </h3>		   
							<p>(참여자수 : ${gradeAvgCnt[0].cnt}명 ) </p><br>
					</c:if>
					</c:if>
					<br><br>
						</div>
					
                	    </div>
                    </div>
                </div>
    <!-- About End -->

 <!-- Feature Start 정모 -->
   <!--  <div class="container-xxl py-5"> -->
        <div class="container-xxl py-5">
                  <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px; ">
                <h6 class="text-primary">${clubvo.club_name } </h6>
                <h1 class="mb-4">정모</h1>
                <!-- 정모만들기 모임장만 보일 수 있도록  -->
                <c:forEach var="member" items="${clubmemebrList}" >
                <c:if test="${member.member_no == member_no && member.club_member_role == 'admin' }">
                <a class="small fw-medium" href="">모임장 정모만들기<i class="fa fa-arrow-right ms-2"></i></a>
                </c:if>
                </c:forEach>
           			 </div>
                <hr><br><br>
            <c:forEach var="m" items="${meetings }">
            <div class="g-5 clubInfoMeeting" align="center" >
                <div class="col-md-6 col-lg-3 wow fadeIn clubInfoMeetingSize" data-wow-delay="0.1s" >
                    <div class="align-items-center mb-4" >
                        <div class="btn-lg-square bg-primary rounded-circle">
                            <i class="fa fa-users text-white" ></i>
                        </div>
                    </div>
                    <h3 class="mb-3" onclick="location.href='#'" id="meetingTitle" class="clubInfoMeetingSize">${m.club_meeting_title }</h3>
                   <p class="meetingDetail"><b>회비 </b>${m.club_meeting_dues }</p>
                 	<p class="meetingDetail"><b>장소 </b>${m.club_meeting_location }</p>
                   <p class="meetingDetail"><b>인원 </b>${m.club_meeting_capacity }명</p>
                </div>
                </div>
                    </c:forEach>
            </div>

    <!-- Feature Start -->
    <!-- 주활동 지역 -->

	<div class="container-xxl py-5">
	    <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s">
		    <h6 class="text-primary">주 활동 지역</h6>
		    	<h1 class="mb-4">자주 만나는 위치</h1>
				<div>

				<div id="map" style="width:80%;height:500px;" class="m-auto"></div>
				
			</div> 
		
		</div>
    </div>

    <!-- 주활동 지역 -->

    <!-- Team Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;"><br>
                <h6 class="text-primary">${clubvo.club_name }</h6>
                <h1 class="mb-4">활동사진</h1>
            </div>
            <hr><br><br>
            <div class="row g-4">
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="team-item rounded overflow-hidden">
                        <div class="d-flex">
                            <img class="img-fluid w-75" src="" alt="">
                            <div class="team-social w-25">
                            </div>
                        </div>
                        <div class="p-4">
                            <h5>Full Name</h5>
                            <span>Designation</span>
                        </div>
                    </div>
                </div>
                <c:forEach var="b" items="${boards }">
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                    <div class="team-item rounded overflow-hidden">
                        <div class="d-flex">
                            <img src="${PageContext.requeset.contextPath }/resources/upload/boards/${b.club_board_image}" >
                        </div>
                        <div class="p-4">
                            <h5>Full Name</h5>
                            <span>Designation</span>
                        </div>
                    </div>
                </div>
                </c:forEach>
                
            </div>
        </div>
    </div>
  </div> 
    
    <!-- Team End -->
    
    <script type="text/javascript">
  
  //모임 게시판으로 이동
  function listClubMember(){
	  location.href='${PageContext.request.contextPath }/club/${clubvo.club_no}/boards/new';
  }
  // 모임 사진첩으로 이동
  function listClubBoard(){
	  location.href='${PageContext.request.contextPath }/club/${clubvo.club_no}/gallery';
  }
  
	
</script>
    
<script>

var x = null;
var y = null;

function getLocation() {
	  if (navigator.geolocation) { // GPS를 지원하면
	    navigator.geolocation.getCurrentPosition(function(position) {
	      alert(position.coords.latitude + ' ' + position.coords.longitude);
	      x = position.coords.latitude;
	      y =position.coords.longitude;
	      
	      var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
	    	    center : new kakao.maps.LatLng(x, y), // 지도의 중심좌표 
	    	    level : 3 // 지도의 확대 레벨 
	    	});

	    	// 마커 클러스터러를 생성합니다 
	    	var clusterer = new kakao.maps.MarkerClusterer({
	    	    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
	    	    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
	    	    minLevel: 10 // 클러스터 할 최소 지도 레벨 
	    	});

	    	// 데이터를 가져오기 위해 jQuery를 사용합니다
	    	// 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
	    	$.get("/download/web/data/chicken.json", function(data) {
	    	    // 데이터에서 좌표 값을 가지고 마커를 표시합니다
	    	    // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
	    	    var markers = $(data.positions).map(function(i, position) {
	    	        return new kakao.maps.Marker({
	    	            position : new kakao.maps.LatLng(position.lat, position.lng)
	    	        });
	    	    });

	    	    // 클러스터러에 마커들을 추가합니다
	    	    clusterer.addMarkers(markers);
	    	});
	      
	      
	      
	    }, function(error) {
	      console.error(error);
	    }, {
	      enableHighAccuracy: false,
	      maximumAge: 0,
	      timeout: Infinity
	    });
	  } else {
	    alert('GPS를 지원하지 않습니다');
	  }
	}
	
	getLocation();

</script>


    
    </body>
	

    
<%@ include file="../include/footer.jsp"%>