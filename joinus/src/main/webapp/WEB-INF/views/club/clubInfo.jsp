<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/club_header.jsp"%>
<link href="${PageContext.request.contextPath }/resources/css/ksm.css" rel="stylesheet">

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
					
					var member_no = ${member_no};
					
					if( member_no == null ){
						alert('회원만 찜기능을 이용할 수 있습니다');
						return false
					}else{
						
					if(confirm(" 우리모임을 찜하시겠어요? ") == true){
						
						$.ajax({
							
							url:'${pageContext.request.contextPath}/club/${clubvo.club_no}/dip',
							type:'POST',
							data: {
								'club_no' : '${clubvo.club_no}',
								'member_no' : member_no
								},
							dataType: 'json'
							
						});
							alert('우리 모임을 찜하셨습니다');
							location.reload();
					 }else{
					        return false;
					    } 
					
					
					}
					
				});
				
				
				// 찜취소
				$('#DDone').click(function(){
					
					var member_no = ${member_no};
					
					if(confirm(" 찜하기를 취소하시겠어요? ") == true){
						
						$.ajax({
							
							url:'${pageContext.request.contextPath}/club/${clubvo.club_no}/dipX',
							type:'POST',
							data: {
								'club_no' : '${clubvo.club_no}',
								'member_no' : member_no
								},
							dataType: 'json'
							
						});
							alert('찜하기가 취소되었습니다');
							location.reload();
					 }else{
					        return false;
					    } 
				
				 });
				
		 
		 // 정모참석
			$("[id='JoinMeeting']").on('click',function(){
				
			var meeting_no = $(this).prop('value');
			 console.log("정모번호: "+ meeting_no);
				
			if( ${member_no} == null ){
					alert('회원만 정모에 참석할 수 있습니다');
					return false
				}else if(${member_no} != null){
					
				
				 if(confirm("정모에 참석하시겠어요?") == true){
					
					$.ajax({
						url:'${pageContext.request.contextPath}/club/${clubvo.club_no}/meeting/'+meeting_no+'/join',
						type:'POST',
						data: {
							'club_no' : '${clubvo.club_no}',
							'member_no' : '${member_no}',
							'club_meeting_no' : meeting_no
							},
						dataType: 'json',
						success: function(){
							 console.log("성공");
						},
						fail: function(data){
				              alert('failed');
							location.reload();

				        }

				        });
							alert(' 정모참석 신청이 완료되었습니다! ');
							location.reload();
				    
				}else{
				        return false;
				    } 
				}			 

				});
				
			 // 정모참석 취소
			$('#outMeeting').click(function(){
				
				
			var meeting_no = $(this).prop('value');
			 console.log("정모번호: "+ meeting_no);
				
			if( ${member_no} == null ){
					alert('회원만 정모에 참석할 수 있습니다');
					return false
				}else if(${member_no} != null){
					
				
				 if(confirm("정모참석을 취소하시겠어요?") == true){
					
					$.ajax({
						url:'${pageContext.request.contextPath}/club/${clubvo.club_no}/meeting/'+meeting_no+'/leave',
						type:'POST',
						data: {
							'club_no' : '${clubvo.club_no}',
							'member_no' : '${member_no}',
							'club_meeting_no' : meeting_no
							},
						dataType: 'json',
						success: function(){
						},
						fail: function(data){
				              alert('failed');
							location.reload();

				        }

				        });
							alert(' 정모 참석 신청이 취소되었습니다! ');
							location.reload();
				    
				}else{
				        return false;
				    } 
				}			 

				});
				
				
				
				
				
		
			});
</script>




