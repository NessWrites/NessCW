<%@page import="util.StringUtil"%>
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
String firstName = (String) sessionHttpSession.getAttribute("firstName");
%>

<head>

<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/all.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/style.css" />

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

.dropdown-menu {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    padding: 12px 16px;
    z-index: 1;
}

.dropdown-menu button {
    background-color: #4CAF50;
    color: white;
    padding: 12px 24px;
    border: none;
    cursor: pointer;
    width: 100%;
    text-align: left;
}

.dropdown-menu button:hover {
    background-color: #45a049;
}

/* .dropdown-list input, .dropdown-list button{
	padding: 10px;
	border-radius: 2px;
	background: #45a049;
} */


</style>


</head>


<body>

<jsp:include page="./headers.jsp" />

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
						href="${pageContext.request.contextPath}/pages/home.jsp"><i
							class="fas fa-home"></i> Home</a></li>

					<!-- 
          <li class="nav-items"><a href="#"><i class="fas fa-hashtag"></i> Explore</a></li>
          <li class="nav-items"><a href="#"><i class="far fa-bell"></i> Notifications</a></li>
          <li class="nav-items"><a href="#"><i class="far fa-envelope"></i> Messages</a></li>
           <li class="nav-items"><a href="#"><i class="far fa-list-alt"></i> Lists</a></li>
          <li class="nav-items"><a href="#"><i class="fas fa-ellipsis-h more-icon"></i> More</a></li>
           -->

					<li class="nav-items"><a href="#"><i
							class="far fa-bookmark"></i> Bookmarks</a></li>
<!-- ProfilePage -->
					<li class="nav-items"><a
						href="${pageContext.request.contextPath}<%=StringUtil.SERVLET_PROFILE%>"><i
							class="far fa-user"></i> Profile</a></li>

				</ul>
			</div>



			<!-- <div class="post">
				<a href="#">Post</a>
			</div> -->
			<div class="profile-box">


				<img
					src="${pageContext.request.contextPath}/resources/images/users/<%=  imageurl%>"
					alt="User Image">

				<div class="profile-link">
					<p class="user-name"><%=firstName%></p>
					<p class="user-name"><%=username%></p>
				</div>
				<div class="options-icon">
					<i class="fas fa-caret-down" onClick="toggleDropdown()"></i>
				</div>
				
				    <!-- Dropdown Menu -->
    			<div id="dropdownMenu" style="display: none;">
    				<div class="dropdown-list">
    					<form action="<%=request.getContextPath()%><%=StringUtil.SERVLET_URL_LOGOUT%>" method="post" id="logout-form">
    						<input type="submit" value="Logout">
    					</form>
    				</div>
    				<div class="dropdown-list">
    					<button type="button" onClick="openChangePasswordPopup()">Change Password</button>
        			</div>
        			
        			<div id="changePasswordModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeChangePasswordPopup()">&times;</span>
        <form id="editProfileForm" action="<%=request.getContextPath()%><%=StringUtil.SERVLET_URL_CHANGE_PASSWORD%>" method="post"> 
            <label for="oldPassword">Old Password</label>
            <input id="oldPassword" name="oldPassword" type="password" placeholder="Type Old Password"><br>

            <label for="newPassword">New Password</label>
            <input id="newPassword" name="newPassword" type="password" placeholder="Type New Password"><br>

            <label for="retypePassword">Retype Password</label>
            <input id="retypePassword" name="retypePassword" type="password" placeholder="Retype Password"><br>

            <input type="submit" value="Submit">
        </form>
    </div>
