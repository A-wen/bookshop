<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="cmSvc" scope="page" class="com.cm.model.CmService" />

<div class="container">	
		<div class="form-inline">
			<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/cm/cm.do" class="form-horizontal">
				<div class=" col-sm-12 form-group">
					<label for="cm_no" class="col-sm-2 control-label">��ܼs�i�s��:</label>
					<div class="col-sm-3">
					<select	id="cm_no" name="cm_no" class="form-control">
						<option value="">�п��
							<c:forEach var="cmVO" items="${cmSvc.all}">
								<option value="${cmVO.cmNo}">${cmVO.cmNo}
							</c:forEach>
					</select> 
					</div>
					<div class="col-sm-2"> 
					<label for="go" class="control-label fontwhite">�e�X�d��</label> 
					<input	type="submit" id="go" class="btn btn-primary form-control" value="�d��">
					<input type="hidden" name="action" value="getOne_For_Display"></input>
					</div>
					<div class="col-sm-5"></div>
				</div>
			</form>
		</div>
		<div class="form-inline">
			<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/cm/cm.do" class="form-horizontal">
				<div class=" col-sm-12 form-group">
					<label for="cm_name" class="col-sm-2 control-label">��ܼs�i�W��:</label>
					<div class="col-sm-3"> 
					<select	id="cm_name" name="cm_no" class="form-control">
						<option value="">�п��
							<c:forEach var="cmVO" items="${cmSvc.all}">
								<option value="${cmVO.cmNo}">${cmVO.cmName}
							</c:forEach>
					</select>
					</div>
					<div class="col-sm-2"> 
					 <label for="go" class="control-label fontwhite">�e�X�d��</label>
					 <input	type="submit" id="go" class="btn btn-primary form-control" value="�d��">
	                 <input type="hidden" name="action" value="getOne_For_Display"></input>
	                 </div>
	                 <div class="col-sm-5"></div>
				</div>
			</form>
		</div>
		
		<div class="form-inline">
			<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/Back-End/cm/cm.do" class="form-horizontal">
				<div class="col-sm-12 form-group">
					<label for="cm_inv" class=" col-sm-2 control-label">��ܼt�ӦW��:</label>
					<div class="col-sm-3"> 
					<select	id="cm_inv" name="cm_no" class="form-control">
						<option value="">�п��
							<c:forEach var="cmVO" items="${cmSvc.all}">
								<option value="${cmVO.cmNo}">${cmVO.cminv}
							</c:forEach>
					</select>
					</div>
					<div class="col-sm-2"> 
					 <label for="go" class="control-label fontwhite">�e�X�d��</label>
					 <input	type="submit" id="go" class="btn btn-primary form-control" value="�d��">
	                 <input type="hidden" name="action" value="getOne_For_Display"></input>
	                 </div>
	                 <div class="col-sm-5"></div>
				</div>
			</form>
		</div>
	</div>
	



