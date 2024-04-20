<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/all.css" />
	
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/style.css" />
	
		
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/stylesheets/headers.css" />
	
</head>


<body>

  <div class="main-flex-container">
    <div class="left-flex-container flex-item">
      <div class="nav-links">
        <ul>
          <li class="nav-items logo"><a href="#">
          <i class="fa-sharp fa-solid fa-icons" style="color: #63E6BE;">
          </i></a></li>
          <li class="nav-items current-page"><a href="#"><i class="fas fa-home"></i> Home</a></li>
          <li class="nav-items"><a href="#"><i class="fas fa-hashtag"></i> Explore</a></li>
          <li class="nav-items"><a href="#"><i class="far fa-bell"></i> Notifications</a></li>
          <li class="nav-items"><a href="#"><i class="far fa-envelope"></i> Messages</a></li>
          <li class="nav-items"><a href="#"><i class="far fa-bookmark"></i> Bookmarks</a></li>
          <li class="nav-items"><a href="#"><i class="far fa-list-alt"></i> Lists</a></li>
          <li class="nav-items"><a href="#"><i class="far fa-user"></i> Profile</a></li>
          <li class="nav-items"><a href="#"><i class="fas fa-ellipsis-h more-icon"></i> More</a></li>
        </ul>
      </div>
      <div class="profile-box">
        
        <img src="<%= request.getContextPath() %>/resources/profile.jpg" alt="Example Image">
        
        <div class="profile-link">
          <p class="full-name">David Herbert</p>
          <p class="user-name">@king.david</p>
        </div>
      </div>
    </div>

    <div class="center-flex-container flex-item">
      <div class="home">
        <h1>Your Profile</h1>
        
      </div>

     

      </div>
      <!-- User Content -->
       

    

    <div class="right-flex-container flex-item">
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
          <a href="#" class="sub-text">Terms</a>
          <a href="#" class="sub-text">Privacy policy</a>
          <a href="#" class="sub-text">Ads info</a>
          <a href="#" class="sub-text">more</a>
          <i class="fas fa-chevron-down sub-text"></i>
        </div>
        <div class="footer-copyright">
          <p class="sub-text">&copy; 2020 Ness, Inc.</p>
        </div>

      </div>
    
    </div>
    
  </div>
  
</body>


</html>