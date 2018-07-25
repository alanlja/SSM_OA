var pageSize = 2;
var totalCount = 0;
var roleOper;
$(document).ready(function() {

    roleOper = new RoleOper();
    //异步请求组织列表信息，参数为分页的起码下标
    LoadRoleListData(0);

    //点击查询按钮进行信息的查询
    $("#queryButton").bind("click",function(){
        LoadRoleListData(0);
    })

    //重置
    $("#resetButton").bind("click",function(){
        $("#qry_roleName").val('');
        $("#qry_state").val('');
        LoadRoleListData(0);
    })


});

function LoadRoleListData(startPage){
    var param = new Object();
    param.startIndex=startPage*pageSize;
    param.pageSize=pageSize;
    param.roleName=$("#qry_roleName").val();
    param.state=$("#qry_state").val();

    $.ajax({
        url:'/role/rolePage',
        async: true,
        type: 'get',
        dataType: 'text',
        data: param,
        success: function(response){
            if(response != '') {
                $("#pageListContainer").empty();
                $("#pageListContainer").html(response);
                LoadRolePageNumber(startPage)
            }
        }
    });

}

//发送请求得到页码信息
function LoadRolePageNumber(startPage){
    $.ajax({
        url: '/role/getPageNumber',
        async: true,
        dataType: 'text',
        data: {
            total:totalCount,
            startIndex: startPage*pageSize,
            pageSize: pageSize
        },
        success: function(response){
            if(response != '') {
                $('#pageNumberToolBar').empty();
                $('#pageNumberToolBar').html(response);
            }
        }
    });
}