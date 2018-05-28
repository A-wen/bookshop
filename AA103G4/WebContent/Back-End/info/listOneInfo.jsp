<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.info.model.*"%>

<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller InformationService.java已存入request的InformationVO物件--%>
<%
	InformationVO infoVO = (InformationVO) request.getAttribute("infoVO");
%>

<%-- 取出 對應的InformationVO物件--%>


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
		<tr>
		<td><%=infoVO.getInfono()%></td>
		<td><%=infoVO.getInfotitle()%></td>
		<td><%=infoVO.getInfoterm()%></td>
		<td><%=infoVO.getInfoexp()%></td>
		</tr>
	</tbody>
</table>
<br>


