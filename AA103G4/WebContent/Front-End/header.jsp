<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="navbar-ex1-collapse">
	        <span class="sr-only">選單切換</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
        </button>
        <a href="<%=request.getContextPath()%>/Front-End/index.jsp"><img class="navbar-brand" src="<%=request.getContextPath()%>/Front-End/img/icon.png"></a>
    </div>
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <!-- 左選單 -->
        <ul class="nav navbar-nav">
			<li class="dropdown"></li>
			<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">讀書會<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="<%=request.getContextPath()%>/Front-End/s_gro/HomePageforClub.jsp">查看讀書會列表</a></li>
		            <li><a href="<%=request.getContextPath()%>/Front-End/event-info/list?act=list">查看所有讀書會活動</a></li>
		        </ul>
		    </li>
		    <li><a href="<%=request.getContextPath()%>/Front-End/book/booklist.jsp">商城</a></li>            
        </ul>
        <!-- 搜尋表單 -->
        <form  METHOD="post" ACTION="<%=request.getContextPath()%>/Front-End/book/book.do" class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" name="book_name" class="form-control" placeholder="請輸入關鍵字">
            </div>
			<input type="hidden" name="action" value="listBooks_ByCompositeQuery">
			<input type="hidden" name="from" value="Front_End">
            <button class="btn btn-default" onclick="this.submit()">搜尋</button>
        </form>
        <!-- 右選單 -->
        
        <ul class="nav navbar-nav navbar-right">
        	<c:if test="${not empty memVO}">
				<li>
					<a href="<%=request.getContextPath()%>/Front-End/member/MemPerson.jsp"> 
					<img src="<%=request.getContextPath()%>/member/ReaderPhoto.do?mem_no=${memVO.mem_no}" width="45" height="25"></a>
				</li>
		        <li class="dropdown">
		            <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
		            <i class="glyphicon glyphicon-user"></i>會員專區<b class="caret"></b></a>
		            <ul class="dropdown-menu">
		                <li><a href="<%=request.getContextPath()%>/Front-End/member/memTransion.jsp">查看個人交易紀錄</a></li>
		                <li><a href="<%=request.getContextPath()%>/Front-End/member/MemPerson.jsp">會員資料修改</a></li>
		                <li><a href="<%=request.getContextPath()%>/Front-End/member/memberTrackBook.jsp">追蹤商品管理</a></li>
		                <li><a href="<%=request.getContextPath()%>/Front-End/cou/listCoupons_ByMemno.jsp?mem_no=${memVO.mem_no}">消費金</a></li>
		                <li><a href="<%=request.getContextPath()%>/Front-End/event-info/edit?act=create">建立讀書會活動</a></li>
		            </ul>
		       	</li>
		       	<li><a href="<%=request.getContextPath()%>/member/MemServlet.do?action=logout"><i class="glyphicon glyphicon-log-out"></i>登出</a></li>
            </c:if>
            <c:if test="${empty memVO}">
				<li><a href="<%=request.getContextPath()%>/Front-End/member/register.jsp"><i class="glyphicon glyphicon-pencil" ></i>註冊</a></li>
	            <li><a href="<%=request.getContextPath()%>/Front-End/member/login.jsp"><i class="glyphicon glyphicon-log-in" ></i>登入</a></li>
			</c:if>
            <li><a href="<%=request.getContextPath()%>/Front-End/Cart/cart"><i class="glyphicon glyphicon-shopping-cart"></i>購物車</a></li>

    	</ul>
	</div>
</nav>


