<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ca.model.*"%>
<%@ page import="com.book.model.*"%>
<%@ page import="com.booktype.model.*"%>
<jsp:useBean id="caSvc" scope="page" class="com.ca.model.ClassifiedAdsService" />
<jsp:useBean id="bookSvc" scope="page" class="com.book.model.BookService" />
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />
<%
	ClassifiedAdsVO caVO = (ClassifiedAdsVO) request.getAttribute("caVO");
%>
<div class="row">
	<div class="col-sm-12">
		<FORM class="form-horizontal"  METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/ca/ca.do" name="form1"  >
			<div class="form-group">
				<label class="col-sm-2 control-label">廣告編號</label>
			    <label class="control-label text-left" style="margin-left: 20px">${caVO.cano}</label>
				<input type="hidden" name="ca_no" value="${caVO.cano}">
			</div>
			
			<div class="form-group">
				<label for="ca_name" class="col-sm-2 control-label">廣告名稱</label>
				<div class="col-sm-6">
					<input type="text" id="ca_name" name="ca_name" class="form-control"	value="<%=caVO.getCaname()%>">
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
				<label for="ca_th" class="col-sm-2 control-label">點擊次數</label>
				<div class="col-sm-6">
					<input type="text" id="ca_th" name="ca_th" class="form-control"	value="<%=caVO.getCath()%>">
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="form-group">
					<label for="type_no" class="col-sm-2 control-label">商品類別</label>
					<div class="col-sm-6">
						<select id="type_no" name="type_no" class="form-control">
						<option value="">請選擇
							<c:forEach var="booktypeVO" items="${booktypeSvc.allBooktype}">
								<option value="${booktypeVO.type_no}" ${(caVO.typeno==booktypeVO.type_no)? 'selected':'' }>
											   ${booktypeVO.type_no}-${booktypeVO.type_name}
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-4"></div>
				</div>
					<div class="form-group">
					<label for="book_no" class="col-sm-2 control-label">書名</label>
					<div class="col-sm-6">
						<select id="book_no" name="book_no" class="form-control">
						<option value="">請選擇
							<c:forEach var="bookVO" items="${bookSvc.allBook}">
								<option value="${bookVO.book_no}" ${(caVO.bookno==bookVO.book_no)? 'selected':'' }>
											   ${bookVO.type_no}-${bookVO.book_name}
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="row">
				<div class="form-group">
					<%
						java.sql.Date date_SQLs = new java.sql.Date(System.currentTimeMillis());
					%>
					<label for="ca_start" class="col-sm-2 control-label">開始時間</label>
					<div class='input-group date' id='ca_start'>
						<input onFocus="this.blur()" type='text' class="form-control"
							name="ca_start" value="<%=caVO.getCastart()%>" /> <span
							class="input-group-addon" id='ca_start'> <span
							class="glyphicon glyphicon-calendar"> </span>
						</span>
					</div>
				</div>
				<script type="text/javascript">
					$(function() {
						$('#ca_start').datetimepicker();
					});
				</script>
			</div>
			<div class="row">
				<div class="form-group">
					<%
						java.sql.Date date_SQLe = new java.sql.Date(System.currentTimeMillis());
					%>
					<label for="ca_end" class="col-sm-2 control-label">結束時間</label>
					<div class='input-group date' id='ca_end'>
						<input onFocus="this.blur()" type='text' class="form-control"
							name="ca_end" value="<%=caVO.getCaend()%>" /> <span
							class="input-group-addon" id='ca_end'> <span
							class="glyphicon glyphicon-calendar"> </span>
						</span>
					</div>
				</div>
				<script type="text/javascript">
					$(function() {
						$('#cm_end').datetimepicker();
					});
				</script>
			</div>
			<br>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="ca_no" value="<%=caVO.getCano()%>">
			<input type="hidden" name="requestURL"	value="<%=request.getParameter("requestURL")%>">
			<input type="hidden" name="whichPage"	value="<%=request.getParameter("whichPage")%>">
			<input type="submit" value="查詢">
		</FORM>

	</div>

</div>
				
			
	
