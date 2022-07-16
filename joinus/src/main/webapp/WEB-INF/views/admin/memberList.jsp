<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<div class="container p-4">
	<h2 class="fs-1 fw-bold text-center my-5">관리자 페이지</h2>
      <div class="row">
        <div class="col-2">
          <div class="accordion accordion-flush" id="accordionFlushExample">
            <div class="accordion-item">
              <h2 class="accordion-header" id="flush-headingOne">
                <button
                  class="accordion-button collapsed"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#flush-collapseOne"
                  aria-expanded="false"
                  aria-controls="flush-collapseOne"
                >
                  회원 관리
                </button>
              </h2>
              <div
                id="flush-collapseOne"
                class="accordion-collapse collapse"
                aria-labelledby="flush-headingOne"
                data-bs-parent="#accordionFlushExample"
              >
                <div class="accordion-body p-2">
                  <ul class="p-0 mb-2">
                    <li class="py-2 text-center">
                      <a href="">전체 회원 목록</a>
                    </li>
                    <li class="py-2 text-center">
                      <a href="">정지 회원 목록</a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="flush-headingTwo">
                <button
                  class="accordion-button collapsed"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#flush-collapseTwo"
                  aria-expanded="false"
                  aria-controls="flush-collapseTwo"
                >
                  Accordion Item #2
                </button>
              </h2>
              <div
                id="flush-collapseTwo"
                class="accordion-collapse collapse"
                aria-labelledby="flush-headingTwo"
                data-bs-parent="#accordionFlushExample"
              >
                <div class="accordion-body">
                  Placeholder content for this accordion, which is intended to
                  demonstrate the <code>.accordion-flush</code> class. This is
                  the second item's accordion body. Let's imagine this being
                  filled with some actual content.
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="flush-headingThree">
                <button
                  class="accordion-button collapsed"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#flush-collapseThree"
                  aria-expanded="false"
                  aria-controls="flush-collapseThree"
                >
                  Accordion Item #3
                </button>
              </h2>
              <div
                id="flush-collapseThree"
                class="accordion-collapse collapse"
                aria-labelledby="flush-headingThree"
                data-bs-parent="#accordionFlushExample"
              >
                <div class="accordion-body">
                  Placeholder content for this accordion, which is intended to
                  demonstrate the <code>.accordion-flush</code> class. This is
                  the third item's accordion body. Nothing more exciting
                  happening here in terms of content, but just filling up the
                  space to make it look, at least at first glance, a bit more
                  representative of how this would look in a real-world
                  application.
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-10">
          <div class="row">
            <div class="container-fluid">
              <form class="d-flex">
                <input
                  class="form-control me-2"
                  type="search"
                  placeholder="회원 검색"
                  aria-label="Search"
                />
                <button class="btn btn-outline-success" type="submit">
                  Search
                </button>
              </form>
            </div>
          </div>
          <section class="row box mt-4 p-3">
            <div class="box-header mt-2 p-2 fs-5 fw-bold border border-dark">
              header
            </div>
            <div class="box-content mt-2 ">
              <table style="width: 100%">
                <thead class="fs-5 border-bottom">
                  <tr>
                    <th class="py-2 px-3">닉네임</th>
                    <th class="p-2">계정</th>
                    <th class="p-2">상태</th>
                    <th class="p-2">가입일</th>
                  </tr>
                </thead>
                <tbody class="fs-5">
                <c:forEach var="member" items="${members }">
                  <tr>
                    <td class="py-2 px-3">${member.member_name }</td>
                    <td class="p-2">${member.member_email }</td>
                    <td class="p-2">${member.member_status }</td>
                    <td class="p-2">${member.member_regdate }</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
            <div class="box-footer mt-2">
              <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-end mb-0">
                	<c:if test="${pageMaker.prev }">
	                	<li class="page-item">
	                    	<a class="page-link" href="members?page=${pageMaker.startPage-1 }" aria-label="Previous">
	                      		<span aria-hidden="true">&laquo;</span>
	                    	</a>
	                  	</li>
	                </c:if>
	                <c:forEach var="idx" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">			
                 		<li class="page-item <c:out value="${pageMaker.cri.page == idx?'active':'' }"/>" 
                 				<c:out value="${pageMaker.cri.page == idx?'aria-current = page':'' }"/>
                 				>
                 			<a class="page-link" href="members?page=${idx }">${idx }</a>
                 		</li>
					</c:forEach>
					<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
	                  	<li class="page-item">
	                    	<a class="page-link" href="members?page=${pageMaker.endPage+1 }" aria-label="Next">
	                      		<span aria-hidden="true">&raquo;</span>
	                    	</a>
	                  	</li>
					</c:if>
                </ul>
              </nav>
            </div>
          </section>
        </div>
      </div>
    </div>

<%@ include file="../include/footer.jsp"%>