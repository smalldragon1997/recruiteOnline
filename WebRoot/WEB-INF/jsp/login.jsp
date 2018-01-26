<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=path%>/css/bootstrap.css" rel="stylesheet">
	<link href="<%=path%>/css/footer.css" rel="stylesheet">
	<link href="<%=path%>/css/login.css" rel="stylesheet">
  </head>
  
  <body  background="<%=path%>/css/images/loginBackground.png" style="background-size:100%">

	<jsp:include page="/WEB-INF/jsp/public/header.jsp" flush="true"></jsp:include>  
	<jsp:include page="/WEB-INF/jsp/public/slide.jsp" flush="true"></jsp:include>   
	<jsp:include page="/WEB-INF/jsp/public/footer.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/public/loginForm.jsp" flush="true"></jsp:include>
	
	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script src="<%=path%>/js/jquery.js"></script>
	<!-- 包括所有已编译的插件 -->
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<script src="<%=path%>/js/jquery.validate.min.js"></script>
	
	<script src="<%=path%>/js/login.js"></script>
  </body>
</html>
