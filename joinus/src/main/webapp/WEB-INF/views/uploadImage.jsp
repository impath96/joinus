<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${savedFileName }
<div class="col-sm-6">
   <div class="card text-center">
      <div class="card-header">
         프로필 이미지
      </div>
      <div id="current-profile-image" class="mt-3">
<%--          <img th:if="${!#strings.isEmpty(profile.profileImage)}" class="rounded"
            th:src="${profile.profileImage}"
            width="125" height="125" alt="name" th:alt="${account.nickname}"/> --%>
      </div>
      <div id="new-profile-image" class="mt-3"></div>
      <div class="card-body">
         <div class="custom-file">
            <input type="file" class="custom-file-input" id="profile-image-file">
            <label class="custom-file-label" for="profile-image-file">프로필 이미지 변경</label>
         </div>
         <div id="new-profile-image-control" class="mt-3">
            <button class="btn btn-outline-primary btn-block" id="cut-button">자르기</button>
            <button class="btn btn-outline-success btn-block" id="confirm-button">확인</button>
            <button class="btn btn-outline-warning btn-block" id="reset-button">취소</button>
         </div>
            <div id="cropped-new-profile-image" class="mt-3"></div>
         </div>
   </div>
</div>
</body>
</html>