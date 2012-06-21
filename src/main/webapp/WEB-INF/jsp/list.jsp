<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" scope="request" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Component List</title>
<link href="css/demo_page.css" rel="stylesheet" />
<link href="css/demo_table.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
		$().ready(function() {
			$('#cpform').ajaxForm({
	            target: '#cpdiv',
	            success : $.unblockUI
	        });
			$('#jobform').ajaxForm({
	            target: '#jobdiv',
	            success : $.unblockUI
	        });
		});
		
		function trigger(cpname) {
			$("#cpname").val(cpname);
			$("#cpform").submit();
		}
		
		function listjob(cpname) {
			$("#lcpname").val(cpname);
			$("#jobform").submit();
		}
</script>
</head>
<body>
<form id="cpform" action="${ctx}/components" method="post">
	<input type="hidden" name="action" value="trigger" />
	<input type="hidden" id="cpname" name="cpname" />
</form>

<form id="jobform" action="${ctx}/components" method="post">
	<input type="hidden" name="action" value="listjob" />
	<input type="hidden" id="lcpname" name="cpname" />
</form>

<div id="cpdiv">
	<jsp:include page="components.jsp" />
</div>

<div id="jobdiv">
	<jsp:include page="jobs.jsp" />
</div>
</body>
</html>