var pageSize = 3;
var totalCount;
$(document).ready(function () {
    loadRoleList();
});

function loadRoleList() {
    var param = new Object();
    var roleSelect = $("#qry_roleId");
    $.getJSON("/auth/queryRoleList",param,function (data) {
        roleSelect.append("<option value=''>请选择</option>");
        $.each(data,function (index,item) {
            roleSelect.append("<option value='" + item.roleId+"'>"+item.roleName+"</option>");
        });
    });
}

function AuthorizationValidate() {
    var roleSelect = $("#qry_roleId").val();
    if(!roleSelect){
        alert("请先选择角色!");
        return false;
    }
    return true;
}

function QueryAuthorizationInfo() {
    var roleSelect = $("#qry_roleId").val();
    if(!roleSelect){
        alert("请先选择角色!");
        return false;
    }

    var auth_type = $("#qry_authorizationObjType").val();
    if(!auth_type){
        alert("请选择授权类型");
        return false;
    }

    if(auth_type == 1){
        //查询授权的组织信息
        queryAuthOrg(0);
    }
    if(auth_type==2){
        //查询授权的人员信息
        queryAuthUser(0);
    }

    if(auth_type==3){
        //查询授权的菜单信息
        queryAuthMenu(0);
    }
}