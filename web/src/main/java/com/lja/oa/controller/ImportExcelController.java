package com.lja.oa.controller;

import com.google.gson.Gson;
import com.lja.oa.service.IUserService;
import common.controller.BaseController;
import common.excel.ExcelUtil;
import common.excel.SimpleReadParameter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ImportExcelController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/uploadExcel")
    public void importExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> resutlMap = new HashMap<>();
        try {
            // 把request对象转成spring提供 MultipartHttpServletRequest对象
            MultipartHttpServletRequest httpServletRequest = (MultipartHttpServletRequest) request;
            // 文件上传列表
            Map<String, MultipartFile> fileMap = httpServletRequest.getFileMap();
            // 遍历
            for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                // 得到上传的文件
                MultipartFile file = entry.getValue();
                // 得到输入流
                InputStream inputStream = file.getInputStream();
                // 创建一个excel文档对象
                Workbook wb = WorkbookFactory.create(inputStream);
                //[{orgId=1, mobilePhone=13378696977, email=1642483368@qq.com, userSex=1, userName=wuyanzu, userChName=吴彦祖}, {orgId=1, mobilePhone=13378696988, email=1642483368@qq.com, userSex=3, userName=fangbingbing, userChName=范冰冰}]
                // 读取wb中的数据
                List<Map<String, Object>> list = readExcel(wb);
                // 把读取到的数据插入到数据库
                userService.insertSheetData(list);
                resutlMap.put("isSuccess", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resutlMap.put("isSuccess", false);
        }

        Gson gson = new Gson();
        String responseContent = gson.toJson(resutlMap);
        this.flushResponse(response, responseContent);
    }

    private List<Map<String, Object>> readExcel(Workbook wb) {
        SimpleReadParameter readParameter = getSimpleReadParameter();
        String[] fieldsName = readParameter.getFieldsId();
        int startIndex = readParameter.getStartIndex();

        List<Map<String, Object>> list = new ArrayList<>();
        ExcelUtil util = new ExcelUtil();
        if (wb != null && wb.getNumberOfSheets() > 0) {
            Sheet sheet = wb.getSheetAt(0);
            List<Map<String, Object>> sheetData = util.readSimple(sheet, startIndex, fieldsName);
            if(sheetData!=null && sheetData.size()>0){
                list.addAll(sheetData);
            }
        }

        return list;
    }

    private SimpleReadParameter getSimpleReadParameter() {
        SimpleReadParameter parameter = new SimpleReadParameter();

        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append("userChName,").append("mobilePhone,").append("email,").append("userSex,").append("userName,")
                .append("orgId,");
        String[] fieldsName = sbBuffer.toString().split(",");
        parameter.setFieldsId(fieldsName);
        parameter.setStartIndex(2);
        return parameter;
    }
}
