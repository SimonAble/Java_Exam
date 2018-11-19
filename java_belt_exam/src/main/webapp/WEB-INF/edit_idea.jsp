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
		<h1>Edit Idea</h1>
	</div>
	
	<div class="nav_contain">
		<div class="nav">
			<a href="/dashboard">Dashboard</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	
	<p><c:out value="${ idea.name }"/></p>
	<p><c:out value="${ idea.description }"/></p>
	
	<div class="box_contain">
		<div class="box">
			<h2>Tell us about your idea, <c:out value="${ username }"/>!</h2>			
			<div class="form_contain">
				<form:form action="/update_idea" method="post" modelAttribute="idea">
					<form:label path="name">Idea Name</form:label>
					<form:errors path="name"></form:errors>
					<form:input path="name"/>
					
					<form:label path="description">Description</form:label>
					<form:errors path="description"></form:errors>
					<form:textarea path="description"/>
					
					<form:hidden path="user" value="${ this_user.id }"/>
					
					<input class="submit" type="submit">
				</form:form>
			</div>		
		</div>
	</div>

	
</body>
</html>