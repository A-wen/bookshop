<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.book.model.*"%>
<jsp:useBean id="updateBookVO" scope="request" class="com.book.model.BookVO" />
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />
<jsp:useBean id="companySvc" scope="page" class="com.company.model.CompanyService" />
<script src="<%=request.getContextPath()%>/js/previewImage.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
	<div class="row" style="margin-bottom:10px">
		<div class="col-sm-12">
			<h1>修改書籍資料</h1>

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
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/book/book.do" enctype="multipart/form-data" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">書籍編號</label>
					<label class="control-label text-left" style="margin-left:20px">${updateBookVO.book_no}</label>
					<input type="hidden" name="book_no" value="${updateBookVO.book_no}">
				</div>
				<div class="form-group">
					<label for="book_name" class="col-sm-2 control-label">書名</label>
					<div class="col-sm-6">
						<input type="text" id="book_name" name="book_name" class="form-control" value="${updateBookVO.book_name}">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="book_price" class="col-sm-2 control-label">定價</label>
					<div class="col-sm-6">
						<input type="text" id="book_price" name="book_price" class="form-control" value="${updateBookVO.book_price}">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="type_no" class="col-sm-2 control-label">類型</label>
					<div class="col-sm-6">
						<select id="type_no" name="type_no" class="form-control">
						<option value="">請選擇
							<c:forEach var="booktypeVO" items="${booktypeSvc.allBooktype}">
								<option value="${booktypeVO.type_no}" ${(updateBookVO.type_no==booktypeVO.type_no)? 'selected':'' }>${booktypeVO.type_name}
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="comp_no" class="col-sm-2 control-label">出版社</label>
					<div class="col-sm-6">
						<select id="comp_no" name="comp_no" class="form-control">
						<option value="">請選擇
							<c:forEach var="companyVO" items="${companySvc.allCompany}">
								<option value="${companyVO.comp_no}" ${(updateBookVO.comp_no==companyVO.comp_no)? 'selected':'' }>${companyVO.comp_name}
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="book_qty" class="col-sm-2 control-label">庫存</label>
					<div class="col-sm-6">
						<input type="text" id="book_qty" name="book_qty" class="form-control" value="${updateBookVO.book_qty}">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="isbn" class="col-sm-2 control-label">ISBN</label>
					<div class="col-sm-6">
						<input type="text" id="isbn" name="isbn" class="form-control" value="${updateBookVO.isbn}">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="book_author" class="col-sm-2 control-label">作者</label>
					<div class="col-sm-6">
						<input type="text" id="book_author" name="book_author" class="form-control" value="${updateBookVO.book_author}">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="saleable" class="col-sm-2 control-label">上下架</label>
						<div class="col-sm-6">
							<select id="saleable" name="saleable" class="form-control">
								<option value="1" ${(updateBookVO.saleable==1)? 'selected':'' }>上架
								<option value="0" ${(updateBookVO.saleable==0)? 'selected':'' }>下架
							</select>
						</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="book_pic" class="col-sm-2 control-label">書籍圖片</label>
					<div class="col-sm-6">
						<img src="<%=request.getContextPath()%>/img?book_no=${updateBookVO.book_no}" width="100" height="100"> <input type="file"
							id="book_pic" name="book_pic" class="upfile">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="book_desc" class="col-sm-2 control-label">書籍描述</label>
					<div class="col-sm-6">
						<textarea class="form-control" id="book_desc" name="book_desc" rows="5">${updateBookVO.book_desc}</textarea>
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="col-sm-2"></div>
				<div class="col-sm-5 text-left">
					<input type="hidden" name="action" value="update_Book">
					<input type="submit" class="btn btn-primary" value="送出">
				</div>
			</form>
		</div>
	</div>
