<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" scope="request" value="${pageContext.request.contextPath}" />

<html>
<body>
<form action="${ctx}/Login" method="post">
<table>
	<tr><th>PASSWORD</th><td><input type="password" name="password" /> default is : mxic</td></tr>
	<tr><td colspan="2"><input type="submit" value="Submit" /></td></tr>
</table>
</form>
</body>
</html>
