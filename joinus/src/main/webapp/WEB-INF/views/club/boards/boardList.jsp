<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../../include/header.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		$('#all').click(function(){
			$(location).attr('href','/club/${club_no}/boards');
		});
		$('#notice').click(function(){
			$(location).attr('href','/club/${club_no}/boards/type/3');
		});
		$('#free').click(function(){
			$(location).attr('href','/club/${club_no}/boards/type/1');
		});
		$('#review').click(function(){
			$(location).attr('href','/club/${club_no}/boards/type/2');
		});
		$('#write').click(function(){
			$(location).attr('href','/club/${club_no}/boards/new');
		});
	});

</script>

    <!-- Projects Start -->
	<div class="row mt-n2 wow fadeInUp" data-wow-delay="0.3s">
		<div class="col-12 text-center">
		    <ul class="list-inline mb-5" id="portfolio-flters">
		        <li class="mx-2 active"><a href="">정보</a></li>
		        <li class="mx-2"><a href="/club/${club_no}/boards">게시판</a></li>
		        <li class="mx-2"><a href="/club/${club_no }/gallery">사진첩</a></li>
		    </ul>
		</div>
	</div>
    <!-- Projects End -->


<!-- Feature Start -->
<div class="container-xxl py-5">
	<div class="container">
		<div class="row g-5">

			<div style="margin-bottom: 2em;">
				<button type="button" class="btn btn-primary" id="all">전체</button>
				<button type="button" class="btn btn-primary" id="notice">공지사항</button>
				<button type="button" class="btn btn-primary" id="free">자유글</button>
				<button type="button" class="btn btn-primary" id="review">정모후기</button>
				<button type="button" class="btn btn-primary" id="write" style="float: right;">글쓰기</button>
			</div>


			<c:forEach var="board" items="${boardList }">
				<div class="wow fadeIn" onclick="location.href='/club/${club_no}/boards/${board.clubBoardsVo.club_board_no }';" style="cursor: pointer;">
<!-- 				<img src="/resources/img/airplaneSky.jpg" style="border-radius: 10px; float: left; width: 200px; height: 150px; margin-right: 2em;"> -->
				<c:if test="${board.clubBoardsVo.club_board_image != null }">
					<img src="${PageContext.request.contextPath }/resources/upload/boards/sm_${board.clubBoardsVo.club_board_image }" class="clubBoardList_smImage">
				</c:if>
	<!-- 				<div> -->
	<!-- 					<h3 style="margin-top: 0.5em;">제목</h3> -->
	<!-- 				</div> -->
					<h5 class="mb-3" style="display: inline-block;">${board.clubBoardsVo.club_board_title }</h5><br>
					<p class="clubBoardList_content">${board.clubBoardsVo.club_board_content }</p>
					<div style="margin-bottom: 1px;">
						<span class="clubBoardList_writer">${board.membersVo.member_name }</span>
						
						<c:if test="${board.clubBoardsVo.club_board_updatedate == null }">
							<span><fmt:formatDate value="${board.clubBoardsVo.club_board_date }" pattern="yyyy.MM.dd"/> </span>
						</c:if>
						club_board_date : ${board.clubBoardsVo.club_board_date }
						<c:if test="${board.clubBoardsVo.club_board_updatedate != null }">
							<span>${board.clubBoardsVo.club_board_updatedate }</span>
						</c:if>
						club_board_updatedate : ${board.clubBoardsVo.club_board_updatedate }
					</div>
					<i class="fa fa-regular fa-thumbs-up"></i> <span class="clubBoardList_likeCnt">${board.clubBoardsVo.club_board_like }</span>
					<i class="fa fa-regular fa-comment"></i> <span class=clubBoardList_commentCnt>${board.clubBoardsVo.club_board_commentcnt }</span>
				</div>
				<hr>
			</c:forEach>
			
			<div>
				<ul class="pagination pagination-sm no-margin pull-right" style="float: right;">
		
				<li style="margin-right: 1em;"><a href="#">«</a></li>
			
				<li >
					<a href="#" style="margin-right: 1em;">1</a>
					<a href="#" style="margin-right: 1em;">2</a>
					<a href="#" style="margin-right: 1em;">3</a>
					<a href="#" style="margin-right: 1em;">4</a>
				</li>			
			
				<li><a href="#">»</a></li>
			
				</ul>
			</div>
			
			
		</div>
		
	
	</div>
</div>


<%@ include file="../../include/footer.jsp"%>