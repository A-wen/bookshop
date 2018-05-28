<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.ca.model.*"%>
<%@ page import="com.book.model.*"%>
<%@ page import="com.booktype.model.*"%>
<%@ page import="java.io.*"%>
<%ClassifiedAdsVO caVO = (ClassifiedAdsVO) request.getAttribute("caVO");%>
<jsp:useBean id="bookSvc" scope="page" class="com.book.model.BookService" />
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />

<table class="table table-striped table-hover">
  <thead>
    <tr>
		<th>廣告編號</th>
		<th>書籍類別</th>
		<th>商品</th>					
		<th>廣告開始日期</th>
		<th>廣告結束日期</th>
		<th>點擊次數</th>	
		<th>廣告名稱 </th>	
		<th>廣告圖示</th>				
	</tr>
	</thead>
	  <tbody>
	  <tr>
	  		<td>${caVO.cano}</td>
			<td>
			<c:forEach var="booktypeVO" items="${booktypeSvc.allBooktype}">
			<c:if test="${caVO.typeno==booktypeVO.type_no}">
			${caVO.typeno}-${booktypeVO.type_name}
			</c:if>
			</c:forEach>
			</td>
			<td>
			<c:forEach var="bookVO" items="${bookSvc.allBook}">
			<c:if test="${caVO.bookno==bookVO.book_no}">
			${caVO.bookno}-${bookVO.book_name}
			</c:if>
			</c:forEach>
			</td>
			<td>${caVO.castart}</td>
			<td>${caVO.caend}</td>				
			<td>${caVO.cath}</td>
			<td>${caVO.caname}</td>			
			<td>
			<c:forEach var="bookVO" items="${bookSvc.allBook}">
			<c:if test="${bookVO.book_no==caVO.bookno}">
			<img src="<%=request.getContextPath()%>/Back-End/img?book_no=${caVO.bookno}"  width="200px" height="100px">
			</c:if>
			</c:forEach>
			</td>	
	 </tr>
	</tbody>
  </table>			