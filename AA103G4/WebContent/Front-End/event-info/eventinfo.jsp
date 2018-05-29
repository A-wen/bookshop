<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="zh-Hant-TW">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
    <title>${event_InfoVO.e_Name}</title>
	<!-- 給FB抓資料用 -->
    <meta property="og:url"           content="<%=request.getContextPath()%>/Front-End/event-info/event/${event_InfoVO.e_No}" />
	<meta property="og:type"          content="website" />
	<meta property="og:title"         content="${event_InfoVO.e_Name}" />
	<meta property="og:description"   content="${event_InfoVO.e_Intro}" />
	<meta property="og:image"         content="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()%>/Front-End/event-info/eventImg/${event_InfoVO.s_gro_info.s_gro_no}/${event_InfoVO.e_Img}" />
	<!-- FB抓資料用 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">

	<!-- Footer用的CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css">
    <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'> 
    <style>
    .iframe-container {
        position: relative;
        width: 100%;
        padding-bottom: 56.25%;
        /* Ratio 16:9 ( 100%/16*9 = 56.25% ) */
    }
    .iframe-container > * {
        display: block;
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        margin: 0;
        padding: 0;
        height: 100%;
        width: 100%;
    }
    
    #count>button:nth-child(2) {
    	margin-top: 10px;
    }
    
    #fb{
    	margin-top: 0px;
    	margin-bottom: 10px;
    	margin-right: 0px;
    	padding-right: 0px;
    	text-align: right;
    }
    </style>
</head>

