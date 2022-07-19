<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp"%>
<link href="${PageContext.request.contextPath }/resources/css/ksm.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">

 

	window.onload=function iamport(){
		
		$(document).ready(function(){
	//가맹점 식별코드
	IMP.init('imp93879156');
	IMP.request_pay({
	    pg : 'kcp',
	    pay_method : 'card',
	    merchant_uid : 'rs_' + new Date().getTime(),
	    name : 'rental place',//결제창에서 보여질 이름
	    amount : ${payment_price},//실제 결제되는 가격
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
    						url : '${PageContext.request.contextPath }/rental/partnerPlaces/'+${payment.partner_place_no}+'/payment', 
    				        type :'POST',
    				        data :{'partner_place_price':${payment.partner_place_price},
    				        	'payment_price':${payment_price},
    				        	'rental_date':${rental_date},
    				        	'rental_time_no':${rental_time_no}},
    				        dataType: 'json', //서버에서 보내줄 데이터 타입
    				        success: function(paymentvo){
    				        	 alert('데이터 저장 모두 완료!'); 
    				        	 $('#add').append('결제가 완료되었습니다');
    				        	 $('#add2').append('5초 후 대관 예약 페이지로 이동합니다');
    				        	 setTimeout(function(){
    				        		 location.href="${PageContext.request.contextPath }/"
    				        	 },5000);
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
	});
        
	
}

</script>
 
 <!-- 404 Start -->
    <div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
        <div class="container text-center">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                
                    	<h2 id="add"> </h2>
                    	<h5 id="add2" class="MainSubTitle"> </h5>
                    	
                </div>
            </div>
        </div>
    </div>
    <!-- 404 End -->
    
    
    
    
<%@ include file="../include/footer.jsp"%>