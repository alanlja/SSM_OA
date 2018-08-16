function queryAuthUser(startPage) {
    var param = new Object();
    param.startIndex = startPage*pageSize;
    param.pageSize = pageSize;
    param.roleId = $("#qry_roleId").val();

    $.ajax({
        url : "/auth/queryUserPage",
        async : true,
        data : param,
        type : "GET",
        success : function (data) {
            if(data != ""){
                $("#pageListContainer").empty();
                $("#pageListContainer").html(data);
                queryAuthUserNumber(startPage);
            }
        }
    });
}

function queryAuthUserNumber(startPage) {
    var param = new Object();
    param.startIndex = startPage*pageSize;
    param.pageSize = pageSize;
    param.total = totalCount;

    $.ajax({
        url:"/auth/queryUserPageNumber",
        async:true,
        data:param,
        type:"GET",
        success:function(data){
            if(data!=""){
                $("#pageNumberToolBar").empty();
                $("#pageNumberToolBar").html(data);
            }
        }
    });
}

function DeleteAuthorizationUser(userId){
    var param = new Object();
    param.userId = userId;
    param.roleId = $("#qry_roleId").val();

    $.ajax({
        url:"/auth/delUserRoleRel",
        async:true,
        data:param,
        type:"GET",
        success:function(response){
            var obj = jQuery.parseJSON(response);
            if(obj.isSuccess==true){
                layer.alert("删除授权组织成功!",1,function(){
                    layer.closeAll();
                    queryAuthOrg(0);
                });
            }else{
                layer.alert("删除授权组织失败!",5,function(){
                    layer.closeAll();
                });
            }
        }
    });
}