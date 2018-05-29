<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>結帳</title>
    <link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.css" >
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css" >
    <!-- Theme color -->
    <link rel="stylesheet" id="switcher" href="<%=request.getContextPath()%>/css/searech-theme.css" >
    <!-- Main style sheet -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/searchstyle.css" >
    <!-- Google Font -->
    <link rel="stylesheet" href='https://fonts.googleapis.com/css?family=Lato' >
    <link rel="stylesheet" href='https://fonts.googleapis.com/css?family=Raleway' >
    <!-- Lobibox CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lobibox.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css"	>
</head>

<body>
    <jsp:include page="/Front-End/header.jsp" />
    <!-- Cart view section -->
    <section id="checkout">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="checkout-area">
                        <form action="<%=request.getContextPath()%>/Front-End/cart/checkout" method="post">
                            <div class="row">
                                <div class="col-md-7">
                                    <div class="checkout-left">
                                        <div class="panel-group" id="accordion">
                                            <!-- Shipping Address -->
                                            <div class="panel panel-default aa-checkout-billaddress">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
							                          <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            							運送地址
                          							</a>
                          							</h4>
                                                </div>
                                            <div id="#collapseOne" class="panel-collapse collapse in">
                                            	<div class="panel-body">
                                            		<div class="row">
                                            			<div class="col-md-6">
                                            				<div class="aa-checkout-single-bill">
                                                            	<input type="text" name="d_name" placeholder="姓名" id="d_name">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <button type="button" id="loadData" class="btn btn-info">讀取會員資料</button>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="aa-checkout-single-bill">
                                                                <input type="text" name="tel" placeholder="連絡電話" id="tel">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-8">
                                                            <div class="aa-checkout-single-bill">
                                                                <textarea cols="8" name="addr" rows="3" id="addr" placeholder="請輸入您的收貨地址"></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
	                            <div class="col-md-5">
	                                <div class="checkout-right">
	                                    <h4>購物明細</h4>
	                                    <div class="aa-order-summary-area">
	                                        <table class="table table-responsive">
	                                            <thead>
	                                                <tr>
	                                                    <th>書籍名稱</th>
	                                                    <th>優惠價</th>
	                                                </tr>
	                                            </thead>
	                                            <tbody>
	                                                <c:forEach var="book" items="${cart}">
	                                                <tr>
	                                                    <td>${book.b_Name} <strong> x  1</strong></td>
	                                                    <td> NT$ <span class="price">
	                              							<c:if test="${book.p_Price==0}">
	                              							${book.b_Price * book.b_Qty}
	                              							</c:if>
	                              							<c:if test="${book.p_Price!=0}">
	                              							${book.p_Price * book.b_Qty}
	                              							</c:if>
	                            							</span>
	                                                    </td>
	                                                </tr>
	                                                </c:forEach>
	                                            </tbody>
	                                            <tfoot>
	                                                <tr>
	                                                    <th>總計</th>
	                                                    <td><strong>NT$ <span id="total"></span></strong></td>
	                                                </tr>
	                                            </tfoot>
	                                        </table>
	                                    </div>
	                                    <h4>付款方式</h4>
	                                    <div class="aa-payment-method">
	                                        <label for="creditcard">
	                                        <input type="radio" id="creditcard" name="p_code" value="1"> 信用卡 </label>
	                                        <label for="cashdelivery">
	                                        <input type="radio" id="cashdelivery" name="p_code" value="2"> 貨到付款 </label>
	                                        <label for="ATM">
	                                        <input type="radio" id="ATM" name="p_code" value="3"> ATM轉帳 </label>
	<!--                                             <label for="paypal"> -->
	<!--                                                 <input type="radio" id="paypal" name="optionsRadios" checked> Via Paypal </label> -->
	<!--                                             <img src="https://www.paypalobjects.com/webstatic/mktg/logo/AM_mc_vs_dc_ae.jpg" border="0" alt="PayPal Acceptance Mark"> -->
	                                        <input type="hidden" name="act" value="toCheckout" />
	                                        <input type="submit" value="結帳" class="aa-browse-btn">
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- / Cart view section -->
    <jsp:include page="/Front-End/footer.jsp" />
    <script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/lobibox.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.card.js"></script>
    <script>
    $().ready(function(){
    	sumTotal();
    	$('#loadData').click(function(){
    		$.ajax({
    			url: '<%=request.getContextPath()%>/member/MemServlet.do',
    			type: 'POST',
    			data: {action:'getInfo'},
    			dataType: "json",
    			success: function(jsonData){
    				if(jsonData.result==true){
    					$('#d_name').val(jsonData.memName);
    					$('#tel').val(jsonData.memTel);
        				Lobibox.alert("success", {
        					title: '提示',
        					msg: "資料讀取成功！",
        					buttons: {
        						ok: {
        					        'class': 'btn btn btn-success',
        					        text: '確定',
        					        closeOnClick: true
        					    }
        					}
        				});
    					
    				}
    			},
    			error: function(){
    				Lobibox.alert("error", {
    					title: '錯誤提示',
    					msg: "網路連線錯誤！",
    					buttons: {
    						ok: {
    					        'class': 'btn btn btn-info',
    					        text: '確定',
    					        closeOnClick: true
    					    }
    					}
    				});
    			}
    		});
    	});
    });
    
    function sumTotal() {
        var sum = 0;
        $(".price").each(function() {
            sum += parseInt($(this).text());
        })
        $("#total").html(sum);
    }
    
    </script>
</body>

</html>

