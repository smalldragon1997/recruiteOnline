(function($) {
	$.fn.serializeJson = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		var str = this.serialize();
		$(array).each(function() {
			if (serializeObj[this.name]) {
				if ($.isArray(serializeObj[this.name])) {
					serializeObj[this.name].push(this.value);
				} else {
					serializeObj[this.name] = [ serializeObj[this.name], this.value ];
				}
			} else {
				serializeObj[this.name] = this.value;
			}
		});
		return serializeObj;
	};
})(jQuery);
$(document).ready(function() {
	
});
//修改密码
function updatePassword(){
	$('#panelName').text('修改密码');
	$('#panelBody').text('');
	$('#panelBody').append('<form action="/recruiteOnline/security/updatePassword" method="post">\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">旧密码：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="oldPassword"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">新密码：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="newPassword"></div>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-12">'+
	        '			<button class="btn btn-warning" type="submit">确认修改密码</button>'+
	        '		</div>\n' +
	        '    </div><br>\n' +
	        '</form>');
};
//修改个人信息
function updateStuInfo(){
	$('#panelName').text('修改个人信息');
	$('#panelBody').text('');
	$('#panelBody').append('<form action="/recruiteOnline/student/updateStuInfo" method="post">\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">姓名：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="name"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">性别：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="sex"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">专业：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="major"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">出生日期：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="birthday"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">主干课程：</div>\n' +
	        '        <div class="col-lg-9"><input type="text" name="majorClass"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">奖惩：</div>\n' +
	        '        <div class="col-lg-9"><textarea name="rewards" rows="10" cols="50" style="resize: none;"></textarea></div>\n' +
	        '    </div><br>\n'+
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">简历：</div>\n' +
	        '        <div class="col-lg-9"><textarea name="resume" rows="10" cols="50" style="resize: none;"></textarea></div>\n' +
	        '    </div><br>\n'+
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">期望薪资：</div>\n' +
	        '        <div class="col-lg-9"><input type="text" name="exceptSal"></div>\n' +
	        '    </div><br>\n'+
	        '    <div class="row">\n' +
	        '        <div class="col-lg-12">'+
	        '			<button class="btn btn-warning" type="submit">确认修改信息</button>'+
	        '		</div>\n' +
	        '    </div><br>\n' +
	        '</form>');
};
//设置个人信息
function addStuInfo(){
	$('#panelName').text('设置个人信息');
	$('#panelBody').text('');
	$('#panelBody').append('<form action="/recruiteOnline/student/addStuInfo" method="post">\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">姓名：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="name"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">性别：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="sex"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">专业：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="major"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">出生日期：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="birthday"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">主干课程：</div>\n' +
	        '        <div class="col-lg-9"><input type="text" name="majorClass"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">奖惩：</div>\n' +
	        '        <div class="col-lg-9"><textarea name="rewards" rows="10" cols="50" style="resize: none;"></textarea></div>\n' +
	        '    </div><br>\n'+
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">简历：</div>\n' +
	        '        <div class="col-lg-9"><textarea name="resume" rows="10" cols="50" style="resize: none;"></textarea></div>\n' +
	        '    </div><br>\n'+
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">期望薪资：</div>\n' +
	        '        <div class="col-lg-9"><input type="text" name="exceptSal"></div>\n' +
	        '    </div><br>\n'+
	        '    <div class="row">\n' +
	        '        <div class="col-lg-12">'+
	        '			<button class="btn btn-warning" type="submit">确认个人信息</button>'+
	        '		</div>\n' +
	        '    </div><br>\n' +
	        '</form>');
};
//查看个人信息
function getStudentInfo(){
	$('#panelName').text('查看个人信息');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/student/getStudentInfo",
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				var comment = data.list[3].info;
				$('#panelBody').append('    <div class="row">\n' +
				        '        <div class="col-lg-3">姓名：</div>\n' +
				        '        <div class="col-lg-3">'+comment.name+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">性别：</div>\n' +
				        '        <div class="col-lg-3">'+comment.sex+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">专业：</div>\n' +
				        '        <div class="col-lg-3">'+comment.major+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">出生日期：</div>\n' +
				        '        <div class="col-lg-3">'+comment.birthday+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">主干课程：</div>\n' +
				        '        <div class="col-lg-9">'+comment.majorClass+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">奖惩：</div>\n' +
				        '        <div class="col-lg-9">'+comment.rewards+'</div>\n' +
				        '    </div><br>\n'+
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">简历：</div>\n' +
				        '        <div class="col-lg-9">'+comment.resume+'</div>\n' +
				        '    </div><br>\n'+
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">期望薪资：</div>\n' +
				        '        <div class="col-lg-9">'+comment.exceptSal+'</div>\n' +
				        '    </div><br>\n'+
				        '    <div class="row">\n' +
				        '        <div class="col-lg-12">'+
				        '			<button class="btn btn-warning" onclick="updateStuInfo()">修改个人信息</button>'+
				        '		</div>\n' +
				        '    </div><br>\n');
			} else {
				$('#panelBody').append(data.msg);
				$('#panelBody').append('    <div class="row">\n' +
				        '        <div class="col-lg-12">'+
				        '			<button class="btn btn-warning" onclick="addStuInfo()">设置个人信息</button>'+
				        '		</div>\n' +
				        '    </div><br>\n');
			}
		},
		error : function() {
			alert('设置个人信息！服务器崩溃');
		}
	});
};
//获得全部单位
function getAllJobs(){
	$('#panelName').text('全部单位');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/public/getJobs",
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				$.each(data.list[3].jobs, function(index, comment) {
					$('#panelBody').append('    <div class="row">\n' +
					        '<div class="col-lg-8">'+(index+1)+'. <a href="#" onclick="getJobDetail('+comment.jobId+')">\n' +
					        comment.name +
					        '</a></div>\n' +
					        '<div class="col-lg-4 text-right"> \n' +
					        '<button class="btn btn-warning" onclick="getJobDetail('+comment.jobId+')">查看详情</button>\n' +
					        '</div>\n' +
					        '</div><br>');
				});

			} else {
				$('#panelBody').text(data.msg);
			}
		},
		error : function() {
			alert('全部单位！服务器崩溃');
		}
	});
};
//获得未读的投简单位
function getNewResumeJobs(){
	$('#panelName').text('未被读的投简单位');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/student/getNewResumedJobs",
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				$.each(data.list[3].jobs, function(index, comment) {
					$('#panelBody').append('    <div class="row">\n' +
					        '<div class="col-lg-8">'+(index+1)+'. <a href="#" onclick="getResumeDetail('+comment.jobId+')">\n' +
					        comment.name +
					        '</a></div>\n' +
					        '<div class="col-lg-4 text-right"> \n' +
					        '<button class="btn btn-warning" onclick="getResumeDetail('+comment.jobId+')">查看单位详情</button>\n' +
					        '<button class="btn btn-warning" onclick="dropResume('+comment.jobId+')">撤回投简</button>\n' +
					        '</div>\n' +
					        '</div><br>');
				});

			} else {
				$('#panelBody').text(data.msg);
			}
		},
		error : function() {
			alert('全部单位！服务器崩溃');
		}
	});
}
//获得已读的投简单位
function getOldResumedJobs(){
	$('#panelName').text('已被读的投简单位');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/student/getOldResumedJobs",
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				$.each(data.list[3].jobs, function(index, comment) {
					$('#panelBody').append('    <div class="row">\n' +
					        '<div class="col-lg-8">'+(index+1)+'. <a href="#" onclick="getResumeDetail('+comment.jobId+')">\n' +
					        comment.name +
					        '</a></div>\n' +
					        '<div class="col-lg-4 text-right"> \n' +
					        '<button class="btn btn-warning" onclick="getResumeDetail('+comment.jobId+')">查看单位详情</button>\n' +
					        '<button class="btn btn-warning" onclick="dropResume('+comment.jobId+')">撤回投简</button>\n' +
					        '</div>\n' +
					        '</div><br>');
				});

			} else {
				$('#panelBody').text(data.msg);
			}
		},
		error : function() {
			alert('全部单位！服务器崩溃');
		}
	});
}
//获得被感兴趣的投简单位
function getInterestResumedJobs(){
	$('#panelName').text('感兴趣的投简单位');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/student/getInterestResumedJobs",
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				$.each(data.list[3].jobs, function(index, comment) {
					$('#panelBody').append('    <div class="row">\n' +
					        '<div class="col-lg-8">'+(index+1)+'. <a href="#" onclick="getResumeDetail('+comment.jobId+')">\n' +
					        comment.name +
					        '</a></div>\n' +
					        '<div class="col-lg-4 text-right"> \n' +
					        '<button class="btn btn-warning" onclick="getResumeDetail('+comment.jobId+')">查看单位详情</button>\n' +
					        '<button class="btn btn-warning" onclick="dropResume('+comment.jobId+')">撤回投简</button>\n' +
					        '</div>\n' +
					        '</div><br>');
				});

			} else {
				$('#panelBody').text(data.msg);
			}
		},
		error : function() {
			alert('全部单位！服务器崩溃');
		}
	});
}
//查看已投简单位详情
function getResumeDetail(data){
	$('#panelName').text('单位详情');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/public/getDetailJob?jobId="+data,
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				var comment = data.list[3].job;
				$('#panelBody').append('    <div class="row">\n' +
				        '        <div class="col-lg-3">单位名称：</div>\n' +
				        '        <div class="col-lg-3">'+comment.name+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">需求专业：</div>\n' +
				        '        <div class="col-lg-3">'+comment.major+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">职位：</div>\n' +
				        '        <div class="col-lg-3">'+comment.position+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">薪资：</div>\n' +
				        '        <div class="col-lg-3">'+comment.salary+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">任职要求：</div>\n' +
				        '        <div class="col-lg-9">'+comment.duty+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">单位地址：</div>\n' +
				        '        <div class="col-lg-9">'+comment.address+'</div>\n' +
				        '    </div><br>\n');
			} else {
				$('#panelBody').text(data.msg);
			}
		},
		error : function() {
			alert('单位详情！服务器崩溃');
		}
	});
};
//查看未投简单位详情
function getJobDetail(data){
	$('#panelName').text('单位详情');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/public/getDetailJob?jobId="+data,
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				var comment = data.list[3].job;
				$('#panelBody').append('    <div class="row">\n' +
				        '        <div class="col-lg-3">单位名称：</div>\n' +
				        '        <div class="col-lg-3">'+comment.name+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">需求专业：</div>\n' +
				        '        <div class="col-lg-3">'+comment.major+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">职位：</div>\n' +
				        '        <div class="col-lg-3">'+comment.position+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">薪资：</div>\n' +
				        '        <div class="col-lg-3">'+comment.salary+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">任职要求：</div>\n' +
				        '        <div class="col-lg-9">'+comment.duty+'</div>\n' +
				        '    </div><br>\n' +
				        '    <div class="row">\n' +
				        '        <div class="col-lg-3">单位地址：</div>\n' +
				        '        <div class="col-lg-9">'+comment.address+'</div>\n' +
				        '    </div><br>\n'+
				        '    <div class="row">\n' +
				        '        <div class="col-lg-12">'+
				        '			<button type="submit" class="btn btn-warning" onclick="resume('+comment.jobId+')">投简</button>\n' +
				        '        </div>\n' +
				        '    </div><br>\n' );
			} else {
				$('#panelBody').text(data.msg);
			}
		},
		error : function() {
			alert('单位详情！服务器崩溃');
		}
	});
};
//投简
function resume(data){
	$('#panelName').text('投简');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/student/resume?jobId="+data,
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				$('#panelBody').append('投简成功');
			} else {
				$('#panelBody').append('投简失败：'+data.msg);
			}
		},
		error : function() {
			alert('投简！服务器崩溃');
		}
	});
};
//撤回投简
function dropResume(data){
	$('#panelName').text('撤回投简');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/student/deleteResumed?jobId="+data,
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				$('#panelBody').append('撤回投简成功');
			} else {
				$('#panelBody').append('撤回投简失败：'+data.msg);
			}
		},
		error : function() {
			alert('投简！服务器崩溃');
		}
	});
};