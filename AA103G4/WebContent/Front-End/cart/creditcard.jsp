<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC >
<html>
<head>
	<link rel="Shortcut Icon" type="image/x-icon" href="<%=request.getContextPath()%>/Front-End/img/icon.ico" />
	<title>Book思議 | 信用卡結帳</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
	<script src="<%=request.getContextPath()%>/js/jquery-2.2.4.min.js"></script>
<%-- 	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script> --%>
	<script src="<%=request.getContextPath()%>/js/jquery.card.js"></script>
	<style>

		.credit-card-div  span {
    		padding-top:10px;
        }
		.credit-card-div img {
    		padding-top:30px;
		}
		.credit-card-div .small-font {
    		font-size:9px;
/*     		-webkit-transform : scale(0.75);  */
/*     		display : inline-block; */
		}
		.credit-card-div .pad-adjust {
    		padding-top:10px;
    		
    		
		}
	</style>
</head>
<body>
	<jsp:include page="/Front-End/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<div class="panel-default ">
					<div class="panel-heading">
						<h3 class="panel-title text-center" id="title">
								<font size="12">刷卡頁</font>
						</h3>
			  		</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container" style="margin-top:50px">

        <div class="row ">
          <div class="col-md-6 col-md-offset-3">
          	<div class="credit-card-div">
				<div class="panel panel-default" >
 					<div class="panel-heading">
      					<div class="row ">
              				<div class="col-md-12" style="margin-top:20px">
                  				<input type="text" class="form-control" placeholder="請輸入信用卡號碼" />
              				</div>
          				</div>
     				<div class="row " style="margin-top:20px">         
	                	<div class="col-sm-8">到期日</div> 
	                	<div class="col-sm-4">驗證碼</div>
     				</div>
     				<div class="row ">	
              			<div class="col-sm-4">
			            	<input type="text" class="form-control" placeholder="月" />
			            </div>
			         	<div class="col-sm-4">
			                <input type="text" class="form-control" placeholder="年" />
			            </div>
			        	<div class="col-sm-4">
			                <input type="text" class="form-control" placeholder="CCV" />
			            </div>
          			</div>
			     	<div class="row ">
			     		<div class="col-md-12 pad-adjust">
							<input type="text" class="form-control" placeholder="持卡人" />
						</div>
					</div>
     				<div class="row">
						<div class="col-md-12 pad-adjust">
    						<div class="checkbox">
    							<label>
      							<input type="checkbox" checked class="text-muted"> Save details for fast payments <a href="#"> learn how ?</a>
    							</label>
  							</div>
						</div>
     				</div>
       				<div class="row ">
            			<div class="col-md-6 col-sm-6 col-xs-6 pad-adjust">
                 			<input type="submit"  class="btn btn-danger" value="CANCEL" />
              			</div>
              		<div class="col-md-6 col-sm-6 col-xs-6 pad-adjust">
                  		<input type="submit"  class="btn btn-warning btn-block" value="PAY NOW" />
              		</div>
          		</div>
     
                   </div>
              </div>
              </div>
              <!-- CREDIT CARD DIV END -->
          </div>      
    </div>
        </div>
    <!-- CONATINER END -->
		

	

		


		
	</div>
	<jsp:include page="/Front-End/footer.jsp" />
	<script>
	$().ready(function(){
		$('form').card({
		    // a selector or DOM element for the container
		    // where you want the card to appear
		    container: '.card-wrapper', // *required*

		    // all of the other options from above
		    formSelectors: {
		        numberInput: 'input#number', // optional — default input[name="number"]
		        expiryInput: 'input#expiry', // optional — default input[name="expiry"]
		        cvcInput: 'input#cvc', // optional — default input[name="cvc"]
		        nameInput: 'input#name' // optional - defaults input[name="name"]
		    },
		});
		
		
		$('#title').click(function(){
			$('#account').val('java.aa103@gmail.com');
			$('#passWord').val('a123456');
		});
	});
	</script>
</body>
</html>
