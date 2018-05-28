<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
    <title>購物車</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <!-- Lobibox CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
    <style>
    
    #blink {
        margin: 20px;
    }
    
    .media-heading {
        margin-left: 10px;
        margin-top: 15px;
        line-height: 110%;
    }
    
    .breadcrumb {
        margin-top: 10px;
        margin-bottom: 5px;
        background: #ffffff;
    }
    
    body {
        background: #EEEEEE;
    }
    
    .container {
        background: #ffffff;
        vertical-align: middle;
    }
    
    .pcs {
        margin-top: 30px;
    }
    
    .price {
        margin-top: 35px;
    }
    
    tr td .media .thumbnail {
        width: 80px;
        margin-top: 1px;
        margin-bottom: 1px;
        padding: 5px;
    }
    
    .promotion {
        color: red;
    }
    </style>
    <!--     <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script> -->
</head>

<body>
	<jsp:include page="/Front-End/header.jsp" />
    <!-- 麵包屑開始 -->
    <div class="container">
        <div class="row">
            <ol class="breadcrumb">
                <li>
                    <a href="<%=request.getContextPath()%>/Front-End/index.jsp">首頁</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/Front-End/book/booklist.jsp">書城</a>
                </li>
                <li class="active">購物車</li>
            </ol>
        </div>
    </div>
    <!-- 麵包屑結束 -->

    <!-- 購物車表格 -->
    <div class="container">
        <form action="<%=request.getContextPath()%>/Front-End/cart/checkout" method="post">
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <table class="table table-hover">
                        <!--購物車表頭 -->
                        <thead>
                            <tr>
                                <th>書籍名稱</th>
                                <th>購買數量</th>
                                <th class="text-center">優惠價</th>
                                <th class="text-center">小計</th>
                                <th> </th>
                            </tr>
                        </thead>
                        <!-- 購物車主體 -->
                        <tbody>
                            <c:forEach var="vo" items="${itemList}">
                                <tr class="itemRow">
                                    <td class="col-sm-8 col-md-6">
                                        <div class="media">
                                            <a class="thumbnail pull-left" href="<%=request.getContextPath()%>/Front-End/book/bookpage/${vo.b_No}"> <img class="media-object bookcover" src="<%=request.getContextPath()%>/img?book_no=${vo.b_No}"> </a>
                                            <div class="media-body">
                                                <h4 class="media-heading"><a href="<%=request.getContextPath()%>/Front-End/book/bookpage/${vo.b_No}">${vo.b_Name}</a></h4>
                                                <h5 class="media-heading"> 作者 <a href="#">${vo.b_Author}</a></h5>
                                                <c:if test="${not empty p_Name }">
                                                    <h5 class="media-heading promotion"> ${vo.p_Name}</h5>
                                                </c:if>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="col-sm-1 col-md-1" style="text-align: center">
                                        <input type="text" class="form-control pcs number required" id="PCS" name="${vo.b_No}" value="${vo.b_Qty}" data-max="${vo.b_Stock}">
                                    </td>
                                    <td class="col-sm-1 col-md-1 text-center ">
                                        <div class="price
                                        ">
                                            <c:choose>
                                                <c:when test="${vo.p_Price==0}">
                                                    <strong>NT$ <span class="b_price">${vo.b_Price}</span> 元</strong>
                                                </c:when>
                                                <c:when test="${vo.p_Price>0}">
                                                    <s>NT$ <span>${vo.b_Price}</span> 元</s>
                                                    </br>
                                                    <strong class="promotion">NT$ <span class="b_price">${vo.p_Price}</span> 元</strong>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </td>
                                    <td class="col-sm-1 col-md-1 text-center price">
                                        <div class="price">
                                            <strong>NT$
                                        <c:choose>
                                        <c:when test="${vo.p_Price==0}">
                                        <span class="sum_b_price"><c:out value="${vo.b_Qty*vo.b_Price}" /></span>
                                        </c:when>
                                        <c:when test="${vo.p_Price>0}">
                                        <span class="sum_b_price"><c:out value="${vo.b_Qty*vo.p_Price}" /></span>
                                        </c:when>
                                        </c:choose>
                                        元</strong>
                                        </div>
                                    </td>
                                    <td class="col-sm-1 col-md-1">
                                        <button type="button" class="btn btn-danger pcs removeBtn">
                                            <span class="glyphicon glyphicon-remove "></span>移除</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <!-- 購物車表尾 -->
                        <tfoot>
                            <tr>
                                <td> </td>
                                <td class="text-right" colspan="2">
                                    <h3>總計</h3> </td>
                                <td class="text-center" colspan="2">
                                    <h3><strong> NT$ <span id="order_total"></span> 元</strong></h3>
                                </td>
                                <tr>
                                    <td> </td>
                                    <td> </td>
                                    <td> </td>
                                    <td>
                                        <button type="button" class="btn btn-default" onclick="window.location.href='<%=request.getContextPath()%>/Front-End/index.jsp'">
                                            <span class="glyphicon glyphicon-shopping-cart"></span> 繼續購物
                                        </button>
                                        
                                        
                                    </td>
                                    <td>
                                    	<input type="hidden" name="act" value="toPayment" />
                                        <button type="submit" class="btn btn-success">
                                            結帳 <span class="glyphicon glyphicon-play"></span>
                                        </button>
                                    </td>
                                </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </form>
    </div>
    
    <jsp:include page="/Front-End/footer.jsp" />
    
    <!-- jQuery -->
    <script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
    <!-- 下面都是會依賴到jQuery的，必須在jquery後引用 -->
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/lobibox.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
    <script src="<%=request.getContextPath()%>/js/messages_zh_TW.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.mask.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.blockUI.js"></script>
    <script>
    //註冊啟動後事件
    $().ready(function() {
        sumPrice(); //畫面讀完後計算一次價格
        $('.itemRow input').mask('0000');
        $('.itemRow input').each(function() {
            $(this).change(function() {
                var currentBNo = $(this).attr('name');
                var currentTR = $(this).closest('tr');
                if ($(this).val() < 1) { //輸入0的狀況
                    Lobibox.confirm({
                        title: "訊息",
                        msg: "是否要從購物車刪除此本書籍",
                        buttons: {
                            yes: {
                                'class': 'btn btn-danger',
                                text: '確定'
                            },
                            no: {
                                'class': 'btn btn-default',
                                text: '取消'
                            }
                        },
                        callback: function(lobibox, type) {
                            if (type === 'yes') {
                                $.ajax({
                                    url: '<%=request.getContextPath()%>/Front-End/Cart/update.do',
                                    data: {
                                        act: 'del',
                                        item: currentBNo
                                    },
                                    type: 'GET',
                                    dataType: "json",
                                    beforeSend: function() {
                                        alert();
                                        $.blockUI({
                                            message: '<h1><img src="<%=request.getContextPath()%>/Front-End/img/Loading.gif" /> 更新購物車</h1>'
                                        });
                                    },
                                    complete: function() {
                                    	$.unblockUI();
                                    },
                                    success: function(jsonData) {
                                        if (jsonData.result == 'success') {
                                            if (jsonData.effect_rows == "1") {
                                                var lobibox = createAlert('success', '提示', '刪除成功');
                                                lobibox.show();
                                                currentTR.remove();
                                            }
                                            if (jsonData.effect_rows == "0") {
                                                var lobibox = createAlert('error', '錯誤', '您已在其他瀏覽器上刪除了這本書');
                                                lobibox.show();
                                                currentTR.remove();
                                            }
                                            sumPrice();
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
                            }
                        }
                    });
                    return;
                }
                if (($(this).val() > 0) && ($(this).val() < $(this).attr('data-max'))) {
                    var itemStr = currentBNo+':'+$(this).val();
                    console.log(itemStr);
                    $.ajax({
                        url: '<%=request.getContextPath()%>/Front-End/Cart/update.do',
                        data: {
                            act: 'update',
                            item: itemStr
                        },
                        type: 'GET',
                        dataType: "json",
                
                        beforeSend: function() {
                            $.blockUI({
                                message: '<h1><img src="<%=request.getContextPath()%>/Front-End/img/Loading.gif" /> 更新購物車</h1>'
                            });
                        },
                        complete: function() {
                            $.unblockUI();
                            
                        },
                        success: function(){
                        	sumPrice();
                        },
                        error: function(xhr, status, error) {
                            var lobibox = createAlert('error', '錯誤', '網路錯誤');
                            lobibox.show();
                        }
                    });
                    return;
                }
            });
        });
        //點擊刪除商品
        $('.removeBtn').each(function() {
            $(this).click(function() {
                //因為按鈕跟書號,數量..不是同一層，所以要先回到上一層在找才找的到
                var currentTR = $(this).closest('tr');
                var currentBNo = $(this).closest('tr').find('input').attr('name');
                Lobibox.confirm({
                    title: "訊息",
                    msg: "是否要從購物車刪除此本書籍",
                    buttons: {
                        yes: {
                            'class': 'btn btn-danger',
                            text: '確定'
                        },
                        no: {
                        	'class': 'btn btn-default',
                        	text: '取消'
                        }
                    },
                    callback: function(lobibox, type) {
                        if (type === 'yes') {
                            $.ajax({
                                url: '<%=request.getContextPath()%>/Front-End/Cart/update.do',
                                data: {
                                    act: 'del',
                                    item: currentBNo
                                },
                                type: 'GET',
                                dataType: "json",
                                beforeSend: function() {
                                	$.blockUI({
                                        message: '<h1><img src="<%=request.getContextPath()%>/Front-End/img/Loading.gif" /> 更新購物車</h1>'
                                    });
                                },
                                complete: function() {
                                 	$.unblockUI();
                                },
                                success: function(jsonData) {
                                    if (jsonData.result == 'success') {
                                        if (jsonData.effect_rows == "1") {
                                            var lobibox = createAlert('success', '提示', '刪除成功');
                                            lobibox.show();
                                            currentTR.remove();
                                        }
                                        if (jsonData.effect_rows == "0") {
                                            var lobibox = createAlert('error', '錯誤', '您已在其他瀏覽器上刪除了這本書');
                                            lobibox.show();
                                            currentTR.remove();
                                        }
                                        sumPrice();
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
                        }
                    }
                });
                return;
            });
        });
    });
    //計算購物價格
    function sumPrice() {
        var subtotal = 0;
        $('.itemRow').each(function() {
            var itemQty = $(this).find('input').val();
            var price = $(this).find('.b_price').text();
            var sum = itemQty * price;
            $(this).find('.sum_b_price').text(itemQty * price);
            subtotal += itemQty * price;
        });
        //$('#order_sum').text(subtotal);
        //TODO扣掉折價券
        $('#order_total').text(subtotal);

    }

    //用來產生漂亮alert用的
    function createAlert(type, title, msg) {
        var msgbox = Lobibox.alert(type, {
            width: 300,
            msg: msg
        });
        msgbox.setTitle(title);
        return msgbox
    }
    </script>
</body>

</html>
