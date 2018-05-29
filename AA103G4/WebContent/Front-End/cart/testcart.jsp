<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>

<body>

	<h2> 寫會員編號到session </h2>
	<form action="<%=request.getContextPath()%>/templogin">
		會員編號:<input type="number" name="mem"></br>
		<input type="submit" value="寫會員編號">
	</form>
	</hr>
	<h2>新增購物車商品</h2>
	
	
    <form action="<%=request.getContextPath()%>/Front-End/Cart/update.do">
        <table>
            <tr>
                <th>測試方法</th>
                <td>
                    <select name="act">
                        <option value="add">新增書籍</option>
                        <option value="update">修改書籍</option>
                        <option value="del">刪除一本或多本書籍</option>
                        <option value="delAll">刪除該會員全部購物車書籍</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>
                    新增- 書籍編號:數量
                    <br> 刪除- 書籍編號:書籍編號:...
                </th>
                <td>
                    <input type="text" name="item">
                </td>
            </tr>
            <tr>
                <th>
                    會員編號
                </th>
                <td>
                    <input type="text" name="mem">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" name=""> </td>
            </tr>
        </table>
    </form>
    <hr>
    <h2>同步購物車測試</h2>
    </br>
    <form action="<%=request.getContextPath()%>/Front-End/Cart/synccart">
        <input type="text" name="mem">
        <input type="submit">
    </form>
    <hr>
    <h2>前端購物車功能測試</h2>
    </br>
   	使用前需要先用寫會員編號到session的功能把會員編號寫入session
        <form method="post" action="<%=request.getContextPath()%>/Front-End/Cart/cart">
        <input type="submit" value="進入購物車">
    </form>
    
</body>

</html>
