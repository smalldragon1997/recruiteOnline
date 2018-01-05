<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-lg-3"></div>
		<div class="col-lg-6">
			<div class="row"></div>
			<div class="row">
				<!-- 左侧导航栏 -->
				<div class="col-lg-2">
					<c:if test="${sessionScope.asManager eq 'yes' }">
						<div>
							<button type="button" class="btn btn-primary"data-toggle="collapse" data-target="#0" style="width:120px;">
								我的单位
							</button>
						</div>
						<div id="0" class="collapse" style="width:120px;">
							<button type="button" class="btn" style="width:120px;" onclick="releaseJob()">
								发布新单位
							</button>
							<button type="button" class="btn" style="width:120px;" onclick="getReleasedJobs()">
								查看已发布单位
							</button>
						</div>
						<div>
							<button type="button" class="btn btn-primary"data-toggle="collapse" data-target="#1" style="width:120px;">
								收到投简
							</button>
						</div>
						<div id="1" class="collapse" style="width:120px;">
							<button type="button" class="btn" style="width:120px;" onclick="getUnreadResumes()">
								未读的已投简
							</button>
							<button type="button" class="btn" style="width:120px;" onclick="getInterestResumes()">
								感兴趣的已投简
							</button>
							<button type="button" class="btn" style="width:120px;" onclick="getOldResumes()">
								已读的已投简
							</button>
						</div>
						<div>
							<button type="button" class="btn btn-primary"data-toggle="collapse" data-target="#2" style="width:120px;">
								个人
							</button>
						</div>
						<div id="2" class="collapse" style="width:120px;">
							<button type="button" class="btn" style="width:120px;" onclick="updatePassword()">
								修改密码
							</button>
						</div>
					</c:if>
					<c:if test="${sessionScope.asManager ne 'yes' }">
						<div>
							<button type="button" class="btn btn-primary"data-toggle="collapse" data-target="#0" style="width:120px;">
								单位
							</button>
						</div>
						<div id="0" class="collapse" style="width:120px;">
							<button type="button" class="btn" style="width:120px;" onclick="getAllJobs()">
								查看单位
							</button>
						</div>
						<div>
							<button type="button" class="btn btn-primary"data-toggle="collapse" data-target="#1" style="width:120px;">
								投简
							</button>
						</div>
						<div id="1" class="collapse" style="width:120px;">
							<button type="button" class="btn" style="width:120px;" onclick="getNewResumeJobs()">
								未被读的投简
							</button>
							<button type="button" class="btn" style="width:120px;" onclick="getOldResumedJobs()">
								已被读的投简
							</button>
							<button type="button" class="btn" style="width:120px;" onclick="getInterestResumedJobs()">
								被感兴趣的投简
							</button>
						</div>
						<div>
							<button type="button" class="btn btn-primary"data-toggle="collapse" data-target="#2" style="width:120px;">
								个人
							</button>
						</div>
						<div id="2" class="collapse" style="width:120px;">
							<button type="button" class="btn" style="width:120px;" onclick="getStudentInfo()">
								查看个人信息
							</button>
							<button type="button" class="btn" style="width:120px;" onclick="updatePassword()">
								修改密码
							</button>
						</div>
					</c:if>
				</div>
				<!-- 右侧主内容 -->
				<div class="col-lg-10">
					<div class="panel panel-info" >
						<div class="panel-heading">
							<h3 class="panel-title" id="panelName">在线招聘管理系统</h3>
						</div>
						<div class="panel-body" id="panelBody">无内容</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-3"></div>
	</div>
</div>