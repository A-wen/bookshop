<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.cm.model.*"%>

<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller InformationService.java已存入request的InformationVO物件--%>
 <%CmVO cmVO = (CmVO) request.getAttribute("cmVO");%>

<%-- 取出 對應的InformationVO物件--%>

<table class="table table-striped table-hover">
	<thead>
		<tr>
		<th>廣告編號</th>
		<th>廣告名稱</th>		
		<th>廣告開始日期</th>
		<th>廣告結束日期</th>
		<th>廣告照片</th>	
		<th>廣告點擊次數</th>	
		<th>廣告廠商</th>
		<th>廣告網站</th>		
	</tr>
	</thead>
	<tbody>
	<tr>
		  	<td>${cmVO.cmNo}</td>
			<td>${cmVO.cmName}</td>
			<td>${cmVO.cmStart}</td>
			<td>${cmVO.cmEnd}</td>
			<td><img src="<%=request.getContextPath()%>/cm/CMReader.do?cm_no=${cmVO.cmNo}"  width="200px" height="100px"></td>			
			<td>${cmVO.cmTh}</td>
			<td>${cmVO.cminv}</td>
			<td>${cmVO.cmUrl}</td>		
	</tr>
	</tbody>
</table>
<br>
