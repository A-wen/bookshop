<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.mem.model.*"%>

<jsp:useBean id="listCoupons_ByMemno" scope="request" type="java.util.Set" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<table class="table table-striped table-hover">
	<thead>
	<tr>
		<th>消費金名稱</th>
		<th>消費金內容</th>
		<th>消費金起始日</th>
		<th>消費金截止日</th>
		<th>消費金金額</th>		
	</tr>
	</thead>
	<tbody>	
	<c:forEach var="couVO" items="${listCoupons_ByMemno}" >
		<tr align='center' valign='middle'><!--將修改的那一筆加入對比色而已-->
			<td>${couVO.couname}</td>
			<td>${couVO.couexp}</td>
			<td>${couVO.coustart}</td>
			<td>${couVO.couend}</td>
			<td>${couVO.coudis}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>