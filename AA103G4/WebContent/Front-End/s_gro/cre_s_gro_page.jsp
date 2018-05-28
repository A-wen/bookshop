<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.s_gro_info.model.*"%>
<%
S_gro_infoVO s_gro_infoVO = (S_gro_infoVO) request.getAttribute("s_gro_infoVO");
%>

<html>
	<head>
		<meta charset="utf-8">
		<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
		<title>建立讀書會</title>
<%-- 		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/calendar.css"> --%>
<%-- 		<script language="JavaScript" src="<%=request.getContextPath()%>/js/calendarcode.js"></script> --%>
<!-- 		<div id="popupcalendar" class="text"></div> -->
		<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/flat-ui.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/pgcolony.css" rel="stylesheet">  	
    	
<body>
	<header>
        
    </header>   

<h3>新增讀書會:</h3>

<section id="body-section">
        <div id="main" class="body-height-min">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/s_gro_info/s_gro_info.do" name="form1">
			<table border="0">
			<div class="container">
				<tr>
					<td><label for="name"><span class="requiredLabel">※</span>讀書會名稱</label></td>	
					<td><input type="TEXT" name="s_gro_name" size="45" 
						value="<%= (s_gro_infoVO==null)? "Git" : s_gro_infoVO.getS_gro_name()%>" /></td>
				</tr>
				<tr>
					<td><label for="body"><span class="requiredLabel">※</span>讀書會簡介</label></td>
					<td><textarea type="TEXT" name="s_con" value="<%= (s_gro_infoVO==null)? "例：這是一個有關Web網頁開發的讀書會。想跟大家一起學習分享。有興趣的朋友可以加入喔!!" : s_gro_infoVO.getS_con()%>" rows="5">
						</textarea></td>
				</tr><br>
				<tr>
					<td><label for="inputTagList"><span class="requiredLabel">※</span>發起人會員編號</label></td>
					<td><input type="TEXT" name="mem_no" size="45"
						value="<%= (s_gro_infoVO==null)? "105" : s_gro_infoVO.getMem_no()%>" /></td>
				</tr>
				
				<jsp:useBean id="s_gro_csSvc" scope="page" class="com.s_gro_cs.model.S_gro_csService" />
				<tr>
					<td><label for="inputTagList"><span class="requiredLabel">※</span>類型</label></td>
					<td><select size="1" name="cs_no">
						<c:forEach var="s_gro_csVO" items="${s_gro_csSvc.all}">
							<option value="${s_gro_csVO.cs_no}" ${(s_gro_infoVO.cs_no==s_gro_csVO.cs_no)? 'selected':'' } >${s_gro_csVO.cs_name}
						</c:forEach>
						</select>
						<a href="<%=request.getContextPath()%>/Front-End/s_gro/addS_gro_cs.jsp"><label for="inputTagList"><span class="requiredLabel">※</span>新增類型</label></a>
					</td>
				</tr>
				
				<tr>
					<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
<!-- 					<td><label for="inputTagList"><span class="requiredLabel">※</span>建立日期</label></td> -->
<!-- 					<td bgcolor="#CCCCFF"> -->
					    <input class="cal-TextBox"
						onFocus="this.blur()" size="9" readonly type="hidden" name="cre_date" value="<%= (s_gro_infoVO==null)? date_SQL : s_gro_infoVO.getCre_date()%>">
<!-- 						<a class="so-BtnLink" -->
<!-- 						href="javascript:calClick();return false;" -->
<!-- 						onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 						onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 						onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','cre_date','BTN_date');return false;"> -->
<%-- 					    <img align="middle" border="0" name="BTN_date"	src="<%=request.getContextPath()%>/Front-End/img/btn_date_up.gif" width="22" height="17" alt="開始日期"></a> --%>
<!-- 					</td> -->
				</tr>
				<tr>
					<td><input type="hidden" name="s_gro_sta" size="45"
						value="正常" /></td>
				</tr>
			
			</table>
			<br>
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增">
			</div>
			</FORM>
		</div>
    </section>
    <footer>
        
    </footer>
</body>
</html>