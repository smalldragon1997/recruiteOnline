<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid ">
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-6">
			<img alt="header" src="<%=path%>/css/images/logo_orange.png">
		</div>
		<c:if test="${sessionScope.isLogin eq 'yes' }">
			<div class="col-lg-2 ">
				<div class="well well-sm">
					欢迎你，${sessionScope.email} <a href="javascript:void(0)"
						id="exit-btn">退出 </a>
				</div>
			</div>
		</c:if>
		<div class="col-lg-2"></div>
	</div>
</div>