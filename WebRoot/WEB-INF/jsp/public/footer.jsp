<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<div class="container-fluid footer">
	<div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8" style="text-align:center;">
				<img alt="footer" src="<%=path%>/css/images/footer.png">
            </div>
            <div class="col-lg-2"></div>
        </div>
</div>