function OrgTreeLayer(){

    var flag = AuthorizationValidate();
    if(flag==false){
        return;
    }

    var htmlStr = "";
    htmlStr += '<div style="width:300px;height:200px" >';
    htmlStr += '<div style="display:block; padding-bottom:5px;" align="center"  >';
    htmlStr += '<div><table><tr><td><input type="hidden" id="tmp_orgId" width="50px"/><input type="text" id="tmp_orgName" width="50px"><button onclick="AddRoleOrgRelFunc();">添加</button></td></tr></table></div>';
    htmlStr += '<div id="orgTree" class="ztree"></div>';
    htmlStr += '</div>';
    htmlStr += '</div>';

    $.layer({
        type : 1,// 1：页面 2：frame
        title : "组织",
        area : [ 'auto', 'auto' ],
        page : {
            html : htmlStr
        }
    });
    loadOrgTree();
}

function loadOrgTree(){
    var setting = {
        data : {
            simpleData : {
                enable : true
                // 使用简单 Array格式的数据
            }
        },
        async : {
            enable : true, // 开启异步加载处理
            url : "/tree/orgSubList",
            autoParam : [ "id", "name" ],
            dataType : "json",// 默认text
            type : "get"// 默认post
        },
        callback : {
            onClick : ClickQueryOrgTreeNodeFunc
        }
    };

    var zNodes = [];

    var zTreeObj = $.fn.zTree.init($("#orgTree"), setting, zNodes);

}

//点击树的目录时得到点击目录的名称
function ClickQueryOrgTreeNodeFunc(event, treeId, treeNode, clickFlag) {

    $("#tmp_orgId").val(treeNode.id);
    $("#tmp_orgName").val(treeNode.name);
}

function AddRoleOrgRelFunc(){
    var param = new Object();
    param.orgId = $("#tmp_orgId").val();
    param.roleId = $("#qry_roleId").val();

    $.ajax({
        url:"/auth/addRoleOrgRel",
        async:true,
        data:param,
        type:"GET",
        success:function(response){
            var obj = jQuery.parseJSON(response);
            if(obj.isSuccess==true){
                layer.alert("授权组织成功!",1,function(){
                    layer.closeAll();
                    queryAuthOrg(0);
                });
            }else{
                layer.alert("授权组织失败!",5,function(){
                    layer.closeAll();
                });
            }
        }
    });
}
