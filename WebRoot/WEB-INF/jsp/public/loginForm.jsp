<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<div class="container-fluid login" >
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<div class="row ">
				<div class="col-lg-12 ">
					<div class="row">
						<div class="col-lg-5 jumbotron ">
						<form id="loginForm">
								<div class="form-group">
									<label class="col-sm-12" style="text-align:center;font-size:30px;color:orange;">登录</label>
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
										<input type="password" class="form-control"
											name="loginPassword" placeholder="请输入密码">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<div class="checkbox">
											<label>  <input type="hidden"
												name="loginRemenber" value="false"> <input
												type="hidden" name="loginRemenber" value="true">
											</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-default"
											id="login-confirm" >登录</button>
									</div>
								</div>
							</form>
						</div>
						<div class="col-lg-1"></div>
						<div class="col-lg-6 jumbotron" >
							<form id="registerForm">
								<div class="form-group">
									<label class="col-sm-12" style="text-align:center;font-size:30px;color:orange;">注册</label>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">邮箱：</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" name="registerEmail"
											placeholder="请输入账号（登录用）" required="true">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">密码：</label>
									<div class="col-sm-9">
										<input type="password" class="form-control"
											name="registerPassword" placeholder="请输入密码"
											id="registerPassword" required="true">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">确认密码：</label>
									<div class="col-sm-9">
										<input type="password" class="form-control"
											name="registerPasswordAgain" placeholder="请输入确认密码"
											required="true">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<div class="checkbox">
											<label> <input type="checkbox" name="asManager"
												value="true"> 注册为管理员 <input type="hidden"
												name="asManager" value="false"> <input
												type="hidden" name="asManager" value="true">
											</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-default"
											id="register-confirm">确认注册</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-2"></div>
	</div>
</div>