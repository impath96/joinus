<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>


<div class="container-fluid bg-light overflow-hidden px-lg-0">
	<div class="container contact px-lg-0" style="width: 60%">
		<div class="row g-0 mx-lg-0">
<!-- 			<div class="col-lg-6 contact-text py-5 wow fadeIn" data-wow-delay="0.5s"> -->
				<div class="p-lg-5" align="center">
					<h6 class="text-primary">Grouping</h6>
					<h1 class="clubWrite_mb-4">게시글</h1>
					<form>
						<div class="row g-3">
							<div class="col-md-4">
									<select class="form-select" style="height: 55px;" name="category">
										<option selected>게시글 카테고리</option>
										<option value="자유글">자유글</option>
										<option value="정모후기">정모후기</option>
										<option value="공지">공지</option>
									</select>
							</div>
							<div class="col-md-8">
								<div class="form-floating">
									<input type="email" class="form-control" id="email"
										placeholder="Your Email"> <label for="email">제목</label>
								</div>
							</div>
							<div class="col-12">
								<div class="form-floating">
									<textarea class="form-control"
										placeholder="Leave a message here" id="message"
										style="height: 100px"></textarea>
									<label for="message">내용</label>
								</div>
							</div>
							
							<div>
								<button type="button" class="btn btn-primary">SignUp</button>
							</div>
							
							
							<div class="col-12 clubWrite_buttonMargin">
								<button class="btn btn-primary rounded-pill py-3 px-5"
									type="submit">등록</button>
							</div>
						</div>
					</form>
				</div>
		</div>
	</div>
</div>


<%@ include file="../include/footer.jsp"%>