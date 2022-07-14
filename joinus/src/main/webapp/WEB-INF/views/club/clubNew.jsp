<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp"%>


	<style>
		.container .contact .px-lg-0 {
			height: 1000px;
		}
		.sub {
		font-size: 0.8em;  color: #32C36C; font-weight: normal; font-style: italic;
		}
		.sub2 {
		font-size: 2em; font-weight: 500; margin-top: 20px; margin-bottom: 30px; color: #343a40;
		}
		.sub3 {
		font-size: 1.3em; font-weight: bold;margin-top: 30px; color: #343a40;
		}
		h1>.mb-4 {
			font-size: 3em;
		}
		.detail, .detail2 , #select2 {
			display: none; 
		}
		
		form {
			width: 50%; 
			margin-bottom: 200px;
		}
		
		.GreenP {
			color:#32C36C;font-size: 0.9em;
		}
		.marginTOP {
			margin-top: 150px;
		}
		
		#NewTitle {
			margin-top: 80px;
			margin-bottom: 80px;
		}
		#NewContent {
			margin-right: 300px;
		}
		
		#centercontrol {
			width: 100%;
		}
		
		#inters {
			cursor: pointer;
		}
		
		#inter,#inter1,#inter2,#inter3,#inter4,#inter5,#inter6,#inter7,#inter8 {
		    font-weight: bold;
			color: white;
			
		}
		
		#newDetail {
		    font-weight: bold;
			color: white;
		}
		
		.interTable {
			padding: 0;
			}
		.interTable>li {
			border-bottom: solid 6px #F6F7F8;
			width: 260;
			padding: 1em;
			display: inline-block;
			background-color: 32C36C;
			font-size: large;
			text-align:center;
			vertical-align: middle;
		    cursor: pointer;
 		  	border-radius: 35px;
 		  	color:white;
		}
		
		
	</style>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	
	//다른관심사출력
	$('#inters').click(function(){
		$('#select,#inters,#hideInter').hide();
		$('#select2').fadeIn();
	});
	
	
	//상세관심사 출력 ajax
	//관심사 텍스트를 클릭하면
	$('#inter,#inter1,#inter2,#inter3,#inter4,#inter5,#inter6,#inter7,#inter8').click(function(){
			
			var item = $(this).text();
			var itemNum;
			$('#interest').val(item);
			
			//관심사 넘버로 변환 후 
			if(item=="요리/제조"){ itemNum = 1; }
			else if(item == "봉사활동"){ itemNum = 2;}
			else if(item == "운동/스포츠"){ itemNum = 3;}
			else if(item == "오락/게임"){ itemNum = 4;}
			else if(item == "음악/악기"){ itemNum = 5;}
			else if(item == "여행"){ itemNum = 6;}
			else if(item == "외국/언어"){ itemNum = 7;}
			else if(item == "문화/공연/축제"){ itemNum = 8;}
			
			$('#inter').unbind('click'); //중복클릭 막기
				
			//상세관심사 텍스트를 출력
		  $.ajax({
			url:'${pageContext.request.contextPath}/club/getdetail',
			data: {itemNum:itemNum},
			datatype: 'json',
			type:'GET',
			success:function(data){
				
				for(i=0;i<data.length;i++){
						if(i == 3 || i == 7){ //한줄에 4개씩
				$('.newForm').append("<li onclick='select(this);'' id='newDetail'>"+data[i].interest_detail_name+"</li><br>");
						}else{
				$('.newForm').append("<li onclick='select(this);'' id='newDetail'>"+data[i].interest_detail_name+"</li>");
						}
				}
				
				$('.detail').slideDown(); //숨겨진 detail 출력
				$('html,body').animate({ scrollTop: $('#newDetail').offset().top }, 200); //포커스이동
				
				}
		 
		});
			
	}); 
	
	// 모임이름 중복체크
	$('#clubName').blur(function(){
	    var name = $('#clubName').val();
	    console.log(name);
	    $.ajax({
	        data : {
	        	club_name : name
	        },
	        url : "${pageContext.request.contextPath}/club/check_clubname",
	        success : function(result) {
	            if(result== '1') {
	                $("#retry").text("이미 만들어진 모임이름입니다. 다른 이름을 입력하세요.");
	                $("#retry").css("color","red");
	                $('#clubBtn').attr("disabled",true); //등록버튼 비활성화
	                
	                
	            } 
	            if(result == '0' && name != '') {
	                $("#retry").text("사용가능한 모임이름입니다.");
	                $("#retry").css("color","#32C36C");
	                $('#clubBtn').attr("disabled",false);
	            }
	        }
	    });
	    });
	
	
	//유효성체크
	$('#clubBtn').click(function(){
		 // 모임이름, 소개, 정원을 필수값으로
		 
		 
		 var reg = /[^0-9]/g; //정원은 숫자만 가능하도록
		 
		 if($('#clubName').val() == ""){
			 alert('모임의 이름을 입력해주세요');
			 $('#clubName').focus();
			 return false;
		 }
		 if($('#clubContent').val() == ""){
			 alert('모임의 소개글을 입력해주세요');
			 $('#clubContent').focus();
			 return false;
		 }
		 if($('#clubcapacity').val() == ""){
			 alert('모임의 정원을 입력해주세요');
			 $('#club_capacity').focus();
			 return false;
		 }
		 if(!reg.test($('#clubcapacity').val())){
			 alert('모임의 정원은 숫자로 입력해주세요');
			 $('#club_capacity').focus();
			 return false;
		 }
		 
		 
		 
		
	});
	
	
});

  
function select(item){
	  var item = $(item).text();
	  $('.detail2').slideDown();
	  $('#interest_detail').val(item);
	  $('html,body').animate({ scrollTop: $('#location').offset().top }, 200);
} 




