<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.info.model.*"%>
<%
  InformationVO infoVO = (InformationVO) request.getAttribute("infoVO"); 
%>			

<div class="container">
		<div class="col-md-12">		
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/info/info.do" class="form-horizontal">
				<div class="form-group">
					<label class="col-md-1 control-label">公告編號:</label>
					<label class="control-label text-left col-md-1" style="margin-left:20px"><%=infoVO.getInfono()%></label>
					<input type="hidden" name="info_no" value="<%=infoVO.getInfono()%>"/>
				</div>
				<div class="col-md-12"></div>
				<div class="form-group">
					<label for="info_title" class="col-md-1 control-label">公告標題:</label>
					<div class="col-md-6">
						<input type="text" id="info_title" name="info_title" class="form-control" value="<%=infoVO.getInfotitle()%>" >
					</div>
					<div class="col-md-12"></div>
				</div>
				<div class="form-group">
					<label for="info_exp" class="col-md-1 control-label">公告內容:</label>
					<div class="col-md-4">
						<textarea  rows="4" cols="50" id="info_exp" name="info_exp" class="form-control" value="<%=infoVO.getInfoexp()%>"> <%=infoVO.getInfoexp()%> </textarea>
					</div>
					
					<div class="col-md-12"></div>
				</div>
			
					<div class="form-group">
					<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
						<label for="info_term" class="col-md-1 control-label">發布時間:</label>
						
						<div class="col-md-4">
							<input type='text'  class="form-control" name="info_term" value="<%=infoVO.getInfoterm()%>" /> 
						</div>								
							
							<div class="col-md-12"></div>
						</div>
					
					
				
				<br> 
			<input type="hidden" name="action" 		value="update">
			<input type="hidden" name="info_no" 	value="<%=infoVO.getInfono()%>">
			<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">		<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
			<input type="hidden" name="whichPage"	value="<%=request.getParameter("whichPage")%>">			<!--只用於:listAllEmp.jsp-->
			<input type="submit" value="送出修改" />
			</form>    
		</div>		
	</div>


