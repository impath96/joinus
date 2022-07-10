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
					url : "${pageContext.request.contextPath}/club/${club_no}/boards/${club_board_no}/comment",
					data : {
// 						club_board_no :
						board_comment_content : $('#board_comment_content').val()
					},
					success : function(data){
						alert('댓글등록완료');
						$('#board_comment_content').val('');
						location.reload();
					},
					error : function(){
						alert('시스템 문제발생');
					}
				});
			} else {
				$('#board_comment_content').focus();
			}
		});
		
		// 댓글수정버튼
		$('.btn').click(function(){
			var id_check = $(this).attr('id');
			var board_comment_no = id_check.substring(12);
// 			alert(id_check);
// 			alert(board_comment_no);
			
			$('.comment'+board_comment_no).hide();
			$('#commentUpForm'+board_comment_no).show();
			
			// 수정 취소 버튼
			$('#commentUpCancelBtn'+board_comment_no).click(function(){
				$('#commentUpForm'+board_comment_no).hide();
				$('.comment'+board_comment_no).show();
			});
			
			// 수정 완료 버튼
			$('#commentUpCompleteBtn'+board_comment_no).click(function(){
				var board_comment_content = $('#commentUp'+board_comment_no).val();
				
				$.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/club/${club_no}/boards/${club_board_no}/comment/modify?board_comment_no="+board_comment_no,
					data : {
						board_comment_content : board_comment_content
					},
					success : function(data){
						location.reload();
					},
					error : function error(){
						alert('시스템 문제발생');
					}
				});
				
				$('#commentUpForm'+board_comment_no).hide();
				$('.comment'+board_comment_no).show();
				
			});
			
		}); // 댓글 수정 버튼
		
		// 댓글 삭제 버튼
		$('.btn').click(function(){
			var id_check = $(this).attr('id');
// 			alert(id_check);
			if(id_check.substring(0, 13) == 'commentDelBtn'){
				var board_comment_no = id_check.substring(13);
// 				alert('삭제버튼');
// 				alert(board_comment_no);

				if(confirm('댓글을 삭제하시겠습니까?')){
					$.ajax({
						type : "post",
						url : "${pageContext.request.contextPath}/club/${club_no}/boards/${club_board_no}/comment/delete?board_comment_no="+board_comment_no,
						success : function(data){
							location.reload();
						},
						error : function error(){
							alert('시스템 문제발생');
						}
					});
				}
			}
		});	// 댓글 삭제 버튼
		
		
		// 좋아요
		$('#like').click(function(){{
			alert('좋아요클릭');
			var like_check = ${checkLike};
			
			if(like_check == 1){
				alert('좋아요 누른 회원');
				// 좋아요 취소
				$.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/club/${club_no}/boards/${club_board_no}/likeDown",
					// 만약 data를 넘겨줄것같으면 세션값에 저장된 member_no -> 근데 굳이 view에서 세션값을 넘겨줄 필요가..? 컨트롤러에서 세션값 바로 쓰면 될 것 같은데
					success : function(){
						alert('좋아요 취소');
// 						location.reload();
					},
					erorr : function error(){
						alert('시스템 문제발생');
					}
				});
			} else {
				// 좋아요 안누른 회원
				// 좋아요
				$.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/club/${club_no}/boards/${club_board_no}/likeUp",
					success : function(){
						alert('좋아요');
					},
					error : function error(){
						alert('시스템 문제발생');
					}
				});
			}
			
		}});
		
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
				<h1 class="clubWrite_mb-4" style="float: left;">${vo.clubBoardsVo.club_board_title }</h1>
				<br>
					<div class="row g-3">
						<div class="col-md-8">
							<div class="form-floating">
								<img class="boardContent_writeImage" src="${PageContext.request.contextPath }/resources/upload/members/${vo.membersVo.member_image }">
								<span style="color: black; margin-left: 5px;">${vo.membersVo.member_name }</span>
							</div>
							<div>
								<c:if test="${vo.clubBoardsVo.club_board_updatedate == null }">
									<span><fmt:formatDate value="${vo.clubBoardsVo.club_board_date }" pattern="yy.MM.dd HH:mm"/> </span>
								</c:if>
								<c:if test="${vo.clubBoardsVo.club_board_updatedate != null }">
									<span><fmt:formatDate value="${vo.clubBoardsVo.club_board_updatedate }" pattern="yy.MM.dd HH:mm"/> </span>
								</c:if>
							</div>
						</div> 
						<div class="col-12">
							<div class="form-floating" style="color: black;">
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
						<a id="like" href="#"><i class="bi bi-heart"></i></a><span class="clubBoardList_likeCnt"> ${likeCnt }</span>
						<i class="fa fa-comments fa-fw"></i><span class=clubBoardList_commentCnt> ${commentCnt }</span>
					</div>
					
					<c:if test="${likeCnt > 0 }">
						<a href="">${likeCnt }명이 좋아하셨습니다.</a>
					</c:if>
					
					<div class="comments_form" style="margin: 30px 0;">
						<div>
							<textarea class="form-control" name="board_comment_content" id="board_comment_content"
								rows="3" placeholder="댓글을 입력해주세요." required></textarea>
								<div style="text-align: right;">
									<button type="button" class="btn btn-primary py-2 mt-2 me-2" id="commentBtn">등록</button>
								</div>
						</div>
					</div>
					<c:if test="${commentCnt > 0 }">
						<c:forEach var="comment" items="${commentList }">
