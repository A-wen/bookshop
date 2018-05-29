<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.booktype.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />
<%
	List<BooktypeVO> list = booktypeSvc.getAllBooktype();
	pageContext.setAttribute("list", list);
%>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-9">
			<table class="table table-striped table-hover h4">
				<thead>
					<tr align='center' valign='middle'>
						<td><b>分類編號</b></td>
						<td><b>分類名稱</b></td>
						<td><b>修改</b></td>
						<td><b>刪除</b></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="booktypeVO" items="${list}">
						<tr align='center' valign='middle' ${(booktypeVO.type_no==param.type_no) ? 'bgcolor=#eee':''}>
							<td>${booktypeVO.type_no}</td>
							<td>${booktypeVO.type_name}</td>
							<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/booktype/booktype.do">
									<input type="submit" value="修改"> 
									<input type="hidden" name="type_no" value="${booktypeVO.type_no}"> 
									<input type="hidden" name="action" value="getOneBooktype_For_Update">
								</FORM></td>
							<td><FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/Back-End/booktype/booktype.do">
									<input type="submit" value="刪除"> 
									<input type="hidden" name="type_no" value="${booktypeVO.type_no}"> 
									<input type="hidden" name="action" value="delete_Booktype">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>