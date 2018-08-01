package com.lja.oa.controller;

import com.lja.oa.pojo.User;
import com.lja.oa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "common/login";
    }

    /**
     * 检验用户名和密码
     */
    @RequestMapping("/loginCheck")
    public ModelAndView checkLogin(User user, HttpServletRequest request){
        ModelAndView result = null;
        User loginUser = userService.checkUserIsExits(user);
        if(loginUser!=null){
            request.getSession().setAttribute("user", loginUser);
            result = new ModelAndView("common/main");
            result.addObject("user", loginUser);
        }else{
            result = new ModelAndView("common/login");
        }
        return result;
    }

    /**
     * 跳转的效果
     */
    @RequestMapping("/frame")
    public String toFrame(HttpServletRequest request){
        String result = null;
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            result = "common/frame";
        }else{
            result = "common/login";
        }
        return result;
    }
}
