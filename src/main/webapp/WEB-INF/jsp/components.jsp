<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" scope="request" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
		$().ready(function() {
			$("#components").dataTable({"sPaginationType":"full_numbers"});
		});
	</script>
<table id="components" class="display">
	<thead>
	<tr><th>Name</th><th>Status</th><th>Info</th><th>Function</th></tr>
	</thead>
	<tbody>
	<c:forEach items="${components}" var="cp">
		<tr><td>${cp.name}</td><td>${cp.status}</td><td>${cp.info}</td><td><input type="button" value="Trigger" onclick="trigger('${cp.name}')"/><input type="button" value="List" onclick="listjob('${cp.name}')"/> </td></tr>
	</c:forEach>
	</tbody>
</table>