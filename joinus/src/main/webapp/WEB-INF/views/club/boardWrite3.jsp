<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../include/header.jsp"%>

<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript">
	//alert('js사용!');
	
	// jQuery
	$(document).ready(function() {

		// 파일추가버튼
		$('#addPhoto').click(function() {
			
			var str = "<span><input type='file' name='photo' id='photo' style='display: inline; margin-bottom:8px;'></span><br>";
			$('#photoGroup').append(str);
			$('#addBlock').hide();

		});	// 파일추가
		
		
		// 등록버튼
		$('#subBtn').click(function(){
			
			if(document.fr.board_type_no.value == ''){
				document.fr.board_type_no.focus();
				return false;
			}
			if(!document.fr.club_board_title.value){
				document.fr.club_board_title.focus();
				return false;
			}
			if(!document.fr.club_board_content.value){
				document.fr.club_board_content.focus();
				return false;
			}
			
			// post로 넘겨서 count 값 검사해서 0일때 0아닐때 각각 처리해야 할듯?
// 			if(count == 0){
// // 				alert('count==0');
// 				$('form').attr({
// 					enctype: ''
// 				});
// 			}
			
			alert(document.getElementById("photo").files[0].name);
			
// 			return false;
			
		}); //등록버튼
		

	});	// jQuery
	
	// JS
	
	
</script>


<div class="container-fluid bg-light overflow-hidden px-lg-0">
	<div class="container contact px-lg-0" style="width: 60%">
		<div class="row g-0 mx-lg-0">
			<!-- 			<div class="col-lg-6 contact-text py-5 wow fadeIn" data-wow-delay="0.5s"> -->
			<div class="p-lg-5" align="center">
				<h6 class="text-primary">JoinUs</h6>
				<h1 class="clubWrite_mb-4">게시글</h1>

				<form name="fr" action="${pageContext.request.contextPath }/club/boardWrite" method="post" enctype="multipart/form-data">
					<input type="hidden" name="club_no" value="${club_no }">
					<input type="hidden" name="member_no" value="${sessionScope.member_no }">
					<div class="row g-3">
						<div class="col-md-4">
							<select class="form-select" style="height: 55px;"
								id="board_type_no" name="board_type_no">
								<option selected value="">게시글 카테고리</option>
								<option value="1">자유글</option>
								<option value="2">정모후기</option>
								<option value="3">공지</option>
							</select>
						</div>
						<div class="col-md-8">
							<div class="form-floating">
								<input type="text" class="form-control" id="club_board_title"
									name="club_board_title" placeholder="title"> <label
									for="club_board_title">제목</label>
							</div>
						</div>
						<div class="col-12">
							<div class="form-floating">
								<textarea class="form-control" placeholder="content"
									id="club_board_content" name="club_board_content"
									style="height: 100px"></textarea>
								<label for="club_board_content">내용</label>
							</div>
						</div>




						<!-- <span style="text-align: left;" class="clubWrite_fileSpan"> -->
						<span class="clubWrite_photoSpan" id="addBlock">
							<button type="button" class="btn btn-primary" id="addPhoto">파일추가</button>
						</span>
						<span id="photoGroup" class="clubWrite_photoGroup"> </span>
						




						<div class="col-12 clubWrite_buttonMargin">
							<!-- <button class="btn btn-primary rounded-pill py-3 px-5" type="submit">등록</button> -->
							<input type="submit" class="btn btn-primary rounded-pill py-3 px-5" id="subBtn" value="등록">
						</div>
					</div>
				</form>


			</div>
		</div>
	</div>
</div>


<%@ include file="../include/footer.jsp"%>