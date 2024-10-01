<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.11.1.min.js' />"></script>

<script type="text/javascript">

	function deleteData(empId) {
		//alert("inside delete function");
		$.ajax({
			url : 'deleteEmp',
	        type: 'GET',
	        dataType: 'json',
	        contentType: 'application/json',
	        mimeType: 'application/json',
	        data: 'id='+empId,
	        success: function (data) {
				var trHTML = '';
				$('#records_table').html('');
				trHTML = '<thead><tr><th>Id</th><th>Name</th><th>Salary</th><th>Action</th></tr></thead>';
				$.each(data, function(i, item) {

					trHTML += '<tbody><tr><td>' + item.id
							+ '</td><td>' + item.name
							+ '</td><td>' + item.salary
							+ '</td><td><a href="#" onclick="deleteData('+item.id+')">Delete</a></td></tr></tbody>';
				});
				$('#records_table').append(trHTML);

	        }
	    });
	}
</script>

</head>
<body>
	<table style="border: 1px solid black;" cellpadding="1" cellspacing="0" border="1px" id="records_table">
		<tr><td>ID</td><td>Name</td><td>Salary</td><td>Actions</td></tr>
		<c:forEach var="employee" items="${list}" >
			<tr>
			<td>${employee.id}</td><td>${employee.name}</td><td>${employee.salary}</td>
			<td><a href="#" id="" onclick="deleteData(${employee.id})">Delete</a></td>  
			</tr>
		</c:forEach>
	</table>

</body>
</html>