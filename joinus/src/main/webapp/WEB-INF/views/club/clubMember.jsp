<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body> 
<!-- 정모 -->
<div class="container px-5">
<div class="pricing-header p-3 pb-md-4 mx-auto text-center py-">
      <h1 class="display-4 fw-normal">정모 리스트</h1>
      <p class="fs-5 text-muted">정모 리스트 제일 빠른 순으로 나열</p>
    </div>
    <div class="row row-cols-1 row-cols-md-3 mb-3 text-center">
      <div class="col">
        <div class="card mb-4 rounded-3 shadow-sm">
          <div class="card-header py-3">
            <h4 class="my-0 fw-normal">정모 이름</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title pricing-card-title">날짜<small class="text-muted fw-light">/mo</small></h1>
            <ul class="list-unstyled mt-3 mb-4">
              <li>10 users included</li>
              <li>2 GB of storage</li>
              <li>Email support</li>
              <li>Help center access</li>
            </ul>
            <button type="button" class="w-100 btn btn-lg btn-outline-primary">Sign up for free</button>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card mb-4 rounded-3 shadow-sm">
          <div class="card-header py-3">
            <h4 class="my-0 fw-normal">Pro</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title pricing-card-title">$15<small class="text-muted fw-light">/mo</small></h1>
            <ul class="list-unstyled mt-3 mb-4">
              <li>20 users included</li>
              <li>10 GB of storage</li>
              <li>Priority email support</li>
              <li>Help center access</li>
            </ul>
            <button type="button" class="w-100 btn btn-lg btn-primary">Get started</button>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card mb-4 rounded-3 shadow-sm border-primary">
          <div class="card-header py-3 text-white bg-primary border-primary">
            <h4 class="my-0 fw-normal">Enterprise</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title pricing-card-title">$29<small class="text-muted fw-light">/mo</small></h1>
            <ul class="list-unstyled mt-3 mb-4">
              <li>30 users included</li>
              <li>15 GB of storage</li>
              <li>Phone and email support</li>
              <li>Help center access</li>
            </ul>
            <button type="button" class="w-100 btn btn-lg btn-primary">Contact us</button>
          </div>
        </div>
      </div>
    </div>
   </div>
<!-- 정모 -->

<!-- 모임회원리스트 -->

<div class="container px-4 py-5" id="icon-grid">
    <h2 class="pb-2 border-bottom">모임 멤버</h2>

    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4 py-5">
   	<c:forEach var="vo" items="${clubMemberList }">
      <div class="col d-flex align-items-start">
        <img src="${vo.membersVo.member_image}" class="img-responsive w-40">
        <div class="px-3">
          <h4 class="d-flex gap-2 w-100 justify-content-between">${vo.membersVo.member_name }</h4>
          <div>
          <small class="opacity-50 text-nowrap">${vo.clubMemberRoleVo.club_role_name }</small>
          </div>

        </div>
      </div>
     </c:forEach> 
           
    </div>
  </div>



   



<!-- 모임회원리스트 -->

<%@ include file="../include/footer.jsp"%>
