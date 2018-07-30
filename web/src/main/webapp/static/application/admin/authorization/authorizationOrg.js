function queryAuthOrg(startPage) {
    var param = new Object();
    param.startIndex = startPage*pageSize;
    param.pageSize = pageSize;
    param.roleId = $("#qry_roleId").val();

    $.ajax({
        url : "/auth/queryOrgPage",
        async : true,
        data : param,
        type : "GET",
        success : function (data) {
            if(data != ""){
                $("#pageListContainer").empty();
                $("#pageListContainer").html(data);
                queryAuthOrgNumber(startPage);
            }
        }
    });
}

function queryAuthOrgNumber(startPage) {
    var param = new Object();
    param.startIndex = startPage*pageSize;
    param.pageSize = pageSize;
    param.total = totalCount;

    $.ajax({
        url:"/auth/queryOrgPageNumber",
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

function DeleteAuthorizationOrg(orgId){
    var param = new Object();
    param.orgId = orgId;
    param.roleId = $("#qry_roleId").val();

    $.ajax({
        url:"/auth/delOrgRoleRel",
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