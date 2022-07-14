<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../../include/header.jsp"%>
<%@ include file="../../include/club_header.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script type="text/javascript">
// 	alert("접속한 member_no : "+${sessionScope.member_no});
// 	alert("모임원 체크 : "+${checkMember});
	
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
	
	// 모임원만 상세보기 가능
	function linkContent(club_board_no){
		if(${checkMember == 0}){
			alert('모임가입을 해주세요.');			
			return false;
		} else if(${checkMember == -1}){
			location.href = "/member/signin";
		} else {
			location.href= "/club/${club_no}/boards/"+club_board_no;
		}
	}

</script>

<!-- Feature Start -->
<div class="container-xxl py-5">
	<div class="container">
		<div class="row g-5">
		
			<!-- Projects Start -->
<!-- 			<div class="row mt-n2 wow fadeInUp" data-wow-delay="0.3s"> -->
<!-- 				<div class="col-12 text-center"> -->
<!-- 				    <ul class="list-inline mb-5" id="portfolio-flters"> -->
<!-- 				        <li class="mx-2 active"><a href="">정보</a></li> -->
<%-- 				        <li class="mx-2"><a href="/club/${club_no}/boards">게시판</a></li> --%>
<%-- 				        <li class="mx-2"><a href="/club/${club_no }/gallery">사진첩</a></li> --%>
<!-- 				    </ul> -->
<!-- 				</div> -->
<!-- 			</div> -->
		    <!-- Projects End -->

			<div style="margin-bottom: 2em;">
				<button type="button" class="btn btn-primary" id="all">전체</button>
				<button type="button" class="btn btn-primary" id="notice">공지사항</button>
				<button type="button" class="btn btn-primary" id="free">자유글</button>
				<button type="button" class="btn btn-primary" id="review">정모후기</button>
				<c:if test="${checkMember == 1 }">
					<button type="button" class="btn btn-primary" id="write" style="float: right;">글쓰기</button>
				</c:if>
			</div>


			<c:forEach var="board" items="${boardList }">
				<div class="wow fadeIn" onclick="return linkContent(${board.clubBoardsVo.club_board_no});" style="cursor: pointer;">
				<c:if test="${board.clubBoardsVo.club_board_image != null }">
					<img src="${PageContext.request.contextPath }/resources/upload/boards/sm_${board.clubBoardsVo.club_board_image }" class="clubBoardList_smImage" style="height: 200px;">
				</c:if>
					<h5 class="mb-3" style="display: inline-block;">${board.clubBoardsVo.club_board_title }</h5><br>
					<p class="clubBoardList_content">${board.clubBoardsVo.club_board_content }</p>
					<div style="margin-bottom: 1em;">
						<img class="boardContent_writeImage" src="${PageContext.request.contextPath }/resources/upload/members/${board.membersVo.member_image }">
						<span class="clubBoardList_writer">${board.membersVo.member_name }</span>
						
						<c:if test="${board.clubBoardsVo.club_board_updatedate == null }">
							<span><fmt:formatDate value="${board.clubBoardsVo.club_board_date }" pattern="yy.MM.dd HH:mm"/> </span>
						</c:if>
						<c:if test="${board.clubBoardsVo.club_board_updatedate != null }">
							<span><fmt:formatDate value="${board.clubBoardsVo.club_board_updatedate }" pattern="yy.MM.dd HH:mm"/></span>
						</c:if>
					</div>
					<i class="bi bi-heart"></i> <span class="clubBoardList_likeCnt">${board.clubBoardsVo.club_board_likecnt }</span>
					<i class="fa fa-comments fa-fw"></i> <span class=clubBoardList_commentCnt>${board.clubBoardsVo.club_board_commentcnt }</span>
				</div>
				<hr style="margin-top: 1em;">
			</c:forEach>
			
			<!-- 페이징블럭 -->
			<!-- 카테고리선택했으면 링크다르게 걸어줘야 할 듯 -->
			<c:if test="${board_type_no > 0 }">

			</c:if>
			
			<div class="d-flex justify-content-center py-3">
	        	<ul class="list-group list-group-horizontal">
	        		<c:if test="${pm.prev }">
	        			<li><a href="${PageContext.request.contextPath }/club/${club_no }/boards?page=${pm.startPage-1}">&laquo;</a></li>
	        		</c:if>
	        		<c:forEach var ="idx" begin ="${pm.startPage }" end="${pm.endPage }">
	        			<li class="list-group-item" <c:out value="${pm.cri.page == idx? 'class=active':'' }"/>>
	        			<a href = "${PageContext.request.contextPath }/club/${club_no }/boards?page=${idx}" >${idx }</a>
	        			</li>
	        		</c:forEach>
	        		
	        		<c:if test="${pm.next && pm.endPage>0 }">
	        			<li> <a href ="${PageContext.request.contextPath }/club/${club_no }/boards?page=${pm.endPage+1}">&raquo;</a></li>
	        		</c:if>
	        	
	        	</ul>
	        </div>
			
			
		</div>
		
	
	</div>
</div>


<%@ include file="../../include/footer.jsp"%>