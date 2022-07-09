<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp"%>

<style>

	.file-input {
		height: 100%;
		left : 0;
		opacity : 0;
		outline : none;
		position: absolute;
		top : 0;
		width: 100%;
	}
	.file-label {
		cursor : pointer;
		overflow : hidden;
	}
	.file-cta{
	    width: 200px;
    	height: 200px;
    	border-radius: 50%;
    	padding: 0;
    	border: none;	
    	white-space: nowrap;
    	vertical-align: top;
    	position: relative;
    	display : inline-flex;
    	justify-content : flex-start;
    	line-height: 1.5;
    	align-items : center;
    	flex-direction : column;
	}
	img {
		max-width : 100%;
		display : block;
	}
	.thumbnail_image{
		width : inherit;
		height : inherit;
		object-fit : cover;
		border-radius : 50%;
		padding : 0;
		border : none;
	}
	.input-label {
		font-size : 2rem;
		font-weight : 500;
		color : #929292;
		display : block;
		margin-bottom : 1rem;
	}
	.input {
		padding : 0.5rem 0.5rem;
		height : 60px;
		background: white;
		border : 0;
		border-radius : 10px;
		margin-top : 10px;
		display : inline-flex;
		font-size : 1.5rem;
		justify-content : flex-start;
		line-height : 1.5;
		position : relative;
		vertical-align : top;
		width : 100%;
		max-width : 100%;
	}
	
	.form-control {
		font-size : 1.5rem;
	}
	#location::placeholder {
		color : 9B9B9B;
	}
</style>

<body>
	<!-- Contact Start -->
	<div class="container-fluid bg-light overflow-hidden px-lg-0"
		style="margin: 6rem 0;">
		<div class="container quote px-lg-0">
			<div class="row g-0 mx-lg-0">
				<div class="col-lg-6 py-5 wow fadeIn mx-auto"
					data-wow-delay="0.5s"
					style="visibility: visible; animation-delay: 0.5s; animation-name: fadeIn;">
					<div class="p-lg-5 pe-lg-0">
						<form action="/settings/profile" id="profile-form" method="post" enctype="multipart/form-data" class="d-flex flex-column justify-content-center align-items-center p-4 my-4 border border-dark ">
							<div class="d-flex align-items-stretch justify-content-start position-relative">
								<label class="file-label d-flex flex-column align-items-stretch justify-content-start position-relative ">
									<input type="file" class="file-input" name="profile_image">
									<span class="file-cta">
										<c:if test="${sessionScope.member.member_image.contains(':') }">
											<img alt="" class="thumbnail_image" src="${member.member_image }">
										</c:if>
										<c:if test="${!sessionScope.member.member_image.contains(':') }">
											<img alt="" class="thumbnail_image" src="${pageContext.request.contextPath }/resources/upload/${member.member_image }">
										</c:if>
									</span>
								</label>
							</div>
							${savedFileName }
							<div style="width:100%;">
								<label for="name" class="input-label">
									<span>닉네임</span>
									<input id="name" type="text" class="input"
											value="aksghcjswo" placeholder="변경할 닉네임을 입력해주세요">
								</label>
							</div>
							<input type="submit" class="btn btn-primary fs-3" style="width:100%;" value="저장하기"/>
						</form>
						<div class="col-12">
							<div class="form-floating">
								<input type="text" class="form-control py-2" id="location" name="location_name"
									placeholder="주소를 입력해주세요" onclick="sample4_execDaumPostcode()" >
							</div>
						</div>
						<br>
						<form>
							<div class="row g-3">
								<div class="col-12 col-sm-6">
									<input type="text" class="form-control border-0"
										placeholder="Your Name" style="height: 55px;">
								</div>
								<div class="col-12 col-sm-6">
									<input type="email" class="form-control border-0"
										placeholder="Your Email" style="height: 55px;">
								</div>
								<div class="col-12 col-sm-6">
									<input type="text" class="form-control border-0"
										placeholder="Your Mobile" style="height: 55px;">
								</div>
								<div class="col-12 col-sm-6">
									<select class="form-select border-0" style="height: 55px;">
										<option selected="">Select A Service</option>
										<option value="1">Service 1</option>
										<option value="2">Service 2</option>
										<option value="3">Service 3</option>
									</select>
								</div>
								<div class="col-12">
									<textarea class="form-control border-0"
										placeholder="Special Note"></textarea>
								</div>
								<div class="col-12">
									<button class="btn btn-primary rounded-pill py-3 px-5"
										type="submit">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample4_execDaumPostcode() {
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
                document.getElementById("location").value = data.jibunAddress;
                
                var guideTextBox = document.getElementById("guide");
            }
        }).open();
    }
</script>
	<%@ include file="../include/footer.jsp"%>