</div>
    			</div>
			</div>
		</div>

		<div class="center-flex-container flex-item">
			<div class="home">
				<h1>Home</h1>
				
			</div>

			<div class="post-tweet">
				<form
					action="<%=request.getContextPath()%>/<%=StringUtil.SERVLET_URL_POST%>"
					method="post" enctype="multipart/form-data">
					<div class="form-group-1">
						<img
							src="${pageContext.request.contextPath}/resources/images/users/<%=  imageurl%>"
							alt="User Image"> <input type="text"
							placeholder="What's happening?" name="posting" required>
					</div>
					<div class="form-group-2">
						<div class="post-icons">
							<label for="imageUpload1"> <i class="far fa-image"></i>
							</label> <input type="file" id="imageUpload1" name="contentImageString"
								style="display: none;"> <i class="fas fa-stream"></i> <i
								class="far fa-smile"></i> <i class="far fa-calendar-check"></i>
						</div>

						<div id="uploadedImage" style="display: none;">
							<img src="" alt="Uploaded Image" width="100%" />
						</div>
						<button class="btn" type="submit">Post</button>
					</div>
				</form>

			</div>

			<!-- User Content -->
			<c:forEach var="post" items="${postLists}">
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
								<div id="editModal-${post.postID}" class="modal">
									<div class="modal-content">
										<span class="close" onclick="closeEditPopup(${post.postID})">&times;</span>
										<textarea id="editContent-${post.postID}" rows="6" cols="50"
											style="font-size: 24px; font-family: monospace;"></textarea>
										<button class=saber 
										onclick="updatePost('${post.postID}')">Save</button>
									</div>
								</div>


								<!-- hidden update form -->	
								<form id="updatePost-${post.postID}" method="post"
									action="<%=request.getContextPath()%><%=StringUtil.SERVLET_URL_MODIFY_USER%>">
									<input type="hidden" name="<%=StringUtil.UPDATE_ID %>"value="${post.postID}" />
									 <input type="hidden" name="<%=StringUtil.UPDATED_POST%>" value="updatedPost"/>
								</form>


								<form id="deleteForm-${post.postID}" method="post"
									action="<%=request.getContextPath()%><%=StringUtil.SERVLET_URL_MODIFY_USER %>">
									<input type="hidden" name="<%=StringUtil.DELETE_ID %>"
										value="${post.postID	}" />
									<button type="button" onclick="confirmDelete('${post.postID}', '${post.post}')">Delete</button>
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















			<div class="tweets">
				<div class="user-pics">
					<img src="<%=request.getContextPath()%>/resources/user2.jpg"
						alt="user2">
				</div>
				<div class="user-content-box">
					<div class="user-names">
						<hi class="full-name">Carole Chavez</hi>
						<p class="user-name">@carolechavez</p>
						<p class="time">. 1hr</p>
					</div>

					<div class="user-content">
						<p>
							If the whole world was blind, how many people would you impress?
							<a href="#">#100daysofcode</a> <a href="#">#javascript</a> <a
								href="#">#womenwhocode</a>
						</p>
					</div>

					<div class="content-icons">
						<i class="far fa-comment blue"> 2k</i> <i
							class="fas fa-retweet green"> 6k</i> <i class="far fa-heart red">
							10k</i> <i class="fas fa-chevron-up blue"></i>
					</div>

				</div>
			</div>

			<div class="tweets">
				<div class="user-pics">
					<img src="<%=request.getContextPath()%>/resources/user1.jpg"
						alt="user1">
				</div>
				<div class="user-content-box">
					<div class="user-names">
						<hi class="full-name">Eric Alvarez</hi>
						<p class="user-name">@eric_alvarez</p>
						<p class="time">. 2hrs</p>
					</div>

					<div class="user-content">
						<p>
							Eat. Code, Sleep. repeat! <a href="#">#CodeNewbie</a> <a href="#">#100DaysOfCode</a>
						</p>
						<img src="<%=request.getContextPath()%>/resources/content1.jpg"
							alt="content1">

					</div>

					<div class="content-icons">
						<i class="far fa-comment blue"> 273</i> <i
							class="fas fa-retweet green"> 2k</i> <i class="far fa-heart red">
							3k</i> <i class="fas fa-chevron-up blue"></i>
					</div>

				</div>
			</div>

			<div class="tweets">
				<div class="user-pics">
					<img src="<%=request.getContextPath()%>/resources/user4.jpg"
						alt="user4">

				</div>
				<div class="user-content-box">
					<div class="user-names">
						<hi class="full-name">Harry Wilson</hi>
						<p class="user-name">@Harrywilson</p>
						<p class="time">. 4hrs</p>
					</div>

					<div class="user-content">
						<p>There's no clear correlation between your background and
							what you are going to achieve in life..</p>
					</div>

					<div class="content-icons">
						<i class="far fa-comment blue"> 4k</i> <i
							class="fas fa-retweet green"> 8k</i> <i class="far fa-heart red">13k</i>
						<i class="fas fa-chevron-up blue"></i>
					</div>

				</div>
			</div>

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

					<div class="user-	">
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

			<div class="tweets">
				<div class="user-pics">
					<img src="<%=request.getContextPath()%>/resources/user5.jpg"
						alt="user5">
				</div>
				<div class="user-content-box">
					<div class="user-names">
						<hi class="full-name">Nora Chao</hi>
						<p class="user-name">@chaonora</p>
						<p class="time">. 6hrs</p>
					</div>

					<div class="user-content">
						<p>Time to settle down and play my favourite game IN THE WORLD
							Earth globe europe-africa</p>

						<p>Haven’t played for about a month, too busy coding. So tired
							this evening though. Poker tournament with husband later..</p>
						<img src="<%=request.getContextPath()%>/resources/content5.jpg"
							alt="content5">

					</div>

					<div class="content-icons">
						<i class="far fa-comment blue"> 78</i> <i
							class="fas fa-retweet green"> 265</i> <i class="far fa-heart red">
							934</i> <i class="fas fa-chevron-up blue"></i>
					</div>

				</div>
			</div>

			<div class="pagnation">
				<a href="#">Load more</a>
			</div>

		</div>



		<div class="right-flex-container flex-item">
			<div class="search-box">
				<i class="fas fa-search"></i> <input type="text"
					placeholder="Search">
					 <button id="searchButton"><i class="fas fa-search"></i></button>
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
	
		function confirmDelete(postID , post) {
			 
			if (confirm("Are you sure you want to delete this post: " + postID + "\n"+ post
					+ "?")) {
				document.getElementById("deleteForm-" + postID).submit();
			}
		}
		
		function updatePost(postID) {
			var updatedContent = document.getElementById("editContent-"+postID).value;
			if (confirm("Are you sure you want to update this post: " + postID +updatedContent
					+ "?")) {
				
				 document.querySelector("#updatePost-" + postID + " input[name='<%=StringUtil.UPDATED_POST%>']").value = updatedContent;
				document.getElementById("updatePost-" + postID ).submit();
			}
			
		
		}
		
		//pop up update user post 
		function openEditPopup(postID, content) {
			console.log(postID);
	        var modal = document.getElementById("editModal-"+postID);
	        var textarea = document.getElementById("editContent-"+postID);
	        textarea.value = content; // Populate textarea with current content
	        modal.style.display = "block";
	    }

	    function closeEditPopup(postID) {
	        var modal = document.getElementById("editModal-"+postID);
	        modal.style.display = "none";
	    }
	    function saveEditedContent() {
	        console.log("console working:");
	        // Get the updated content from the textarea with the id "editContent"
	        var updatedContent = document.getElementById("editContent").value;

	        // Get the post ID (You need to replace this comment with the actual code to retrieve the post ID)
	        var postId = document.getElementById("postID").value;

	        // Create a new XMLHttpRequest object to send an asynchronous HTTP request
	        var xhr = new XMLHttpRequest();

	        // Open a PUT request to the specified servlet URL
	        xhr.open("PUT", "<%=request.getContextPath()%><%=StringUtil.SERVLET_URL_MODIFY_USER%>", true);

	        // Send the request with the post ID and updated content as parameters (no need to encode)
	        xhr.send("postId=" + postId + "&updatedContent=" + updatedContent);
	    }
	    
	    function closeChangePasswordPopup() {
	        var modal = document.getElementById("changePasswordModal");
	        modal.style.display = "none";
	    }
	    
	    
	    function openChangePasswordPopup() {
			console.log("i am opened and popped!");
	        var modal = document.getElementById("changePasswordModal");
	        modal.style.display = "block";
	    }
	    
	    
	    function toggleDropdown() {
	        var dropdownMenu = document.getElementById('dropdownMenu');
	        if (dropdownMenu.style.display === "none") {
	            dropdownMenu.style.display = "block";
	        } else {
	            dropdownMenu.style.display = "none";
	        }
	    }
		
		
</script>



</html>