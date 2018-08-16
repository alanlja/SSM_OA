package com.lja.oa.controller;

import com.lja.oa.dao.LeaveBillMapper;
import com.lja.oa.pojo.LeaveBill;
import com.lja.oa.pojo.User;
import com.lja.oa.service.WorkFlowService;
import common.WorkflowBean;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workflow")
public class WorkFlowController {
    @Autowired
    private WorkFlowService workFlowService;

    @Autowired
    private LeaveBillMapper leaveBillMapper;

    @RequestMapping("/toDeployHome")
    public ModelAndView toDeployHome(){
        ModelAndView result = new ModelAndView("views/workflow/workflowPage");
        // 1.布署信息
        List<Deployment> depList = workFlowService.getDeployMentList();
        // 2.流程定义信息
        List<ProcessDefinition> pdList = workFlowService.getProcessDefinitionList();
        result.addObject("depList", depList);
        result.addObject("pdList", pdList);
        return result;
    }

    /**
     * 布署流程
     */
    @RequestMapping("/newdeploy")
    public String newdeploy(WorkflowBean bean) {
        // 上传的文件
        MultipartFile file = bean.getFile();
        // 布署流程的名称
        String filename = bean.getFilename();
        workFlowService.newDeploy(file, filename);
        return "redirect:/workflow/todeployHome";
    }

    /**
     * 查看流程图
     * @throws IOException
     */
    @RequestMapping("/proccessImage")
    public void proccessImage(WorkflowBean bean,HttpServletResponse response) throws IOException {
        //得到部署id
        String deploymentId = bean.getDeploymentId();
        //图片名称
        String imageName = bean.getImageName();
        InputStream inputStream = workFlowService.getImageInputStream(deploymentId,imageName);
        OutputStream outputStream = response.getOutputStream();
        for(int b=-1;((b=inputStream.read())!=-1);){
            outputStream.write(b);
        }
        outputStream.close();
        inputStream.close();
    }

    /**
     * 删除布署流程
     */
    @RequestMapping("/delDeployment")
    public String delDeployment(WorkflowBean bean){
        //得到布署 id
        String deploymentId = bean.getDeploymentId();
        workFlowService.delDeployment(deploymentId);
        return "redirect:/workflow/todeployHome";
    }

    /**
     * 启动流程
     * 1.更新请假单的状态
     * 2.动态设置任务的办理人
     * 3.让业务绑定流程
     */
    @RequestMapping("/startProcess")
    public String startProcess(WorkflowBean bean,HttpSession session){
        workFlowService.startProcess(bean,session);
        return "redirect:/workflow/listTask";
    }

    /**
     * 查询当前用户正在执行的任务列表
     * @return
     */
    @RequestMapping("/listTask")
    public ModelAndView listTask(HttpSession session){
        ModelAndView result = new ModelAndView("views/workflow/task");
        User user = (User) session.getAttribute("user");
        String userChName = user.getUserChName();
        List<Task> taskList = workFlowService.getTaskListByUserChName(userChName);
        result.addObject("taskList", taskList);
        return result;
    }

    /**
     * 跳转到办理任务的url
     */
    @RequestMapping("/viewTaskForm")
    public String viewTaskForm(WorkflowBean bean){
        //任务id
        String taskId = bean.getTaskId();
        String url = workFlowService.getUrlByTaskId(taskId);
        url = url+"?taskId="+taskId;
        return "redirect:"+url;///workflow/audit?taskId=1
    }

    /**
     * 办理任务
     */

    @RequestMapping("/audit")
    public ModelAndView auditTask(WorkflowBean bean){
        ModelAndView result = new ModelAndView("views/workflow/taskForm");
        //得到任务id
        String taskId = bean.getTaskId();
        //得到请假单的对象，做数据回显操作
        LeaveBill leaveBill = workFlowService.getLeaveBillByTaskId(taskId);
        result.addObject("leaveBill", leaveBill);
        result.addObject("taskId", taskId);

        //查询连线
        List<String> outComeList = workFlowService.getOutComeList(taskId);
        result.addObject("outComeList", outComeList);

        //查询批注信息
        List<Comment> commentList = workFlowService.getCommentByTaskId(taskId);
        result.addObject("commentList", commentList);
        return result;
    }

    /**
     * 完成任务
     */
    @RequestMapping("/submitTask")
    public String completeTask(WorkflowBean bean,HttpSession session){
        workFlowService.completeTask(bean,session);
        return "redirect:/workflow/listTask";
    }

    /**
     * 查看审核记录
     */
    @RequestMapping("/showHisComment")
    public ModelAndView showHisComment(WorkflowBean bean){
        ModelAndView result = new ModelAndView("views/workflow/taskFormHis");
        //得到请假单对象
        Long id = bean.getId();
        LeaveBill leaveBill = leaveBillMapper.queryLeaveBillById(id);
        result.addObject("lb", leaveBill);

        //查询历史的批注信息
        List<Comment> commentList = workFlowService.getHisCommentById(id);
        result.addObject("commentList", commentList);
        return result;

    }

    /***
     * 查看当前的流程图
     */
    @RequestMapping("/viewCurrentProcessImage")
    public ModelAndView viewCurrentProcessImage(WorkflowBean bean){
        ModelAndView result = new ModelAndView("views/workflow/image");
        //得到任务id
        String taskId = bean.getTaskId();
        //通过任务id得到流程定义对象
        ProcessDefinition pd = workFlowService.getProcessDefinitionByTaskId(taskId);
        result.addObject("deploymentId", pd.getDeploymentId());
        result.addObject("imageName", pd.getDiagramResourceName());
        //查询任务节点的坐标
        Map<String, Object> zuobiaoMap = workFlowService.getZuoBiaoByTaskId(taskId);
        result.addObject("acs", zuobiaoMap);
        return result;
    }
}
