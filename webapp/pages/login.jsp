<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheets/login.css" />


</head>
<body>
	<div class="login-box">
		<h2>Login</h2>
		
		<%-- Check if there's an error message --%>
    <% if (request.getAttribute("errorMessage") != null) { %>
        <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
		
		<form action="<%=request.getContextPath() %>/login" method="post">
		 <i class="fa fa-twitter"></i>
			<div class="row">
				<div class="col">
					<label for="username">Username:</label> 
					<input type="text" id="username" name="username" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> 
					<input type="password" id="password" name="password" required>
				</div>
			</div>
			<button type="submit" class="login-button">Login</button>
		</form>
	</div>
</body>
</html>