
<%@page import="util.StringUtil"%>
<header>
	
  <div class="header-container">
    <div class="logo">
      <a href="">
        
        
        <img
					src="${pageContext.request.contextPath}/resources/images/postContents/icons-solid.svg"
					alt="User Image">
      </a>
    </div>
    <nav>
      <ul>
        <li><a href="${pageContext.request.contextPath}/pages/home.jsp">Home</a></li>
        
        <li><a href="${pageContext.request.contextPath}<%=StringUtil.SERVLET_PROFILE%>">Profile</a></li>
      </ul>
    </nav>

  </div>
</header>


<style>
/* Reset some default styles */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* Header styles */
header {
  background-color: #fff;
  border-bottom: 1px solid #e6ecf0;
  padding: 12px 16px;
  
    position: fixed;
  top: 0;
  left: 0;
  width: 60%;
  z-index: 999;
}

.header-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
  
  

  max-width: 70%;
  margin: 0 auto;
}

.logo img {
  width: 30px;
  height: 30px;
}

nav ul {
  display: flex;
  list-style-type: none;
}

nav ul li {
  margin-right: 20px;
}

nav ul li a {
  color: #333;
  text-decoration: none;
  font-size: 15px;
  font-weight: 500;
}

.user-actions .tweet-button {
  background-color: #1da1f2;
  color: #fff;
  border: none;
  border-radius: 20px;
  padding: 8px 16px;
  font-size: 15px;
  font-weight: 700;
  cursor: pointer;
}
</style>