<body>
    
    
 <div class="container-xxl py-5 ">
        <div class="container"> 
    		
	
	
	
	
    			<!-- About Start  모임 설명 -->
    			<div class="row g-4 text-center justify">
                    
                        <h4 class="text-primary" >${interDetail }</h4>
                       <p id="club_capa"><i class="fa fa-check-circle text-primary me-3" ></i>정원:${clubvo.club_capacity } 명</p>
                        <h1 class="mb-4" id="club_name">${clubvo.club_name }
						
						
						
							<!-- 찜기능 -->	
							 <c:if test="${clubmember ne '0'}">	
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
					
                        <hr>
                        
                	   <c:if test="${!empty clubvo.club_image }">
							<img src="${PageContext.requeset.contextPath }/resources/upload/clubs/${clubvo.club_image}" id="club_image">
						</c:if>
						
							<h4 class="detailInfoCon"> &#128172; </h4>
         	               <h4 id="club_content" style="white-space: pre-wrap;">${clubvo.club_content }</h4>
							
							
				
					
					
					<div>
			<!--  모임멤버면 별점, 별점 후 평균값 / 멤버가 아니면 가입하기 버튼 /  --> 

				<!-- 회원인 경우만 출력 -->
				<c:if test="${!empty member_no}">
						
					<!-- 모임X 벤 당한 회원은 가입버튼X 벤당한거 아직안함-->
					<c:if test="${ graded eq '0' &&  clubmember eq '0' }">
						<div class="btn btn-primary rounded-pill py-3 px-5 mt-3" id="joinClub">가입하기</div>
					</c:if>
					
					
					 <!-- 모임O / 별점X  -->
                	<c:if test="${ graded eq '0' &&  clubmember eq member_no}">
						<div id="grade" align="center">
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
						<h4 class="">우리모임의 평균 별점은?</h4> 
							<h3 class="" > &#127775; ${gradeAvgCnt[0].avg}점 &#127775; </h3>		   
							<p>(참여자수 : ${gradeAvgCnt[0].cnt}명 ) </p><br>
					</c:if>
				</c:if>
                
                
                </div>
                </div>
                
                
                
                
                

 				<!-- 정모 -->
 				
 				
                  <div class="text-center mx-auto mb-5 marginTOP ">
                <h6 class="text-primary">${clubvo.club_name } </h6>
                <h1 class="mb-4">정모</h1>
           
           
	                <!-- 정모만들기 모임장만 보일 수 있도록  -->
	                <c:if test="${clubmemberRole == 'admin'}">
	                <a class="small fw-medium" href="${pageContext.request.contextPath}/club/${clubvo.club_no}/meeting/new'">모임장 정모만들기<i class="fa fa-arrow-right ms-2"></i></a>
	                </c:if>
           			 </div><hr><br><br>
           		
           		
           <c:if test="${!empty meetings}" >
 			<div class="row g-4 text-center justify" >
 			
 			
			   <c:forEach var="m" items="${meetings}">
	             
	           			 	 				
						 <c:set var="doneLoop" value="false"/><!-- break 기능 -->
	    	    <c:forEach var="mm" items="${meetingMbrs}">
	                    		<c:if test="${not doneLoop}">
	             
	                <div class="col-md-6 col-lg-4 wow fadeInUp" data-wow-delay="0.1s" >
			        
			     		   <!-- 모임가입시 참석하기 버튼 출력 -->
			       		  <c:if test="${clubmember ne '0'}">
				       	 
			         		${m.club_meeting_no }
			         		${mm.club_meeting_no }
								<!-- 내가 참석신청을 한 정모list에 해당 정모no이 있는지로 비교 -->
                    			<c:if test="${m.club_meeting_no eq mm.club_meeting_no}">
	                    			<button id="outMeeting" class="btn btn-primary" value="${m.club_meeting_no}">참석취소하기</button>
					                  <c:set var="doneLoop" value="true"/>
                    			</c:if>
                    		 
						 
			         			<c:if test="${m.club_meeting_no ne mm.club_meeting_no}">
		                    		<button id="JoinMeeting" class="btn btn-primary" value="${m.club_meeting_no}">참석하기</button>
					                  <c:set var="doneLoop" value="true"/>
                    			</c:if>
                    		
                    		</c:if>
                    	
                    			
                    	
			       	  <%-- 	<c:choose>
							  	<c:when test="${m.club_meeting_no ne mm.club_meeting_no}">
		                    		<button id="JoinMeeting" class="btn btn-primary" value="${m.club_meeting_no}">참석하기</button>
					                  <c:set var="doneLoop" value="true"/>
                    			</c:when>
	                    		<c:otherwise>
	                    			<button id="outMeeting" class="btn btn-primary" value="${m.club_meeting_no}">참석취소하기</button>
					                  <c:set var="doneLoop" value="true"/>
	                    		</c:otherwise>
			       	  	</c:choose> --%>
	                	 
                 
                  	  
                  	  			<!-- 내 정모list가 없다면 참석하기 버튼 띄우기  -->
                    			<c:if test="${meetingMbrsNull eq '0'}">
		                    		<button id="JoinMeeting" class="btn btn-primary" value="${m.club_meeting_no}">참석하기</button>
	                    		</c:if>
	                    		
                 
                    
                    
                  	<div class="service-item rounded overflow-hidden Grrrr">
                    <h3 class="mb-3 MainTextTitle">${m.club_meeting_title }</h3>
                   <p class="MainTextSub"><b>회비 </b>${m.club_meeting_dues }</p>
                 	<p class="MainTextSub"><b>장소 </b>${m.club_meeting_location }</p>
                   <p class="MainTextSub"><b>인원 </b>${m.club_meeting_capacity }명</p>
                </div>
              
              
                
                </div>
                   </c:if>	
            	    </c:forEach>
                    </c:forEach>
                    </div>
                </c:if>
          
                
                
                
                <c:if test="${empty meetings}" >
                 <div class="g-5" align="center" >
               	 <div class="col-md-6 col-lg-3 wow fadeIn clubInfoMeetingSize" data-wow-delay="0.1s" >
               	  <div class="align-items-center mb-4" >
               	  
               	  	<h4 class="mainGr"> 생성된 정모가 없습니다 </h4>
               	  
                 </div>
                </div>
                </div>
                </c:if>
                
                
                
                
                
          
           



    <!-- 사진 -->
          <div class="text-center mx-auto mb-5 marginTOP ">
                <h6 class="text-primary">${clubvo.club_name }</h6>
                <h1 class="mb-4">활동사진</h1>
            </div><hr><br><br>
            
         <c:if test="${!empty boards}" >
        
         <div class="row g-4 text-center justify">
                <c:forEach var="b" items="${boards }">
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                    <div class="team-item rounded overflow-hidden">
                        <div class="mainImg" >
                            <img src="${PageContext.requeset.contextPath }/resources/upload/boards/${b.club_board_image}" 
                            onclick="location.href='${PageContext.requeset.contextPath }/club/${clubvo.club_no}/boards/${b.club_board_no}'">
                        </div>
                    </div>
                </div>
                </c:forEach>
                
            </div>
            </c:if>
            
            
            
             <c:if test="${empty boards}" >
          	   <div class="row g-4 text-center justify">
               	 <div class="col-md-6 col-lg-3 wow fadeIn clubInfoMeetingSize" data-wow-delay="0.1s" >
               	  <div class="align-items-center mb-4" >
               	  	<h4 class="mainGr"> 등록된 모임사진이 없습니다 </h4>
                 </div>
                </div>
                </div>
             </c:if>
            
            
            
    </div>
 </div>
    </body>
	

    
<%@ include file="../include/footer.jsp"%>