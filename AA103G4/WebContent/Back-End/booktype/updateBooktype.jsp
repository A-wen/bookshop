<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="updateBooktypeVO" scope="request" class="com.booktype.model.BooktypeVO" />
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />
<jsp:useBean id="companySvc" scope="page" class="com.company.model.CompanyService" />
<script src="<%=request.getContextPath()%>/js/previewImage.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
		<%-- 錯誤表列 --%>
		<c:if test="${not empty updateMsgs}">
			<font color='red'>請修正以下錯誤:
				<ul class="h2">
					<c:forEach var="message" items="${updateMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
		<div class="col-sm-6">
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/booktype/booktype.do" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">類別編號</label> 
					<label class="control-label text-left" style="margin-left: 20px">${updateBooktypeVO.type_no}</label>
					<input type="hidden" name="type_no" value="${updateBooktypeVO.type_no}">
				</div>
				<div class="form-group">
					<label for="type_name" class="col-sm-2 control-label">類別名稱</label>
					<div class="col-sm-4">
						<input type="text" id="type_name" name="type_name" class="form-control" value="${updateBooktypeVO.type_name}">
					</div>
					<div class="col-sm-2">
						<input type="hidden" name="action" value="update_Booktype">
						<input type="submit" class="btn btn-primary" value="送出">
					</div>
				</div>
			</form>
		</div>
