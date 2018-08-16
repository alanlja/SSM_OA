package com.lja.oa.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpSession;

import com.lja.oa.dao.LeaveBillMapper;
import com.lja.oa.pojo.LeaveBill;
import com.lja.oa.pojo.User;
import com.lja.oa.service.WorkFlowService;
import common.WorkflowBean;
import org.activiti.engine.*;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service("workFlowService")
public class WorkFlowServiceImpl implements WorkFlowService {
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private LeaveBillMapper leaveBillMapper;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private HistoryService historyService;
	

	@Override
	public List<Deployment> getDeployMentList() {
		return repositoryService.createDeploymentQuery().list();
	}

	@Override
	public List<ProcessDefinition> getProcessDefinitionList() {
		return repositoryService.createProcessDefinitionQuery()
							.orderByProcessDefinitionVersion().asc()
							.list();
	}

	@Override
	public void newDeploy(MultipartFile file, String filename) {
		InputStream in;
		try {
			in = file.getInputStream();
			ZipInputStream zipInputStream = new ZipInputStream(in);
			repositoryService.createDeployment()
								.name(filename)
								.addZipInputStream(zipInputStream)
								.deploy();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public InputStream getImageInputStream(String deploymentId, String imageName) {		
		return repositoryService.getResourceAsStream(deploymentId, imageName);
	}

	@Override
	public void delDeployment(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}

	/**
	 * 启动流程
	 * 1.更新请假单的状态
	 * 2.动态设置任务的办理人
	 * 3.让业务绑定流程
	 */
	@Override
	public void startProcess(WorkflowBean bean, HttpSession session) {
		User user = (User) session.getAttribute("user");
		String userChName = user.getUserChName();
		//1.更新请假单的状态
		//请假单id
		Long id = bean.getId();
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("state", 1);
		leaveBillMapper.updateLeaveBillState(map);
		
		//设置任务办理人
		Map<String, Object> variables = new HashMap<>();
		variables.put("inputUser", userChName);
		
		//businessKey  LeaveBill.2
		LeaveBill leaveBill = leaveBillMapper.queryLeaveBillById(id);
		String key = leaveBill.getClass().getSimpleName();
		String businessKey = key+"."+id;
		
		//启动流程	
		runtimeService.startProcessInstanceByKey(key, businessKey, variables);
	}

	@Override
	public List<Task> getTaskListByUserChName(String userChName) {
		return taskService.createTaskQuery().taskAssignee(userChName).list();
	}

	@Override
	public String getUrlByTaskId(String taskId) {
		TaskFormData taskFormData = formService.getTaskFormData(taskId);
		String url = taskFormData.getFormKey();
		return url;
	}

	@Override
	public LeaveBill getLeaveBillByTaskId(String taskId) {
		//得到任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//得到流程实例id
		String processInstanceId = task.getProcessInstanceId();
		//得到流程实例对象
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		//得到businessKey
		String businessKey = processInstance.getBusinessKey();
		String id = "";
		if(StringUtils.isNotBlank(businessKey)){
			id = businessKey.split("\\.")[1];
		}
		LeaveBill leaveBill = leaveBillMapper.queryLeaveBillById(Long.parseLong(id));
		return leaveBill;
	}

	@Override
	public List<String> getOutComeList(String taskId) {
		List<String> list = new ArrayList<>();
		//得到任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//得到流程定义id
		String processDefinitionId = task.getProcessDefinitionId();
		//得到流程定义实体对象-->xml文件
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
		
		//得到流程实例id
		String processInstanceId = task.getProcessInstanceId();
		//流程实例对象 
		 ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		 //得到活动id
		 String activityId = processInstance.getActivityId();
		 //活动对象
		 ActivityImpl activity = pde.findActivity(activityId);
		 List<PvmTransition> outgoingTransitions = activity.getOutgoingTransitions();
		 for (PvmTransition pvm : outgoingTransitions) {
			String name = (String) pvm.getProperty("name");
			if(StringUtils.isNotBlank(name)){
				list.add(name);
			}else{
				list.add("提交");
			}
		}
		return list;
	}

	@Override
	public List<Comment> getCommentByTaskId(String taskId) {
		//得到任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//流程实例id
		String processInstanceId = task.getProcessInstanceId();
		List<Comment> commnetList = taskService.getProcessInstanceComments(processInstanceId);
		return commnetList;
	}

	@Override
	public void completeTask(WorkflowBean bean,HttpSession session) {
		User user = (User) session.getAttribute("user");
		String userChName = user.getUserChName();
		//得到任务id
		String taskId = bean.getTaskId();
		//得到连线的名称 
		String outcome = bean.getOutcome();
		//请假单id
		Long id = bean.getId();
		
		//得到任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//得到流程实例id
		String processInstanceId = task.getProcessInstanceId();
		//得到批注信息
		String comment = bean.getComment();
		
		//设置批注的创建人
		Authentication.setAuthenticatedUserId(userChName);
		
		//提交批注
		taskService.addComment(taskId, processInstanceId, comment);
		//设置连线
		Map<String, Object> variables = new HashMap<>();
		variables.put("outcome", outcome);
		
		taskService.complete(taskId, variables);
		
		
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(processInstance==null){
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("state", 2);
			//设置请假单状态
			leaveBillMapper.updateLeaveBillState(map);
		}
	}

	@Override
	public List<Comment> getHisCommentById(Long id) {
		//得到请假单的对象
		LeaveBill leaveBill = leaveBillMapper.queryLeaveBillById(id);
		String key = leaveBill.getClass().getSimpleName();
		String bussinessKey = key+"."+id;
		
		HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(bussinessKey).singleResult();
		//得到流程实例id
		String processInstanceId = processInstance.getId();
		
		List<Comment> commnetList = taskService.getProcessInstanceComments(processInstanceId);	
		return commnetList;
	}

	@Override
	public ProcessDefinition getProcessDefinitionByTaskId(String taskId) {
		//得到任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//得到流程定义id
		String processDefinitionId = task.getProcessDefinitionId();
		//得到流程定义对象
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		return pd;
	}

	@Override
	public Map<String, Object> getZuoBiaoByTaskId(String taskId) {
		//得到任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//得到流程定义id
		String processDefinitionId = task.getProcessDefinitionId();
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
		
		String processInstanceId = task.getProcessInstanceId();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String activityId = processInstance.getActivityId();
		ActivityImpl activity = pde.findActivity(activityId);
		Map<String, Object> map = new HashMap<>();
		map.put("x", activity.getX());
		map.put("y", activity.getY());
		map.put("width", activity.getWidth());
		map.put("height", activity.getHeight());
		return map;
	}

}
