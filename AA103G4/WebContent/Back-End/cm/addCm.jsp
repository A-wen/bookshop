<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cm.model.*"%>

<%
	CmVO cmVO = (CmVO) request.getAttribute("cmVO");
%>
<div class="row">
	<div class="col-sm-12">
		<h1>新增廣告</h1>
		<hr>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/cm/cm.do" name="form1"	enctype="multipart/form-data"  class="form-horizontal">
			<div class="form-group">
				<label for="cm_name" class="col-sm-2 control-label">廣告名稱:</label>
				<div class="col-sm-6">
					<input type="text" id="cm_name" name="cm_name" class="form-control"	value=""/>
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="cm_th" class="col-sm-2 control-label">點擊次數:</label>
				<div class="col-sm-6">
					<input type="text" id="cm_th" name="cm_th" class="form-control"	value=""/>
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="cm_inv" class="col-sm-2 control-label">廠商:</label>
				<div class="col-sm-6">
					<input type="text" id="cm_inv" name="cm_inv" class="form-control" value=""/>
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="cm_url" class="col-sm-2 control-label">網址:</label>
				<div class="col-sm-6">
					<input type="text" id="cm_url" name="cm_url" class="form-control" value=""/>
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="cm_pic" class="col-sm-2 control-label">圖片:</label>
				<div class="col-sm-6">
					<input type="file" id="cm_pic" name="cm_pic" class="upfile" size="45" value=""/>
				</div>
				<div class="col-sm-4"></div>
			</div>			
				<div class="form-group">			    
				<label for="cm_start" class="col-sm-2 control-label">開始時間:</label>
				<div class="col-sm-6">
					<input type="text" id="cm_start" name="cm_start" class="form-control" value="" /> 
				</div>
				<div class="col-sm-4"></div>
			</div>
				<div class="form-group">			
				<label for="cm_end" class="col-sm-2 control-label">結束時間:</label>
				<div class="col-sm-6">
					<input type="text" id="cm_end" name="cm_end" class="form-control" value="" /> 
				</div>
				<div class="col-sm-4"></div>
			</div>			
			<div>
				<input type="hidden" name="action" value="insert">
				<input type="submit" class="btn btn-primary" value="送出">
				<button type="button" class="btn-primary" id="wow">顯靈</button>
			</div>
		</Form>
	</div>
</div>
<script src="<%=request.getContextPath()%>/js/previewImage.js"></script>

<script>
	$(document).ready(function(){
		$('#wow').click(function(){
		$('input#cm_name').val("tibame學習網");			
		$('input#cm_th').val("20");			
		$('input#cm_inv').val("tibame");			
		$('input#cm_url').val("http://www.tibame.com/q?pg=home_welcome&cp=20000");			
		$('input#cm_start').val("2017-09-29");			
		$('input#cm_end').val("2017-09-29");			
		})
	})
	</script>


