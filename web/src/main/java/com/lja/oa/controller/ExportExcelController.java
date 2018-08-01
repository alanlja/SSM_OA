package com.lja.oa.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lja.oa.service.IUserService;
import common.controller.BaseController;
import common.excel.ExcelUtil;
import common.excel.SimpleExportParameter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ExportExcelController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/exportUserExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 最终文件名称
        String fileName = "";

        Map<String, Object> paramMap = this.getParam(request);
        // 员工信息表.xls
        Object fileName_temp = paramMap.get("fileName");

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String defaultDate = sdf.format(date);

        if (fileName_temp != null && !("").equals(fileName_temp)) {
            String fileChName = fileName_temp.toString();
            if (fileChName.endsWith("xls") || fileChName.endsWith("xlsx")) {
                fileChName = fileChName.substring(0, fileChName.lastIndexOf("."));
            }
            fileName = fileChName + defaultDate;
        } else {
            fileName = defaultDate;
        }
        // 统一加后缀 .xls
        fileName = fileName + ".xls";

        OutputStream outputStream = null;
        try {
            // 得到一个输出流
            outputStream = response.getOutputStream();

            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO-8859-1"));

            // 创建一个excel文档对象
            Workbook wb = new HSSFWorkbook();

            // 给excel文档对象填充数据
            this.fillExcel(wb);

            // 以输出流把文档输出
            wb.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * 填充excel文档数据
     * @param wb
     */
    private void fillExcel(Workbook wb) {
        SimpleExportParameter exportParameter = getSimpleExportParameter();
        ExcelUtil util = new ExcelUtil();
        Sheet sheet = wb.createSheet();
        util.simpleExport(wb, sheet, exportParameter);
    }

    private SimpleExportParameter getSimpleExportParameter() {
        SimpleExportParameter parameter = new SimpleExportParameter();
        //数据库查询的字段名称 要与这里定义的名称一致
        String fieldsId = "userChName,userSex,mobilePhone,provinceName,cityName,countryName,userBirthday";
        String fieldsName = "姓名,性别,电话,省份,地市,区县,生日";
        String widths = "20,20,20,20,20,20,20";

        String[] ids = fieldsId.split(",");
        String[] names = fieldsName.split(",");
        String[] widthes = widths.split(",");

        String title = "..员工信息";
        String titleEn = "员工信息";

        List<Map<String, Object>> list = userService.getColumnList();

        parameter.setFieldsId(ids);
        parameter.setFieldsName(names);
        parameter.setWidths(widthes);
        parameter.setTitle(title);
        parameter.setTitleEn(titleEn);
        parameter.setDataList(list);
        return parameter;
    }
}
