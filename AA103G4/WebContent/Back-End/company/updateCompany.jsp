<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="updatecompanyVO" scope="request" class="com.company.model.CompanyVO" />
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
		<hr>
		<div class="col-sm-6">
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/company/company.do" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">出版社編號</label>
					<label class="control-label text-left" style="margin-left:20px">${updatecompanyVO.comp_no}</label>
					<input type="hidden" name="comp_no" value="${updatecompanyVO.comp_no}">
				</div>
				<div class="form-group">
					<label for="comp_name" class="col-sm-2 control-label">出版社名稱</label>
					<div class="col-sm-6">
						<input type="text" id="comp_name" name="comp_name" class="form-control" value="${updatecompanyVO.comp_name}">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="comp_tel" class="col-sm-2 control-label">出版社電話</label>
					<div class="col-sm-6">
						<input type="text" id="comp_tel" name="comp_tel" class="form-control" value="${updatecompanyVO.comp_tel}">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="comp_add" class="col-sm-2 control-label">出版社地址</label>
					<div class="col-sm-6">
						<input type="text" id="comp_add" name="comp_add" class="form-control" value="${updatecompanyVO.comp_add}" />
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="comp_number" class="col-sm-2 control-label">出版社統編</label>
					<div class="col-sm-6">
						<input type="text" id="comp_number" name="comp_number" class="form-control" value="${updatecompanyVO.comp_number}" />
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="comp_contact" class="col-sm-2 control-label">聯絡人</label>
					<div class="col-sm-6">
						<input type="text" id="comp_contact" name="comp_contact" class="form-control" value="${updatecompanyVO.comp_contact}" />
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="comp_email" class="col-sm-2 control-label">聯絡人E-mail</label>
					<div class="col-sm-6">
						<input type="text" id="comp_email" name="comp_email" class="form-control" value="${updatecompanyVO.comp_email}">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="col-sm-2"></div>
				<div>
					<input type="hidden" name="action" value="insert_Company">
					<input type="submit" class="btn btn-primary" value="送出">
				</div>
			</form>
		</div>
