var pageSize = 2;
var totalCount = 0;
var menuOper;
$(document).ready(function () {
    menuOper = new MenuOper();
    // 异步请求菜单列表信息，参数为分页的起码下标
    LoadMenuListData(0);

    //点击查询
    $("#queryButton").bind("click",function () {
        LoadMenuListData(0);
    });

    //重置
    $("#resetButton").bind("click", function() {
        $("#qry_menuParentName").val('');
        $("#qry_menuParentId").val('');
        $("#qry_menuName").val('');
        $("#qry_isPublish").val('');
        LoadMenuListData(0);
    });
});

// 加载菜单列表信息
function LoadMenuListData(startPage) {
    var param = new Object();
    param.startIndex = startPage*pageSize;
    param.pageSize = pageSize;
    param.menuParentId = $("#qry_menuParentId").val();
    param.menuName = $("#qry_menuName").val();
    param.isPublish = $("#qry_isPublish").val();
    
    $.ajax({
       url : '/menu/menuPage',
        async : true,
        type : 'get',
        dataType : 'text',
        data : param,
        success : function (response) {
            if (response != ''){
                $('#pageListContainer').empty();
                $('#pageListContainer').html(response);
                LoadOrgPageNumber(startPage);
            }
        }
    });
}

// 发送请求得到页码信息
function LoadOrgPageNumber(startPage) {
    $.ajax({
        url : '/menu/getPageNumber',
        async : true,
        dataType : 'text',
        data : {
            total : totalCount,
            startIndex : startPage * pageSize,
            pageSize : pageSize
        },
        success : function(response) {
            if (response != '') {
                $('#pageNumberToolBar').empty();
                $('#pageNumberToolBar').html(response);
            }
        }
    });
}