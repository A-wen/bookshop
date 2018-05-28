<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.info.model.*"%>

<script src="<%=request.getContextPath()%>/js/previewImage.js"></script> 

<%
	InformationVO infoVO = (InformationVO) request.getAttribute("infoVO");
%>
<div class="row">
	<div class="col-sm-12">
		<h1>新增公告</h1>
		<hr>
		<form class="form-horizontal" METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/info/info.do">
			<!-- 標題 -->
			<div class="form-group">
				<label for="info_title" class="col-sm-2 control-label">公告標題</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="info_title"	name="info_title" value="" />
				</div>
				<div class="col-sm-4"></div>
			</div>
			<!-- 內容 -->
			<div class="form-group">
				<label for="info_exp" class="col-sm-2 control-label">公告內容</label>
				<div class="col-sm-6">
					<textarea name="info_exp" id="info_exp" rows="10" class="form-control"></textarea>
					<div class="col-sm-4"></div>
				</div>
			</div>
			<!-- 時間 -->
			<div class="form-group">
				<label for="info_term" class="col-sm-2 control-label">公告時間</label>
				<div class="col-sm-6">
					<%
						java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
					%>
					<input type="text" class="form-control" id="info_term"	name="info_term" value="" />
				</div>
				<div class="col-sm-4"></div>
			</div>
			

			<div>
				<input type="hidden" name="action" value="insert"> 
				<input type="submit" class="btn btn-primary" value="送出">
				<button type="button" class="btn btn-primary" id="wow">顯靈</button>
			</div>
		</form>
	</div>
</div>
<script>
$(document).ready(function(){
		$('#wow').click(function(){
		$('input#info_title').val("伺服器維修");			
		$('textarea#info_exp').val("進行維修時，您將暫時無法登入。不便之處，敬請見諒");
		$('input#info_term').val("2016-09-29");	
		})
	})
</script>



