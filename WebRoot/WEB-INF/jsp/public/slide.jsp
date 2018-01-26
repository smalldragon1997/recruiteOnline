<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<div class="container-fluid mainPanel">
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-4">
			<div id="myCarousel" class="carousel slide">
				<!-- 轮播（Carousel）指标 计数器-->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="2" class="active"></li>
				</ol>
				<!-- 轮播（Carousel）项目 -->
				<div class="carousel-inner">
					<div class="item active">
						<a href="#"><img src="<%=path%>/css/images/1.jpg"
							style="height: 300px;width:700px;"></a>
					</div>
					<div class="item">
						<a href="#"><img src="<%=path%>/css/images/2.jpg"
							style="height: 300px;width:700px;"></a>
					</div>
					<div class="item">
						<a href="#"><img src="<%=path%>/css/images/3.jpg"
							style="height: 300px;width:700px;"></a>
					</div>
				</div>
				<a class="carousel-control left" href="#myCarousel"
					data-slide="prev" style="line-height: 500px">&lsaquo;</a> <a
					class="carousel-control right" href="#myCarousel" data-slide="next"
					style="line-height: 500px">&rsaquo;</a>

			</div>
		</div>
		<div class="col-lg-4 jumbotron">
			<form id="loginForm">
				<div class="form-group">
					<label class="col-sm-12"
						style="text-align:center;font-size:30px;color:orange;">登录</label>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label">邮箱：</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="loginEmail"
							placeholder="请输入账号">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">密码：</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" name="loginPassword"
							placeholder="请输入密码">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label> <input type="hidden" name="loginRemenber"
								value="false"> <input type="hidden" name="loginRemenber"
								value="true">
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default" id="login-confirm">登录</button>
						<button type="button" class="btn btn-default" onclick="regForm()">注册</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>