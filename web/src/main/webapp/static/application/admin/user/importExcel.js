function importUserExcel() {
    var htmlStr = "";
    htmlStr += '<div style="width:600px;" >';
    htmlStr += '<div style="display:block; padding-bottom:20px;" align="center" >';
    htmlStr += '<form  id = "userExcelFileFormId" action="/uploadExcel" method="post" >';
    htmlStr += '<input type="file" id="userExcelFile" name="userExcelFile"  onchange="ValidateFileType()" >';
    htmlStr += ' <input type="submit"  value="导入"  />  ';
    htmlStr += '</form>';
    htmlStr += '</table>';
    htmlStr += '</div>';
    htmlStr += '</div>';

    $.layer({
        type : 1,
        title : '导入用户',
        area : [ 'auto', 'auto' ],
        page : {
            html : htmlStr
        }
    });

    submitForm();
}

function ValidateFileType() {
    var array = new Array();
    //xx.xls xlsx
    var fileName = $("#userExcelFile").val();
    array = fileName.split(".");
    var suffix = array[array.length-1];
    if(suffix!="xls"&&suffix!="xlsx"){
        alert("您选择的不是excel文档，请重新选择!!");
        $("#userExcelFile").val('');
    }
}

function submitForm(){

    var options = {
        beforeSubmit:showRequest,//表单提交前调用的方法
        success:showResponse,//表单提交成功后调用的方法
        resetForm:false,
        dataType:"json"

    }

    $("#userExcelFileFormId").submit(function(){
        $(this).ajaxSubmit(options);
        //阻止表单自动 提交
        return false;
    });
}

function showRequest(){

}

function showResponse(response){
    if(response.isSuccess == true){
        alert("导入excel文档成功!");
        window.location = "/user/userMana";//刷新
    }
}