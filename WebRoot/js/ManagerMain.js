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
//获得已读的已投简信息
function getOldResumes(){
	$('#panelName').text('已读的已投简');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/manager/getOldResumes",
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				var body = '';
				$.each(data.list[3].listJsons, function(index, comment) {
					body += '<div class="panel panel-warning"><div class="panel-heading">';
					body += '<h3 class="panel-title"><a href="#"  onclick="getJobDetail('+comment.parent.link+')">'+comment.parent.name+'</a></h3></div>';
					body += '<div class="panel-body">';
					$.each(comment.childList, function(index, stu){
						body+='    <div class="row">\n' +
				        '<div class="col-lg-4">'+(index+1)+'. <a href="#" onclick="getStudentInfo('+stu.stuId+')">\n' +
				        stu.name +
				        '</a></div>\n' +
				        '<div class="col-lg-8 text-right"> \n' +
				        '<button class="btn btn-warning" onclick="getStudentInfo('+stu.stuId+')">查看学生详情</button></a>\n' +
				        '<button class="btn btn-warning" onclick="interestResume('+stu.stuId+','+comment.parent.link+')">感兴趣</button></a>\n' +
				        '</div>\n' +
				        '</div><br>';
					});
					body += '</div></div>';
				});
				$('#panelBody').append(body);

			} else {
				$('#panelBody').text(data.msg);
			}
		},
		error : function() {
			alert('历史已投简！服务器崩溃');
		}
	});
};
//获得未处理的已投简信息
function getUnreadResumes(){
	$('#panelName').text('未读的已投简');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/manager/getUnreadResumes",
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				var body = '';
				$.each(data.list[3].listJsons, function(index, comment) {
					body += '<div class="panel panel-success"><div class="panel-heading">';
					body += '<h3 class="panel-title"><a href="#"  onclick="getJobDetail('+comment.parent.link+')">'+comment.parent.name+'</a></h3></div>';
					body += '<div class="panel-body">';
					$.each(comment.childList, function(index, stu){
						body+='    <div class="row">\n' +
				        '<div class="col-lg-4">'+(index+1)+'. <a href="#" onclick="getStudentInfo('+stu.stuId+')">\n' +
				        stu.name +
				        '</a></div>\n' +
				        '<div class="col-lg-8 text-right"> \n' +
				        '<button class="btn btn-warning" onclick="getStudentInfo('+stu.stuId+')">查看学生详情</button></a>\n' +
				        '<button class="btn btn-warning" onclick="readResume('+stu.stuId+','+comment.parent.link+')">不感兴趣</button></a>\n' +
				        '<button class="btn btn-warning" onclick="interestResume('+stu.stuId+','+comment.parent.link+')">感兴趣</button></a>\n' +
				        '</div>\n' +
				        '</div><br>';
					});
					body += '</div></div>';
				});
				$('#panelBody').append(body);

			} else {
				$('#panelBody').text(data.msg);
			}
		},
		error : function() {
			alert('发布新单位！服务器崩溃');
		}
	});
};
//获得感兴趣的已投简信息
function getInterestResumes(){
	$('#panelName').text('感兴趣的已投简');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/manager/getInterestResumes",
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				var body = '';
				$.each(data.list[3].listJsons, function(index, comment) {
					body += '<div class="panel panel-info"><div class="panel-heading">';
					body += '<h3 class="panel-title"><a href="#"  onclick="getJobDetail('+comment.parent.link+')">'+comment.parent.name+'</a></h3></div>';
					body += '<div class="panel-body">';
					$.each(comment.childList, function(index, stu){
						body+='    <div class="row">\n' +
				        '<div class="col-lg-4">'+(index+1)+'. <a href="#" onclick="getStudentInfo('+stu.stuId+')">\n' +
				        stu.name +
				        '</a></div>\n' +
				        '<div class="col-lg-8 text-right"> \n' +
				        '<button class="btn btn-warning" onclick="getStudentInfo('+stu.stuId+')">查看学生详情</button></a>\n' +
				        '<button class="btn btn-warning" onclick="readResume('+stu.stuId+','+comment.parent.link+')">不感兴趣</button></a>\n' +
				        '</div>\n' +
				        '</div><br>';
					});
					body += '</div></div>';
				});
				$('#panelBody').append(body);

			} else {
				$('#panelBody').text(data.msg);
			}
		},
		error : function() {
			alert('发布新单位！服务器崩溃');
		}
	});
};
//获得已发布的单位
function getReleasedJobs(){
	$('#panelName').text('已发布单位');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/manager/getReleasedJobs",
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
					        '<button class="btn btn-warning" onclick="alterJob('+comment.jobId+')">编辑</button></a>\n' +
					        '<button class="btn btn-warning" onclick="dropJob('+comment.jobId+')">删除</button></a>\n' +
					        '</div>\n' +
					        '</div><br>');
				});

			} else {
				$('#panelBody').text(data.msg);
			}
		},
		error : function() {
			alert('已发布单位！服务器崩溃');
		}
	});
};
//发布新单位
function releaseJob(){
	$('#panelName').text('发布新单位');
	$('#panelBody').text('');
	$('#panelBody').append('<form action="/recruiteOnline/manager/releaseJob" method="post">\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">单位名称：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="name"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">需求专业：</div>\n' +
	        '        <div class="col-lg-3"><div><input type="text" name="major"></div></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">职位：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="position"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">薪资：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="salary"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">任职要求：</div>\n' +
	        '        <div class="col-lg-9"><textarea name="duty" rows="10" cols="50" style="resize: none;"></textarea></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">单位地址：</div>\n' +
	        '        <div class="col-lg-9"><textarea name="address" rows="10" cols="50" style="resize: none;"></textarea></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3"><button type="submit" class="btn btn-warning">确认发布</button></div>\n' +
	        '    </div><br>\n' +
	        '</form>');
}
//编辑单位
function alterJob(data){
	$('#panelName').text('编辑单位');
	$('#panelBody').text('');
	$('#panelBody').append('<form action="/recruiteOnline/manager/updateJob" method="post">\n' +
	        '    <div class="row">\n' +
	        '        <input type="hidden" name="jobId" value="'+data+'">\n' +
	        '        <div class="col-lg-3">单位名称：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="name"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">需求专业：</div>\n' +
	        '        <div class="col-lg-3"><div><input type="text" name="major"></div></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">职位：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="position"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">薪资：</div>\n' +
	        '        <div class="col-lg-3"><input type="text" name="salary"></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">任职要求：</div>\n' +
	        '        <div class="col-lg-9"><textarea name="duty" rows="10" cols="50" style="resize: none;"></textarea></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3">单位地址：</div>\n' +
	        '        <div class="col-lg-9"><textarea name="address" rows="10" cols="50" style="resize: none;"></textarea></div>\n' +
	        '    </div><br>\n' +
	        '    <div class="row">\n' +
	        '        <div class="col-lg-3"><button type="submit" class="btn btn-warning">确认修改</button></div>\n' +
	        '    </div><br>\n' +
	        '</form>');
};
//单位详情
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
				        '			<button type="submit" class="btn btn-warning" onclick="alterJob('+comment.jobId+')">编辑</button>\n' +
				        '			<button type="submit" class="btn btn-warning" onclick="dropJob('+comment.jobId+')">删除</button>\n' +
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
//删除单位
function dropJob(data){
	$('#panelName').text('删除单位');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/manager/deleteJob?jobId="+data,
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data) {
				$('#panelBody').append('删除单位成功');
			} else {
				$('#panelBody').append('删除单位成失败...');
			}
		},
		error : function() {
			alert('单位详情！服务器崩溃');
		}
	});
};
//查看学生信息
function getStudentInfo(data){
	$('#panelName').text('查看学生信息');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/manager/getStudentInfo?stuId="+data,
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
				        '    </div><br>\n');
			} else {
				$('#panelBody').append(data.msg);
			}
		},
		error : function() {
			alert('学生信息！服务器崩溃');
		}
	});
};
// 设置不感兴趣
function readResume(stuId,jobId){
	$('#panelName').text('对这个学生不感兴趣');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/manager/readResume?stuId="+stuId+'&jobId='+jobId,
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				$('#panelBody').append('操作成功');
			} else {
				$('#panelBody').append(data.msg);
			}
		},
		error : function() {
			alert('不感兴趣！服务器崩溃');
		}
	});
};
//设置感兴趣
function interestResume(stuId,jobId){
	$('#panelName').text('对这个学生感兴趣');
	$('#panelBody').text('');
	$.ajax({
		url : "/recruiteOnline/manager/interestResume?stuId="+stuId+'&jobId='+jobId,
		dataType : "json",
		type : "POST",
		success : function(data) {
			if (data.state) {
				$('#panelBody').append('操作成功');
			} else {
				$('#panelBody').append(data.msg);
			}
		},
		error : function() {
			alert('感兴趣！服务器崩溃');
		}
	});
};//修改密码
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