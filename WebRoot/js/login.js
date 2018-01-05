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
	/*注册表单验证*/
	var registerForm = $('#registerForm');
	registerForm.validate({
		rules : { //name
			registerUsername : {
				required : true,
				rangelength : [ 6, 16 ]
			},
			registerEmail : {
				required : true,
				email : true,
				rangelength : [ 4, 30 ]
			},
			registerPassword : {
				rangelength : [ 6, 16 ],
				required : true
			},
			registerPasswordAgain : {
				required : true,
				equalTo : '#registerPassword'
			}
		},
		messages : {
			registerUsername : {
				required : "必须输入用户名",
				rangelength : "用户名长度6~16"
			},
			registerEmail : {
				required : "必须输入邮箱",
				email : "请输入正确的邮箱格式",
				rangelength : "邮箱长度不能大于30"
			},
			registerPassword : {
				rangelength : "密码长度在6-16为位",
				required : '必须输入密码'
			},
			registerPasswordAgain : {
				required : "必须输入确认密码",
				equalTo : '两次输入不一致'
			}
		}
	}
	);
	/*注册表单验证*/
	$('#register-confirm').click(function() {
		if (registerForm.valid()) {
			//防止重复提交
			$(this).attr("disabled", "true").text('正在注册...'); //设置变灰按钮
			var data = $('#registerForm').serializeJson();
			$.ajax({
				url : "/recruiteOnline/security/register",
				contentType : 'application/json;charset=utf-8',
				dataType : "json",
				data : JSON.stringify(data),
				type : "POST",
				success : function(data) {
					if (data.state) {
						/*这里使用的异步请求，当请求登录成功的时候，重新定位到index界面*/
						alert(data.msg);
						window.location.href = data.list[3].page;
					} else {
						alert(data.msg);
						setTimeout("$('#register-confirm').removeAttr('disabled').text('确认注册')", 1000); //设置1秒后提交按钮 显示
					}
				},
				error : function() {
					alert('注册失败！服务器崩溃');
					setTimeout("$('#register-confirm').removeAttr('disabled').text('确认注册')", 1000); //设置1秒后提交按钮 显示
				}
			});
		}
	});

	/*登录表单验证*/
	var loginForm = $('#loginForm');
	loginForm.validate({
		rules : { //name
			loginEmail : {
				required : true,
				email : true,
				rangelength : [ 4, 30 ],
			},
			loginPassword : {
				rangelength : [ 6, 16 ],
				required : true
			}
		},
		messages : {
			loginEmail : {
				required : "必须输入邮箱",
				email : "请输入正确的邮箱格式",
				rangelength : "邮箱长度不能大于30"
			},
			loginPassword : {
				rangelength : "密码长度在6-16为位",
				required : '必须输入密码'
			}
		}
	}
	);
	/*登录表单验证*/
	$('#login-confirm').click(function() {
		//通过表单验证即可提交请求
		if (loginForm.valid()) {
			//防止重复提交
			$(this).attr("disabled", "true").text('正在登录...'); //设置变灰按钮
			var data = $('#loginForm').serializeJson();
			$.ajax({
				url : "/recruiteOnline/security/login",
				contentType : 'application/json;charset=utf-8',
				dataType : "json",
				data : JSON.stringify(data),
				type : "POST",
				success : function(data) {
					if (data.state) {
						/*这里使用的异步请求，当请求登录成功的时候，重新定位到index界面*/
						window.location.href = data.list[3].page;
					} else {
						alert(data.msg);
						setTimeout("$('#login-confirm').removeAttr('disabled').text('登录')", 1000); //设置1秒后提交按钮 显示
					}
				},
				error : function() {
					alert('登陆失败！服务器崩溃');
					setTimeout("$('#login-confirm').removeAttr('disabled').text('登录')", 1000); //设置1秒后提交按钮 显示
				}
			});
		}
	});
	//退出登录
	$('#exit-btn').click(function() {
		$.ajax({
			url : "/recruiteOnline/security/exit",
			dataType : "json",
			type : "POST",
			success : function(data) {
				if (data.state) {
					/*这里使用的异步请求，当请求成功的时候，重新定位到index界面*/
					alert(data.msg);
					window.location.href = data.list[3].page;
				} else {
					alert(data.msg);
				}
			},
			error : function() {
				alert('退出失败！服务器崩溃');
			}
		});
	});
});