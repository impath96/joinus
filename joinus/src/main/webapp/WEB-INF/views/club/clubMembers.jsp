<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="../include/header.jsp"%>
<%@ include file="../include/club_header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body> 

<!-- 정모 -->
<div class="container px-4 py-5">
<div class="row featurette">
      <div class="col-md-7 order-md-2">
        <h1 class="featurette-headin">${clubInfo[0].club_name} </h1>
        <h2 class="featurette-heading">${clubInfo[0].club_content}</h2>
        <h2 class="featurette-heading">${clubInfo[0]}</h2>

      </div>
      <div class="col-md-5 order-md-1">
        <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#eee"></rect><text x="50%" y="50%" fill="#aaa" dy=".3em">500x500</text></svg>
      </div>
    </div>
      	<div class="text-end col-md-6 ms-auto">
          <button class="btn btn-primary rounded-pill py-3 px-5" type="submit">정보 수정하기</button>
      	</div>
</div>
<!-- 모임회원리스트 -->

<div class="container px-4 py-5" id="icon-grid">
	<div class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
    <h2 class="pb-2 border-bottom">모임 멤버</h2>
        <h3 class="text-end col-md-6 ms-auto"><a href="${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/leave ">모임 나가기 <i class="fa fa-arrow-right ms-3"></i></a> </h3>
	</div>
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4 py-5">
   	<c:forEach var="vo" items="${clubMemberList }" varStatus="status">
      <form action="" role="form">
      <div class="col d-flex align-items-start">
        <img src="${vo.membersVo.member_image}" class="img-responsive w-40">
        <div class="px-3">
          <h4 class="d-flex gap-2 w-100 justify-content-between">${vo.membersVo.member_name }</h4>
          
          <br>
          <input type="hidden" name="ban_member_no_${status.index}" value="${vo.membersVo.member_no }">
          <div>
          <small class="opacity-50 text-nowrap">${vo.clubMemberRoleVo.club_role_name }</small><br>
          <small class="opacity-50 text-nowrap">모임가입일 : </small>
          <small class="opacity-50 text-nowrap">${vo.clubMembersVo.club_member_regdate }</small><br>
          
          </div>
        </div>
       <c:if test="${result == 1}">
        <div class="text-end col-md-6 ms-auto">
        <button type="button" class="btn btn-warning px-2"
      		 	onclick="location.href='${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/clubMembers/auth?member_no=${vo.membersVo.member_no }';">모임장 양도</button><br>
        <button type="button" class="btn btn-danger px-2"
        		onclick="location.href='${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/clubMembers/ban?member_no=${vo.membersVo.member_no }';">강퇴</button>
<%--         <button type="button" class="btn btn-danger px-2" onclick="location.href='${PageContext.request.contextPath}/club/clubMember/ban?member_no=ban_member_no${status.index}';">강퇴</button> --%>
        </div>
       </c:if>	
      </div>
       </form>
     </c:forEach>         
    </div>
  </div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"> </script>
<script type="text/javascript">

	var BAN = '${BAN}';
	if(BAN == "BANOK"){
		alert('해당 회원을 강퇴시켰습니다.');
	}



</script>
<!-- 모임회원리스트 -->

<%@ include file="../include/footer.jsp"%>
