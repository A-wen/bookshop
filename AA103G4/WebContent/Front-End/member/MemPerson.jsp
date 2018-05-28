<%@page import="com.track.model.TrackVO"%>
<%@page import="com.track.model.TrackService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>
<jsp:useBean id="memService" scope="session" class="com.mem.model.MemService" />
<jsp:useBean id="bookService" scope="page" class="com.book.model.BookService" />
<jsp:useBean id="bookVO" scope="page" class="com.book.model.BookVO" />
<style type="text/css">
.mg {
	margin: 10px;
}

.startCss {
	vertical-align: middle;
	white-space: nowrap;
}
</style>
<head>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />	
<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Book思議 | 會員個人資料</title>

</head>
<body>
	<jsp:include page="/Front-End/header.jsp" />
	<div class="container-fluid">
		<div class="row" style="margin: 0px auto">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<div class="panel">
					<div class="panel-heading" style="background-color:#D9D9D9">
						<h3 class="panel-title">
							<img
								src="<%=request.getContextPath()%>/member/ReaderPhoto.do?mem_no=${memVO.mem_no}"
								width="150" height="70" border="0" class="img-thumbnail">
							<h4><i class="glyphicon glyphicon-user"></i><b>Book41會員:${memVO.mem_name}<p>個人基本資料</b></h4>
						</h3>
					</div>
				</div>
				<div>
					<!-- Modal -->
					<div id="myModal2" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">${memVO.mem_name}您好，以下為修改欄位</h4>
								</div>
								<div class="modal-body">
									<form name="form1"
										action="<%=request.getContextPath()%>/member/MemServlet.do"
										method="post" enctype="multipart/form-data"
										class="form-horizontal">
										<div class="col-sm-12">
											<input type="hidden" name="mem_no"
												value="${memVO.mem_no}">
											<input type="hidden" name="mem_name" value="${memVO.mem_name}">
											<input type="hidden" name="mem_nic" value="${memVO.mem_nic}">
											<input type="hidden" name="mem_mail" value="${memVO.mem_mail}">
										</div>

										<label for="phoneNumber" class="control-label">修改電話</label> 
										<input type="text" name="mem_tel" id="phoneNumber"											
											   class="form-control" placeholder="請輸入想要修改的電話"
											   value="<%=((MemVO) (request.getAttribute("memVO")) == null) ? "": ((MemVO) request.getAttribute("memVO")).getMem_tel()%>"> 
										<label for="passWord"
											class="control-label">修改密碼</label> <input type="password"
											name="mem_psw" id="passWord" class="form-control"
											value="${(memVO==null)?memVO.memPsw:param.memPsw}"
											onblur="checkpsd1()" placeholder="請輸入想要修改的密碼"> <label
											for="passWord2" class="control-label">確認修改密碼</label> <input
											type="password" name="mem_psw2" id="passWord2"
											class="form-control" onblur="checkpsd2()"
											placeholder="請重新輸入修改的密碼"> <label for="memPhoto"
											class="control-label">更換大頭貼</label> <input type="file"
											name="mem_photo" id="memPhoto" class="form-control">
										<br>
										<button class="btn btn-info">
											<i class="glyphicon glyphicon-ok-sign" align='center'></i>送出修改
										</button>
										<button type="button" class="btn btn-info magic">神奇小按鈕</button>
										<input type="hidden" name="action" value="updateMember">
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">關閉</button>
								</div>
								</form>
							</div>

						</div>

					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" style="background-color:#D9D9D9">
						<ul class="list-group">
							<div class="input-group input-group-lg">
								<span class="input-group-addon"> <span
									class="glyphicon glyphicon-earphone"></span>
								</span>
								<li class="list-group-item"><font color='#5A5AAD'><b><h4>電話:</h4></font>
									<h3>${memVO.mem_tel}</h3></b></li>
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-addon"> <span
									class="glyphicon glyphicon-envelope"></span>
								</span>
								<li class="list-group-item"><font color='#5A5AAD'><b><h4>帳號:</h4></font>
									<h3>${memVO.mem_mail}</h3></b></li>
							</div>
							<div class="input-group input-group-lg">
						<span class="input-group-addon"> 
						<span class="glyphicon glyphicon-user"></span>
						</span>
							<li class="list-group-item"><font color='#5A5AAD'><b><h4>暱稱:</h4></font><h3>${memVO.mem_nic}</h3></b></li>
							</div>
						</ul>
						<button type="button" class="btn btn-info" data-toggle="modal"
							data-target="#myModal2">
							<i class="glyphicon glyphicon-pencil"></i>更新您的資料
						</button>
					</div>
				</div>
			</div>
			<div class="col-sm-4"></div>
		</div>
	</div>
	<div style="position: fixed; bottom: 0; left: 0; right: 0">
		<jsp:include page="/Front-End/footer.jsp"></jsp:include>
	</div>
</body>
<script>
$(document).ready(function(){
	$('.magic').click(function(){
		$('input[name="mem_tel"]').val("0956181155");	
		$('input[name="mem_psw"]').val("a123456");	
		$('input[name="mem_psw2"]').val("a123456");	
	})
})
</script>
</html>