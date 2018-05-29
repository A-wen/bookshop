<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.book.model.*" %>
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />
<jsp:useBean id="companySvc" scope="page" class="com.company.model.CompanyService" />

<div class="row">
	<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
	<div class="col-sm-10">
		<form METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/book/book.do" class="form-horizontal">
			<div class="col-sm-10">
				<div class="form-group">
					<div class="col-sm-3">
						<label for="book_name" class="control-label">書名:</label>
						<input type="text" id="book_name" name="book_name" class="form-control" value="${querybookVO.book_name}">
					</div>
					<div class="col-sm-3">
						<label for="book_author" class="control-label">作者:</label>
						<input type="text" id="book_author" name="book_author" class="form-control" value="${querybookVO.book_author}">
					</div>
					<div class="col-sm-3">
						<label for="type_no" class="control-label">類型:</label>
						<select id="type_no" name="type_no" class="form-control">
							<option value="">請選擇
							<c:forEach var="booktypeVO" items="${booktypeSvc.allBooktype}">
								<option value="${booktypeVO.type_no}" ${querybookVO.type_no==booktypeVO.type_no? 'selected' : ''}>${booktypeVO.type_name}
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-3">
						<label for="comp_no" class="control-label">出版社:</label>
						<select id="comp_no" name="comp_no" class="form-control">
							<option value="">請選擇
							<c:forEach var="companyVO" items="${companySvc.allCompany}">
								<option value="${companyVO.comp_no}" ${querybookVO.comp_no==companyVO.comp_no? 'selected' : ''}>${companyVO.comp_name}
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="col-sm-2">
				<label for="go" class="control-label fontwhite">送出查詢</label>
				<input type="submit" id="go" class="btn btn-primary form-control" value="查詢">
				<input type="hidden" name="action" value="listBooks_ByCompositeQuery">
				<input type="hidden" name="from" value="Back_End">
			</div>
		</form>
	</div>
	<div class="col-sm-2">
		<form METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/book/book.do" class="form-horizontal">
			<label for="goAll" class="control-label fontwhite">查詢全部</label>
			<input type="hidden" name="action" value="listBooks_ByCompositeQuery">
			<input type="hidden" name="book_name" value="">
			<input type="hidden" name="book_author" value="">
			<input type="hidden" name="type_no" value="">
			<input type="hidden" name="comp_no" value="">
			<input type="hidden" name="from" value="Back_End">
			<input type="submit" id="goAll" class="btn btn-primary form-control" value="查詢全部書籍" />
		</form>
	</div>
</div>
<div class="text-center">
	<%-- 錯誤表列 --%>
	<c:if test="${not empty queryMsgs}">
		<div class="h2">
			<font color='red'>
					<c:forEach var="message" items="${queryMsgs}">
						${message}
					</c:forEach>
			</font>
		</div>
	</c:if>
</div>	
<div id="resultPage">
	<%
		if (request.getAttribute("listBooks_ByCompositeQuery") != null) {
	%>
	<jsp:include page="listBooks.jsp" />
	<%
		}
	%>
</div>