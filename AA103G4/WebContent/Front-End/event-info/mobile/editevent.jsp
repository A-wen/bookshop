<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
    <head>
    
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${pageStr.title}</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
        <!--  日期選擇器的css -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-datetimepicker.min.css">
        <!-- jqueryUI的css -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css">
        <!-- jQuery -->
        <script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
        <!-- bootstrap-datetimepicker -->
        <script src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.zh-TW.js"></script>
        <!-- Bootstrap JavaScript -->
        <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
        <!-- jQuery UI 要在BootStrap後面-->
        <script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>
        <!-- CKEDITER的JS -->
        <script src="http://cdn.ckeditor.com/4.5.10/basic/ckeditor.js"></script>
        <!-- jQuery Validation Plugin -->
        <script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
        <script src="<%=request.getContextPath()%>/js/messages_zh_TW.js"></script>
        <style>
        .form-control::-moz-placeholder {
        color: #80A8ED;
        font-style: italic;
        }
        
        .form-control:-ms-input-placeholder {
        color: #80A8ED;
        font-style: italic;
        }
        
        .form-control::-webkit-input-placeholder {
        color: #80A8ED;
        font-style: italic;
        }
        
        #eventPic {
        width: 700px;
        height: 350px;
        }
        
        #eventPic img {
        max-height: 100%;
        max-width: 100%;
        }
        
        .ui-widget-overlay {
        opacity: 0.8;
        background: "black";
        }
        
        
        /*下面是錯誤提示用的css*/
        select.error,
        textarea.error,
        input.error {
        color: #FF0000;
        }
        
        label.error {
        color: red !important;
        font-weight: normal !important;
        }
        
        #e_Img-error {
            
        margin-left: 20px;
        margin-top: 7px;
        }
        
        </style>
    </head>
    <body>
        <h1> 建立讀書會活動表單 </h1>
        <div class="col-lg-10">
            <div class="container-fluid">
                <div class="col-lg-10">
                    <div class="well bs-component">
                        <fieldset>
                            <div id="legend" class="">
                                <legend class="">建立活動</legend>
                                
                            </div>
                            <!-- 上傳宣傳圖片 -->
                            <form id="uploadImg" class="form-horizontal" method="post" ACTION="<%=request.getContextPath()%>/Front-End/event-info/imgupload.do" enctype="multipart/form-data">
                                <div class="form-group row">
                                    <label for="eventName" class="col-lg-2 control-label">宣傳圖片</label>
                                    <div class="col-lg-8">
                                        <div class="input-group">
                                            <span class="input-group-btn" id="uploadSpan">
                                                <input id="fileInput" type="file" name="upImg" accept="image/jpeg,image/png" style="display:none"/>
                                                <button id="uploadBtn" type="button" class="btn btn-primary" name="action" value="${pageStr.action}"> <i class="glyphicon glyphicon-cloud-upload" onclick="$('#fileInput').click();"></i> 上傳圖片</button>
                                                <input name="action" type="hidden" value="uploadEventImg" />
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </form>
<%--                             <form id="eventInfoForm" class="form-horizontal" method="post" ACTION="<%=request.getContextPath()%>/Front-End/event-info/eventinfo.do">
        --%>
        		<form id="eventInfoForm" class="form-horizontal" method="post" ACTION="<%=request.getContextPath()%>/eventinfo/eventinfo.do">
                                                     
                                
                                <!-- 圖片預覽(圖片上傳後才會出現) 另外於此有個隱藏欄位儲存檔案名稱 -->
                                <div class="form-group row" id="previewImg" style="display:none">
                                    <label for="eventName" class="col-lg-2 control-label"></label>
                                    <div class="col-xs-8">
                                        <div id="eventPic">
                                            <img class="img-responsive" src="${pageStr.imgSrc}" alt="圖片預覽" >
                                        </div>
                                    </div>
                                </div>
                                <!-- 活動名稱欄位 -->
                                <div class="form-group row">
                                    <label for="eventName" class="col-lg-2 control-label">活動名稱</label>
                                    <div class="col-xs-8">
                                        <input class="form-control required" maxLength="20" type="text" placeholder="請輸入活動名稱，最多20個字" id="eventName" name="e_Name" value="${Event_InfoVO.e_Name}">
                                    </div>
                                </div>
                                <!-- 活動狀態欄位 -->
                                <div class="form-group">
                                    <div id="col-lg-4">
                                        <label for="select" class="col-lg-2 control-label" data-width="auto">活動狀態</label>
                                        <div class="col-lg-2">
                                            <select class="form-control" id="select" name="e_Status">
                                                <option value="1">報名中</option>
                                                <option value="2">已額滿</option>
                                                <option value="3">已結束</option>
                                                <option value="4">取消</option>
                                            </select>
                                        </div>
                                    </div>
                                    <!-- 活動人數欄位  -->
                                    <div id="col-lg-4">
                                        <label for="eventLimit" class="col-lg-2 control-label" data-width="auto">活動人數上限</label>
                                        <div class="col-lg-2">
                                            <input class="form-control digits required" type="number" placeholder="" id="eventLimit" name="e_Limit" id="e_Limit" value="${Event_InfoVO.e_Limit}">
                                        </div>
                                        <div class="help-block col-lg-2"> 0:不限制</div>
                                    </div>
                                </div>
                                <!-- 活動時間欄位 -->
                                <div class="form-group row">
                                    <label for="eventDate" class="col-lg-2 control-label">活動時間</label>
                                    <div class="col-xs-8">
                                        <input type="text" placeholder="格式：2016-09-30 10:00" class="form-control form_datetime" id="eventDate" name="eventDate" >
                                    </div>
                                </div>
                                
                                <!-- 活動地點欄位 -->
                                <div class="form-group row">
                                    <label for="eventLoc" class="col-lg-2 control-label">活動地點</label>
                                    <div class="col-xs-8">
                                        <input class="form-control required" type="text" placeholder="中壢資策會" id="eventLoc" name="e_Loc" value="${Event_InfoVO.e_Loc}">
                                    </div>
                                </div>
                                <!-- 活動地址欄位 -->
                                <div class="form-group row">
                                    <label for="eventAddr" class="col-lg-2 control-label">活動地址</label>
                                    <div class="col-xs-8">
                                        <input class="form-control required" type="text" placeholder="桃園市中壢區中大路300號" id="eventAddr" name="e_Addr" value="${Event_InfoVO.e_Addr}">
                                    </div>
                                </div>
                                <!-- 活動簡介欄位 -->
                                <div class="form-group">
                                    <label for="eventIntro" class="col-lg-2 control-label">活動簡介</label>
                                    <div class="col-lg-8">
                                        <textarea class="form-control required" rows="3" id="eventIntro" name="e_Intro" >${Event_InfoVO.e_Intro}</textarea>
                                        <span class="help-block">您可於此輸入關於您活動的簡短介紹</span>
                                    </div>
                                </div>
                                <!-- 活動說明欄位 -->
                                <div class="form-group">
                                    <label for="eventDesc" class="col-lg-2 control-label">活動說明</label>
                                    <div class="col-lg-8">
                                        <textarea class="form-control required" rows="10" id="eventDesc" name="e_Desc">${Event_InfoVO.e_Desc}</textarea>
                                        <script>
                                        CKEDITOR.replace('e_Desc');
                                        </script>
                                        <span class="help-block">您可於此輸入關於您活動完整說明</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-8 col-lg-offset-2" id="formEnd">
                                    		<input type=text" name="g_No" id="g_No" value="${param.g_No}">
										<!-- 隱藏欄位 -->
										<!-- 記活動編號 -->
                                    		<input type="hidden" name="e_No" id="e_No" value="${Event_InfoVO.e_No}"> 
										<!-- 記原檔名 -->
										<input type="hidden" name="ori_e_Img" id="ori_e_Img" value="${Event_InfoVO.e_Img}"> 
                                    		<!-- 記新檔名 -->
                                    		<input type="hidden" name="e_Img" id="e_Img"> 
                                    		<!-- 記讀書會編號 -->
                                    		<%-- <input type="hidden" name="g_No" id="g_No" value="${Event_InfoVO.g_No}"> --%> 
										<!-- 按鈕 -->
                                        <button type="reset" class="btn btn-default"><i class="glyphicon glyphicon-remove-sign"></i> 取消</button>
                                        <button type="submit" class="btn btn-primary" name="action" value="${pageStr.action}"> <i class="glyphicon glyphicon-ok-sign"></i> 確定</button>
                                        <button type="button" class="btn btn-warning" id="check">檢查</button>
                                    </div>
                                </div>
                            </form>
                        </fieldset>
                    </div>
                </div>
            </div>
        </div>
        <script>
        //jquery監視下載完成行為
        $().ready(function() {

            //AJAX上傳圖片功能
            $("#uploadImg").on('submit', (function(e) {
                e.preventDefault(); //取消事件的預設行為
                $.ajax({
                    url: $(this).attr('action'),
                    type: "post",
                    data: new FormData(this),
                    contentType: false,
                    dataType: "json", //這是預期收到的回應
                    processData: false, // To send DOMDocument or non processed data file it is set to false
                    success: function(data) {
                    	//成功後，清除檔案框的值,並且將隱藏的圖片框顯示出來
                        $('#uploadImg').value = "";
                        var url = "<%=request.getContextPath()%>/uploaded/temp/" + data.msg;
                        $('#previewImg').show();
                        $('#eventPic img').attr('src', url);
                        $('#formEnd #e_Img').attr('value', data.msg);
                    },
                    error: function(data) {
                        alert(data.msg);
                    }
                });
            }));
            
            //上傳檔案按鈕，點擊時觸發檔案標籤的點擊事件開啟選擇檔案上傳
            $("#uploadBtn").click(function() {
                $('#fileInput').trigger('click');
            });

            //檢視檔案上傳標籤。當檔案被選取後立刻submit
            $('#fileInput').change(function() {
                $('#uploadImg').submit();
            });

            //日期選擇           
            $("#eventDate").datetimepicker({
                format: 'yyyy-mm-dd hh:ii',
                autoclose: true,
                pickerPosition: 'bottom-right',
                todayHighlight: 'true',
                language: "zh-TW"
            }).datetimepicker('setStartDate', '<fmt:formatDate value="${date}" pattern="yyyy-MM-dd hh:mm"/>');
            $('#eventDate').val('<fmt:formatDate type="both" pattern="YYYY-MM-dd HH:mm" value="${Event_InfoVO.e_Date}" />'); 
            //直接在html裡設定初值都會是00:00，所以用jquery塞
            
            //驗證器自定方法
            //1. 日期格式
            $.validator.addMethod("dateTime", function(value, element) {
                var stamp = value.split(" ");
                var validDate = !/Invalid|NaN/.test(new Date(stamp[0]).toString());
                var validTime = /^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$/i.test(stamp[1]);
                return this.optional(element) || (validDate && validTime);
            }, "時間格式錯誤"); //錯誤訊息
            //2. 活動時間 //驗證條件還要在改，如果狀態是取消或是已結束，則不檢查
            $.validator.addMethod("minDate", function(value, element) {
                var curDate = new Date();
                //var inputDate = new Date(value); //IE不能這樣驗證...
                var a = value.toString().split(" ");
                var d = a[0].split("-");
                var t = a[1].split(":");
                var inputDate = new Date(d[0], (d[1] - 1), d[2], t[0], t[1], 0);
                if (inputDate >= curDate)
                    return true;
               	if ($('#select'>=3))
               		return true;
                return false;
            }, "請輸入未來的日期時間"); //錯誤訊息

            //eventInfoForm表單驗證
            $("#eventInfoForm").validate({
                //debug: true,  //jquery驗證器的debug模式開關
                ignore: [],
                rules: {
                    eventDate: { //抓id=eventDate的欄位來驗證
                        required: true,
                        dateTime: true,
                        minDate: true
                    },
                    e_Img: {
                        required: true,
                    }
                },
                errorPlacement: function(error, element) {
                    //加到下一個td中
                    if (element.attr("name") == 'e_Img') {
                    	$('#e_Img-error').remove();
                        error.insertAfter('#uploadSpan');
                    } else {
                        error.insertAfter(element);
                    }
                },
                messages: {
                    e_Img: "請上傳一張宣傳圖片",
                    e_Limit: "請輸入大於或等於零的數字"
                }
            });

            //初始化設定
            //1. 當預覽圖片的src不是空時載入圖片
            if($('#previewImg').val.length!=0){
            	$('#previewImg').show();
            }
            //2. 活動狀態設定
            $('#select').val(<c:out value="${Event_InfoVO.e_Stat}" default="1" />);
            if($('#select').val()>2)  //當狀態是取消||已結束時，不給使用者改狀態
            	$('#select').attr('disabled', 'disabled');
            
        });

        </script>
    </body>
</html>