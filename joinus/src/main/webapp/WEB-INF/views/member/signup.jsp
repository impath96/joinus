<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../include/header.jsp"%>

<script type="text/javascript">
	// jQuery 시작
	$(function() {
		let email_auth_cd = '';
		
		// 
		$('#email_check_btn').click(function() {
			var email = $('#member_email').val(); //email값이 "member_email"인 입력란의 값을 저장
			if (email == '') {
				alert("이메일을 입력해주세요.");
				return false;
			}
			$.ajax({
				url : '/member/emailCheck', //Controller에서 인식할 주소
				type : 'post', //POST 방식으로 전달
				data : {
					email : email
				},
				success : function(cnt) {
					if (cnt != 1) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 이메일
						alert("사용가능한 이메일입니다.");
					} else { // cnt가 1일 경우 -> 이미 존재하는 이메일
						alert("이미 사용중인 이메일입니다.");
					}
				},
				error : function() {
					alert("에러입니다");
				}
			});
		});
		$('#pass_confirm').click(function() {
			if ($('#member_pass').val() != $('#member_passConfirm').val()) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			} else {
				alert("비밀번호가 일치합니다.");
			}
		});
		$('#email_auth_confirm').click(function() {
			if ($('#email_auth_key').val() == '') {
				alert("인증번호를 입력해주세요.");
			} else if ($('#email_auth_key').val() != email_auth_cd) {
				alert("인증번호가 일치하지 않습니다.");
				return false;
			} else {
				alert("인증번호가 일치합니다.");
			}
		});
		$("#email_auth_btn").click(function() {
			var email = $('#member_email').val();
			if (email == '') {
				alert("이메일을 입력해주세요.");
				return false;
			}
			$.ajax({
				type : "POST",
				url : "/member/emailAuth",
				data : {
					email : email
				},
				success : function(data) {
					alert("인증번호가 발송되었습니다.");
					email_auth_cd = data;
				},
				error : function(data) {
					alert("메일 발송에 실패했습니다.");
				}
			}); // ajax끝
		}); // button
	}); // jquery
</script>
<body>
	<!-- Contact Start -->
	<div class="container-fluid bg-light overflow-hidden px-lg-0">
		<div class="container contact px-lg-0" style="width: 60%">
			<div class="row g-0 mx-lg-0">
				<div class="p-lg-5 ps-lg-0" align="center">
					<h6 class="text-primary">JOINUS</h6>
					<h1 class="mb-4">회원가입</h1>
					<form:form modelAttribute="member" method="POST" action="/member/signup">
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<form:input path="member_email" type="email" class="form-control" id="member_email"/>
								<form:errors cssStyle="color: red;" path="member_email"/>
								<label for="member_email">이메일주소</label> <br>
								<button class="btn btn-primary rounded-pill py-3 px-5"
									type="button" id="email_auth_btn">인증번호 받기</button>
							</div>
						</div>
						<br>
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<input type="text" class="form-control" id="email_auth_key"
									maxlength="6"> <label for="email_auth_key">인증번호
									입력</label> <br>
								<button class="btn btn-primary rounded-pill py-3 px-5"
									type="button" id="email_auth_confirm">이메일 인증</button>
							</div>
						</div>
						<br>
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<form:input path="member_pass" type="password" class="form-control" id="member_pass"/>
								<form:errors cssStyle="color: red;" path="member_pass" />
								<label for="member_pass">비밀번호</label>
							</div>
						</div>
						<br>
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<input type="password" class="form-control" id="member_passConfirm">
									<label for="member_passConfirm">비밀번호 확인</label>
									<br>
								<button class="btn btn-primary rounded-pill py-3 px-5"
									type="button" id="pass_confirm">비밀번호 확인</button>
							</div>
						</div>
						<br>
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<form:input path="member_name" type="text" class="form-control" id="member_name"/>
								<form:errors cssStyle="color: red;" path="member_name" />
								<label for="member_name">이름</label>
							</div>
						</div>
						<br>
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<form:input path="member_location" type="text" class="form-control" id="member_location" onclick="searchLocation()"/>
								<form:errors cssStyle="color: red;" path="member_location" />
								<label for="member_location">주소</label>
							</div>
						</div>
						<br>
						<div class="col-12">
							<input type="submit" value="회원가입"
								class="btn btn-primary rounded-pill py-3 px-5">
						</div>
						<hr>

 						<div class="col-12">
							<a href="https://kauth.kakao.com/oauth/authorize?client_id=e56b53633c44d91056a98f83b04e7bfe&redirect_uri=http://localhost:8088/oauth/kakao&response_type=code">
								<img alt="카카오 로그인" src="${pageContext.request.contextPath }/resources/img/kakao_login.png">
							</a>
							<a href="https://accounts.google.com/o/oauth2/v2/auth?access_type=offline&prompt=consent&response_type=code&client_id=463761723189-9objhtfnhck66j6bbvi2h0b7hmgnpsf9.apps.googleusercontent.com&redirect_uri=http://localhost:8088/oauth/google&scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email">
								<img alt="구글 로그인" src="${pageContext.request.contextPath }/resources/img/google_login.png">
							</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
    function searchLocation() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("member_location").value = data.jibunAddress;
                
                var guideTextBox = document.getElementById("guide");
            }
        }).open();
    }
    </script>
	<%@ include file="../include/footer.jsp"%>