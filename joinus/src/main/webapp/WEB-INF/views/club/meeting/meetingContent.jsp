<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="../../include/header.jsp"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ce8d060125bcc89e0c25ee69f6b5c7b0&libraries=services"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"> </script>


<body> 
<!-- 정모 -->
<div class="container px-5">
	<div class="row g-5">
      
      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">일정 수정하기</h4>
        <form class="needs-validation" action ="" method="post">
          <div class="row g-3">
            <div class="col-12">
              <label for="firstName" class="form-label">제목</label>
              <input type="text" class="form-control" name="club_meeting_title" value="${meetingList[0].club_meeting_title}" disabled="disabled">
              <div class="invalid-feedback">
                Valid first name is required. 
              </div>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">날짜</label>
              <input type="date" class="form-control" id="club_meeting_date" value="${meetingList[0].club_meeting_date}" disabled="disabled">
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">정원</label>
              <input type="number" class="form-control" name="club_meeting_capacity" value="${meetingList[0].club_meeting_capacity}" disabled="disabled">
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">장소</label>
              	<div class="input-group">
              		<input type="text" class="form-control" id="club_meeting_location" value="${meetingList[0].club_meeting_location}" disabled="disabled">
            	</div>
            </div>
  
            <div class="col-12">
              <label for="address2" class="form-label">참가비 <span class="text-muted">(선택)</span></label>
              <input type="number" class="form-control" name="club_meeting_dues" value="${meetingList[0].club_meeting_dues}" disabled="disabled">
            </div>

          </div>

          <hr class="my-4">

          <h4 class="mb-3">Payment</h4>

          <div class="my-3">
            <div class="form-check">
              <input id="credit" name="paymentMethod" type="radio" class="form-check-input" checked="" required="">
              <label class="form-check-label" for="credit">Credit card</label>
            </div>
          </div>

          <div class="row gy-3">
            <div class="col-md-6">
              <label for="cc-name" class="form-label">계좌번호</label>
              <input type="text" class="form-control" id="cc-name" placeholder="">
              <small class="text-muted">계좌번호는 모임 회원에게만 보여요!</small>
            </div>

          </div>
          <hr class="my-4">
	
			<div class="margin">
			
			<div class="btn-group">
			<button type="button" class="btn btn-secondary btn-flat"
				onclick ="${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/meeting/${meetingList[0].club_meeting_no}/modify">수정하기</button>
			</div>
			
			<div class="btn-group">
			<button type="button" class="btn btn-danger btn-flat"
				onclick ="${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/meeting/${meetingList[0].club_meeting_no}/delete">삭제하기</button>
			</div>
			
			<div class="btn-group">
			<button type="button" class="btn btn-success btn-flat"
				onclick ="${PageContext.request.contextPath}/club/${clubInfo[0].club_no}/meeting/${meetingList[0].club_meeting_no}/close">마감하기</button>
			</div>
			
			</div>



        </form>
      </div>
    </div>
</div>
<!-- 정모 -->


<%@ include file="../../include/footer.jsp"%>
