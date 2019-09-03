<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File</title>
</head>
<body>
	<h3>File Upload</h3>
	<hr/>
	<form action="do_save.do" method="post" enctype="multipart/form-data">
		<input type="file" name="file01" /><br/>
		<input type="text" name="work_div" size="15"><br/>
		<input type="submit" value="전송" />
	</form>
</body>
</html>