</script>


<body>
    
    <!-- Contact Start -->
                
        <div id="NewTitle" align="center">
            <h6 class="text-primary">JoinUs</h6>
            <h1 class="mb-4">소모임 등록하기</h1>
            <p class="mb-4">우리들만의 모임을 자유롭게 만들어보세요! </p>
        </div>
     
<!--     <div class="container-fluid bg-light overflow-hidden px-lg-0" align="center" >
 -->                <hr>    
        <div class="container contact px-lg-0" >
            <div class="col-lg-6 contact-text py-5 wow fadeIn" data-wow-delay="0.5s" id="centercontrol">
             
	              <div id="NewContent" align="center">
                  <p class="sub2"> 관심사를 선택해주세요 </p><br>   
                   	 	<p > ▼ </p>
            	  <div id="hideInter">
                  <p class="sub3"> ${membervo.member_name }님의 관심사 </p>   
                  </div>
                  <div id="select" >
                  			<ul class="interTable">
                  			<li id="inter">${interest.interest_name }</li>
                  			</ul>
                  </div>
                  <div id="select2" >
                  			<ul class="interTable">
                  			<li id="inter1">요리/제조</li>
                  			<li id="inter2">봉사활동</li>
                  			<li id="inter3">운동/스포츠</li>
                  			<li id="inter4">오락/게임</li> <br>
                  			<li id="inter5">음악/악기</li>
                  			<li id="inter6">여행</li>
                  			<li id="inter7">외국/언어</li>
                  			<li id="inter8">문화/공연/축제</li>
                  			</ul>
                  </div>
                  <p class="sub" id="inters"> 다른 관심사로 모임을 만들고 싶으신가요?</p>   
                  
                  	 	
                  	 	
                  <div class="detail" >
                   	 	<p class="marginTOP"> ▼ </p>
	                 <p class="sub2"> 세부관심사를 선택해주세요 </p>    
	                 <ul class="interTable newForm">
	                	</ul>
	              </div>
	                
		       
               
                  <div class="detail2" >
               				<br><br>
                         <form action="" method="post" enctype="multipart/form-data">    
                         <input type="hidden" value="${membervo.member_no}" name="member_no" >                      
                         <div class="row g-3" align="center">
	                 <p class="sub2"> 모임의 정보를 작성해주세요 </p>     
	               			  <div class="col-md-6"><br>
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="member_location" readonly="readonly" value="${membervo.member_location}" id="location">
                                        <label for="name">지역</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                </div>
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="interest" name="interest_no" readonly="readonly">
                                        <label for="name">관심사</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="interest_detail" name="interest_detail_name" readonly="readonly">
                                        <label for="name">세부관심사</label>
                                    </div>
                                </div>
                                  <div class="col-12">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="clubName" name="club_name" >
                                        <label for="subject">모임이름</label>
                                    </div>
                                    <p id="retry" class="GreenP"></p>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <textarea class="form-control" name="club_content" id="clubContent" style="height: 100px;"></textarea>
                                        <label for="message">모임의 소개글을 입력하세요</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" name="club_capacity" id="clubcapacity"
                                        onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                                        <label for="name">정원</label>
                                    </div>
                                </div>
                              
                           
                              	 <div class="col-12">
                                    <div class="form-floating">
                                        <input type="file" class="btn py-2 position-absolute top-0 end-0 mt-2 me-2"  id="input-image"name="file">
                                    </div>
                                </div><br><br>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <p class="GreenP">대표이미지를 올려주세요(선택사항)</p>
                                    </div>
                                    </div> 
                                    <hr> 
                                 <div class="col-12">
                                    <button class="btn btn-primary rounded-pill py-3 px-5" type="submit" id="clubBtn">모임 개설하기</button>
                                </div>
                             </div>
                        </form>
                                  </div>
                              </div> 
                                </div>
                                </div>
	<hr>
     
  <!-- Contact End -->
    
    </body>
	

    
<%@ include file="../include/footer.jsp"%>