<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/main.css" />
</head>
<body>
	<div class="header">
		<h1>Showing Idea</h1>
	</div>
	
	<div class="nav_contain">
		<div class="nav">
			<a href="/dashboard">Dashboard</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	
	<div class="box_contain">
		<div class="box">
			<h2>Ideas</h2>
			<div class="table_contain">
				<table>
					<tr>
						<th>Ideas</th>
						<th>Created By:</th>
						<th>Likes</th>
						<th>Action</th>
					</tr>
					
					<%-- <c:forEach items="${ products }" var="product">
					<tr>
						<td><c:out value="${ product.name }"/></td>
						<td><c:out value="${ product.description }"/></td>
						<td>$<c:out value="${ product.price }"/></td>
						<td><a href="/show_product/${ product.id }">Show Product</a></td>
						<td><a href="/delete_product/${ product.id }">Remove Product</a></td>
					</tr>
					</c:forEach> --%>		
				</table>
			</div>
		</div>
	</div>

</body>
</html>