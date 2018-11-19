<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java Belt Exam Dashboard</title>
<link rel="stylesheet" type="text/css" media="screen" href="css/main.css" />
</head>
<body>
	
	
	<div class="header">
		<h1>Great Ideas</h1>
		<h2>Welcome to your idea board, <c:out value="${ username }"/></h2>
	</div>
	
	<div class="nav_contain">
		<div class="nav">
			<a href="/new_idea">Create Idea</a>
			<a href="/low_likes">Low Likes</a>
			<a href="/high_likes">High Likes</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	
	
	<div class="box_contain">
		<div class="box">
			<h2>Ideas</h2>
			<div class="table_contain">
				<table>
					<tr>
						<th>Idea</th>
						<th>Description</th>
						<th>Created By:</th>
						<th>Likes</th>
						<th>View</th>
						<th>Remove</th>
					</tr>
					
					<c:forEach items="${ ideas }" var="idea">
					
					<tr>
						<td><c:out value="${ idea.name }"/></td>
						<td><c:out value="${ idea.description }"/></td>
						<td><c:out value="${ idea.user.username }"/></td>
						
						<c:if test="${ !likes.contains(idea) }">
						<td><a href="/like_idea/${ id }/${ idea.id }">Like</a></td>
						</c:if>
						
						<c:if test="${ likes.contains(idea) }">
						<td><a href="/unlike_idea/${ id }/${ idea.id }">Unlike</a></td>
						</c:if>
						
						<td><a href="/show_idea/${ idea.id }">Show Idea</a></td>
						
						
						
						<c:if test="${ idea.user.id == id}">
						<td class="remove"><a href="/delete_idea/${ idea.id }">Remove Idea</a></td>
						</c:if>
						<c:if test="${ idea.user.id != id }">
						<td><c:out value="${idea.user.username }"/>'s Idea</td>
						</c:if>
					</tr>
					
					</c:forEach>	
				</table>
			</div>
		</div>
	</div>
	
</body>
</html>