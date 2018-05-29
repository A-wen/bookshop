<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<div class="container">
	<div class="form-inline">
		<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/cou/cou.do">
			<label for="cou_no" class="control-label">輸入優惠金編號 (如2001):</label>
		    <input type="text" name="cou_no" name="cou_no" class="form-control" />
	        <label for="go" class="control-label fontwhite">送出查詢</label> 
	        <input type="submit" id="go" class="btn btn-primary form-control" value="查詢"> 
	        <input type="hidden" name="action" value="getOne_For_Display">
		</FORM>
	</div>


	<jsp:useBean id="couSvc" scope="page" class="com.cou.model.CouponService" />

	<div class="form-inline">
		<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/cou/cou.do">
			<div class="form-group">
				<label for="cou_no" class="control-label">選擇優惠金編號:</label>
				 <select size="1" name="cou_no" id="cou_no" class="form-control">
					<option value="">請選擇
						<c:forEach var="couVO" items="${couSvc.all}">
							<option value="${couVO.couno}">${couVO.couno}
						</c:forEach>
				</select> <label for="go" class="control-label fontwhite">送出查詢</label> 
				<input type="submit" id="go" class="btn btn-primary form-control" value="查詢"> 
				<input type="hidden" name="action" value="getOne_For_Display"></input>
			</div>
		</FORM>
	</div>
	<div class="form-inline">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/cou/cou.do">
			<div class="form-group">
				<label for="cou_name" class="control-label">選擇優惠金名稱:</label> 
				<select	id="cou_name" name="cou_no" class="form-control">
					<option value="">請選擇
						<c:forEach var="couVO" items="${couSvc.all}">
							<option value="${couVO.couno}">${couVO.couname}
						</c:forEach>
				</select> 
				<label for="go" class="control-label fontwhite">送出查詢</label>
		        <input type="submit" id="go" class="btn btn-primary form-control" value="查詢"> 
	     		<input type="hidden" name="action" value="getOne_For_Display"></input>
			</div>
		</form>
	</div>

	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
	
	<div class="form-inline">
		<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/member/MemServlet.do">
			<div class="form-group">
				<label for="mem_no" class="control-label">選擇會員:</label>
				 <select size="1" name="mem_no" id="mem_no" class="form-control">
					<c:forEach var="memVO" items="${memSvc.all}">
						<option value="${memVO.memNo}">${memVO.memName}
					</c:forEach>
				</select> <label for="go" class="control-label fontwhite">送出查詢</label> 
				<input type="submit" id="go" class="btn btn-primary form-control" value="查詢"> 
				<input type="hidden" name="action" value="listCoupons_ByMemno_A"></input>
			</div>
		</FORM>
	</div>
</div>


