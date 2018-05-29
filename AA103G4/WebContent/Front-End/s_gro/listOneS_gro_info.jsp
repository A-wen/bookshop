<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.s_gro_info.model.*"%>
<%@ page import="com.s_gro_cs.model.*"%>
<%@ page import="com.s_book.model.*"%>
<%@ page import="com.s_gro_dis.model.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%S_gro_infoVO s_gro_infoVO = (S_gro_infoVO) request.getAttribute("s_gro_infoVO");%>

<%-- 取出 對應的DeptVO物件--%>
<%
S_gro_csService s_gro_csSvc = new S_gro_csService();
S_gro_csVO s_gro_csVO = s_gro_csSvc.getOneS_gro_cs(s_gro_infoVO.getCs_no());
%>

<html>
<head>
<title>讀書會資料 - listOneS_gro_info.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 Script 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>讀書會資料 - listOneS_gro_info.jsp</h3>
		<a href="<%=request.getContextPath()%>/Front-End/s_gro_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">繼續搜尋讀書會</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>讀書會編號</th>
		<th>讀書會名稱</th>
		<th>讀書會內容介紹</th>
		<th>發起人會員編號</th>
		<th>類別編號</th>
		<th>建立時間</th>
		<th>讀書會書單編號</th>
		<th>討論文章編號</th>
		<th>讀書會狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${s_gro_infoVO.s_gro_no}</td>
		<td>${s_gro_infoVO.s_gro_name}</td>
		<td>${s_gro_infoVO.s_con}</td>
		<td>${s_gro_infoVO.mem_no}</td>
		<td>【<%=s_gro_csVO.getCs_name()%>】</td>
		<td>${s_gro_infoVO.cre_date}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_book/s_book.do" >
			   <input type="submit" value="按我查詢">
			   <input type="hidden" name="s_gro_no" value="${s_gro_infoVO.s_gro_no}">
			   <input type="hidden" name="action" value="listS_books_ByCompositeQuery">
			</FORM>
		</td>
		<td>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_dis/s_gro_dis.do" >
			    <input type="submit" value="按我查詢">
			    <input type="hidden" name="s_gro_no" value="${s_gro_infoVO.s_gro_no}">
			    <input type="hidden" name="action" value="listS_gro_diss_ByCompositeQuery">
			 </FORM>
		</td>
		<td>${s_gro_infoVO.s_gro_sta}</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="s_gro_no" value="${s_gro_infoVO.s_gro_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="s_gro_no" value="${s_gro_infoVO.s_gro_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>								
	</tr>
</table>

</body>
</html>