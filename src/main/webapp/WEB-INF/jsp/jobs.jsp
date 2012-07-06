<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" scope="request" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
		$().ready(function() {
			$("#jobs").dataTable({"sPaginationType":"full_numbers"});
		});
	</script>
Jobs
<table id="jobs" class="display">
	<thead>
	<tr><th>Component</th><th>State</th><th>MQ ID</th><th>Path</th><th>Error Log</th><th>Create Date</th></tr>
	</thead>
	<tbody>
	<c:forEach items="${jobs}" var="job">
		<tr><td>${job.component}</td><td>${job.state}</td><td>${job.mqId}</td><td>${job.absolutePath}</td><td>${job.errorPath}</td><td>${job.createdDate}</td></tr>
	</c:forEach>
	</tbody>
</table>
