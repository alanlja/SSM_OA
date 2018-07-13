package com.lja.oa.controller;

import com.google.gson.Gson;
import com.lja.oa.pojo.Area;
import com.lja.oa.service.AreaService;
import common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AreaController extends BaseController {

   @Autowired
    private AreaService areaService;

    @RequestMapping(method = RequestMethod.GET,value = "/getAreaList")
    @ResponseBody
    public void getAreaList(HttpServletRequest request, HttpServletResponse response){
        String areaParentId = request.getParameter("areaParentId");
        List<Area> list = areaService.selectByAreaParentId(areaParentId);
        Gson gson = new Gson();
        String resultJson = gson.toJson(list);
        this.flushResponse(response,resultJson);
    }
}
