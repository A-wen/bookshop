<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ca.model.*"%>
<%@ page import="com.book.model.*"%>
<%@ page import="com.booktype.model.*"%>
<%
	ClassifiedAdsVO caVO = (ClassifiedAdsVO) request.getAttribute("caVO");
%>
<jsp:useBean id="caSvc" scope="page" class="com.ca.model.ClassifiedAdsService" />
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />
<jsp:useBean id="bookSvc" scope="page" class="com.book.model.BookService" />
<div class="row">
	<div class="col-sm-12">
		<h1>新增推薦分類廣告</h1>
		<hr>
		<FORM class="form-horizontal" METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/ca/ca.do" name="form1">
			<div class="form-group">
				<label for="ca_name" class="col-sm-2 control-label">廣告名稱:</label>
				<div class="col-sm-6">
					<input type="text" id="ca_name" name="ca_name" class="form-control"
						value="<%=(caVO == null) ? "100" : caVO.getCaname()%>" />
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="type_no" class="col-sm-2 control-label">商品類型:</label>
				<div class="col-sm-6">
					<select id="type_no" name="type_no" class="form-control">
						<option value="">請選擇</option>
							<c:forEach var="booktypeVO" items="${booktypeSvc.allBooktype}">
								<option value="${caVO.type_no}"
									${(caVO.typeno==booktypeVO.type_no)? 'selected':'' }>
									${booktypeVO.type_no}-${booktypeVO.type_name}
								</option>
							</c:forEach>
					</select>
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="book_no" class="col-sm-2 control-label">商品編號:</label>
				<div class="col-sm-6">
					<select id="book_no" name="book_no" class="form-control">
						<option value="">請選擇</option>
							<c:forEach var="bookVO" items="${bookSvc.allBook}">
								<option value="${caVO.book_no}"
									${(caVO.bookno==bookVO.book_no)? 'selected':'' }>
									${bookVO.type_no}-${bookVO.book_no}-${bookVO.book_name}
								</option>
							</c:forEach>
					</select>
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			
			<div class="form-group">
			<%
					java.sql.Date date_SQLs = new java.sql.Date(System.currentTimeMillis());
			%>
				<label for="ca_start" class="col-sm-2 control-label">開始時間:</label>
				<div class="col-sm-6">
					<input type="text" id="ca_start" name="ca_start" class="form-control"
						value="<%=(caVO == null) ? date_SQLs : caVO.getCastart()%>" /> 
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
			<%
					java.sql.Date date_SQLe = new java.sql.Date(System.currentTimeMillis());
				%>
				<label for="ca_end" class="col-sm-2 control-label">結束時間:</label>
				<div class="col-sm-6">
					<input type="text" id="ca_end" name="ca_end" class="form-control"
						value="<%=(caVO == null) ? date_SQLe : caVO.getCaend()%>" /> 
				</div>
				<div class="col-sm-4"></div>
			</div>
			



			<div>
				<input type="hidden" name="action" value="insert">
				<input type="submit" class="btn btn-primary" value="送出">
			</div>
		</Form>
	</div>
</div>

 <script src="<%=request.getContextPath()%>/js/previewImage.js"></script> 

