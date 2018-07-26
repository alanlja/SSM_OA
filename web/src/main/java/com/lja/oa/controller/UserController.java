package com.lja.oa.controller;

import com.google.gson.Gson;
import com.lja.oa.pojo.User;
import com.lja.oa.pojo.Users;
import com.lja.oa.service.IUserService;
import common.controller.BaseController;
import common.email.FastestEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/userMana")
    public String toUserMana(){
        return "user/userMana";
    }

    /**
     * 查询分页数据
     */
    @RequestMapping("/userPage")
    public ModelAndView getUserPage(HttpServletRequest request){
        Map<String, Object> paramMap = this.getParam(request);
        Map<String, Object> resultMap = userService.getPage(paramMap);

        List<User> userList = (List<User>) resultMap.get("userList");
        int count = (int)resultMap.get("count");

        ModelAndView result = new ModelAndView("user/userList");
        result.addObject("userList",userList);
        result.addObject("count",count);
        return result;
    }

    /**
     * 展示页码
     * @return
     */
    @RequestMapping("/getUserPageNumber")
    public ModelAndView getUserPageNumber(HttpServletRequest request){
        ModelAndView result = new ModelAndView("user/userPageNumber");
        int total =Integer.parseInt(request.getParameter("total"));
        int startIndex =Integer.parseInt(request.getParameter("startIndex"));
        int pageSize =Integer.parseInt(request.getParameter("pageSize"));
        return this.getPageNumberInfo(total, startIndex, pageSize, result);
    }

    /**
     * 新增用户
     */
    @RequestMapping("/addUser")
    public void addUser(User user, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            String birthday_temp = request.getParameter("birthday_temp");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = sdf.parse(birthday_temp);
            user.setUserBirthday(birthday);
            userService.addUser(user);
            resultMap.put("isSuccess", true);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("isSuccess", false);
        }
        Gson gson = new Gson();
        String responseContent = gson.toJson(resultMap);
        this.flushResponse(response,responseContent);
    }

    /**
     * 通过userid查询用户对象
     */
    @RequestMapping("/queryUserById")
    public void queryUserById(int userId,HttpServletResponse response){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Users user = userService.queryUserById(userId);
        resultMap.put("user", user);
        Gson gson = new Gson();
        String responseContent = gson.toJson(resultMap);
        this.flushResponse(response, responseContent);
    }

    /**
     * 修改用户信息
     */
    @RequestMapping("/updateUser")
    public void updateUser(User user,HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            String birthday_temp = request.getParameter("birthday_temp");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = sdf.parse(birthday_temp);
            user.setUserBirthday(birthday);
            userService.updateUser(user);
            resultMap.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isSuccess", false);
        }
        Gson gson = new Gson();
        String responseContent = gson.toJson(resultMap);
        this.flushResponse(response, responseContent);
    }

    /**
     * 删除 用户信息
     */
    @RequestMapping("/delUser")
    public void delUser(long userId,HttpServletResponse response){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            userService.delUserById(userId);
            resultMap.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("isSuccess", false);
        }
        Gson gson = new Gson();
        String responseContent = gson.toJson(resultMap);
        this.flushResponse(response, responseContent);
    }

    /**
     * 查询每个省中每个性别的人数
     */
    @RequestMapping("/getUserSexStaticties")
    public void getUserSexStaticties(HttpServletResponse response){
        Map<String, Object> resutlMap = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            //[{girl=1, provinceName=北京市, secrity=0, boy=0}, {girl=0, provinceName=广东省, secrity=0, boy=2}, {girl=0, provinceName=福建省, secrity=0, boy=1}]
            list = userService.getUserSexStaticties();
            resutlMap.put("list", list);
            resutlMap.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            resutlMap.put("isSuccess", false);
        }
        Gson gson = new Gson();
        //{"list":[{"girl":1,"provinceName":"北京市","secrity":0,"boy":0},{"girl":0,"provinceName":"广东省","secrity":0,"boy":2},{"girl":0,"provinceName":"福建省","secrity":0,"boy":1}],"isSuccess":true}
        String responseContent = gson.toJson(resutlMap);
        this.flushResponse(response, responseContent);
    }

    /**
     * 查询每个省份的人数
     */
    @RequestMapping("/getProvincePersonStaticties")
    public void getProvincePersonStaticties(HttpServletResponse response){
        Map<String, Object> resutlMap = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            list = userService.getProvincePersonStaticties();
            resutlMap.put("list", list);
            resutlMap.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            resutlMap.put("isSuccess", false);
        }
        Gson gson = new Gson();
        String responseContent = gson.toJson(resutlMap);
        this.flushResponse(response, responseContent);
    }

    /**
     * 发送邮件
     */
    @RequestMapping("/sendEmail")
    public void sendEmail(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> resutlMap = new HashMap<>();
        try {
            String sendAddress = request.getParameter("sendAddress");
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            FastestEmail.sendEmail(sendAddress,title,content);
            resutlMap.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            resutlMap.put("isSuccess", false);
        }
        Gson gson = new Gson();
        String responseContent = gson.toJson(resutlMap);
        this.flushResponse(response, responseContent);
    }
}
