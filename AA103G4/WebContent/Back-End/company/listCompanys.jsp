<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.company.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="companySvc" scope="page"
	class="com.company.model.CompanyService" />
<%
	List<CompanyVO> list = companySvc.getAllCompany();
	pageContext.setAttribute("list", list);
%>
	<table class="table table-striped table-hover h4">
		<thead>
			<tr align='center' valign='middle'>
				<td><b>出版社編號</b></td>
				<td><b>出版社名稱</b></td>
				<td><b>出版社電話</b></td>
				<td><b>出版社地址</b></td>
				<td><b>出版社統編</b></td>
				<td><b>聯絡人</b></td>
				<td><b>E-mail</b></td>
				<td><b>修改</b></td>
				<td><b>刪除</b></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="companyVO" items="${list}">
				<tr align='center' valign='middle'
					${(companyVO.comp_no==param.comp_no) ? 'bgcolor=#eee':''}>
					<td>${companyVO.comp_no}</td>
					<td>${companyVO.comp_name}</td>
					<td>${companyVO.comp_tel}</td>
					<td class="text-left">${companyVO.comp_add}</td>
					<td>${companyVO.comp_number}</td>
					<td>${companyVO.comp_contact}</td>
					<td>${companyVO.comp_email}</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/company/company.do">
									<input type="submit" value="修改"> 
							<input type="hidden" name="comp_no" value="${companyVO.comp_no}"> 
							<input type="hidden" name="action" value="getOneCompany_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/company/company.do">
							<input type="submit" value="刪除">
							<input type="hidden" name="comp_no" value="${companyVO.comp_no}"> 
							<input type="hidden" name="action" value="delete_Company">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
