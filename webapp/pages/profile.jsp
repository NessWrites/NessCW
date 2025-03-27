<%@page import="model.UserModel"%>
<%@page import="util.StringUtil"%>
<%@page import="model.PostModel"%>
<%@page import="model.UserPostModel"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<%
HttpSession sessionHttpSession = request.getSession();
String username = (String) sessionHttpSession.getAttribute("displayName");
int userID = (Integer) sessionHttpSession.getAttribute("userID");
String imageurl = (String) sessionHttpSession.getAttribute("profileImage");

%>

<head>

<meta charset="ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/all.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/style.css" />


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/profile2.css" />

<style>
/* Dropdown Button */
.dropbtn {
	/* background-color: #4CAF50; */
	margin-right: 15px;
	color: black;
	font-size: 16px;
	border: none;
	cursor: pointer;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
	position: relative;
	display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {
	background-color: #ddd;
}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
	display: block;
}
</style>


</head>


<body>
<jsp:include page="./headers.jsp" />
<br>
<br>
	

	<%
	String userSession = (String) session.getAttribute("displayName");

	String cookieUsername = null;
	String cookieSessionID = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(StringUtil.USER))
		cookieUsername = cookie.getValue();
			if (cookie.getName().equals(StringUtil.JSESSIONID))
		cookieSessionID = cookie.getValue();
		}
	}
	%>


	<div class="main-flex-container">
		<div class="left-flex-container flex-item">
			<div class="nav-links">
				<ul>
					<li class="nav-items logo"><a href="#"> <i
							class="fa-sharp fa-solid fa-icons" style="color: #63E6BE;"> </i></a></li>

					<li class="nav-items current-page"><a
						href="${pageContext.request.contextPath}/pages/home.jsp"
						style="color: black"><i class="fas fa-home"></i> Home</a></li>



					<li class="nav-items"><a href="#"><i
							class="far fa-bookmark" style="color: black"></i> Bookmarks</a></li>
					<!-- profilepath -->
					<li class="nav-items"><a
						href="${pageContext.request.contextPath}<%=StringUtil.SERVLET_PROFILE%>"
						style="color: rgb(126, 245, 148);"><i class="far fa-user"></i>
							Profile</a></li>

				</ul>

			</div>

			<div class="profile-box">
				<img
					src="${pageContext.request.contextPath}/resources/images/users/<%=  imageurl%>"
					alt="User Image">

				<div class="profile-link">
					<p class="full-name"><%=username%></p>
					<p class="full-name"><%=userID%></p>

				</div>
				<div class="options-icon">
					<i class="fas fa-caret-down"></i>
				</div>
			</div>
		</div>
		<!-- ProfileEdit -->

		<!-- <p>users.userDescription</p> -->

		<div class="center-flex-container flex-item">
			<div class="home">
				<h1>Profile</h1>
				<c:forEach var="users" items="${userLists}">
					<%-- <c:if test="${users.displayName.equals(userID)}"> --%>
					<i class="fas fa-magic" onclick="openProfileEditPopup()"></i>
					<%-- </c:if> --%>
				</c:forEach>
				<!-- ProfileUpdate -->
				<!-- value janey thau from here to md -->

			</div>

			<!-- Modal -->
			<div id="editProfileModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeEditPopupProfile()">&times;</span>
        <form id="editProfileForm" action="<%=request.getContextPath()%><%=StringUtil.SERVLET_URL_UPDATE_PROFILE_INFO%>" method="post">
        	<input type="number" name="userID" style="display:none" value="<%=userID%>" readonly>
            <label for="fname">First Name</label>
            <input id="fname" name="firstName" type="text" placeholder="First Name" value="${profileDetail[0].firstName}"><br>
            <label for="lname">Last Name</label>
            <input id="lname" name="lastName" type="text" placeholder="Last Name" value="${profileDetail[0].lastName}"><br>
            <label for="phoneNumber">Phone Number</label>
            <input id="phoneNumber" name="phoneNumber" type="text" placeholder="Phone Number" value="${profileDetail[0].phoneNumber}"><br>
            <label for="email">Email</label>
            <input id="email" name="email" type="email" placeholder="Email" value="${profileDetail[0].email}"><br>
            <label for="displayName">Display Name</label>
            <input id="displayName" name="displayName" type="text" placeholder="Display Name" value="${profileDetail[0].displayName}"><br>
            <label for="userDescription">Bio</label>
            <input id="userDescription" name="userDescription" type="text" placeholder="Describe yourself" value="${profileDetail[0].userDescription}"><br>
            <label for="dateOfBirth">Date of Birth</label>
            <input id="dateOfBirth" name="dateOfBirth" type="date" placeholder="Birth Date" value="${profileDetail[0].dateOfBirth}"><br>

            <input type="submit" value="Submit">
        </form>
    </div>
