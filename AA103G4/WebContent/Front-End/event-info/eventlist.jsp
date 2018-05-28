<%-- JSP設定 --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>


<%-- HTML內容開始 --%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">    
	    <title>Book思議  | 讀書會活動編輯清單</title>
	    <link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />  
	    <!-- CSS -->    
	    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
	    <link id="switcher" href="<%=request.getContextPath()%>/css/searech-theme.css" rel="stylesheet">
	    <link href="<%=request.getContextPath()%>/css/searchstyle.css" rel="stylesheet">
	    <link href="<%=request.getContextPath()%>/css/font-awesome.css" rel="stylesheet">
	    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css">
	    <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
	    <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'> 
	    <link href="<%=request.getContextPath()%>/css/all.css"	rel="stylesheet">
	</head>
	<body>
		<jsp:include page="/Front-End/header.jsp" />	
		<!-- 內容區塊 -->
		<div class="col-lg-10 col-lg-offset-1" style="margin-bottom:80px">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>活動編號</th>
						<th>讀書會名稱</th>
						<th>活動名稱</th>
						<th>活動狀態</th>
						<th>活動簡介</th>
						<th>活動時間</th>
						<th>活動位置</th>
						<th>詳細地址</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<!-- JSTL迭代取物件，然後用EL印出物件屬性 -->
					<c:forEach var="eventVO" items="${events}" begin="0" end="57">
					<tr>
						<td><a href="event/${eventVO.e_No}">${eventVO.e_No}</a></td>
						<td>${eventVO.s_gro_info.s_gro_name}</td>
						<td>${eventVO.e_Name}</td>
						<td>${eventVO.event_Status.s_Exp}</td>
						<td>${eventVO.e_Intro}</td>
						<td><fmt:formatDate type="both" pattern="MM-dd HH:mm" value="${eventVO.e_Date}" /></td>
						<td>${eventVO.e_Loc}</td>
						<td>${eventVO.e_Addr}</td>
						<td>
							<form method="post" action="edit">
								<input type="hidden" name="e_No" value="${eventVO.e_No}">
								<button id="uploadBtn" type="submit" class="btn btn-primary" name="action" value="editEvent">
								<i class="glyphicon glyphicon-cloud-upload" onclick="$('#fileInput').click();"></i>  修改活動資訊
							</button>
							</form>
						</td>
					</tr>
					</c:forEach>					
				</tbody>
			</table>
			
		</div>
		
		<jsp:include page="/Front-End/footer.jsp" />
		<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
  		<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	</body>
</body>
</html>