<body>	
	<jsp:include page="/Front-End/header.jsp" />
    <!-- 內容開始 -->
    <div class="container">
        <!-- 活動大圖開始 -->
        <div style="margin:20px 0px">
            <img class="img-responsive" 
            src="<%=request.getContextPath()%>/Front-End/event-info/eventImg/${event_InfoVO.s_gro_info.s_gro_no}/${event_InfoVO.e_Img}" 
            alt="活動宣傳圖" 
            style="width: 100%;"
            onerror="this.src='<%=request.getContextPath()%>/ErrorPage/fake.jpg'" > 
        </div>
        <!-- 活動大圖結束 -->
        <div class="row">
            <!-- Blog Post Content Column -->
            <div class="col-lg-8">
                <!-- Title -->
                <h1>${event_InfoVO.e_Name}</h1>
                <!-- Author -->
                <p class="lead">
                    by <a href="#">${event_InfoVO.s_gro_info.s_gro_name}</a>
                </p>
                <hr>
                <!-- Date/Time -->
                <p><span class="glyphicon glyphicon-time"></span> 活動時間：
                    <fmt:formatDate type="both" pattern="YYYY-MM-dd HH:mm" value="${event_InfoVO.e_Date}" />
                </p>
                <!-- Location -->
                <p><span class="glyphicon glyphicon-map-marker"></span> 活動地點：${event_InfoVO.e_Addr}(${event_InfoVO.e_Loc})</p>
                <hr>
                <div class="iframe-container">
                    <!-- embed code here -->
                    <div id="map-canvas">
                    </div>
                </div>
                <!-- 活動內容 -->
                <div>
                    <h1>活動內容說明</h1> ${event_InfoVO.e_Desc}
                </div>
            </div>
            <!-- 右側區塊開始 -->
            <div class="col-md-4">
                <!-- 來賓區塊開始 -->
                <div class="well ">
                    <h4>來賓</h4>
                    <div class="row ">
                        <div class="container-fluid">
                            <table class="table table-bordered table-hover text-center">
                                <thead>
                                    <tr>
                                        <th class="col-lg-5 text-center">參加</th>
                                        <th class="col-lg-5 text-center">可能參加</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td id="count_going"><c:out value="${event_Count.Going}" default="0" /></td>
                                        <td id="count_maybe"><c:out value="${event_Count.Maybe}" default="0" /></td>
                                    </tr>
                                    <tr>                                
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>  
                    <!-- FB分享按鈕 -->
                    <div class="container-fluid" id="fb">
						<div class="fb-share-button" data-href="<%=request.getContextPath()%>/Front-End/event-info/event/${event_InfoVO.e_No}" 
							 data-layout="button_count" data-size="large" data-mobile-iframe="true">
						<a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse">分享</a>
						</div>
					<!-- 參加活動按鈕 -->
					</div> 
                    <div class="row" >
						<div id="count" class="container-fluid" date-action="<c:out value="${event_MemVO.m_Status}" default="0" />">
							<button type="button" id="going" class="btn btn-primary btn-block join" 
									data-eno="${event_InfoVO.e_No}" date-action="">我要報名！
							</button>
							<button type="button" id="maybe" class="btn btn-primary btn-block join" 
									data-eno="${event_InfoVO.e_No}" date-action="">我想參加！
							</button>
						</div>	
                    </div>
                    <!-- /.row -->
                </div>
                <!-- 來賓區塊結束 -->
                <!-- 讀書會搜索區塊開始 -->
                <div class="well">
                    <h4>活動搜索</h4>
                    <div class="input-group">
                        <input type="text" class="form-control">
                        <span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<span class="glyphicon glyphicon-search"></span>
                        	</button>
                        </span>
                    </div>
                    <!-- /.input-group -->
                </div>
                <!-- 讀書會搜索區塊結束 -->
            </div>
        </div>
        <hr>
    </div>
    <div style="margin-top:80px"></div>
    <jsp:include page="/Front-End/footer.jsp" />

    <!-- jQuery -->
    <script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
    <!-- Bootstrap JavaScript -->
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <!-- GoogleMap api -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCE-MLsN7nYiVj9QK3f4d41ISKq3f8P4TU"></script>
    <script src="<%=request.getContextPath()%>/js/lobibox.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.blockUI.js"></script>
	
	<div id="fb-root"></div>
	<script>
		//FB分享用
		(function(d, s, id) {
		  var js, fjs = d.getElementsByTagName(s)[0];
		  if (d.getElementById(id)) return;
		  js = d.createElement(s); js.id = id;
		  js.src = "//connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v2.7&appId=293160204377550";
		  fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
		//google地圖用
		function initialize() {
		    $('map-canvas').css({
		        'width': '100%',
		        'height': '300px'
		    });
		    var add = '${event_InfoVO.e_Addr}';
		    geocoder = new google.maps.Geocoder();
		    geocoder.geocode({
		        'address': add
		    }, function(results, status) {
		
		        if (status == google.maps.GeocoderStatus.OK) {
		            var myOptions = {
		                    zoom: 18,
		                    center: results[0].geometry.location,
		                    mapTypeId: google.maps.MapTypeId.ROADMAP
		                }
		                // alert(results[0].geometry.location);
		            map = new google.maps.Map(document.getElementById("map-canvas"), myOptions);
		            var marker = new google.maps.Marker({
		                map: map,
		                position: results[0].geometry.location
		            });
		        } else {
		            alert("地址有誤，請通知客服人員");
		        }
		    });
		}
		google.maps.event.addDomListener(
		    window, 'load', initialize);
		
	  	$().ready(function(){
			//處理按鈕動作
			changeJoinBtn();
			$('.join').each(function(){
		  		$(this).click(function(){
					var action = $(this).attr('date-action');
					var eno = $(this).attr('data-eno');
		  			var btn = $(this);
		            $.ajax({
		                url: '<%=request.getContextPath()%>/Front-End/event-info/join',
		                data: {
		                 	action: action, //點擊動作編號
		                  	event: eno      //事件編號
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
		                        btn.attr('date-action',jsonData.status);
		                        $('#count').attr('date-action',action);	                        
		                        changeJoinBtn();
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
	    //改變參加按鈕動作
	    //三種狀況  1.已報名  2.想去 3.已取消(同預設)
	    function changeJoinBtn(){
	    	var action = $('#count').attr('date-action');
	    	//alert(typeof(action));
	    	$('.join').attr("disabled", false);
	    	switch(action){
	    	case "1":
	    		$('#going').html("已參加!").attr('date-action',1).attr("disabled", true)
	    			       .attr("class","btn btn-success btn-block join");
	    		$('#maybe').html("取消參加!").attr('date-action',0)
	    				   .attr("class","btn btn-danger btn-block join");
	    		break;
	    	case "2":
	    		$('#going').html("我要報名!").attr('date-action',1)
	    				   .attr("class","btn btn-primary btn-block join");
	    		$('#maybe').html("不想參加").attr('date-action',0)
	    				   .attr("class","btn btn-danger btn-block join");
	    		break;
	    	default:
	    		$('#going').html("我要報名!").attr('date-action',1)
	    				   .attr("class","btn btn-primary btn-block join");
	    		$('#maybe').html("我想參加!").attr('date-action',2)
	    				   .attr("class","btn btn-primary btn-block join");
	    		$('#count').attr('date-action',1);
	    		break;
	    	}
	    }
	</script>
</body>
</html>
