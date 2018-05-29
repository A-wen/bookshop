<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cou.model.*"%>
<%@ page import="com.mem.model.*"%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<%
  CouponVO couVO = (CouponVO) request.getAttribute("couVO"); 
%>

<div class="container">
	<div class="col-sm-12">
		<FORM class="form-horizontal" METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/cou/cou.do" name="form1" >
			<div class="form-group">
				<label class="col-sm-2 control-label">優惠金編號</label>
				<label	class="control-label text-left" style="margin-left: 20px">${couVO.couno}</label>
				<input type="hidden" name="cou_no" value="${couVO.couno}">
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">會員</label>
				<label	class="control-label text-left" style="margin-left: 20px">
							<c:forEach var="memVO" items="${memSvc.all}">
							<c:if test="${couVO.memno==memVO.mem_no}">
								${memVO.mem_name}
							</c:if>
							</c:forEach>
				</label>
				<input type="hidden" name="mem_no" value="${couVO.memno}">
			</div>
			<div class="form-group">
				<label for="cou_name" class="col-sm-2 control-label">優惠金名稱</label>
				<div class="col-sm-6">
					<input type="text" id="cou_name" name="cou_name" class="form-control" value="${couVO.couname}">
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="cou_dis" class="col-sm-2 control-label">折扣金額:</label>
				<div class="col-sm-6">
					<input type="text" id="cou_dis" name="cou_dis" class="form-control"	value="${couVO.coudis}">
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="cou_exp" class="col-sm-2 control-label">優惠說明:</label>
				<div class="col-sm-6">
					<textarea  id="cou_exp" name="cou_exp" class="form-control"
						value="${couVO.couexp}"></textarea>
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">優惠開始日</label>
				<label class="control-label text-left" style="margin-left: 20px"><%=couVO.getCoustart()%></label>
				<input type="hidden" name="cou_start" value="${couVO.coustart}">
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">優惠結束日</label>
				<label class="control-label text-left" style="margin-left: 20px"><%=couVO.getCouend()%></label>
				<input type="hidden" name="cou_end" value="${couVO.couend}">
			</div>
			
		




			<br> 
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="cou_no" value="<%=couVO.getCouno()%>">
			<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">		
			<input type="hidden" name="whichPage"	value="<%=request.getParameter("whichPage")%>">
			<input type="submit" value="送出修改">
		</FORM>

	</div>

</div>


	