<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.info.model.*"%>


<%
    InformationService infoSvc = new InformationService();
	List<InformationVO> list = infoSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="InformationService" scope="page" class="com.info.model.InformationService" />

<table class="table table-striped table-hover">
  <thead>
    <tr>
		<th>公告編號</th>
		<th>公告標題</th>		
		<th>公告日期</th>
		<th>公告內容</th>
	</tr>
	</thead>
  <tbody>	
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="infoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(infoVO.infono==param.infono) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${infoVO.infono}</td>
			<td>${infoVO.infotitle}</td>
			<td>${infoVO.infoterm}</td>
			<td>${infoVO.infoexp}</td>
					
	
          
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/info/info.do">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="info_no" value="${infoVO.infono}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/info/info.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="info_no" value="${infoVO.infono}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<%@ include file="pages/page2.file" %>

