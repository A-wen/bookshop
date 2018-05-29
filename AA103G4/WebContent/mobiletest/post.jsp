<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/api/ShoppingCart/post">
		<input type="text" name="mem_no">
		<input type="submit">
	
	</form>
</body>
</html>