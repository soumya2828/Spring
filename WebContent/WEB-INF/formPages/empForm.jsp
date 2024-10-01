<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Form</title>
</head>
<body>

	<form:form action="empSave" method="post">
	<table>
		<tr>
			<td>Employee ID</td>
			<td>:</td>
			<td><form:input path="id"></form:input></td>
		</tr>
		<tr>
			<td>Employee Name</td>
			<td>:</td>
			<td><form:input path="name"></form:input></td>
		</tr>
		<tr>
			<td>Employee Salary</td>
			<td>:</td>
			<td><form:input path="salary"></form:input></td>
		</tr>
		<tr>
			<td colspan="3"><input type="submit"  value="save" name="save"/></td>
		</tr>	
	</table>
	
	</form:form>
</body>
</html>