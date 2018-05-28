<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.booktype.model.*"%>
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />
<jsp:useBean id="companySvc" scope="page" class="com.company.model.CompanyService" />
<script src="<%=request.getContextPath()%>/js/previewImage.js"></script>
<%
	BooktypeVO insertbooktypeVO = (BooktypeVO) request.getAttribute("insertbooktypeVO");
%>
	<div class="row">
		<div class="col-sm-12">
<%-- 錯誤表列 --%>
	<c:if test="${not empty addMsgs}">
		<div class="h4">
			<font color='red'>
					<c:forEach var="message" items="${addMsgs}">
						${message}
					</c:forEach>
			</font>
		</div>
	</c:if>
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/booktype/booktype.do" class="form-horizontal">
				<div class="form-group">
					<div class="col-sm-4">
						<div class="col-sm-3">
							<label for="book_name" class="control-label">類別名稱</label>
						</div>
						<div class="col-sm-7">
							<input type="text" id="book_name" name="type_name" class="form-control" value="${insertbooktypeVO!=null?insertbooktypeVO.type_name:''}">
						</div>
						<div class="col-sm-1">
							<input type="hidden" name="action" value="insert_Booktype">
							<input type="submit" class="btn btn-primary" value="送出">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
