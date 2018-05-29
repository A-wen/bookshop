<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.cm.model.*"%>
<%@ page import="java.io.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    CmService cmSvc = new CmService();
	List<CmVO> list = cmSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="CmService" scope="page" class="com.cm.model.CmService" />
<table class="table table-striped table-hover">
  <thead>
    <tr>
		<th>編號</th>
		<th>廣告名稱</th>		
		<th>開始日期</th>
		<th>結束日期</th>
		<th>廣告照片</th>	
		<th>點擊次數</th>	
		<th>廣告廠商</th>
		<th>廣告網站</th>		
	</tr>
	</thead>
  <tbody>	
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="cmVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(cmVO.cmNo==param.cmNo) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${cmVO.cmNo}</td>
			<td>${cmVO.cmName}</td>
			<td>${cmVO.cmStart}</td>
			<td>${cmVO.cmEnd}</td>			
			<td><img src="<%=request.getContextPath()%>/cm/CMReader.do?cm_no=${cmVO.cmNo}"  width="200px" height="100px"></td>	
			<td>${cmVO.cmTh}</td>
			<td>${cmVO.cminv}</td>
			<td>${cmVO.cmUrl}</td>
		
			
					
	
          
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/cm/cm.do">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="cm_no" value="${cmVO.cmNo}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/cm/cm.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="cm_no" value="${cmVO.cmNo}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<%@ include file="pages/page2.file" %>

