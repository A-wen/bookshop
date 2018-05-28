<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ca.model.*"%>
<%@ page import="com.book.model.*"%>
<%@ page import="com.booktype.model.*"%>
<jsp:useBean id="caSvc" scope="page" class="com.ca.model.ClassifiedAdsService" />
<jsp:useBean id="bookSvc" scope="page" class="com.book.model.BookService" />
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />

<div class="container" >
	<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/ca/ca.do" class="form-horizontal">
		
		<div class="form-inline">			
				<div class=" col-sm-12 form-group">
					<label for="ca_no" class="control-label col-sm-2">選擇廣告編號:</label>
					<div class="col-sm-3">
					<select	id="ca_no" name="ca_no" class="form-control">
						<option value="">請選擇</option>
							<c:forEach var="caVO" items="${caSvc.all}">
								<option value="${caVO.cano}">${caVO.cano}</option>
							</c:forEach>
					</select>
					</div>
					<div class="col-sm-2"> 
					<label for="go" class="control-label fontwhite">送出查詢</label> 
					<input	type="submit" id="go" class="btn btn-primary form-control" value="查詢">
					<input type="hidden" name="action" value="getOne_For_Display">
					</div>
					<div class="col-sm-5"></div>
				</div>
			</div>
			</form>
			
		<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/ca/ca.do" class="form-horizontal">
		
			<div class="form-inline">	
				<div class="col-sm-12 form-group">
					<label for="ca_name" class="control-label col-sm-2">廣告名稱:</label>
					<div class="col-sm-3">					
					<select	id="ca_name" name="ca_no" class="form-control">					
						<option value="">請選擇</option>
							<c:forEach var="caVO" items="${caSvc.all}">
								<option value="${caVO.cano}">${caVO.caname}</option>
							</c:forEach>
					</select> 
					</div>
					<div class="col-sm-2">
					<label for="go" class="control-label fontwhite">送出查詢</label> 
					<input	type="submit" id="go" class="btn btn-primary form-control" value="查詢">
					<input type="hidden" name="action" value="getOne_For_Display">
					</div>
				</div>
				<div class="col-sm-5"></div>
			</div>
		</form>
		<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/ca/ca.do" class="form-horizontal">
		
			<div class="form-inline">	
				<div class="col-sm-12 form-group">
					<label for="ca_no" class="control-label col-sm-2">選擇廣告編號:</label>
					<div class="col-sm-3">
					<select	id="ca_no" name="ca_no" class="form-control">
						<option value="">請選擇</option>
							<c:forEach var="caVO" items="${caSvc.all}">
								<option value="${caVO.cano}">${caVO.cano}</option>
							</c:forEach>
					</select> 
					</div>
					<div class="col-sm-2">
					<label for="go" class="control-label fontwhite">送出查詢</label> 
					<input	type="submit" id="go" class="btn btn-primary form-control" value="查詢">
					<input type="hidden" name="action" value="getOne_For_Display">
					</div>
				</div>
				<div class="col-sm-5"></div>
				</div>
			</form>
		</div>	

