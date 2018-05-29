<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.ca.model.*"%>
<%@ page import="com.book.model.*"%>
<%@ page import="com.booktype.model.*"%>
<%@ page import="java.io.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
	ClassifiedAdsService caSvc = new ClassifiedAdsService();
	List<ClassifiedAdsVO> list = caSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="ClassifiedAdsService" scope="page" class="com.ca.model.ClassifiedAdsService" />
<jsp:useBean id="bookSvc" scope="page" class="com.book.model.BookService" />
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />
<table class="table table-striped table-hover">
  <thead>
    <tr>
		<th>廣告編號</th>
		<th>書籍類別</th>
		<th>商品編號</th>					
		<th>開始日期</th>
		<th>結束日期</th>
		<th>點擊次數</th>	
		<th>廣告名稱 </th>	
		<th>廣告圖示</th>				
	</tr>
  </thead>
  <tbody>	
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="caVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(caVO.cano==param.cano) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${caVO.cano}</td>
			<td>
			<c:forEach var="booktypeVO" items="${booktypeSvc.allBooktype}">
			<c:if test="${caVO.typeno==booktypeVO.type_no}">
			${caVO.typeno}${booktypeVO.type_name}
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
          
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/ca/ca.do">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="ca_no" value="${caVO.cano}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/ca/ca.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="ca_no" value="${caVO.cano}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<%@ include file="pages/page2.file" %>

