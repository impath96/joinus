<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<body>
	<!-- Contact Start -->
	<div class="container-fluid bg-light overflow-hidden px-lg-0">
		<div class="container contact px-lg-0" style="width: 60%">
			<div class="row g-0 mx-lg-0">
				<div class="p-lg-5 ps-lg-0" align="center">
					<h6 class="text-primary">Grouping</h6>
					<h1 class="mb-4">회원가입</h1>
					<form action="/member/signup">
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<input type="email" class="form-control" id="subject"
									placeholder="Subject"> <label for="subject">이메일주소</label>
							</div>
						</div>
						<br>
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<input type="password" class="form-control" id="subject"
									placeholder="Subject"> <label for="subject">비밀번호</label>
							</div>
						</div>
						<br>
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<input type="password" class="form-control" id="subject"
									placeholder="Subject"> <label for="subject">비밀번호
									확인</label>
							</div>
						</div>
						<br>
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<input type="password" class="form-control" id="subject"
									placeholder="Subject"> <label for="subject">이름</label>
							</div>
						</div>
						<br>
						<div class="col-12">
							<div class="form-floating" style="width: 50%;">
								<input type="password" class="form-control" id="subject"
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
							<a href="https://kauth.kakao.com/oauth/authorize?client_id=e56b53633c44d91056a98f83b04e7bfe&redirect_uri=http://localhost:8088/member/oauth/kakao&response_type=code">
								<img alt="카카오 로그인" src="${pageContext.request.contextPath }/resources/img/kakao_login.png">
							</a>
							<a href="https://accounts.google.com/o/oauth2/v2/auth?access_type=offline&prompt=consent&response_type=code&client_id=463761723189-9objhtfnhck66j6bbvi2h0b7hmgnpsf9.apps.googleusercontent.com&redirect_uri=http://localhost:8088/member/oauth/google&scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email">
								<img alt="구글 로그인" src="${pageContext.request.contextPath }/resources/img/google_login.png">
							</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer.jsp"%>