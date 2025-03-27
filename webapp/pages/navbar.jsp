<!DOCTYPE html>
<html>
<head>
  <title>Ness Navbar</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/navbar.css" >
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
  
  <%
    HttpSession sessionHttpSession = request.getSession();
    String username = (String) sessionHttpSession.getAttribute("displayName");
    String userId = (String) sessionHttpSession.getAttribute("userId");
    String imageurl = (String) sessionHttpSession.getAttribute("profileImage");
    //ArrayList<UserPostModel> postLists = (ArrayList<UserPostModel>) sessionHttpSession.getAttribute("PostList");
    
    
    
%>
  
  
  <style>
    body {
      display: flex;
      height: 100vh;
      margin: 0;
    }

    .header {
      background-color: #f1f1f1;
      padding: 20px;
      width: 25%;
    }

    .content {
      background-color: #fff;
      padding: 20px;
      width: 50%;
    }

    .footer {
      background-color: #f1f1f1;
      padding: 20px;
      width: 25%;
    }
  </style>
</head>
<body>
  <div class="header">
   
    <div class="navbar">
    <ul>
      <li><a href="#"><i class="fas fa-home"></i>Home</a></li>
      <li><a href="#"><i class="fas fa-hashtag"></i>Explore</a></li>
      <li><a href="#"><i class="fas fa-bell"></i>Notifications</a></li>
      <li><a href="#"><i class="fas fa-envelope"></i>Messages</a></li>
      <li><a href="#"><i class="fas fa-bookmark"></i>Bookmarks</a></li>
      <li><a href="#"><i class="fas fa-list"></i>Lists</a></li>
      <li><a href="#"><i class="fas fa-user"></i>Profile</a></li>
      
    </ul>
     <div class="profile-box">
        
       
		<img src="${pageContext.request.contextPath}/resources/images/users/<%=imageurl%>" alt="User Image">
        
        <div class="profile-link">
          <p class="full-name"><%=username%></p>
          <p class="user-name"><%=userId%></p>
        </div>
        <div class="options-icon"><i class="fas fa-caret-down"></i></div>
      </div>
    
  </div>
    
    
   
    
  </div>
  <div class="content">
    <h2>Content</h2>
    <p>This is the content section.</p>
  </div>
  
  
  <!-- FOOTER BEGINS -->
  
  <div class="footer">
    <h2>Footer</h2>
    
	<div class="search-box">
        <i class="fas fa-search"></i>
        <input type="text" placeholder="Search Post">
      </div>

      <div class="trends">
        <ul>
          <li class="nav-list header">
            <h2>Trends for you</h2>
            <i class="fas fa-cog">
          </i>
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
          
          <li class="nav-list"><a href="#">Show more</a></li>
        </ul>
      </div>
	
  </div>
</body>
</html>