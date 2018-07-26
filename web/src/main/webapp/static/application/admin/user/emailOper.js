function EmailOper() {

    this.sendEmail = function (email) {
        var htmlStr = emailOper.initHtml();
        $.layer({
            type : 1,
            title : "邮件发送",
            area : [ 'auto', 'auto' ],
            page : {
                html : htmlStr
            }
        });
        $("#sendAddress").val(email);
    }

    this.send = function () {
        var sendAddress = $("#sendAddress").val();

        var title = $("#title").val();
        if (!title){
            alert("邮件标题不能为空!!")
            return null;
        }

        var content = $("#content").val();
        if(!content){
            alert("邮件内容不能为空!!");
            return null;
        }
        var param = new Object();
        param.sendAddress = sendAddress;
        param.title = title;
        param.content = content;

        $.ajax({
            url:"/user/sendEmail",
            async:true,
            type:"POST",
            data:param,
            success:function(data){
                var obj = jQuery.parseJSON(data);
                if(obj.isSuccess==true){
                    var alerts = layer.alert("发送成功!",1,function(){
                        layer.close(alerts);
                        location.reload();
                    });
                }else{
                    var alerts = layer.alert("发送失败!",5,function(){
                        layer.close(alerts);
                    })
                }
            }
        });

    }

    this.initHtml = function() {

        var htmlStr = "";
        htmlStr += '<div style="width:600px;" >';
        htmlStr += '<div style="display:block; padding-bottom:20px;" align="center"  >';

        htmlStr += '<table border="0" >';

        htmlStr += '<tr>';
        htmlStr += '<td colspan="2">';
        htmlStr += '<span>收件人:</span>';
        htmlStr += '<input type="text"  id="sendAddress" readonly="readonly" style="width:480px;">';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr>';
        htmlStr += '<td colspan="2">';
        htmlStr += '<span>标题:</span>';
        htmlStr += '<input type="text"  id="title"  style="width:480px;">';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr>';
        htmlStr += '<td colspan="2">';
        htmlStr += '<span>内容:</span>';
        htmlStr += '<textarea type="text" rows="4" id="content" style="width:480px;"></textarea>';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr><td colspan="2" align="center">';
        htmlStr += '<input  name="" type="button"  onclick="javascript:emailOper.send();" value="发送"  />';
        htmlStr += '</td></tr>';
        htmlStr += '</table>';
        htmlStr += '</div>';
        htmlStr += '</div>';

        return htmlStr;
    }
}