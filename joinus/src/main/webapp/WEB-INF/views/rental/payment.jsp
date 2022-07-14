<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp"%>

	<style>
		
		
	</style>

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
	    name : '상품1' , //결제창에서 보여질 이름
	    amount : 100, //실제 결제되는 가격
	    buyer_email : 'iamport@siot.do',
	    buyer_name : '구매자이름',
	    buyer_tel : '010-1234-5678',
	    buyer_addr : '서울 강남구 도곡동',
	    buyer_postcode : '123-456'
	    m_redirect_url : 'http://localhost:8080/orderCompleteMobile'
	}, function(rsp) {
		console.log(rsp);
		// 결제검증
		$.ajax({
        	type : 'POST',
        	url : '/verifyIamport/' + rsp.imp_uid 
        }).done(function(result){

            // rsp.paid_amount와 result.response.amount(서버 검증) 비교 후 로직 실행
            if(rsp.paid_amount === result.response.amount){
                alert("결제가 완료되었습니다.");
                $.ajax({
                    type:'get',
                    url:'/payments/status/paid/' + rsp.imp_uid ,
                  	success(function(data){
                  		data.response.list.amount
                  		data.response.list.name
                    
                }).done(function() {
                    window.location.reload();
                }).fail(function(error){
                        alert(JSON.stringify(error));
                })
            } else{
                alert("결제에 실패했습니다."+"에러코드 : "+rsp.error_code+"에러 메시지 : "+rsp.error_message);

            }
        })
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
                    <p class="mb-4">We’re sorry, the page you have looked for does not exist in our website! Maybe go to our home page or try to use a search?</p>
                    <a class="btn btn-primary rounded-pill py-3 px-5" href="">Go Back To Home</a>
                </div>
            </div>
        </div>
    </div>
    <!-- 404 End -->
    
    
    
    
<%@ include file="../include/footer.jsp"%>