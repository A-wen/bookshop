<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.info.model.*" %>
<script src="<%=request.getContextPath()%>/js/previewImage.js"></script>


<div class="container">	
	<div class="form-inline">
		<form METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/info/info.do" class="form-horizontal">			
				<div class="form-group">					
						<label for="info_no" class="control-label">輸入公告編號:</label>
						<input type="text" id="info_no" name="info_no" class="form-control" value='${infoVO.infono}'>
						<label for="go" class="control-label fontwhite">送出查詢</label>
						<input type="submit" id="go" class="btn btn-primary form-control" value="查詢">
						<input type="hidden" name="action" value="getOne_For_Display"></input>											
				</div>
			</form>
		<form METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/info/info.do" class="form-horizontal">			
				<div class="form-group">	
					  <jsp:useBean id="infoSvc" scope="page" class="com.info.model.InformationService" />					
						<label for="info_no" class="control-label">選擇公告編號:</label>
						<select id="info_no" name="info_no" class="form-control">
							<option value="">請選擇
							<c:forEach var="infoVO" items="${infoSvc.all}">
								<option value="${infoVO.infono}">${infoVO.infono}
							</c:forEach>
						</select>						
						<label for="go" class="control-label fontwhite">送出查詢</label>
						<input type="submit" id="go" class="btn btn-primary form-control" value="查詢">
						<input type="hidden" name="action" value="getOne_For_Display"></input>						
					</div>				
			</form>
		<form METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/info/info.do" class="form-horizontal">			
				<div class="form-group">				
						<label for="info_title" class="control-label">選擇公告名稱:</label>
						<select id="info_title" name="info_no" class="form-control">
							<option value="">請選擇
							<c:forEach var="infoVO" items="${infoSvc.all}">
							<option value="${infoVO.infono}">${infoVO.infotitle}	
							</c:forEach>
						</select>			
						<label for="go" class="control-label fontwhite">送出查詢</label>
						<input type="submit" id="go" class="btn btn-primary form-control" value="查詢">
						<input type="hidden" name="action" value="getOne_For_Display"></input>											
				</div>				
		</form>
	</div>	
</div>
<div class="text-center">
	<%-- 錯誤表列 --%>
	<c:if test="${not empty queryMsgs}">
		<div class="h2">
			<font color='red'>
					<c:forEach var="message" items="${queryMsgs}">
						${message}
					</c:forEach>
			</font>
		</div>
	</c:if>
</div>	
<hr>
<div id="OneInfo">
<%if (request.getAttribute("infoVO")!=null){%>
       <jsp:include page="listOneInfo.jsp" />
<%} %>
</div>










