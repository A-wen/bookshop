<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <title>Book思議  | 讀書會活動列表</title>
    <link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />  
    <!-- CSS -->    
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <link id="switcher" href="<%=request.getContextPath()%>/css/searech-theme.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/searchstyle.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css">
    <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'> 
    <link href="<%=request.getContextPath()%>/css/all.css"	rel="stylesheet">
    <style>
      .eImg{
		padding-top: 80px;
/* 		margin-bottom: 40px; */
		max-width: 100%;
		padding-bottom: 90px;
		width: auto;
      }
    
    </style> 
  </head>
  <body class="productPage">  
	<jsp:include page="/Front-End/header.jsp" />	
  <!-- product category -->
  <section id="aa-product-category">
    <div class="container">
      <div class="row">
        <div class="col-lg-9 col-md-9 col-sm-8 col-md-push-3">
          <div class="aa-product-catg-content">
            <div class="aa-product-catg-head">
              <div class="aa-product-catg-head-left">
                <form action="<%=request.getContextPath()%>/Front-End/event-info/list" class="aa-show-form">
                  <input type="hidden" name="act" value="list">
                  <label for="rows">每頁數量</label>
                  <select name="rows" onchange="this.form.submit()" id="rows">
                    <option value="9"}>9</option>
                    <option value="12">12</option>
                    <option value="24">24</option>
                  </select>
                </form>
              </div>	
              <div class="aa-product-catg-head-right">
              	<span>共有${eventCount}筆資料，第${currentPage}/${pages}頁</span>
                <a id="grid-catg" href="#"><span class="fa fa-th"></span></a>
                <a id="list-catg" href="#"><span class="fa fa-list"></span></a>
              </div>
            </div>
            <div class="aa-product-catg-body">
              <ul class="aa-product-catg">
                <!-- JSTL迭代取物件，然後用EL印出物件屬性 -->
                <c:forEach var="eventVO" items="${events}" begin="0" end="57">
                <!-- 一個活動項目開始 -->
                <li>
                  <figure>
                    <a class="aa-product-img" href="event/${eventVO.e_No}">
                    	<img class="eImg" src="eventImg/${eventVO.s_gro_info.s_gro_no}/${eventVO.e_Img}" 
                    		 alt="${eventVO.s_gro_info.s_gro_name}宣傳圖片">
                    </a>
                    <a class="aa-add-card-btn" href="event/${eventVO.e_No}" style="bottom: 0%"><span class="glyphicon glyphicon-info-sign"></span>查看活動內容</a>
                    <figcaption>
                      <h4 class="aa-product-title"><a href="event/${eventVO.e_No}">${eventVO.e_Name}</a></h4>
                      <span>活動時間：</span><span class="aa-product-price"><fmt:formatDate type="both" pattern="MM-dd HH:mm" value="${eventVO.e_Date}" /></span>
                      <br>${eventVO.event_Status.s_Exp}
			          <p class="aa-product-descrip">
                      	<br>活動地點：${eventVO.e_Loc}<br>
                      	活動簡介：<br>${eventVO.e_Intro}
                      </p>
                    </figcaption>
                  </figure>
				  <div class="aa-product-hvr-content">
                    <a href="#" data-eno="${eventVO.e_No}" data-toggle="tooltip" data-placement="top" title="我要參加" class="going"><span class="glyphicon glyphicon-plus"></span></a>
                    <a href="#" data-eno="${eventVO.e_No}" data-toggle="tooltip" data-placement="top" title="可能參加" class="maybey"><span class="glyphicon glyphicon-star-empty"></span></a>
                  </div>
                  <c:if test="${eventVO.event_Status.s_No==2}">
                  	<span class="aa-badge aa-sold-out" href="#">已額滿</span> 
                  </c:if>
                                          
                </li>
                <!-- 一個活動項目結束 -->   
                </c:forEach>	                 
              </ul>
            </div>
			<!-- 頁數列 -->
            <div class="aa-product-catg-pagination" style="margin-bottom:50px">
              <nav>
                <ul class="pagination">
                  <c:if test="${currentPage!=1}">
					<li>
                    	<a href="<%=request.getContextPath()%>/Front-End/event-info/list?act=list&page=${currentPage-1}" 
                       	   class="pageLink" aria-label="Previous">
                      		<span aria-hidden="true">&laquo;</span>
                    	</a>
                  	</li>
                  </c:if>
                  <c:forEach varStatus="page" begin="1" end="${pages}">
                  	<li>
                  		<a href="<%=request.getContextPath()%>/Front-End/event-info/list?act=list&page=${page.count}"
                  		   class="pageLink">
                  			${page.count}
                  		</a>
                  	</li>
                  </c:forEach>
                  <c:if test="${currentPage!=pages}">
                  <li>
                    <a href="<%=request.getContextPath()%>/Front-End/event-info/list?act=list&page=${currentPage+1}" 
                       class="pageLink" aria-label="Next">
                      	<span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                  </c:if>
                </ul>
              </nav>
            </div>
          </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-4 col-md-pull-9">
          <aside class="aa-sidebar">
            <!-- single sidebar -->
            <div class="aa-sidebar-widget">
              <h3>條件過濾</h3>
              <ul class="aa-catg-nav">
                <li><a href="#">即將進行的活動</a></li>
                <li><a href="">全部活動</a></li>
              </ul>
            </div>
          </aside>
        </div>
      </div>
    </div>
  </section>
  <div style="margin-top:500px"></div>
  <!-- / product category -->
  <jsp:include page="/Front-End/footer.jsp" />
  <script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/lobibox.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/jquery.blockUI.js"></script>
  <script>
  	$().ready(function(){
  		//設定每頁數量的預設值
  		$('#rows').val(<c:out value="${rows}" default="9" />);
  		//設定分頁的超連結(如果每頁數量不是9時，就加上每頁列數)
  		$('.pageLink').each(function(){
  			if($('#rows').val()!=9){
  				var _href = $(this).attr("href");
  				$(this).attr("href",_href+'&rows='+$('#rows').val());
  			}
  		});
  		
  		$('.going').each(function(){
  			var eno = $(this).attr('data-eno');
  			$(this).click(function(){
                $.ajax({
                    url: '<%=request.getContextPath()%>/Front-End/event-info/join',
                    data: {
                    	action: '1',
                    	event: eno
                    },
                    type: 'POST',
                    dataType: "json",
                    beforeSend: function() {
                        $.blockUI({
                            message: '<h1><img src="<%=request.getContextPath()%>/Front-End/img/Loading.gif" />資料傳輸中</h1>'
                        });
                    },
                    complete: function() {
                    	$.unblockUI();
                    },
                    success: function(jsonData) {
                        if (jsonData.result == 'success') {
                            var lobibox = createAlert('success', '通知', jsonData.msg);
                            lobibox.show();
                        }
                        if (jsonData.result == 'fail') {
                            var lobibox = createAlert('error', '錯誤', jsonData.msg);
                            lobibox.show();
                        }
                    },
                    error: function(xhr, status, error) {
                        var lobibox = createAlert('error', '錯誤', '網路錯誤');
                        lobibox.show();
                    }
                });
  			});
  		});

  		$('.maybey').each(function(){
  			var eno = $(this).attr('data-eno');
  			$(this).click(function(){
                $.ajax({
                    url: '<%=request.getContextPath()%>/Front-End/event-info/join',
                    data: {
                    	action: '2',
                    	event: eno
                    },
                    type: 'POST',
                    dataType: "json",
                    beforeSend: function() {
                        $.blockUI({
                            message: '<h1><img src="<%=request.getContextPath()%>/Front-End/img/Loading.gif" />資料傳輸中</h1>'
                        });
                    },
                    complete: function() {
                    	$.unblockUI();
                    },
                    success: function(jsonData) {
                        if (jsonData.result == 'success') {
                            var lobibox = createAlert('success', '通知', jsonData.msg);
                            lobibox.show();
                        }
                        if (jsonData.result == 'fail') {
                            var lobibox = createAlert('error', '錯誤', jsonData.msg);
                            lobibox.show();
                        }
                    },
                    error: function(xhr, status, error) {
                        var lobibox = createAlert('error', '錯誤', '網路錯誤');
                        lobibox.show();
                    }
                });
  			});
  		});
  	});
    //用來產生漂亮alert用的
    function createAlert(type, title, msg) {
        var msgbox = Lobibox.alert(type, {
            width: 300,
            msg: msg
        });
        msgbox.setTitle(title);
        return msgbox
    }
    //轉換列表用的
    $("#list-catg").click(function(e){
	  e.preventDefault(e);
	  $(".aa-product-catg").addClass("list");
	});
	$("#grid-catg").click(function(e){
	  e.preventDefault(e);
	  $(".aa-product-catg").removeClass("list");
	});
  </script>
  </body>
</html>