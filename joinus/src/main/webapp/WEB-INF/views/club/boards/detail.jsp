<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ include file="../../include/header.jsp"%>


				

<div class="container-fluid bg-light overflow-hidden px-lg-0">
	<div class="container contact px-lg-0" style="width: 60%">
		<div class="row g-0 mx-lg-0">
			<div class="p-lg-5" align="center">
				<h6 class="text-primary">JoinUs</h6>
				<h1 class="clubWrite_mb-4">게시글 상세정보</h1>
				
				
				<form name="fr" action="${pageContext.request.contextPath }/club/delete">
					<input type="hidden" name="club_board_no" value="${vo.club_board_no }">
				</form>
				
					<div class="row g-3">
						<div class="col-md-4">
							<input type="text" class="form-control" style="height: 55px;" id="board_type_no" name="board_type_no"
							value="${vo.boardtypeVo.board_type_name }" readonly="readonly">
						</div>
						<div class="col-md-8">
							<div class="form-floating">
								<input type="text" class="form-control" id="club_board_title"
									name="club_board_title" readonly="readonly" value="${vo.club_board_title }"><label
									for="club_board_title"></label>
							</div>
						</div>
						<hr>
						<div class="col-12">
							<div class="form-floating">
								<textarea class="form-control"
									id="club_board_content" name="club_board_content"
									style="height: 100px" readonly="readonly">${vo.club_board_content }</textarea>
							</div>
						</div>
						
						
						<hr>
						
					<div>
						
					</div>
					<div style="text-align: left;">
					<i class="fa fa-comments fa-fw"></i>댓글
						<i class="bi bi-heart"></i>좋아요
						</div>
						<div class="comments_form" style="margin: 60px 0;">
						<div id="comment" style="display: none;">
							<textarea class="form-control" name="board_comment_content" id="board_comment_content"
								rows="3" placeholder="댓글을 입력해주세요." required></textarea>
								<input class="btn btn-primary rounded-pill py-2 px-7" type="button" value="댓글작성">
						</div>
					</div>
						
						
						
						<div class="col-12 clubWrite_buttonMargin">
								<input type="submit" id="modify" class="btn btn-primary rounded-pill py-2 px-7" value="수정하기">
								<input type="submit" id="delete" class="btn btn-danger rounded-pill py-2 px-7" value="삭제하기">
								<input type="button" class="btn btn-success rounded-pill py-2 px-7" id="commentBtn" value="댓글">
								<input type="submit" class="btn btn-success rounded-pill py-2 px-7" value="목록" onclick="location.href='/club/listAll';">
						</div>
					</div>
				
		
			</div>
		</div>
	</div>
</div>

<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript">


	$(document).ready(function(){
			
		var formObj = $('form[name="fr"]');
		
		// 삭제
		$('#delete').click(function(){
			 formObj.attr("action", "/club/delete");
			 formObj.attr("method", "POST");
			 formObj.submit();
			
		});
		
		// 수정
		$('#modify').click(function(){
			formObj.attr("action", "/club/modify");
			formObj.submit();
		});
		
		// 댓글버튼
		$('#commentBtn').click(function() {
			alert('체크');
			$('#comment').show(function(){
				
			});
			var comment = {
				'board_comment_content'	:$('#board_comment_content').val()
				
			};
			
			
			
		});
			
		
		
	}); //JQuery

</script>






<%@ include file="../../include/footer.jsp"%>