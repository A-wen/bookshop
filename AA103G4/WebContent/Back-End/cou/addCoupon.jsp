<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cou.model.*"%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<%
	CouponVO couVO = (CouponVO) request.getAttribute("couVO");
%>

<div class="row">
	<div class="col-sm-12">
		<h3>消費金資料:</h3>
		<hr>
		<FORM class="form-horizontal" METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/cou/cou.do"	name="form1">
			<div class="form-group">
				<label for="mem_no" class="col-sm-2 control-label">消費金受益人:</label>
				<div class="col-sm-6">
					<select  id="mem_no" name="mem_no" class="form-control" >
						<option value="">請選擇</option>
							<c:forEach var="memVO" items="${memSvc.all}">
								<option value="${memVO.mem_no}"${(couVO.memno==memVO.mem_no? 'selected':'')}>
									${memVO.mem_name}
								</option>
							</c:forEach>
					</select>	
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="cou_dis" class="col-sm-2 control-label">消費金金額:</label>
				<div class="col-sm-6">
					<input type="text" id="cou_dis" name="cou_dis" class="form-control"	value="" />
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			<div class="form-group">
				<label for="cou_name" class="col-sm-2 control-label">消費金標題:</label>
				<div class="col-sm-6">
					<input type="text" id="cou_name" name="cou_name" class="form-control" value="" />
				</div>
				<div class="col-sm-4"></div>
			</div>
			
			<div class="form-group">
				<label for="cou_exp" class="col-sm-2 control-label">消費金內容:</label>
				<div class="col-sm-6">
					<textarea id="cou_exp" name="cou_exp" class="form-control"></textarea>
				</div>
				<div class="col-sm-4"></div>
			</div>	
					
				<div class="form-group">					
					<label for="cou_start" class="col-sm-2 control-label">開始時間</label>
					<div class="col-sm-6">									
						<input  type='text' class="form-control" name="cou_start" id="cou_start" value="" />						
					</div>				
					<div class="col-sm-4"></div>
				</div>
						
				<div class="form-group">					
					<label for="cou_end" class="col-sm-2 control-label">結束時間</label>
					<div class="col-sm-6">											
						<input  type='text' class="form-control" name="cou_end"	id="cou_end" value="" />						
					</div>					
						<div class="col-sm-4"></div>
					</div>
			

	
			<div>
				<input type="hidden" name="action" value="insert">
				<input type="submit" value="送出新增">
				<button type="button" class="btn-primary" id="wow">顯靈</button>
			</div>
		</FORM>
	</div>
</div>
<script>
$(document).ready(function(){
		$('#wow').click(function(){			
		$('input#cou_dis').val("100");
		$('input#cou_name').val("會員獎勵");	
		$('textarea#cou_exp').val("親愛的用戶你好,此優惠有期限限制請盡快使用");
		$('input#cou_start').val("2016-09-29");
		$('input#cou_end').val("2017-09-29");
		})
	})
</script>