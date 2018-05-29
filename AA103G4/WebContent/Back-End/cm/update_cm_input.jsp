<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cm.model.*"%>
<%
	CmVO cmVO = (CmVO) request.getAttribute("cmVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<div class="row">
	<div class="col-sm-12">
		<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/cm/cm.do" name="form1" enctype="multipart/form-data" class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-2 control-label">廣告編號</label>
				 <label	class="control-label text-left" style="margin-left: 20px">${cmVO.cmNo}</label>
				<input type="hidden" name="cm_no" value="${cmVO.cmNo}">
			</div>
			
			<div class="form-group">
				<label for="cm_name" class="col-sm-2 control-label">廣告名稱</label>
				<div class="col-sm-6">
					<input type="text" id="cm_name" name="cm_name" class="form-control"	value="<%=cmVO.getCmName()%>">
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="cm_th" class="col-sm-2 control-label">點擊次數:</label>
				<div class="col-sm-6">
					<input type="text" id="cm_th" name="cm_th" class="form-control"	value="<%=cmVO.getCmTh()%>">
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="cm_inv" class="col-sm-2 control-label">廠商:</label>
				<div class="col-sm-6">
					<input type="text" id="cm_inv" name="cm_inv" class="form-control" value="<%=cmVO.getCminv()%>">
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="cm_url" class="col-sm-2 control-label">網址:</label>
				<div class="col-sm-6">
					<input type="text" id="cm_url" name="cm_url" class="form-control"
						value="<%=cmVO.getCmUrl()%>">
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="cm_pic" class="col-sm-2 control-label">圖片:</label>
				<div class="col-sm-6">
					<input type="file" id="cm_pic" name="cm_pic" class="upfile"
						value="<%=cmVO.getCmPic()%>">
				</div>
				<div class="col-sm-4"></div>
			</div>
			
				<div class="form-group">
					<%
						java.sql.Date date_SQLs = new java.sql.Date(System.currentTimeMillis());
					%>
					<label for="cm_start" class="col-sm-2 control-label">廣告開始日</label>
					<div class="col-sm-6">
						<input  type='text'  name="cm_start"  class="form-control" id="cm_start"
						        value="<%=cmVO.getCmStart()%>"/> 
					</div>
					<div class="col-sm-4"></div>
				</div>
				
			
			
				<div class="form-group">
					<%
						java.sql.Date date_SQLe = new java.sql.Date(System.currentTimeMillis());
					%>
					<label for="cm_end" class="col-sm-2 control-label">廣告結束日</label>
					<div class="col-sm-6">
						<input  type='text'  name="cm_end" class="form-control" id="cm_end"
						        value="<%=cmVO.getCmEnd()%>" />
					</div>
					<div class="col-sm-4"></div>
				</div>
				
			<br>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="cm_no" value="<%=cmVO.getCmNo()%>">
			<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">
			<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
			<input type="hidden" name="whichPage"	value="<%=request.getParameter("whichPage")%>">
			<!--只用於:listAllEmp.jsp-->
			<input type="submit" value="送出修改">
		</FORM>

	</div>

</div>


