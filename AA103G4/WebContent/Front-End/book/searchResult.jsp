<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="com.book.model.*"%> --%>
<%@ page import="java.util.*"%>
<jsp:useBean id="bookSvc" scope="page" class="com.book.model.BookService" />
<jsp:useBean id="bookList" scope="request" type="java.util.List" />
<jsp:useBean id="querybookVO" scope="session" class="com.book.model.BookVO" />
<div class="text-center" height="50">
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
</div>
<c:forEach var="bookVO" items="${bookList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<div class="row">
		<div class="col-xs-12 col-sm-1">
			<a href="bookpage/${bookVO.book_no}">
			<img src="<%=request.getContextPath()%>/img?book_no=${bookVO.book_no}" style="width: 200%">
			</a>
		</div>
		<div class="col-xs-12 col-sm-8 h3">
			<div class="row">
				<div class="col-sm-3 text-right">書名：</div>
				<div class="col-sm-9"><a href="bookpage/${bookVO.book_no}" >${bookVO.book_name}</a></div>
			</div>
			<div class="row" style="margin-top: 20px">
				<div class="col-sm-3 text-right">定價：</div>
				<div class="col-sm-9">${bookVO.book_price}元</div>
			</div>
			<div class="row" style="margin-top: 20px">
				<div class="col-sm-3 text-right">作者：</div>
				<div class="col-sm-9">${bookVO.book_author}</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-2 text-left">
			<div>
				<input type="hidden" class="mem_no" name="mem_no" value="${memVO.mem_no}">
				<input type="hidden" class="book_no" name="book_no" value="${bookVO.book_no}">
				<button class="btn-primary img-rounded addtrack h3" style="width:90%">
					<i class="glyphicon glyphicon-heart"></i><b>加入追蹤</b>
				</button>
			</div>
			<div>
				<input type="hidden" class="item" name="item" value="${bookVO.book_no}:1">
				<button class="btn-primary img-rounded addcart h3" style="width:90%">
					<i class="glyphicon glyphicon-ok-sign"></i><b>放入購物車</b>
				</button>
			</div>
		</div>
	</div>
	<hr>
</c:forEach>
<div class="text-center">
	<%@ include file="pages/page2_ByCompositeQuery.file" %>
</div>