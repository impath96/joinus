<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
										 <img alt="" class="thumbnail_image" src="https://cdn.inflearn.com/public/main/profile/default_profile.png">
									</span>
								</label>
							</div>
							<input type="file" name="file2">
							<div style="width:100%;">
								<label for="name" class="input-label">
									<span>닉네임</span>
									<input id="name" type="text" class="input"
											value="aksghcjswo" placeholder="변경할 닉네임을 입력해주세요">
								</label>
							</div>
							<input type="submit" class="btn btn-primary fs-3" style="width:100%;" value="저장하기"/>
						</form>
						<form action="/settings/profile" method="post" enctype="multipart/form-data">
							<input type="file" name="filesss"> 
							<input type="submit" value="전송">
						</form>
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
	<%@ include file="../include/footer.jsp"%>