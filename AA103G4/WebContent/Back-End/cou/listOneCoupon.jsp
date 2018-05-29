<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%CouponVO couVO = (CouponVO) request.getAttribute("couVO");%>

<%-- 取出 對應的DeptVO物件--%>
<%
  MemService memSvc = new MemService();
  MemVO memVO = memSvc.getOneMember(couVO.getMemno());
%>

<table class="table table-striped table-hover">
	<thead>
		<tr>
		<th>優惠編號</th>
		<th>優惠名稱</th>
		<th>優惠說明</th>
		<th>優惠金額</th>
		<th>優惠起始日</th>
		<th>優惠截止日</th>
		<th>有此優惠的會員</th>
	</tr>
	</thead>
	<tbody>
	<tr>
	<tr align='center' valign='middle'>
		<td><%=couVO.getCouno()%></td>
		<td><%=couVO.getCouname()%></td>
		<td><%=couVO.getCouexp()%></td>
		<td><%=couVO.getCoudis()%></td>
		<td><%=couVO.getCoustart()%></td>
		<td><%=couVO.getCouend()%></td>
		<td><%=couVO.getMemno()%>【<%=memVO.getMem_name()%> - <%=memVO.getMem_tel()%>】</td>
	</tr>
	</tbody>
</table>
<br>
