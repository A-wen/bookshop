<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cou.model.*"%>
<%
	CouponService couSvc = new CouponService();
	List<CouponVO> list = couSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="couService" scope="page" class="com.cou.model.CouponService" />


<table class="table table-striped table-hover">
  <thead>
    <tr>
		<th>優惠編號</th>		
		<th>優惠名稱</th>
		<th>優惠開始</th>
		<th>優惠結束</th>
		<th>折扣金額</th>
		<th>優惠說明</th>
		<th>會員編號</th>
		
		</tr>
	</thead>
  <tbody>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="couVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(couVO.couno==param.couno) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${couVO.couno}</td>			
			<td>${couVO.couname}</td>
			<td>${couVO.coustart}</td>
			<td>${couVO.couend}</td>
			<td>${couVO.coudis}</td>
			<td>${couVO.couexp}</td>
						
			<td><c:forEach var="memVO" items="${memSvc.all}">
                    <c:if test="${couVO.memno==memVO.mem_no}">
	                    ${memVO.mem_no}【${memVO.mem_name}】
                    </c:if>
                </c:forEach>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/cou/cou.do">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="cou_no" value="${couVO.couno}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/cou/cou.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="cou_no" value="${couVO.couno}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    <input type="hidden" name="action"value="delete">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<%@ include file="pages/page2.file" %>


