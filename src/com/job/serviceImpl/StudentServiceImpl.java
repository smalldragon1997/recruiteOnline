package com.job.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.dao.InfoDao;
import com.job.dao.JobDao;
import com.job.dao.StuSendDao;
import com.job.entity.Info;
import com.job.entity.Job;
import com.job.entity.StuSend;
import com.job.json.MsgJson;
import com.job.service.StudentService;
import com.job.tools.MyMsgJson;
import com.job.tools.MyTimestramp;
import com.sun.mail.handlers.multipart_mixed;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private InfoDao infoDao;
	@Autowired
	private StuSendDao stuSendDao;

	@Autowired
	private JobDao jobDao;

	// 添加 学生信息
	@Override
	public MsgJson<String, Object> addStuInfo(MsgJson<String, Object> msgFromController) {
		// TODO Auto-generated method stub
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取
		Info info = (Info) msgFromController.getJsonData("info");
		// 操作
		if (infoDao.insertInfo(info) != 1) {
			msgToController.setMsg("添加学生信息失败");
			msgToController.setState(false);
			return msgToController;
		}
		return msgToController;
	}

	// 更新 学生信息
	@Override
	public MsgJson<String, Object> alterStuInfo(MsgJson<String, Object> msgFromController) {
		// TODO Auto-generated method stub
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取
		Info info = (Info) msgFromController.getJsonData("info");
		// 操作
		if (infoDao.updateInfo(info) != 1) {
			msgToController.setMsg("更新学生信息失败");
			msgToController.setState(false);
			return msgToController;
		}
		return msgToController;
	}

	// 删除 学生信息
	@Override
	public MsgJson<String, Object> dropStuInfo(MsgJson<String, Object> msgFromController) {
		// TODO Auto-generated method stub
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取
		int stuId = (Integer) msgFromController.getRequestData("stuId");
		// 操作
		if (infoDao.deleteInfo(stuId) == 1) {
			return msgToController;
		}
		return msgToController;
	}

	// 添加 投简信息
	@Override
	public MsgJson<String, Object> addStuSend(MsgJson<String, Object> msgFromController) {
		// TODO Auto-generated method stub
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取
		int jobId = Integer.parseInt((String) msgFromController.getRequestData("jobId"));
		int stuId = (Integer) msgFromController.getSessionData("stuId");
		StuSend stuSend = new StuSend(stuId,jobId,MyTimestramp.setTime(new Timestamp(System.currentTimeMillis())),1);
		StuSend oldStuSend = stuSendDao.getStuSendById(stuId, jobId);
		Info info = infoDao.getInfo(stuId);
		if(info==null){
			msgToController.setState(false);
			msgToController.setMsg("没有设置个人信息不能投简哦");
			return msgToController;
		}
		if(oldStuSend!=null){
			msgToController.setState(false);
			msgToController.setMsg("不能重复投简");
			return msgToController;
		}
		// 操作
		if (stuSendDao.insertStuSend(stuSend) != 1) {
			msgToController.setState(false);
			msgToController.setMsg("未知原因投简失败");
			return msgToController;
		}
		return msgToController;
	}

	// 删除 投简信息
	@Override
	public MsgJson<String, Object> dropStuSend(MsgJson<String, Object> msgFromController) {
		// TODO Auto-generated method stub
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取
		int stuId = (Integer) msgFromController.getSessionData("stuId");
		int jobId = Integer.parseInt((String) msgFromController.getRequestData("jobId"));
		// 操作
		if (stuSendDao.deleteStuSend(stuId, jobId) != 1) {
			msgToController.setState(false);
			msgToController.setMsg("未知原因删除投简失败");
			return msgToController;
		}
		return msgToController;
	}

	// 获取 未处理的已投简单位
	@Override
	public MsgJson<String, Object> getNewStuSendJob(MsgJson<String, Object> msgFronController) {
		// TODO Auto-generated method stub
		// 获取
		int stuId = (Integer) msgFronController.getSessionData("stuId");
		// 返回
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();

		List<StuSend> stuSends = stuSendDao.getStuSendByStuId(stuId);
		List<Job> jobs = new ArrayList<Job>();
		for (int i = 0; i < stuSends.size(); i++) {
			// 添加单位信息
			if (stuSends.get(i).getState() == 1) {
				Job job = jobDao.getJob(stuSends.get(i).getJobId());
				jobs.add(job);
			}
		}
		if (jobs.size() == 0) {
			msgToController.setMsg("你没有未被读的投简信息");
			msgToController.setState(false);
		} else {
			msgToController.setJsonData("jobs", jobs);
		}
		return msgToController;
	}

	// 获取 已处理的已投简单位
	@Override
	public MsgJson<String, Object> getOldStuSendJob(MsgJson<String, Object> msgFronController) {
		// TODO Auto-generated method stub
		// 获取
		int stuId = (Integer) msgFronController.getSessionData("stuId");
		// 返回
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();

		List<StuSend> stuSends = stuSendDao.getStuSendByStuId(stuId);
		List<Job> jobs = new ArrayList<Job>();
		for (int i = 0; i < stuSends.size(); i++) {
			// 添加单位信息
			if (stuSends.get(i).getState() == 2) {
				Job job = jobDao.getJob(stuSends.get(i).getJobId());
				jobs.add(job);
			}
		}
		if (jobs.size() == 0) {
			msgToController.setMsg("你没有已被读的投简信息");
			msgToController.setState(false);
		} else {
			msgToController.setJsonData("jobs", jobs);
		}
		return msgToController;
	}

	// 获取 被感兴趣的已投简单位
	@Override
	public MsgJson<String, Object> getInterestStuSendJob(MsgJson<String, Object> msgFronController) {
		// TODO Auto-generated method stub
		// 获取
		int stuId = (Integer) msgFronController.getSessionData("stuId");
		// 返回
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();

		List<StuSend> stuSends = stuSendDao.getStuSendByStuId(stuId);
		List<Job> jobs = new ArrayList<Job>();
		for (int i = 0; i < stuSends.size(); i++) {
			// 添加单位信息
			if (stuSends.get(i).getState() == 3) {
				Job job = jobDao.getJob(stuSends.get(i).getJobId());
				jobs.add(job);
			}
		}
		if (jobs.size() == 0) {
			msgToController.setMsg("你没有已被感兴趣的投简信息");
			msgToController.setState(false);
		} else {
			msgToController.setJsonData("jobs", jobs);
		}
		return msgToController;
	}

}
