package com.job.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.job.dao.JobDao;
import com.job.dao.ManDao;
import com.job.dao.ManLogDao;
import com.job.dao.ManRegDao;
import com.job.dao.StuDao;
import com.job.dao.StuLogDao;
import com.job.dao.StuRegDao;
import com.job.entity.Man;
import com.job.entity.ManLog;
import com.job.entity.ManReg;
import com.job.entity.Stu;
import com.job.entity.StuLog;
import com.job.entity.StuReg;
import com.job.json.LoginJson;
import com.job.json.MsgJson;
import com.job.json.RegisterJson;
import com.job.service.SecurityService;
import com.job.tools.MyCookie;
import com.job.tools.MyEnCoding;
import com.job.tools.MyMsgJson;
import com.job.tools.MyTimestramp;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private StuDao stuDao;
	@Autowired
	private StuLogDao stuLogDao;
	@Autowired
	private StuRegDao stuRegDao;
	@Autowired
	private ManDao manDao;
	@Autowired
	private ManLogDao manLogDao;
	@Autowired
	private ManRegDao manRegDao;

	// 注册用户服务
	// 事务的管理 无事务就创建新事务 存在事务就在该事务中进行
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public MsgJson<String, Object> register(MsgJson<String, Object> msgFromController) {

		// 获取注册信息
		RegisterJson registerJson = (RegisterJson) msgFromController.getJsonData("registerJson");

		String registerEmail = registerJson.getRegisterEmail();
		String registerPassword = registerJson.getRegisterPassword();
		boolean asManager;
		if (registerJson.getAsManager().length == 3)
			asManager = true;
		else
			asManager = false;
		// 初始化 返回消息 返回给前端的session或response或request
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		msgToController.setMsg("注册成功");
		// 如果存在cookie分隔符& 也就是非法字符 则报错
		if (registerEmail.contains("&")) {
			msgToController.setMsg("注册失败：邮箱存在非法字符'&'");
			msgToController.setState(false);
			return msgToController;
		}
		// 如果用户名存在，否则返回主界面
		if (stuDao.getStuByEmail(registerEmail) == null && manDao.getManByEmail(registerEmail) == null) {
			// 获得MD5加密的密码，失败则返回注册界面
			try {
				registerPassword = MyEnCoding.encoderByMd5(registerPassword);
			} catch (Exception e) {
				msgToController.setMsg("注册失败：加密错误");
				msgToController.setState(false);
				return msgToController;
			}
			// 获得注册时间
			Timestamp registerTimestamp = MyTimestramp.setTime(new Timestamp(System.currentTimeMillis()));
			// 初始化自动登录有效时间
			Timestamp lastTimestamp = MyTimestramp.setTime(new Timestamp(System.currentTimeMillis()));
			if (asManager) {
				// 封装用户注册数据
				Man register = new Man(1, registerEmail, registerPassword);
				// 如果注册成功，保存登录状态，前往获取用户信息，否则回到注册界面
				if (manDao.insertMan(register) == 1) {
					// 更新stuReg表中用户的信息数据
					ManReg manReg = new ManReg(register.getManId(), registerTimestamp, "袁伟伦");
					// 更新stuLog表中的数据
					ManLog manLog = new ManLog(register.getManId(), lastTimestamp, lastTimestamp);
					manRegDao.insertManReg(manReg);
					manLogDao.insertManLog(manLog);
					// 设置session中的用户信息
					msgToController.setSessionData("email", registerEmail);
					// 设置session的 attr 使得控制层可以接收到需要返回给前端的属性
					msgToController.setSessionData("isLogin", "yes");
					msgToController.setSessionData("asManager", "yes");
					msgToController.setSessionData("manId", register.getManId());
				} else {
					msgToController.setMsg("注册失败:系统错误");
					msgToController.setState(false);
					return msgToController;
				}
			} else {
				// 封装用户注册数据
				Stu register = new Stu(1, registerEmail, registerPassword);
				// 如果注册成功，保存登录状态，前往获取用户信息，否则回到注册界面
				if (stuDao.insertStu(register) == 1) {
					// 更新stuReg表中用户的信息数据
					StuReg stuReg = new StuReg(register.getStuId(), registerTimestamp, "袁伟伦");
					// 更新stuLog表中的数据
					StuLog stuLog = new StuLog(register.getStuId(), lastTimestamp, lastTimestamp);
					stuRegDao.insertStuReg(stuReg);
					stuLogDao.insertStuLog(stuLog);
					// 设置session中的用户信息
					msgToController.setSessionData("email", registerEmail);
					// 设置session的 attr 使得控制层可以接收到需要返回给前端的属性
					msgToController.setSessionData("isLogin", "yes");
					msgToController.setSessionData("asManager", "no");
					msgToController.setSessionData("stuId", register.getStuId());
				} else {
					msgToController.setMsg("注册失败:系统错误");
					msgToController.setState(false);
					return msgToController;
				}
			}
			msgToController.setJsonData("page", "/recruiteOnline/page/main");
		} else {
			// 用户名存在，返回主界面
			msgToController.setMsg("邮箱已存在");
			msgToController.setState(false);
			return msgToController;
		}
		return msgToController;
	}

	// 自动登录服务
	@Override
	public MsgJson<String, Object> freeLogin(MsgJson<String, Object> msgFromController) {

		// 初始化 返回消息 没有返回给前端的session或response或request
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取用户登录状态 下标为0代表取的是session的hashmap
		HashMap<String, Object> sessionMap = msgFromController.getSessionDataMap();
		// 用户没有登录
		if (sessionMap.get("isLogin") == null) {
			// 获取request中的cookies数组
			Cookie cookies[] = (Cookie[]) msgFromController.getRequestData("cookies");

			// 如果有cookie，获得cookies数组
			Cookie cookie = MyCookie.getCookie(cookies, "recruiteOnline");

			// 如果cookie不为空 解析cookie内容
			if (cookie != null) {
				// 获得cookie值，并base64解码用“&”分割
				String value[] = null;
				// 对读取的cookie解码获取正确的值
				String cookieValue = URLDecoder.decode(cookie.getValue());
				// 得到value[]分别为[0]账号[1]MD5(账号+MD5(密码)+有效时间)[2]有效时间
				value = MyEnCoding.getFromBase64(cookieValue).split("&");
				System.out.println("读取时的value" + cookie.getValue());
				for (int i = 0; i < value.length; i++) {
					System.out.println("value的第" + i + "段" + value[i]);
				}
				// 如果长度不为3 自动登录失败
				if (value.length != 3) {
					msgToController.setMsg("cookie读取错误，自动登录失败");
					System.out.println("cookie读取错误，自动登录失败");
					msgToController.setState(false);
					msgToController.setJsonData("page", "login");
					return msgToController;
				}
				// 如果有效时间超时，自动登陆失败
				if (Timestamp.valueOf(value[2]).getTime() - new Timestamp(System.currentTimeMillis()).getTime() < 0) {
					msgToController.setMsg("cookie已超时，自动登录失败");
					System.out.println("cookie已超时，自动登录失败");
					msgToController.setState(false);
					msgToController.setJsonData("page", "login");
					return msgToController;
				}
				Stu stu = stuDao.getStuByEmail(value[0]);
				Man man = manDao.getManByEmail(value[0]);
				// 根据账号查找 不存在则失败
				if (stu == null && man == null) {
					msgToController.setMsg("根据cookie读取账号错误，自动登录失败");
					System.out.println("根据cookie读取账号错误，自动登录失败");
					msgToController.setState(false);
					msgToController.setJsonData("page", "login");
					return msgToController;
				} else {
					// 长度为3 有效时间未超时 账号存在 则判断cookie中的值是否有效
					try {
						if (stu == null) {
							// 获得系统中用户注册信息的MD5(账号+MD5(密码)+有效时间)
							String md5 = MyEnCoding.encoderByMd5(value[0] + stuDao.getStuByEmail(value[0]).getPassword()
									+ stuLogDao.getStuLog(stu.getStuId()).getAbleTime());

							System.out.println("数据库的取出的值" + md5);
							// 如果匹配则登录成功
							if (value[1].equals(md5)) {
								// 设置返回的session信息 下标0代表session
								msgToController.setSessionData("isLogin", "yes");
								msgToController.setSessionData("email", value[0]);
								msgToController.setSessionData("manId", man.getManId());
								msgToController.setSessionData("asManager", "yes");
								msgToController.setJsonData("page", "platform");
							}
						} else {
							// 获得系统中用户注册信息的MD5(账号+MD5(密码)+有效时间)
							String md5 = MyEnCoding.encoderByMd5(value[0] + manDao.getManByEmail(value[0]).getPassword()
									+ manLogDao.getManLog(man.getManId()).getAbleTime());

							System.out.println("数据库的取出的值" + md5);
							// 如果匹配则登录成功
							if (value[1].equals(md5)) {
								// 设置返回的session信息 下标0代表session
								msgToController.setSessionData("isLogin", "yes");
								msgToController.setSessionData("email", value[0]);
								msgToController.setSessionData("stuId", stu.getStuId());
								msgToController.setSessionData("asManager", "no");
								msgToController.setJsonData("page", "platform");
							}
						}
					} catch (Exception e) {
						msgToController.setMsg("系统错误，自动登录失败");
						System.out.println("系统错误，自动登录失败");
						msgToController.setState(false);
						msgToController.setJsonData("page", "login");
						return msgToController;
					}
				}
			} else { // 如果cookie为空 name直接跳转到主页 不做任何操作
				msgToController.setMsg("找不到cookie，自动登录失败");
				System.out.println("Service找不到cookie，自动登录失败");
				msgToController.setState(false);
				msgToController.setJsonData("page", "login");
				return msgToController;
			}
		}
		msgToController.setJsonData("page", "platform");
		return msgToController;
	}

	// 登录服务
	@Override
	public MsgJson<String, Object> login(MsgJson<String, Object> msgFromController) {
		// 获得表单信息
		LoginJson loginJson = (LoginJson) msgFromController.getJsonData("loginJson");

		String loginEmail = loginJson.getLoginEmail();
		String loginPassword = loginJson.getLoginPassword();
		String loginRemenber[] = loginJson.getLoginRemenber();
		if (loginRemenber.length == 2) {
			System.out.println("未勾选7天");
		} else {
			System.out.println("已勾选7天");
		}
		// 初始化提示消息msg
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		msgToController.setMsg("登录成功");

		// 获取用户注册信息
		Stu stu = stuDao.getStuByEmail(loginEmail);
		Man man = manDao.getManByEmail(loginEmail);

		if (stu == null && man == null) {
			msgToController.setMsg("邮箱不存在");
			msgToController.setState(false);
			return msgToController;
		}
		// 获取当前系统的时间戳
		Timestamp lastTimestamp = MyTimestramp.setTime(new Timestamp(System.currentTimeMillis()));
		// 如果用户名和密码不为空获取用户名和密码，否则返回登录界面
		if (loginEmail != null && loginPassword != null) {
			// 获得MD5加密的密码，失败则返回注册界面
			try {
				loginPassword = MyEnCoding.encoderByMd5(loginPassword);
			} catch (Exception e) {
				msgToController.setMsg("密码加密失败");
				msgToController.setState(false);
				return msgToController;
			}
			// 前往匹配用户密码
			if ((stu != null && stu.getEmail().equals(loginEmail) && stu.getPassword().equals(loginPassword))
					|| (man != null && man.getEmail().equals(loginEmail) && man.getPassword().equals(loginPassword))) {
				// 匹配成功保存用户登录状态
				msgToController.setSessionData("isLogin", "yes");
				if (stu == null) {
					msgToController.setSessionData("email", man.getEmail());
					msgToController.setSessionData("asManager", "yes");
					msgToController.setSessionData("manId", man.getManId());
				} else {
					msgToController.setSessionData("email", stu.getEmail());
					msgToController.setSessionData("asManager", "no");
					msgToController.setSessionData("stuId", stu.getStuId());
				}
				msgToController.setJsonData("page", "/recruiteOnline/page/main");
				// 如果勾选7天免登陆
				// 保存客户端cookie
				if (loginRemenber.length == 3) {
					System.out.println("已经勾选7天免登陆 执行保存cookie操作");
					// 设置自动登录有效时间为7天 7*24*60*60*1000毫秒
					lastTimestamp = new Timestamp(System.currentTimeMillis());
					long time = lastTimestamp.getTime();
					time = time + 7 * 24 * 60 * 60 * 1000;
					lastTimestamp = MyTimestramp.setTime(new Timestamp(time));
					try {
						// 更新cookie的value：base64(账号+MD5(账号+MD5(密码)+有效时间)+有效时间)
						String CookieValue = MyEnCoding.getBase64(
								loginEmail + "&" + MyEnCoding.encoderByMd5(loginEmail + loginPassword + lastTimestamp)
										+ "&" + lastTimestamp);
						// 对cookievalue进行编码 以防止出现特殊字符 导致cookie错误
						CookieValue = URLEncoder.encode(CookieValue);
						System.out.println("登录时保存的cookie" + CookieValue);
						Cookie cookie = new Cookie("recruiteOnline", CookieValue);
						cookie.setMaxAge(7 * 24 * 60 * 60);
						// 写入responseHashMap供controller使用
						msgToController.setResponseData("cookie", cookie);

						// 更新 数据库表中的lastTime自动登录有效时间
						if (stu == null) {
							ManLog manLog = new ManLog(man.getManId(),
									MyTimestramp.setTime(new Timestamp(System.currentTimeMillis())), lastTimestamp);
							manLogDao.updateManLog(manLog);

						} else {
							StuLog stuLog = new StuLog(stu.getStuId(),
									MyTimestramp.setTime(new Timestamp(System.currentTimeMillis())), lastTimestamp);
							stuLogDao.updateStuLog(stuLog);
						}

					} catch (Exception e) {
						msgToController.setMsg(msgToController.getMsg() + " 但是cookie保存失败");
						System.out.println(msgToController.getMsg() + " 但是cookie在Service中保存失败");
					}
				}
			} else {
				msgToController.setMsg("用户名或密码错误");
				msgToController.setState(false);
				return msgToController;
			}
		} else {
			// 用户名和密码为空，返回登录界面
			msgToController.setMsg("请输入完整");
			msgToController.setState(false);
			return msgToController;
		}

		return msgToController;
	}

	// 注销服务
	@Override
	public MsgJson<String, Object> exit(MsgJson<String, Object> msgFromController) {
		// 获得从控制层传来的数据
		Cookie cookies[] = (Cookie[]) msgFromController.getRequestData("cookies");
		// 初始化返回控制层的数据
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		msgToController.setMsg("退出成功");
		// 如果有cookie，获得cookies数组
		Cookie cookie = MyCookie.getCookie(cookies, "recruiteOnline");
		if (cookie != null) {
			cookie.setMaxAge(0);
			msgToController.setResponseData("cookie", cookie);
			msgToController.setMsg(msgToController.getMsg() + " cookie删除成功");
		}
		msgToController.setSessionData("session", "invalidate");
		msgToController.setJsonData("page", "/recruiteOnline/page/login");

		return msgToController;
	}

	// 修改密码
	@Override
	public void alterPwd(MsgJson<String, Object> msgFromController) {
		
		String oldPassword = (String) msgFromController.getRequestData("oldPassword");
		String newPassword = (String) msgFromController.getRequestData("newPassword");
		if(msgFromController.getSessionData("stuId")==null){
			int manId = (Integer) msgFromController.getSessionData("manId");
			Man man = manDao.getMan(manId);
			try {
				if(man.getPassword().equals(MyEnCoding.encoderByMd5(oldPassword))){
					//如果密码匹配 则更新数据库的加密密码
					Man newMan = new Man(manId,man.getEmail(),MyEnCoding.encoderByMd5(newPassword));
					manDao.updateMan(newMan);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			int stuId = (Integer) msgFromController.getSessionData("stuId");
			Stu stu = stuDao.getStu(stuId);
			try {
				if(stu.getPassword().equals(MyEnCoding.encoderByMd5(oldPassword))){
					//如果密码匹配 则更新数据库的加密密码
					Stu newStu = new Stu(stuId,stu.getEmail(),MyEnCoding.encoderByMd5(newPassword));
					stuDao.updateStu(newStu);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
