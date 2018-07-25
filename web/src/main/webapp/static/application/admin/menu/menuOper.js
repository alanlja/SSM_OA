function MenuOper(){
    // 弹出增强菜单的框
    this.showMenuAddLayer = function () {
        var htmlStr = menuOper.initHtml();

        $.layer({
            type : 1,
            title : false,
            area : [ 'auto', 'auto'],
            page : {
                html : htmlStr
            }
        });
    }

    this.addMenu = function () {

        var menuId = $("#menuId").val();
        var param = new Object();
        param.menuId = menuId;
        param.menuParentId = $("#menuParentId").val();
        param.menuType = $("#menuType").val();
        param.menuName = $("#menuName").val();
        param.isPublish = $("#isPublish").val();
        param.menuPath = $("#menuPath").val();
        param.descContent = $("#descContent").val();
        /**
         * 当菜单类型为目录时，数据库IS_PARENT字段值为1
         当菜单类型为菜单时，IS_PARENT字段值为2
         */
        if (param.menuType == 1){
            param.isParent = 1;
        }else {
            param.isParent = 2;
        }

        var url = "/menu/add"
        var desc = "新增";
        // 如果 menuId存在，则为编辑
        if (menuId) {
            var url = "/menu/update"
            var desc = "编辑";
        } else {
            var url = "/menu/add"
            var desc = "新增";
        }

        $.ajax({
           url : url,
            type : 'post',//提交方式
            data : param,
            success : function (msg) {
                var obj = jQuery.parseJSON(msg);
                if (true == obj.isSuccess){
                    var alerts = layer.alert(desc + "菜单成功", 1, function() {
                        layer.close(alerts);
                        location.reload();// 刷新页面
                    });
                } else {
                    var alerts = layer.alert(desc + "菜单失败", 5, function() {
                        layer.close(alerts);
                    });
                }
            }
        });
    }

    this.editMenu = function(menuId) {
        var htmlStr = menuOper.initHtml();
        $.layer({
            type : 1,
            title : "编辑菜单",
            area : [ 'auto', 'auto' ],
            page : {
                html : htmlStr
            }
        });
        var param = new Object();
        param.menuId = menuId;
        $.getJSON("/menu/queryMenu", param, function(data) {
            $("#menuId").val(data.menu.menuId);
            $("#menuName").val(data.menu.menuName);
            $("#isPublish").val(data.menu.isPublish);
            $("#descContent").val(data.menu.descContent);
            $("#menuParentId").val(data.menu.menuParentId);
            $("#menuParentName").val(data.menu.menuParentName);
            $("#menuPath").val(data.menu.menuPath);
            $("#isPublish").val(data.menu.isPublish);
            $("#menuType").val(data.menu.menuType);

        });
    }

    this.showMenu = function(menuId) {
        var htmlStr = menuOper.initHtml();
        $.layer({
            type : 1,
            title : "菜单详情",
            area : [ 'auto', 'auto' ],
            page : {
                html : htmlStr
            }
        });
        var param = new Object();
        param.menuId = menuId;
        $.getJSON("/menu/queryMenu", param, function(data) {
            $("#menuId").val(data.menu.menuId);
            $("#menuName").val(data.menu.menuName);
            $("#isPublish").val(data.menu.isPublish);
            $("#descContent").val(data.menu.descContent);
            $("#menuParentId").val(data.menu.menuParentId);
            $("#menuParentName").val(data.menu.menuParentName);
            $("#menuPath").val(data.menu.menuPath);
            $("#isPublish").val(data.menu.isPublish);
            $("#menuType").val(data.menu.menuType);

            $("#saveButton").hide();

        });
    }

    this.deleteMenu = function(menuId) {
        var param = new Object();
        param.menuId = menuId;
        $.ajax({
            url : "/menu/delete",
            type : 'POST',// 提交的方式
            data : param,
            success : function(msg) {

                var obj = jQuery.parseJSON(msg);

                if (true == obj.isSucess) {
                    var alerts = layer.alert("删除菜单成功", 1, function() {
                        layer.close(alerts);
                        location.reload();// 刷新页面
                    });
                } else {
                    var alerts = layer.alert("删除菜单失败", 5, function() {
                        layer.close(alerts);
                    });
                }

            }

        });
    }

    // 弹出框内容
    this.initHtml = function() {
        var htmlStr = '';
        htmlStr += '<div style="width:600px;" >';
        htmlStr += '<div style="display:block; padding-bottom:20px;" align="center"  >';
        htmlStr += '<table border="0" >';
        htmlStr += '<tr style="display:none">';
        htmlStr += '<td colspan="2">';
        htmlStr += '<span>菜单ID:</span>';
        htmlStr += '<input type="text"  id="menuId" style="width:220px;">';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr >';
        htmlStr += '<td>';
        htmlStr += '<span>所属目录：</span>';
        htmlStr += '<input type="text"  readonly="readonly" id="menuParentName" >';
        htmlStr += '<input type="hidden"   id="menuParentId" >';
        htmlStr += '<input type="button"   id="menuTreeId"  onclick="AddMenuTreeLayer();" value="选择">';
        htmlStr += '</td>';
        htmlStr += '<td>';
        htmlStr += '<span>菜单类型:</span>';
        htmlStr += '<select id="menuType" style="width:220px;">';
        htmlStr += '<option value="">请选择</option>';
        htmlStr += '<option value="1">目录</option>';
        htmlStr += '<option value="2">菜单</option>';
        htmlStr += '</select>';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr>';
        htmlStr += '<td>';
        htmlStr += '<span>菜单名称:</span>';
        htmlStr += '<input type="text"  id="menuName" style="width:220px;">';
        htmlStr += '</td>';
        htmlStr += '<td>';
        htmlStr += '<span>菜单状态:</span>';
        htmlStr += '<select id="isPublish" style="width:220px;">';
        htmlStr += '<option value="">请选择</option>';
        htmlStr += '<option value="1">可用</option>';
        htmlStr += '<option value="2">不可用</option>';
        htmlStr += '</select>';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr>';
        htmlStr += '<td colspan ="2">';
        htmlStr += '<span>菜单路径:</span>';
        htmlStr += '<input type="text"  id="menuPath" style="width:440px;">';
        htmlStr += '</td>';
        htmlStr += '</tr>';

        htmlStr += '<tr>';
        htmlStr += '<td colspan="2" align="center" >';
        htmlStr += '<span>菜单描述:</span>';
        htmlStr += '<textarea rows="3"  id="descContent" style="width:500px;"></textarea>';
        htmlStr += '</td>';
        htmlStr += '</tr>';
        htmlStr += '<tr id="saveMenuTr"><td colspan="2" align="center">';
        htmlStr += '<input id="saveButton"  type="button"  onclick="javascript:menuOper.addMenu(\'add\');" value="保存"  />';
        htmlStr += '</td></tr>';
        htmlStr += '</table>';
        htmlStr += '</div>';
        htmlStr += '</div>';

        return htmlStr;
    }
}