<%-- 						작성자 : ${comment.membersVo.member_name } --%>
<%-- 						내용 : ${comment.boardCommentsVo.board_comment_content } --%>
							
							
							<article class="comment${comment.boardCommentsVo.board_comment_no }">
								<div>
									<img class="boardContent_writeImage" src="${PageContext.request.contextPath }/resources/upload/members/${comment.membersVo.member_image }">
									<h4 class="comment-author" style="padding-top: 0.7em;">${comment.membersVo.member_name }
										<div class="form-group pull-right">
											<!-- 세션값과 댓글작성자비교 -->
											<input type="button" id="commentUpBtn${comment.boardCommentsVo.board_comment_no }" class="btn btn-primary py-2 mt-2 me-2 tabActive" value="수정">
											<input type="button" id="commentDelBtn${comment.boardCommentsVo.board_comment_no }" class="btn btn-primary py-2 mt-2 me-2" value="삭제">
										</div>
									</h4>
									<div id="comment_content" style="padding-top: 1em; padding-bottom: 1em;">${comment.boardCommentsVo.board_comment_content }</div>
									<small class="comment-meta" style="padding: 0;">
										<c:if test="${comment.boardCommentsVo.board_comment_updatedate == null }">
											<span><fmt:formatDate value="${comment.boardCommentsVo.board_comment_date }" pattern="yy.MM.dd HH:mm"/> </span>
										</c:if>
										<c:if test="${comment.boardCommentsVo.board_comment_updatedate != null }">
											<span><fmt:formatDate value="${comment.boardCommentsVo.board_comment_updatedate }" pattern="yy.MM.dd HH:mm"/> </span>
										</c:if>
									</small>
								</div>
							</article>
							
							<!-- 댓글수정폼 -->
							<div id="commentUpForm${comment.boardCommentsVo.board_comment_no }" style="display: none;">
								<textarea class="form-control" name="commentUp${comment.boardCommentsVo.board_comment_no }"
									id="commentUp${comment.boardCommentsVo.board_comment_no }" rows="3" required>${comment.boardCommentsVo.board_comment_content }</textarea>
								<div>
									<input type="button" id="commentUpCompleteBtn${comment.boardCommentsVo.board_comment_no }" class="button" value="수정">
									<input type="button" id="commentUpCancelBtn${comment.boardCommentsVo.board_comment_no }" class="button" value="취소">
								</div>
							</div>
							
							
							
							
						</c:forEach>
					</c:if>


			</div>
		</div>
	</div>
</div>



<%@ include file="../../include/footer.jsp"%>