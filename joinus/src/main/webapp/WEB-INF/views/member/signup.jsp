<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../include/header.jsp"%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		// alert('호출');
		var email_auth_cd = '';

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
					<h6 class="text-primary">JoinUs</h6>
					<h1 class="mb-4">회원가입</h1>
					<form:form modelAttribute="membersVo" method="POST" action="/member/signup">
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
								<label for="subject">비밀번호</label>
							</div>
						</div>
						<br>
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<input type="password" class="form-control" id="member_passConfirm">
									<label for="subject">비밀번호 확인</label>
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
								<label for="subject">이름</label>
							</div>
						</div>
						<br>
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<input type="text" class="form-control" id="subject"
									placeholder="Subject"> <label for="subject">주소</label>
							</div>
						</div>
						<br>
						<div class="col-12">
							<input type="submit" value="회원가입"
								class="btn btn-primary rounded-pill py-3 px-5">
						</div>
						<hr>

						<div class="col-12">
							<a
								href="https://kauth.kakao.com/oauth/authorize?client_id=e56b53633c44d91056a98f83b04e7bfe&redirect_uri=http://localhost:8088/member/oauth/kakao&response_type=code">
								<img alt="카카오 로그인"
								src="${pageContext.request.contextPath }/resources/img/kakao_login.png">
							</a> <a
								href="https://accounts.google.com/o/oauth2/v2/auth?access_type=offline&prompt=consent&response_type=code&client_id=463761723189-9objhtfnhck66j6bbvi2h0b7hmgnpsf9.apps.googleusercontent.com&redirect_uri=http://localhost:8088/member/oauth/google&scope=https://www.googleapis.com/auth/userinfo.email">
								<img alt="구글 로그인"
								src="${pageContext.request.contextPath }/resources/img/google_login.png">
							</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer.jsp"%>