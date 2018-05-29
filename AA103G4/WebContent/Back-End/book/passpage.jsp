<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="passBookVO" scope="request" class="com.book.model.BookVO" />

<form id="passform" METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/book/book.do">
<input type="hidden" name="book_name" value="${passBookVO.book_name}">
<input type="hidden" name="type_no" value="${passBookVO.type_no}">
<input type="hidden" name="comp_no" value="${passBookVO.comp_no}">
<input type="hidden" name="book_author" value="${passBookVO.book_author}">
<input type="hidden" name="action" value="listBooks_ByCompositeQuery">
<input type="hidden" name="from" value="Back_End">
<input type="hidden" name="pass" value="${param.pass}">
</form>

<script>
window.onload=function (){
	var passform =document.getElementById("passform");
	passform.submit();
};
</script>
