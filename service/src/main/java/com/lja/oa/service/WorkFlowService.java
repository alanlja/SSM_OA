package com.lja.oa.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.lja.oa.pojo.LeaveBill;
import common.WorkflowBean;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.web.multipart.MultipartFile;

public interface WorkFlowService {

	List<Deployment> getDeployMentList();

	List<ProcessDefinition> getProcessDefinitionList();

	void newDeploy(MultipartFile file, String filename);

	InputStream getImageInputStream(String deploymentId, String imageName);

	void delDeployment(String deploymentId);

	void startProcess(WorkflowBean bean, HttpSession session);

	List<Task> getTaskListByUserChName(String userChName);

	String getUrlByTaskId(String taskId);

	LeaveBill getLeaveBillByTaskId(String taskId);

	List<String> getOutComeList(String taskId);

	List<Comment> getCommentByTaskId(String taskId);

	void completeTask(WorkflowBean bean, HttpSession session);

	List<Comment> getHisCommentById(Long id);

	ProcessDefinition getProcessDefinitionByTaskId(String taskId);

	Map<String, Object> getZuoBiaoByTaskId(String taskId);

}
