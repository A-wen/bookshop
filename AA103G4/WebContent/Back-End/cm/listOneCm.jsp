<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.cm.model.*"%>

<%-- �����m�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller InformationService.java�w�s�Jrequest��InformationVO����--%>
 <%CmVO cmVO = (CmVO) request.getAttribute("cmVO");%>

<%-- ���X ������InformationVO����--%>

<table class="table table-striped table-hover">
	<thead>
		<tr>
		<th>�s�i�s��</th>
		<th>�s�i�W��</th>		
		<th>�s�i�}�l���</th>
		<th>�s�i�������</th>
		<th>�s�i�Ӥ�</th>	
		<th>�s�i�I������</th>	
		<th>�s�i�t��</th>
		<th>�s�i����</th>		
	</tr>
	</thead>
	<tbody>
	<tr>
		  	<td>${cmVO.cmNo}</td>
			<td>${cmVO.cmName}</td>
			<td>${cmVO.cmStart}</td>
			<td>${cmVO.cmEnd}</td>
			<td><img src="<%=request.getContextPath()%>/cm/CMReader.do?cm_no=${cmVO.cmNo}"  width="200px" height="100px"></td>			
			<td>${cmVO.cmTh}</td>
			<td>${cmVO.cminv}</td>
			<td>${cmVO.cmUrl}</td>		
	</tr>
	</tbody>
</table>
<br>
