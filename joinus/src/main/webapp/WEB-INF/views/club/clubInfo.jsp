<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="../include/header.jsp"%>


	<style>
		
		.datail_vertical {
			margin-top: 70px;
			width: 1296px;
			text-align: center;
		}
		#detail_nav {
			align-content: center;
		}
		#meetingTitle {
			cursor: pointer;
		}
		
		#myform fieldset{
	    display: inline-block;
	    direction: rtl;
	    border:0;
		}
		#myform fieldset legend{
		    text-align: right;
		}
		#myform input[type=radio]{
		    display: none;
		}
		#myform label{
		    font-size: 3em;
		    color: transparent;
		    text-shadow: 0 0 0 #f0f0f0;
		}
		#myform label:hover{
		    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
		}
		#myform label:hover ~ label{
		    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
		}
		#myform input[type=radio]:checked ~ label{
		    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
		}
		#reviewContents {
		    width: 100%;
		    height: 150px;
		    padding: 10px;
		    box-sizing: border-box;
		    border: solid 1.5px #D3D3D3;
		    border-radius: 5px;
		    font-size: 16px;
		    resize: none;
		}
				
		#club_image {
			width: 800px;
			height: auto;
			margin-top: 30px; margin-bottom: 30px;
		}		
		#imgDiv {
			text-align: center;
		}
		#Dip {
			width: 30px; height: 30px;
			cursor: pointer;
		}
		#DDone {
			width: 30px; height: 30px;
			cursor: pointer;
		} 
		
		#detailcss {
			width: 1296px;
			margin-top: 50px; margin-bottom: 200px; 
		}
		#datailbtn {
			width: 1296px;
			margin-top: 50px; margin-bottom: 50px; 
		}
		#club_name {
			font-size: 5em;
		}
		#club_content {
			color: #9B9B9B;
			margin-top: 20px;
		}
		#club_capa {
			text-align: right;
			font-style: italic;
			font-size: medium;
		}
						
	</style>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript">
	
		 $(document).ready(function(){
	
			 // 모임가입
			$('#joinClub').click(function(){
				
				 if(confirm("모임에 가입하시겠어요?") == true){
					
						var member_no = ${member_no};
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
    
    <!-- 게시판 nav -->
   	 <div class="col-12 text-center">
			<ul class="list-inline mb-5" id="portfolio-flters">
			<li class="mx-2 active" >모임정보</li>
			<li class="mx-2"  onclick="listClubBoard()">모임게시판</li>
			<li class="mx-2"  onclick="listClubPhoto()">사진첩</li>
			<li class="mx-2"  onclick="listClubMember()">모임회원</li>
			</ul>
	</div><br>
	
	
    <!-- About Start  모임 설명 -->
    	<!-- 	<div class="container-fluid bg-light overflow-hidden my-5 px-lg-0"> -->
    	  <div class="container">
                  <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 1296px; ">
                    <div class="datail_vertical">
                        <h6 class="text-primary" >${interDetail }</h6>
                        <h1 class="mb-4" id="club_name">${clubvo.club_name }
						<!-- 찜기능 -->	
                        <c:if test="${empty dipMember }">
	                        <img src="../resources/img/heart.png" id="Dip">
                       </c:if>
                        <c:if test="${dipMember eq member_no }">
	                        <img src="../resources/img/heartt.png"  id="DDone">
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
					
						  <h5 class="text-primary">소개글</h5>
         	               <h4 id="club_content" style="white-space: pre-wrap;">${clubvo.club_content }</h4>
         	               </div>
					  <hr>
					 
					 <div id="datailbtn"> <!--  모임멤버면 별점, 별점 후 평균값 / 멤버가 아니면 가입하기 버튼 /  --> 
					 <!-- 회원인 경우만 출력 -->
					<c:if test="${!empty member_no}">
					 <!-- 모임X 벤 당한 회원은 가입버튼X -->
					<c:if test="${ graded eq '0' &&  clubmember  eq '0' }">
							<div id="joinBTN">
								<div class="btn btn-primary rounded-pill py-3 px-5 mt-3" id="joinClub">가입하기</div>
							</div>
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
						</div>
					
					</div>
					
                	    </div>
                    </div>
                </div>
    <!-- About End -->

 <!-- Feature Start 정모 -->
   <!--  <div class="container-xxl py-5"> -->
        <div class="container">
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
            <div class="row g-5" align="center" >
                <div class="col-md-6 col-lg-3 wow fadeIn" data-wow-delay="0.1s" >
                    <div class="d-flex align-items-center mb-4" >
                        <div class="btn-lg-square bg-primary rounded-circle me-3">
                            <i class="fa fa-users text-white" ></i>
                        </div>
                    </div>
                    <h3 class="mb-3" onclick="meetingTitle()" id="meetingTitle">정모주제</h3>
                    <span>날짜</span><br>
                    <span>장소</span><br>
                    <span>인원</span><br>
                </div>
                 <div class="col-md-6 col-lg-3 wow fadeIn" data-wow-delay="0.1s">
                    <div class="d-flex align-items-center mb-4">
                        <div class="btn-lg-square bg-primary rounded-circle me-3">
                            <i class="fa fa-users text-white"></i>
                        </div>
                    </div>
                    <h3 class="mb-3">정모주제</h3>
                    <span>날짜</span><br>
                    <span>장소</span><br>
                    <span>인원</span><br>
                </div>
              <div class="col-md-6 col-lg-3 wow fadeIn" data-wow-delay="0.1s">
                    <div class="d-flex align-items-center mb-4">
                        <div class="btn-lg-square bg-primary rounded-circle me-3">
                            <i class="fa fa-users text-white"></i>
                        </div>
                    </div>
                    <h3 class="mb-3">정모주제</h3>
                    <span>날짜</span><br>
                    <span>장소</span><br>
                    <span>인원</span><br>
                </div>
                 <div class="col-md-6 col-lg-3 wow fadeIn" data-wow-delay="0.1s">
                    <div class="d-flex align-items-center mb-4">
                        <div class="btn-lg-square bg-primary rounded-circle me-3">
                            <i class="fa fa-users text-white"></i>
                        </div>
                    </div>
                    <h3 class="mb-3">정모주제</h3>
                    <span>날짜</span><br>
                    <span>장소</span><br>
                    <span>인원</span><br>
                </div>
            </div>
        </div>
    </div>
    <!-- Feature Start -->



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
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
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
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
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
            </div>
        </div>
    </div>
    <!-- Team End -->
    
    <script type="text/javascript">
  
  //모임 게시판으로 이동
  function listClubMember(){
	  location.href='${PageContext.request.contextPath }/test';
  }
  // 모임 사진첩으로 이동
  function listClubBoard(){
	  location.href='${PageContext.request.contextPath }/test';
  }
  // 정모페이지로 이동  	
  function meetingTitle(){
	  location.href='${PageContext.request.contextPath }/test';
  }
	
    </script>
    </body>
	

    
<%@ include file="../include/footer.jsp"%>