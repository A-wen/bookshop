	
	//左方讀書會的按鈕
$(document).ready(function() {
		$("#btn_Associations").click(function() {
			$("#btn_Associations_list").toggle(500);
		});
//左方會員的按鈕
//	$("#btn_Member").click(function() {
//		$("#btn_Member_list").toggle(500);
//	});
//左方已參加的讀書會的按鈕
	$("#myAssociations").click(function() {
		$("#Associations_join").toggle(500);
	});

//左方我建立的讀書會的按鈕

	$("#AssociationsCreate").click(function() {
		$("#My_Associations").toggle(500);
	});

		$("#more").click(function() {
			$("#post3").toggle(500);
		});
	});