package com.job.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.dao.InfoDao;
import com.job.dao.JobDao;
import com.job.dao.ManDao;
import com.job.entity.Info;
import com.job.entity.Job;
import com.job.json.ListJson;
import com.job.json.MsgJson;
import com.job.json.NameLinkJson;
import com.job.service.PublicService;
import com.job.tools.MyMsgJson;

@Service("publicService")
public class PublicServiceImpl implements PublicService {

	@Autowired
	private JobDao jobDao;
	@Autowired
	private ManDao manDao;
	@Autowired
	private InfoDao infoDao;

	// 获取 单位信息
	@Override
	public MsgJson<String, Object> getJob(MsgJson<String, Object> msgFromController) {
		// TODO Auto-generated method stub
		// 返回
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取
		int jobId = Integer.parseInt((String) msgFromController.getRequestData("jobId"));
		Job job = jobDao.getJob(jobId);
		if (job == null) {
			msgToController.setMsg("获取失败");
			msgToController.setState(false);
		} else {
			msgToController.setJsonData("job", job);
		}
		return msgToController;
	}

	// 获取 学生信息
	@Override
	public MsgJson<String, Object> getStuInfo(MsgJson<String, Object> msgFromController) {
		// TODO Auto-generated method stub
		// 返回
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取
		int stuId;
		if(msgFromController.getSessionData("stuId")!=null){
			stuId = (Integer) msgFromController.getSessionData("stuId");
		}else {
			stuId = Integer.parseInt((String) msgFromController.getRequestData("stuId"));
		}
		Info info = infoDao.getInfo(stuId);
		if (info == null) {
			msgToController.setMsg("你还没有设置个人信息");
			msgToController.setState(false);
		} else {
			msgToController.setJsonData("info", info);
		}
		return msgToController;
	}

	// 获取 所有单位
	@Override
	public MsgJson<String, Object> getAllJobs() {
		// TODO Auto-generated method stub
		// 返回
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		List<Job> jobs = jobDao.getAllJobs();
		if (jobs.size() == 0) {
			msgToController.setMsg("没有信息");
			msgToController.setState(false);
		} else {
			msgToController.setJsonData("jobs", jobs);
		}
		return msgToController;
	}
}
