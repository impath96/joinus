<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../../include/header.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		// 폼태그 정보
		var formObj = $('form[role="form"]');
		console.log(formObj);

		$('#modBtn').click(function(){
			location.href = "/club/${club_no}/boards/${club_board_no}/modify";
		});
		$('#delBtn').click(function(){
			formObj.attr("action", "/club/${club_no}/boards/${club_board_no}/delete");
			formObj.attr("method","POST");
			formObj.submit();
		});
		$('#backBtn').click(function(){
			location.href = "/club/${club_no}/boards";
		});
		
		// 댓글등록버튼
		$('#commentBtn').click(function(){
// 			alert('댓글등록');
			if($('#board_comment_content').val() != ''){
				alert('값O');
				$.ajax({
					type : "post",
					url : "/club/${club_no}/boards/${club_board_no}/comment",
					data : {
// 						club_board_no :
						board_comment_content : $('#board_comment_content').val()
					},
					success : function(){
						alert('댓글등록완료');
						$('#board_comment_content').val('');
					},
					error : function(){
						alert('시스템 문제발생');
					}
				});
			} else {
				$('#board_comment_content').focus();
			}
		});
		
	});

</script>

<!-- nav -->
<div class="row mt-n2 wow fadeInUp" data-wow-delay="0.3s">
	<div class="col-12 text-center">
	    <ul class="list-inline mb-5" id="portfolio-flters">
	        <li class="mx-2"><a href="">정보</a></li>
	        <li class="mx-2"><a href="/club/${club_no}/boards">게시판</a></li>
	        <li class="mx-2"><a href="/club/${club_no }/gallery">사진첩</a></li>
	    </ul>
	</div>
</div>
<!-- nav -->

<form action="" role="form">
	<input type="hidden" name="club_board_no" value="${club_board_no }">
</form>

<div class="container-fluid overflow-hidden px-lg-0">
	<div class="container contact px-lg-0" style="width: 60%">
		<div class="row g-0 mx-lg-0">
			<!-- 			<div class="col-lg-6 contact-text py-5 wow fadeIn" data-wow-delay="0.5s"> -->
			<div class="p-lg-5" align="center">
				<h6 class="text-primary">${vo.clubsVo.club_name } / ${vo.boardTypesVo.board_type_name }</h6>
				<h1 class="clubWrite_mb-4">게시글</h1>

					<div class="row g-3">
						<div class="col-md-8">
							<div class="form-floating">
								제목 : ${vo.clubBoardsVo.club_board_title }
							</div>
						</div> 
						<div class="col-12">
							<div class="form-floating">
								내용 : ${vo.clubBoardsVo.club_board_content }
							</div>
						</div>
						
						<div class="col-12 clubWrite_buttonMargin" style="text-align: right;">
<!-- 							<input type="button" class="btn btn-primary rounded-pill py-3 px-5" id="modBtn" value="수정"> -->
<!-- 							<input type="button" class="btn btn-primary rounded-pill py-3 px-5" style="margin-left: 2em;" id="delBtn" value="삭제"> -->
<!-- 							<input type="button" class="btn btn-primary rounded-pill py-3 px-5" style="margin-left: 2em;" id="backBtn" value="목록"> -->
							<button type="button" class="btn btn-primary py-2 mt-2 me-2" id="modBtn">수정</button>
							<button type="button" class="btn btn-primary py-2 mt-2 me-2" id="delBtn">삭제</button>
							<button type="button" class="btn btn-primary py-2 mt-2 me-2" id="backBtn">목록</button>
						</div>
						
					</div>
					
					<hr style="margin-top: 3em;">
					
					<div style="text-align: left;">
						<i class="bi bi-heart"></i><span class="clubBoardList_likeCnt"> 0</span>
						<i class="fa fa-comments fa-fw"></i><span class=clubBoardList_commentCnt> 0</span>
					</div>
					
					<div class="comments_form" style="margin: 30px 0;">
						<div>
							<textarea class="form-control" name="board_comment_content" id="board_comment_content"
								rows="3" placeholder="댓글을 입력해주세요." required></textarea>
								<div style="text-align: right;">
									<button type="button" class="btn btn-primary py-2 mt-2 me-2" id="commentBtn">등록</button>
								</div>
						</div>
					</div>


			</div>
		</div>
	</div>
</div>



<%@ include file="../../include/footer.jsp"%>