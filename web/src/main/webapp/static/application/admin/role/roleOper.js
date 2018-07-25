function RoleOper() {
    this.alertaddRole = function () {
        // 初始化弹出框html代码
        var htmlStr = roleOper.initHtml();
        $.layer({
            type : 1,
            title : false,
            area : ['auto','auto'],
            page : {
                html : htmlStr
            }
        });
    }

    this.addRole = function (flag) {
        var roleId = $("#roleId").val();
        var param = new Object();
        param.roleId = roleId;
        param.roleName = $("#roleName").val();
        param.roleDesc = $("#roleDesc").val();
        param.state = $("#state").val();

        var url = "/role/add";
        var desc = "新增";
        if (roleId) {
            var url = "/role/update";
            var desc = "编辑";
        } else {
            var url = "/role/add";
            var desc = "新增";
        }

        $.ajax({
            url : url,
            async : true,
            type : 'post',
            dataType : 'text',
            data : param,
            success : function(msg) {
                var obj = jQuery.parseJSON(msg);
                if (true == obj.isSucess) {
                    var alerts = layer.alert(desc + "角色成功", 1, function() {
                        layer.close(alerts);
                        location.reload();// 刷新页面
                    });
                } else {
                    var alerts = layer.alert(desc + "角色失败", 5, function() {
                        layer.close(alerts);
                    });
                }
            }
        });

    }

    // 编辑角色信息
    this.editRole = function(roleId) {
        var htmlStr = roleOper.initHtml();
        $.layer({
            type : 1,
            title : "编辑角色",
            area : [ 'auto', 'auto' ],
            page : {
                html : htmlStr
            }
        });
        var param = new Object();
        param.roleId = roleId;
        // 发送异步请求
        $.getJSON("/role/queryRole", param, function(data) {
            $("#roleId").val(data.role.roleId);
            $("#roleName").val(data.role.roleName);
            $("#state").val(data.role.state);
            $("#roleDesc").val(data.role.roleDesc);
        });
    }

    // 显示角色信息
    this.showRole = function(roleId) {
        var htmlStr = roleOper.initHtml();
        $.layer({
            type : 1,
            title : "显示角色",
            area : [ 'auto', 'auto' ],
            page : {
                html : htmlStr
            }
        });
        var param = new Object();
        param.roleId = roleId;
        // 发送异步请求
        $.getJSON("/role/queryRole", param, function(data) {
            $("#roleId").val(data.role.roleId);
            $("#roleName").val(data.role.roleName);
            $("#state").val(data.role.state);
            $("#roleDesc").val(data.role.roleDesc);

            // 隐藏保存按钮
            $("#saveButton").hide();
        });
    }

    // 删除角色信息
    this.deleteRole = function(roleId) {
        var param = new Object();
        param.roleId = roleId;
        // 发送异步请求
        $.ajax({
            url : "/role/delete",
            async : true,
            type : 'post',
            dataType : 'text',
            data : param,
            success : function(msg) {
                var obj = jQuery.parseJSON(msg);
                if (true == obj.isSucess) {
                    var alerts = layer.alert("删除角色成功", 1, function() {
                        layer.close(alerts);
                        location.reload();// 刷新页面
                    });
                } else {
                    var alerts = layer.alert("删除角色失败", 5, function() {
                        layer.close(alerts);
                    });
                }
            }
        });
    }

    this.initHtml = function() {
        var htmlStr = '';
        htmlStr += '<div style="width:600px;" >';
        htmlStr += '<div style="display:block; padding-bottom:20px;" align="center"  >';
        htmlStr += '<table border="0" >';
        htmlStr += '<tr style="display:none">';
        htmlStr += '<td colspan="2">';
        htmlStr += '<span>角色ID:</span>';
        htmlStr += '<input type="text"  id="roleId" style="width:220px;">';
        htmlStr += '</td>';
        htmlStr += '</tr>';
        htmlStr += '<tr>';
        htmlStr += '<td>';
        htmlStr += '<span>角色名称:</span>';
        htmlStr += '<input type="text"  id="roleName" style="width:220px;">';
        htmlStr += '</td>';
        htmlStr += '<td>';
        htmlStr += '<span>角色状态:</span>';
        htmlStr += '<select id="state" style="width:220px;">';
        htmlStr += '<option value="">请选择</option>';
        htmlStr += '<option value="1">可用</option>';
        htmlStr += '<option value="2">不可用</option>';
        htmlStr += '</select>';
        htmlStr += '</td>';
        htmlStr += '</tr>';
        htmlStr += '<tr>';
        htmlStr += '<td colspan="2" align="center" >';
        htmlStr += '<span>角色描述:</span>';
        htmlStr += '<textarea rows="3"  id="roleDesc" style="width:500px;"></textarea>';
        htmlStr += '</td>';
        htmlStr += '</tr>';
        htmlStr += '<tr id="saveRoleTr"><td colspan="2" align="center">';
        htmlStr += '<input id="saveButton"  type="button"  onclick="javascript:roleOper.addRole(\'add\');" value="保存"  />';
        htmlStr += '</td></tr>';
        htmlStr += '</table>';
        htmlStr += '</div>';
        htmlStr += '</div>';

        return htmlStr;
    }
}