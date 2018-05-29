<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="booktypeSvc" scope="page" class="com.booktype.model.BooktypeService" />
<jsp:useBean id="companySvc" scope="page" class="com.company.model.CompanyService" />
<script src="<%=request.getContextPath()%>/js/previewImage.js"></script>
<%@ page import="com.book.model.*"%>
<%
	BookVO insertbookVO = (BookVO) request.getAttribute("insertbookVO");
%>
	<div class="row">
		<div class="col-sm-12">

<%-- 錯誤表列 --%>
<c:if test="${not empty addMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul class="h2">
		<c:forEach var="message" items="${addMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/Back-End/book/book.do" enctype="multipart/form-data" class="form-horizontal">
				<div class="form-group">
					<label for="book_name" class="col-sm-2 control-label">書名</label>
					<div class="col-sm-6">
						<input type="text" id="book_name" name="book_name" class="form-control" value="${(insertbookVO==null)? '神奇小書' : insertbookVO.book_name}">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="book_price" class="col-sm-2 control-label">定價</label>
					<div class="col-sm-6">
						<input type="text" id="book_price" name="book_price" class="form-control" value="${(insertbookVO==null)? '300' : insertbookVO.book_price}">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="type_no" class="col-sm-2 control-label">類型</label>
					<div class="col-sm-6">
						<select id="type_no" name="type_no" class="form-control">
							<option value="">請選擇
							<c:forEach var="booktypeVO" items="${booktypeSvc.allBooktype}">
								<option value="${booktypeVO.type_no}" ${(insertbookVO==null)&&insertbookVO.type_no==booktypeVO.type_no? 'selected' : ''}>
								${booktypeVO.type_name}
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="comp_no" class="col-sm-2 control-label">出版社</label>
					<div class="col-sm-6">
						<select id="comp_no" name="comp_no" class="form-control">
							<option value="">請選擇
							<c:forEach var="companyVO" items="${companySvc.allCompany}">
								<option value="${companyVO.comp_no}" ${(insertbookVO==null)&&insertbookVO.comp_no==companyVO.comp_no? 'selected' : ''}>
								${companyVO.comp_name}
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="book_qty" class="col-sm-2 control-label">庫存</label>
					<div class="col-sm-6">
						<input type="text" id="book_qty" name="book_qty" class="form-control" value="">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="isbn" class="col-sm-2 control-label">ISBN</label>
					<div class="col-sm-6">
						<input type="text" id="isbn" name="isbn" class="form-control" value="">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="book_author" class="col-sm-2 control-label">作者</label>
					<div class="col-sm-6">
						<input type="text" id="book_author" name="book_author" class="form-control" value="">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="book_pic" class="col-sm-2 control-label">書籍圖片</label>
					<div class="col-sm-6">
						<img src="" width="100" height="100"> <input type="file"
							id="book_pic" name="book_pic" class="upfile">
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label for="book_desc" class="col-sm-2 control-label">書籍描述</label>
					<div class="col-sm-6">
						<textarea class="form-control" id="book_desc" name="book_desc" rows="5"></textarea>
					</div>
					<div class="col-sm-4"></div>
				</div>
				<div class="col-sm-2"></div>
				<div>
					<input type="hidden" name="action" value="insert_Book">
					<input type="submit" class="btn btn-primary" value="送出">
					<button type="button" class="magic btn btn-primary">神奇小按鈕</button>
				</div>
			</form>
		</div>
	</div>
	<script>
	$(document).ready(function(){
		$('.magic').click(function(){
			$('input[name="book_name"]').val("神奇小書");
			$('input[name="book_price"]').val("300");
			$('select[name="type_no"]').val("200");
			$('select[name="comp_no"]').val("30");
			$('input[name="book_qty"]').val("50");
			$('input[name="isbn"]').val("9105306135241");
			$('input[name="book_author"]').val("陳小宏");
			$('textarea[name="book_desc"]').val("第三版特別涵蓋 Windows Phone 專案讓你的物件充分運用抽象化與繼承的強大威力C#、XAML 與 .NET 編程實務的學習指南探索 MVVM（Model-View-ViewModel）設計模式的絕妙奧義『假如你想要以趣味橫生的方式深入學習 C#，這本書正是為你量身訂製的。』 － Andy Parker，C# 程式設計新手『《深入淺出 C#》將引領各種背景的初學者一窺 C# 與 .NET Framework 的堂奧，並與之建立一段富有成效的長遠關係。』 — Chris Burrows，Microsoft C# Compiler 團隊的開發者『《深入淺出 C#》讓我馬上能夠充分掌控我的第一個大型 C# 開發專案 — 讚啦，強力推薦。』 — Shalewa Odusanya，Technical Account Manager，Google你將從本書學到什麼？《深入淺出 C#》是一段有關以 C#、XAML、.NET Framework 與 Visual Studio IDE 進行編程的完整學習體驗。本書專為你的大腦量身訂製，從第一章開始便讓你充分融入，全心參與，在當中，你將打造一支功能完整的電玩遊戲。之後，你會學到有關類別與物件導向編程、繪製圖形與動畫、使用 LINQ 查詢資料以及將資料序列化到檔案的一切知識。另外，你將透過建造真實專案、實際打造遊戲及解決謎題來學習各種知識。完成本書閱讀之際，你將成為一位值得信賴的 C# 程式設計師，並且能夠充分地享受這項工作所帶來的樂趣。這本書為何如此與眾不同？ 我們認為你的時間寶貴，不應該將它浪費在與新概念周旋不下的窘境中。運用認知科學與學習理論的最新研究成果，《深入淺出 C#》採取專為大腦運作而設計的豐富視覺化風格，而不是令你昏昏欲睡的冗贅敘述。");
		})
	})
	</script>
