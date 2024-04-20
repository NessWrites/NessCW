<%@page import="util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/stylesheets/register.css" />
</head>
<body>
	<div class="container">
		<h1>Registration Form</h1>
		<form action="../RegisterServlet" method="post">
			<div class="row">
				<div class="col">
					<label for="firstName">First Name:</label> <input type="text"
						id="firstName" name="firstName" required>
				</div>
				<div class="col">
					<label for="lastName">Last Name:</label> <input type="text"
						id="lastName" name="lastName" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="username">Username:</label> <input type="text"
						id="username" name="username" required>
				</div>
				<div class="col">
					<label for="birthday">Birthday:</label> <input type="date"
						id="birthday" name="birthday" required>
				</div>

			</div>
			<div class="row">
				<div class="col">
					<label for="gender">Gender:</label> <select id="gender"
						name="gender" required>
						<option value="M">Male</option>
						<option value="F">Female</option>
					</select>
				</div>
				<div class="col">
					<label for="email">Email:</label> <input type="email" id="email"
						name="email" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="phoneNumber">Phone Number:</label> <input type="tel"
						id="phoneNumber" name="phoneNumber" required>
				</div>
				<div class="col">
					<label for="subject">Subject:</label> <select id="subject"
						name="subject" required>
						<option value="computing">Computing</option>
						<option value="multimedia">Multimedia</option>
						<option value="networking">Networking</option>
						<option value="networking">A.I.</option>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" required>
				</div>
				<div class="col">
					<label for="retypePassword">Retype Password:</label> <input
						type="password" id="retypePassword" name="retypePassword" required>
				</div>
			</div>
			<button type="submit">Submit</button>

			<%
			if (request.getAttribute("errorMessage") != null) {
			%>
			<p style="color: red;"><%=request.getAttribute("errorMessage")%></p>
			<%
			}
			%>	
			

		</form>
		
		
		<%
						String errMsg = (String) request.getAttribute(StringUtil.MESSAGE_ERROR_REGISTER);
								String successMsg = (String) request.getAttribute(StringUtil.MESSAGE_SUCCESS);

								if (errMsg != null) {
							// print
						%>
		<h4 class="error-msg">
			<%
			out.println(errMsg);
			%>
		</h4>
		<%
		}

		if (successMsg != null) {
		// print
		%>
		<h4 class="success-msg">
			<%
			out.println(successMsg);
			%>
		</h4>
		<%
		}
		%>
	</div>
</body>
</html>