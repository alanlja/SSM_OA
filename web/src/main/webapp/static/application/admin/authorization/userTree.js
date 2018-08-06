function UserTreeLayer(){

    var flag = AuthorizationValidate();
    if(flag==false){
        return;
    }

    var htmlStr = "";
    htmlStr += '<div style="width:300px;height:200px" >';
    htmlStr += '<div style="display:block; padding-bottom:5px;" align="center"  >';
    htmlStr += '<div><table><tr><td><input type="hidden" id="tmp_userId" width="50px"/><input type="text" id="tmp_userName" width="50px"><button onclick="AddRoleUserRelFunc();">添加</button></td></tr></table></div>';
    htmlStr += '<div id="userTree" class="ztree"></div>';
    htmlStr += '</div>';
    htmlStr += '</div>';

    $.layer({
        type : 1,// 1：页面 2：frame
        title : "用户",
        area : [ 'auto', 'auto' ],
        page : {
            html : htmlStr
        }
    });
    loadUserTree();
}

function loadUserTree(){
    var setting = {
        data : {
            simpleData : {
                enable : true
                // 使用简单 Array格式的数据
            }
        },
        async : {
            enable : true, // 开启异步加载处理
            url : "/tree/userSubList",
            autoParam : [ "id", "name" ],
            dataType : "json",// 默认text
            type : "get"// 默认post
        },
        callback : {
            onClick : ClickQueryUserTreeNodeFunc
        }
    };

    var zNodes = [];

    var zTreeObj = $.fn.zTree.init($("#userTree"), setting, zNodes);

}

//点击树的目录时得到点击目录的名称
function ClickQueryUserTreeNodeFunc(event, treeId, treeNode, clickFlag) {
    if(treeNode.isParent==true){
        alert("请选择人员!");
        return;
    }

    $("#tmp_userId").val(treeNode.id);
    $("#tmp_userName").val(treeNode.name);
}

function AddRoleUserRelFunc(){
    var param = new Object();
    param.userId = $("#tmp_userId").val();
    param.roleId = $("#qry_roleId").val();

    $.ajax({
        url:"/auth/addRoleUserRel",
        async:true,
        data:param,
        type:"GET",
        success:function(response){
            var obj = jQuery.parseJSON(response);
            if(obj.isSuccess==true){
                layer.alert("授权人员成功!",1,function(){
                    layer.closeAll();
                    queryAuthUser(0);
                });
            }else{
                layer.alert("授权人员失败!",5,function(){
                    layer.closeAll();
                });
            }
        }
    });
}