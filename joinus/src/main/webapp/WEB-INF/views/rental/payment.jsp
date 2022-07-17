<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp"%>
<link href="${PageContext.request.contextPath }/resources/css/ksm.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">

	window.onload=function iamport(){
	//가맹점 식별코드
	IMP.init('imp93879156');
	IMP.request_pay({
	    pg : 'kcp',
	    pay_method : 'card',
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : '상품이름' , //결제창에서 보여질 이름
	    amount : 100, //실제 결제되는 가격
	    buyer_name : '구매자이름',
	    buyer_email : 'xoxomini4@naver.com'
	}, function(rsp){
		console.log(rsp);
		
		if(rsp.success){ //결제성공시
			$.ajax({ //결제검증
	        	type : 'POST',
	        	url : '${PageContext.request.contextPath }/rental/verifyIamport/'+rsp.imp_uid 
			}).done(function(result){
	            // rsp.paid_amount와 result.response.amount(서버 검증) 비교 후 로직 실행
		            if(rsp.paid_amount === result.response.amount){
	                alert("결제 검증완료"); console.log("결제 검증완료");
	        		
    					$.ajax({
    						url : '${PageContext.request.contextPath }/rental/paypay', 
    				        type :'get',
    				        data : 'amount',
    				        dataType: 'json', //서버에서 보내줄 데이터 타입
    				        success: function(res){
    				        	 alert(res); 
    				        	 $('#add').append('데이터 받기 성공 = '+res);
    				        }
    				        });
	        			}
	            
	        }).fail(function(error){
					console.log("검증..데이터 저장 실패"); 
			});
		}else{
                alert('결제에 실패했습니다'+'에러코드 : '+rsp.error_code+'에러 메시지 : '+rsp.error_message);
            }
		});
        
		// 결제 후 페이지 리로드, 결제가 완료된다면 결제 완료 페이지로 출력
	
}

</script>
 
 <!-- 404 Start -->
    <div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
        <div class="container text-center">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <h3 class="display-1">결제페이지</h3>
                    <h1 class="mb-4"> </h1>
                    	<div id="add"></div>
                    <a class="btn btn-primary rounded-pill py-3 px-5" href="">Go Back To Home</a>
                </div>
            </div>
        </div>
    </div>
    <!-- 404 End -->
    
    
    
    
<%@ include file="../include/footer.jsp"%>