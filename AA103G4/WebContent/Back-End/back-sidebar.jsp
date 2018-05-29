<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.competence.model.*" %>

<%
	List<CompetenceVO> barlist = (List<CompetenceVO>)session.getAttribute("funlist");
	List<Integer> funnolist = new ArrayList<Integer>();
	for(CompetenceVO aCompe:barlist){
		funnolist.add(aCompe.getFunNo());
	}
%>

<script>
$(function() {
    $("div.nav-item").click(function() {
        $(".list-hidden").hide();
        $(this).next().toggle();
    });true
}); 
</script>


<div class="nav nav-pills nav-stacked">
    <div class="nav-item bg-gray" style="display:<%=funnolist.contains(101) ? "block" :"none"%>"><a class="nav-link">員工管理</a></div>
    <div class="list-hidden" style="display:${param.page.equals('acc')||param.page.equals('emp') ? 'block' :'none'}">
        <a id="employee" class="nav-link ${param.page.equals('acc')? 'active' : ''}" href="<%=request.getContextPath()%>/Back-End/manager/empAccount.jsp?page=acc">帳號管理</a>
        <a id="fun" class="nav-link ${param.page.equals('emp')? 'active' : ''}" href="<%=request.getContextPath()%>/Back-End/manager/listAllEmp.jsp?page=emp">權限管理</a>
    </div>
    <div class="nav-item bg-gray" style="display:<%=funnolist.contains(102) ? "block" :"none"%>"><a class="nav-link">會員管理</a></div>
    <div class="list-hidden" style="display:${param.page.equals('mem')||param.page.equals('track')||param.page.equals('coupon') ? 'block' :'none'}">
    
        <a id="mem" class="nav-link ${param.page.equals('mem')? 'active' : ''}" href="<%=request.getContextPath()%>/Back-End/manager/listAllMem.jsp?page=mem">會員資料管理</a>
        <a id="coupon" class="nav-link ${param.page.equals('coupon')? 'active' : ''}" href="<%=request.getContextPath()%>/Back-End/cou/Coupon.jsp?page=coupon&action=all">優惠卷管理</a>
    </div>
    <div class="nav-item bg-gray" style="display:<%=funnolist.contains(103) ? "block" :"none"%>"><a class="nav-link">商品管理</a></div>
    <div class="list-hidden" style="display:${param.page.equals('book')||param.page.equals('type')||param.page.equals('comp') ? 'block' :'none'}">
        <a id="book" class="nav-link ${param.page.equals('book')? 'active' : ''}" href="<%=request.getContextPath()%>/Back-End/book/book.jsp?page=book&action=query">書籍管理</a>
        <a id="booktype" class="nav-link ${param.page.equals('type')? 'active' : ''}" href="<%=request.getContextPath()%>/Back-End/booktype/booktype.jsp?page=type&action=query">類別管理</a>
        <a id="company" class="nav-link ${param.page.equals('comp')? 'active' : ''}" href="<%=request.getContextPath()%>/Back-End/company/company.jsp?page=comp&action=query">出版社管理</a>
    </div>
    <div class="nav-item bg-gray" style="display:<%=funnolist.contains(104) ? "block" :"none"%>"><a class="nav-link">讀書會管理</a></div>
    <div class="list-hidden" style="display:${param.page.equals('gro') ? 'block' :'none'}">
        <a id="group" class="nav-link ${param.page.equals('gro')? 'active' : ''}" href="<%=request.getContextPath()%>/Back-End/gro/back_groreport.jsp?page=gro">讀書會管理</a>
    </div>
    <div class="nav-item bg-gray" style="display:<%=funnolist.contains(106) ? "block" :"none"%>"><a class="nav-link">首頁內容管理</a></div>
    <div class="list-hidden" style="display:${param.page.equals('info')||param.page.equals('ca')||param.page.equals('cm') ? 'block' :'none'}">
        <a id="info" class="nav-link ${param.page.equals('info')? 'active' : ''}" href="<%=request.getContextPath()%>/Back-End/info/backhomeinfo.jsp?page=info&action=all">公告管理</a>
        <a id="cm" class="nav-link ${param.page.equals('cm')? 'active' : ''}" href="<%=request.getContextPath()%>/Back-End/cm/CommercialMessage.jsp?page=cm&action=all">廣告管理</a>
    </div>
</div>