</div>


			<form id="updateuser-${post.postID}" method="post"
				action="<%=request.getContextPath()%><%=StringUtil.SERVLET_URL_MODIFY_PROFILE_INFO%>">
				<input type="hidden" name="<%=StringUtil.FIRST_NAME%>"
					value="firstName" /> 
					<input type="hidden"
					name="<%=StringUtil.LAST_NAME%>" value="lastName" /> 
					<input type="hidden" name="<%=StringUtil.PASSWORD%>" value="password" />
				
				<input type="hidden" name="<%=StringUtil.PHONE_NUMBER%>"
					value="phoneNumber" /> 
					<input type="hidden"
					name="<%=StringUtil.EMAIL%>" value="email" /> <input type="hidden"
					name="<%=StringUtil.USER_DESCRIPTION%>" value="userDescription" />
				
				<input type="hidden" name="<%=StringUtil.IMAGE_STRING%>"
					value="image" />
			</form>

			<div class="profile_post_follow_bx">
				<div class="profile_bx">
					<div class="profile_card">
						<form id="profilePicChangeForm" action="<%=request.getContextPath()%><%=StringUtil.SERVLET_URL_UPDATE_IMAGE%>" method="post" enctype="multipart/form-data" >
							<div>
        						<img class="display_pics" src="${pageContext.request.contextPath}/resources/images/users/<%=imageurl%>" alt="Current Profile Picture" onclick="document.getElementById('profilePicInput').click()">
        						<!--  <input type="file" id="profilePicInput" name="profilePic" accept="image/*" style="display: none;" onChange="changeProfilePicture()">-->
        						<input type="file" id="profilePicInput" name="profilePic" accept="image/*" style="opacity: 0; position: absolute; z-index: -1;" onChange="changeProfilePicture()">
        						
    						</div>							
														
						</form>
						
						<h5>${profileDetail[0].firstName}</h5>
						<h6>${profileDetail[0].displayName}</h6>
						<address>
							<a href="#"> <i class="fa-solid fa-location-dot"></i> ${profileDetail[0].dateOfBirth}
								
							</a> <a href="#"> <i class="fa-solid fa-earth-asia"></i>
							</a>
						</address>
						<p>${profileDetail[0].userDescription}</p>
						<div class="data">
							<div class="data_card">
								<p>Tweets</p>
								<h4>857</h4>
							</div>
							<div class="data_card">
								<p>Followers</p>
								<h4>11.2k</h4>
							</div>
							<div class="data_card">
								<p>Following</p>
								<h4>243</h4>
							</div>
						</div>
					</div>

				</div>
			</div>


			<div class="post-tweet"></div>


			<!-- userContent -->
			<c:forEach var="post" items="${userOnlyPosts}">

				<div class="tweets">
					<div class="user-pics">
						<img
							src="${pageContext.request.contextPath}/resources/images/users/${post.imageString}"
							alt="user3">
					</div>


					<div class="user-content-box">
						<div style="display: flex; justify-content: space-between;"
							class="user-names">
							<div>
								<h1 class="full-name">${post.userName}</h1>

								<p class="time" style="font-size: 15px;">${post.time}</p>
							</div>
							<c:if test="${post.userID.equals(userID)}">

								<button class="edit-btn"
									onclick="openEditPopup('${post.postID}', '${post.post}')">Edit</button>

								<!-- Modal -->
								<div id="editModal" class="modal">
									<div class="modal-content">
										<span class="close" onclick="closeEditPopup()">&times;</span>
										<textarea id="editContent" rows="6" cols="50"
											style="font-size: 14px; font-family: monospace;"></textarea>
										<button class=saber onclick="updatePost('${post.postID}')">Save</button>
									</div>
								</div>


								<!-- hidden update form -->
								<form id="updatePost-${post.postID}" method="post"
									action="<%=request.getContextPath()%><%=StringUtil.SERVLET_URL_MODIFY_USER%>">
									<input type="hidden" name="<%=StringUtil.UPDATE_ID %>"
										value="${post.postID}" /> <input type="hidden"
										name="<%=StringUtil.UPDATED_POST%>" value="updatedPost" />


								</form>


								<form id="deleteForm-${post.postID}" method="post"
									action="<%=request.getContextPath()%>/<%=StringUtil.SERVLET_URL_MODIFY_USER %>">
									<input type="hidden" name="<%=StringUtil.DELETE_ID %>"
										value="${post.postID	}" />
									<button type="button" onclick="confirmDelete('${post.postID}')">Delete</button>
								</form>

							</c:if>

						</div>

						<div class="user-content">
							<p>${post.post}</p>
						</div>

						<c:if test="${!empty post.postImageString}">
							<img
								src="<%=request.getContextPath()%>/resources/images/postContents/${post.postImageString}"
								alt="content1">
						</c:if>
						<div class="content-icons">
							<i class="far fa-comment blue"> 109</i> <i
								class="fas fa-retweet green"> 865</i> <i
								class="far fa-heart red">1.6k</i>
						</div>

					</div>



				</div>


			</c:forEach>














			<section class="follow-users-box">

				<div class="follow-header">
					<h1 class="main-text">Who to follow</h1>
					<i class="fas fa-chevron-down"></i>
				</div>

				<div class="follow-user">
					<div class="related-followers">
						<p class="sub-text">
							<i class="fas fa-user"></i> Ellie Jamie and 20 others follow
						</p>
					</div>

					<div class="user-profile">
						<div class="user-pics">
							<img src="<%=request.getContextPath()%>/resources/user6.jpg"
								alt="user6">
						</div>

						<div class="user-profile-content">
							<div class="title-container">
								<div class="user-names">
									<div class="full-name">
										<h1 class="main-text">Linda Shelton #BlackLivesMatter</h1>
									</div>
									<div class="user-name">
										<p class="sub-text">@Linda_shelton</p>
									</div>
								</div>

								<div class="follow-btn">
									<a href="#">follow</a>
								</div>

							</div>
							<div class="bio-container">
								<p>
									<a href="#">♯WordPress/Php</a> Geek Smiling face with
									sunglasses <a href="#">♯JavascriptDeveloper</a> Slightly
									smiling face <a href="#">♯ToolsCreator</a> <a href="#">♯http://webscreenshot.now.sh</a>
									Always ready to help with code Handshake
								</p>
							</div>

						</div>

					</div>

				</div>

				<div class="follow-user">
					<div class="related-followers">
						<p class="sub-text">
							<i class="fas fa-user"></i> JavaScript Ninja and 200 others
							follow
						</p>
					</div>

					<div class="user-profile">
						<div class="user-pics">
							<img src="<%=request.getContextPath()%>/resources/user7.jpg"
								alt="user7">
						</div>

						<div class="user-profile-content">
							<div class="title-container">
								<div class="user-names">
									<div class="full-name">
										<h1 class="main-text">Gavin Johnson #JavaScript</h1>
									</div>
									<div class="user-name">
										<p class="sub-text">@Gavinjohnson</p>
									</div>
								</div>

								<div class="follow-btn">
									<a href="#">follow</a>
								</div>

							</div>
							<div class="bio-container">
								<a href="#">♯JavascriptDeveloper</a> Avocado | Fire Speaker |
								Fire Teacher | Youtube - <a href="#">http://bit.ly/jqqyt</a> |<a
									href="#"> @twilio</a> Champion | Motto: <a href="#">#LearnBuildTeach</a>
								</p>
							</div>

						</div>

					</div>

				</div>

				<div class="follow-user">
					<div class="related-followers">
						<p class="sub-text">
							<i class="fas fa-user"></i> The Code Boy and 50 others follow
						</p>
					</div>

					<div class="user-profile">
						<div class="user-pics">
							<img src="<%=request.getContextPath()%>/resources/user8.jpg"
								alt="user8">
						</div>

						<div class="user-profile-content">
							<div class="title-container">
								<div class="user-names">
									<div class="full-name">
										<h1 class="main-text">William D Gallucci</h1>
									</div>
									<div class="user-name">
										<p class="sub-text">@Iamwilliamd_shelton</p>
									</div>
								</div>

								<div class="follow-btn">
									<a href="#">follow</a>
								</div>

							</div>
							<div class="bio-container">
								<p>
									software engineer at <a href="#">@soonastudios</a> . career
									switcher. vue + rails. tweeting about tech, books, startups,
									and big ideas. writing @ <a href="#">http://jamestucker.dev.</a>
								</p>
							</div>

						</div>

					</div>

				</div>

				<div class="follow-footer">
					<a href="#">Show more</a>
				</div>

			</section>

			<div class="tweets"></div>

			<div class="pagnation">
				<a href="#">Load more</a>
			</div>

		</div>



		<div class="right-flex-container flex-item">
			<div class="search-box">
				<i class="fas fa-search"></i> <input type="text"
					placeholder="Search Post">
			</div>

			<div class="trends">
				<ul>
					<li class="nav-list header">
						<h2>Trends for you</h2> <i class="fas fa-cog"> </i>
					<li class="nav-list">
						<div class="trend-list">
							<p class="sub-text">Trending in Kathmandu</p>
							<p class="main-text">#Crush</p>
							<p class="sub-text">564K Posts</p>
						</div>
						<div class="trend-icon">
							<i class="fas fa-chevron-down"></i>
						</div>
					</li>
					<li class="nav-list">
						<div class="trend-list">
							<p class="sub-text">Trending in Kathmandu</p>
							<p class="main-text">#WritingCommunity</p>
							<p class="sub-text">555K Posts</p>
						</div>
						<div class="trend-icon">
							<i class="fas fa-chevron-down"></i>
						</div>
					</li>
					<li class="nav-list">
						<div class="trend-list">
							<p class="sub-text">Trending in Kathmandu</p>
							<p class="main-text">#Lover</p>
							<p class="sub-text">444K Posts</p>
						</div>
						<div class="trend-icon">
							<i class="fas fa-chevron-down"></i>
						</div>
					</li>
					<li class="nav-list">
						<div class="trend-list">
							<p class="sub-text">Trending in Kathmandu</p>
							<p class="main-text">#Dota2</p>
							<p class="sub-text">333K Posts</p>
						</div>
						<div class="trend-icon">
							<i class="fas fa-chevron-down"></i>
						</div>
					</li>
					<li class="nav-list">
						<div class="trend-list">
							<p class="sub-text">Trending in Kathmandu</p>
							<p class="main-text">#AI not 3</p>
							<p class="sub-text">222K Posts</p>
						</div>
						<div class="trend-icon">
							<i class="fas fa-chevron-down"></i>
						</div>
					</li>


					<li class="nav-list"><a href="#">Show more</a></li>
				</ul>
			</div>

			<div class="right-footer">
				<div class="footer-links">
					<a href="#" class="sub-text">Terms</a> <a href="#" class="sub-text">Privacy
						policy</a> <a href="#" class="sub-text">Ads info</a> <a href="#"
						class="sub-text">more</a> <i class="fas fa-chevron-down sub-text"></i>
				</div>
				<div class="footer-copyright">
					<p class="sub-text">&copy; 2020 Ness, Inc.</p>
				</div>

			</div>

		</div>

	</div>

	<!-- Add the background overlay -->
	<div class="modal-overlay"></div>




