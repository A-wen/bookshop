<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	
	<c:forEach var="group" items="${groupList}">
		<tr align='center' valign='middle'>
		<td>${group.s_gro_no}</td>
		<td>${group.s_gro_name}</td>
		<td>${group.s_con}</td>
		<td>${group.mem_no}</td>
		<c:forEach var="category" items="${csList}">
			<c:if test="${category.cs_no==group.cs_no}" >
				<td>【${category.cs_name}】</td>
			</c:if>
		</c:forEach>
		
		<td>${group.cre_date}</td>
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
		<td>${group.s_gro_sta}</td>
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
	</c:forEach>

</table>

</body>
</html>