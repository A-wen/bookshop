<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.book.model.*" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="bookSvc" scope="page" class="com.book.model.BookService" />
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />
<jsp:useBean id="companySvc" scope="page" class="com.company.model.CompanyService" />
<jsp:useBean id="listBooks_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="querybookVO" scope="session" class="com.book.model.BookVO" />

<table class="table table-striped table-hover">
  <thead>
    <tr align='center' valign='middle' style="white-space: nowrap;">
		<td width="45"><b>書號</b></td>
		<td width="100"><b>圖片</b></td>
		<td width="300"><b>書名</b></td>
		<td width="50"><b>定價</b></td>
		<td width="120"><b>作者</b></td>
		<td width="110"><b>類型</b></td>
		<td width="50"><b>出版社</b></td>
		<td width="90"><b>ISBN</b></td>
		<td width="100"><b>敘述</b></td>
		<td width="45"><b>庫存</b></td>
		<td width="45"><b>上架</b></td>
		<td width="45"><b>修改</b></td>
		<td width="45"><b>刪除</b></td>
    </tr>
  </thead>
  <tbody>
		<%@ include file="pages/page1_ByCompositeQuery.file" %>
		<c:forEach var="bookVO" items="${listBooks_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle' style="white-space: nowrap;">
				<!--將修改的那一筆加入對比色而已-->
				<td>${bookVO.book_no}</td>
				<td><img width="80" src="<%=request.getContextPath()%>/img?book_no=${bookVO.book_no}"></td>
				<td>${bookVO.book_name}</td>
				<td>${bookVO.book_price}</td>
				<td>${bookVO.book_author}</td>
				<td><c:forEach var="booktypeVO"
						items="${booktypeSvc.allBooktype}">
						<c:if test="${bookVO.type_no==booktypeVO.type_no}">
	                    	【${booktypeVO.type_name}】
                    </c:if>
					</c:forEach></td>
				<td><c:forEach var="companyVO" items="${companySvc.allCompany}">
						<c:if test="${bookVO.comp_no==companyVO.comp_no}">
	                    	【${companyVO.comp_name}】
                    </c:if>
					</c:forEach></td>
				<td>${bookVO.isbn}</td>
				<td><button class="text btn btn-info" id="${bookVO.book_no}">內容點閱</button></td>
				<td>${bookVO.book_qty}</td>
				<td><c:if test="${bookVO.saleable==1}">
						<c:out value="${true}" />
					</c:if> <c:if test="${bookVO.saleable!=1}">
						<c:out value="${false}" />
					</c:if></td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/book/book.do">
						<input type="submit" class="btn-success" value="修改">
						<input type="hidden" name="book_no" value="${bookVO.book_no}"> 
						<input type="hidden" name="action" value="getOneBook_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Back-End/book/book.do">
						<input type="submit" class="btn-danger" value="刪除">
						<input type="hidden" name="book_no" value="${bookVO.book_no}">
						<input type="hidden" name="action" value="delete_Book">
					</FORM>
				</td>
			</tr>
		</c:forEach>
  </tbody>
</table>
<br>

<%@ include file="pages/page2_ByCompositeQuery.file"%>