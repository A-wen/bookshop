<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.company.model.*"%>
<%
	CompanyVO insertcompanyVO = (CompanyVO) request.getAttribute("insertcompanyVO");
%>
	<div class="row">
		<div class="col-sm-12">

<%-- 錯誤表列 --%>
<c:if test="${not empty addMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul class="h5">
		<c:forEach var="message" items="${addMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/company/company.do" class="form-horizontal">
				<div class="form-group">
					<label for="comp_name" class="col-sm-2 control-label">出版社名稱</label>
					<div class="col-sm-6">
						<input type="text" id="comp_name" name="comp_name" class="form-control" value="">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="comp_tel" class="col-sm-2 control-label">出版社電話</label>
					<div class="col-sm-6">
						<input type="text" id="comp_tel" name="comp_tel" class="form-control" value="">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="comp_add" class="col-sm-2 control-label">出版社地址</label>
					<div class="col-sm-6">
						<input type="text" id="comp_add" name="comp_add" class="form-control" value="" />
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="comp_number" class="col-sm-2 control-label">出版社統編</label>
					<div class="col-sm-6">
						<input type="text" id="comp_number" name="comp_number" class="form-control" value="" />
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="comp_contact" class="col-sm-2 control-label">聯絡人</label>
					<div class="col-sm-6">
						<input type="text" id="comp_contact" name="comp_contact" class="form-control" value="" />
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="comp_email" class="col-sm-2 control-label">聯絡人E-mail</label>
					<div class="col-sm-6">
						<input type="text" id="comp_email" name="comp_email" class="form-control" value="">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="col-sm-2"></div>
				<div>
					<input type="hidden" name="action" value="insert_Company">
					<input type="submit" class="btn btn-primary" value="送出">
					<button type="button" class="magic btn btn-primary">神奇小按鈕</button>
				</div>
			</form>
		</div>
	</div>
	<script>
	$(document).ready(function(){
		$('.magic').click(function(){
		$('input[name="comp_name"]').val("III出版社");			
		$('input[name="comp_tel"]').val("0211445599");			
		$('input[name="comp_add"]').val("桃園市中壢區中大路300號");			
		$('input[name="comp_number"]').val("09870987");			
		$('input[name="comp_contact"]').val("王寶強");			
		$('input[name="comp_email"]').val("QQQ@gmail.com");			
		})
	})
	</script>
