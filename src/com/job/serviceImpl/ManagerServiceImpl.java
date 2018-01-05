package com.job.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.dao.InfoDao;
import com.job.dao.JobDao;
import com.job.dao.ManJobDao;
import com.job.dao.ManStuDao;
import com.job.dao.StuSendDao;
import com.job.entity.Info;
import com.job.entity.Job;
import com.job.entity.ManJob;
import com.job.entity.StuSend;
import com.job.json.JobJson;
import com.job.json.ListJson;
import com.job.json.MsgJson;
import com.job.json.NameLinkJson;
import com.job.service.ManagerService;
import com.job.tools.MyMsgJson;
import com.job.tools.MyTimestramp;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private JobDao jobDao;
	@Autowired
	private ManJobDao manJobDao;
	@Autowired
	private ManStuDao manStuDao;
	@Autowired
	private StuSendDao stuSendDao;
	@Autowired
	private InfoDao infoDao;

	// 获取 已发布单位
	@Override
	public MsgJson<String, Object> getJobs(MsgJson<String, Object> msgFromController) {
		// TODO Auto-generated method stub
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取
		int manId = (Integer) msgFromController.getSessionData("manId");
		List<ManJob> manJobs = manJobDao.getManJobByManId(manId);
		List<Job> jobs = new ArrayList<Job>();
		for (int i = 0; i < manJobs.size(); i++) {
			Job job = jobDao.getJob(manJobs.get(i).getJobId());
			jobs.add(job);
		}
		if (jobs.size() == 0) {
			msgToController.setMsg("你还没有发布的单位");
			msgToController.setState(false);
		} else {
			msgToController.setJsonData("jobs", jobs);
		}
		return msgToController;
	}

	// 添加 单位信息
	@Override
	public boolean addJob(MsgJson<String, Object> msgFromController) {
		// 获得发布job的表单
		Job job = (Job) msgFromController.getRequestData("job");
		int manId = (Integer) msgFromController.getSessionData("manId");
		// 操作
		if (jobDao.insertJob(job) == 1) {
			manJobDao.insertManJob(new ManJob(manId, job.getJobId(), "添加",
					MyTimestramp.setTime(new Timestamp(System.currentTimeMillis()))));
			return true;
		}
		return false;
	}

	// 更新 单位信息
	@Override
	public boolean alterJob(MsgJson<String, Object> msgFromController) {
		// 获得发布job的表单
		Job job = (Job) msgFromController.getRequestData("job");
		// 操作
		if (jobDao.updateJob(job) == 1) {
			return true;
		}
		return false;
	}

	// 删除 单位信息
	@Override
	public boolean dropJob(MsgJson<String, Object> msgFromController) {
		int jobId = Integer.parseInt((String) msgFromController.getRequestData("jobId"));
		int manId = (Integer) msgFromController.getSessionData("manId");
		// 操作
		if (jobDao.deleteJob(jobId) == 1) {
			manJobDao.deleteManJob(manId, jobId);
			return true;
		}
		return false;
	}

	// 获取 最新已投简学生信息
	@Override
	public MsgJson<String, Object> getNewStuSend(MsgJson<String, Object> msgFromController) {
		// 获取
		int manId = (Integer) msgFromController.getSessionData("manId");
		// 返回
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获得含有jobid的列表
		List<ManJob> manJobs = manJobDao.getManJobByManId(manId);
		List<StuSend> stuSends = new ArrayList<StuSend>();

		// 储存每个单位的投简信息 的列表
		List<ListJson<Info>> listJsons = new ArrayList<ListJson<Info>>();
		// 每个单位的信息
		NameLinkJson nameLinkJson = null;
		// 每个单位的学生列表
		List<Info> infos = new ArrayList<Info>();
		// 每个单位的学生列表
		ListJson<Info> listJson = null;
		for (int i = 0; i < manJobs.size(); i++) {
			// 获取 学生投简列表
			int jobId = manJobs.get(i).getJobId();
			stuSends = stuSendDao.getStuSendByJobId(jobId);
			for (int j = 0; j < stuSends.size(); j++) {
				// 添加当前job的学生Info
				if (stuSends.get(j).getState() == 1) {
					Info info = infoDao.getInfo(stuSends.get(j).getStuId());
					infos.add(info);
				}
			}
			if (infos.size() == 0)
				continue;
			else {
				nameLinkJson = new NameLinkJson();
				// 添加 链接 和单位名称
				nameLinkJson.setLink(Integer.toString(jobId));
				nameLinkJson.setName(jobDao.getJob(jobId).getName());
			}
			// 重置
			listJson = new ListJson<Info>();
			// 收集 单位信息
			listJson.setParent(nameLinkJson);
			// 收集 学生信息
			listJson.setChildList(infos);
			// 添加至结果
			listJsons.add(listJson);
			// 重置学生信息列表
			infos = new ArrayList<Info>();
		}
		if (listJsons.size() == 0) {
			msgToController.setMsg("还没有新的学生投简已发布的单位");
			msgToController.setState(false);
		} else {
			msgToController.setJsonData("listJsons", listJsons);
		}
		return msgToController;
	}

	// 获取 历史已投简学生信息
	@Override
	public MsgJson<String, Object> getOldStuSend(MsgJson<String, Object> msgFronController) {
		// 获取
		int manId = (Integer) msgFronController.getSessionData("manId");
		// 返回
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获得含有jobid的列表
		List<ManJob> manJobs = manJobDao.getManJobByManId(manId);
		List<StuSend> stuSends = new ArrayList<StuSend>();

		// 储存每个单位的投简信息 的列表
		List<ListJson<Info>> listJsons = new ArrayList<ListJson<Info>>();
		// 每个单位的信息
		NameLinkJson nameLinkJson = null;
		// 每个单位的学生列表
		List<Info> infos = new ArrayList<Info>();
		// 每个单位的学生列表
		ListJson<Info> listJson = null;
		for (int i = 0; i < manJobs.size(); i++) {
			// 获取 学生投简列表
			int jobId = manJobs.get(i).getJobId();
			stuSends = stuSendDao.getStuSendByJobId(jobId);
			for (int j = 0; j < stuSends.size(); j++) {
				// 添加当前job的学生Info
				if (stuSends.get(j).getState() == 2) {
					Info info = infoDao.getInfo(stuSends.get(j).getStuId());
					infos.add(info);
				}
			}
			if (infos.size() == 0)
				continue;
			else {
				nameLinkJson = new NameLinkJson();
				// 添加 链接 和单位名称
				nameLinkJson.setLink(Integer.toString(jobId));
				nameLinkJson.setName(jobDao.getJob(jobId).getName());
			}
			// 重置
			listJson = new ListJson<Info>();
			// 收集 单位信息
			listJson.setParent(nameLinkJson);
			// 收集 学生信息
			listJson.setChildList(infos);
			// 添加至结果
			listJsons.add(listJson);
			// 重置学生信息列表
			infos = new ArrayList<Info>();
		}
		if (listJsons.size() == 0) {
			msgToController.setMsg("你没有添加历史的学生投简");
			msgToController.setState(false);
		} else {
			msgToController.setJsonData("listJsons", listJsons);
		}
		return msgToController;
	}

	// 设置 已投简学生信息
	@Override
	public MsgJson<String, Object> alterNewStuSend(MsgJson<String, Object> msgFronController) {
		// TODO Auto-generated method stub
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取
		int jobId = Integer.parseInt((String) msgFronController.getRequestData("jobId"));
		int stuId = Integer.parseInt((String) msgFronController.getRequestData("stuId"));
		int state = Integer.parseInt((String) msgFronController.getRequestData("state"));
		// 设置
		StuSend stuSend = stuSendDao.getStuSendById(stuId, jobId);
		stuSend.setState(state);
		// 更新
		if (stuSendDao.updateStuSend(stuSend) != 1) {
			msgToController.setMsg("操作失败");
			msgToController.setState(false);
		}
		return msgToController;
	}

	// 获取 感兴趣已投简学生信息
	@Override
	public MsgJson<String, Object> getInterestStuSend(MsgJson<String, Object> msgFronController) {
		// 获取
		int manId = (Integer) msgFronController.getSessionData("manId");
		// 返回
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获得含有jobid的列表
		List<ManJob> manJobs = manJobDao.getManJobByManId(manId);
		List<StuSend> stuSends = new ArrayList<StuSend>();

		// 储存每个单位的投简信息 的列表
		List<ListJson<Info>> listJsons = new ArrayList<ListJson<Info>>();
		// 每个单位的信息
		NameLinkJson nameLinkJson = null;
		// 每个单位的学生列表
		List<Info> infos = new ArrayList<Info>();
		// 每个单位的学生列表
		ListJson<Info> listJson = null;
		for (int i = 0; i < manJobs.size(); i++) {
			// 获取 学生投简列表
			int jobId = manJobs.get(i).getJobId();
			stuSends = stuSendDao.getStuSendByJobId(jobId);
			for (int j = 0; j < stuSends.size(); j++) {
				// 添加当前job的学生Info
				if (stuSends.get(j).getState() == 3) {
					Info info = infoDao.getInfo(stuSends.get(j).getStuId());
					infos.add(info);
				}
			}
			if (infos.size() == 0)
				continue;
			else {
				nameLinkJson = new NameLinkJson();
				// 添加 链接 和单位名称
				nameLinkJson.setLink(Integer.toString(jobId));
				nameLinkJson.setName(jobDao.getJob(jobId).getName());
			}
			// 重置
			listJson = new ListJson<Info>();
			// 收集 单位信息
			listJson.setParent(nameLinkJson);
			// 收集 学生信息
			listJson.setChildList(infos);
			// 添加至结果
			listJsons.add(listJson);
			// 重置学生信息列表
			infos = new ArrayList<Info>();
		}
		if (listJsons.size() == 0) {
			msgToController.setMsg("你没有添加感兴趣的学生投简");
			msgToController.setState(false);
		} else {
			msgToController.setJsonData("listJsons", listJsons);
		}
		return msgToController;
	}

}