</body>
<script>





	document.getElementById('imageUpload1').addEventListener('change',
			function() {
				var file = this.files[0];
				console.log(file)
				var imagePreview = document.getElementById('uploadedImage');
				var img = imagePreview.getElementsByTagName('img')[0];

				if (file) {
					console.log("here")
					var reader = new FileReader();
					reader.onload = function(e) {

						console.log({
							target : e.target
						})

						img.src = e.target.result;
						imagePreview.style.display = 'block';
					}
					reader.readAsDataURL(file);
				}
			});

	// JavaScript (three dots)
	document.addEventListener("DOMContentLoaded", function() {
		const dropdownButton = document.querySelector(".dropbtn");
		const dropdownContent = document.querySelector(".dropdown-content");

		dropdownButton.addEventListener("click", function() {
			dropdownContent.classList.toggle("show");
		});
	});
	
		function confirmDelete(postID) {
			 
			if (confirm("Are you sure you want to delete this post: " + postID
					+ "?")) {
				document.getElementById("deleteForm-" + postID).submit();
			}
		}
		
		function updatePost(postID) {
			var updatedContent = document.getElementById("editContent").value;
			if (confirm("Are you sure you want to update this post: " + postID +updatedContent
					+ "?")) {
				
				 document.querySelector("#updatePost-" + postID + " input[name='<%=StringUtil.UPDATED_POST%>']").value = updatedContent;
				document.getElementById("updatePost-" + postID ).submit();
			}
			
		
		}
		
		//pop up update user post 
		function openEditPopup(postID, content) {
			console.log("i am opened and popped!");
	        var modal = document.getElementById("editModal");
	        var textarea = document.getElementById("editContent");
	        textarea.value = content; // Populate textarea with current content
	        modal.style.display = "block";
	    }

	    function closeEditPopup() {
	        var modal = document.getElementById("editModal");
	        modal.style.display = "none";
	    }
	    
	    
	    function openProfileEditPopup() {
			console.log("i am opened and popped!");
	        var modal = document.getElementById("editProfileModal");
	        var form = document.getElementById("editProfileForm");
	        //textarea.value = content; // Populate textarea with current content
	        modal.style.display = "block";
	    }
	   

	    /* functions for update profile details */
	    
	    
	    function updateProfile(postID) {
	    	
			var mainDiv = document.getElementById("editProfileModal");
			console.log("i am profiled!!!!!", updatedContent);
			if (confirm("Are you sure you want to update your profile: "
					+ "?")) {
				
				 document.querySelector("#updatePost-" + postID + " input[name='<%=StringUtil.UPDATED_POST%>']").value = updatedContent;
				document.getElementById("updatePost-" + postID ).submit();
			}
			
		
		}
		
		/* //pop up update user Profile 
		function openEditPopupProfile(postID, content) {
			console.log();
	        var modal = document.getElementById("editModal");
	        var textarea = document.getElementById("editContent");
	        textarea.value = content; // Populate textarea with current content
	        modal.style.display = "block";
	    } */

	    function closeEditPopupProfile() {
	        var modal = document.getElementById("editProfileModal");
	        modal.style.display = "none";
	    }
	    
	    function changeProfilePicture() {
	    	console.log("hello =")
	    	var form = document.getElementById("profilePicChangeForm")
	    	
	    	if (confirm("Are you sure you want to update your profile picture"
					+ "?")) {
				
				form.submit();
			}
	    }
	    
	    
	    
		
		
</script>



</html>