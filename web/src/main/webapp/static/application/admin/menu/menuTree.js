function QueryMenuTreeLayer(){
	
   var htmlStr = "";
    htmlStr += '<div style="width:300px;height:200px" >';
	htmlStr += '<div style=" line-height:30px; text-indent:10px; margin-bottom:20px; background-color:#eee; position:relative;">组织</div>';
	htmlStr += '<div style="display:block; padding-bottom:5px;" align="center"  >';
	htmlStr += '<div><table><tr><td><input type="hidden" id="tmp_menuParentId" width="50px"/><input type="text" id="tmp_menuParentName" width="50px"><button onclick="SetQueryMenuTreeParam();">确定</button></td></tr></table></div>';
	htmlStr += '<div id="QueryMenuTree" class="ztree"></div>';
	htmlStr += '</div>';
	htmlStr += '</div>';
	
  $.layer({
		type : 1,//1：页面 2：frame
		title : false,
		area : [ 'auto', 'auto' ], 
		page : {
			html : htmlStr
		}
	  });
  
  //调用树
  QueryMenuTreeObj();
} 

function QueryMenuTreeObj(){
	 
 var setting = {   
	        data: {    
	            simpleData: {    
	                enable: true  //使用简单 Array格式的数据 
	            }    
	        },
	        async: {    
	            enable: true, //开启异步加载处理   
	            url:"/tree/menuDirSubList",
	            autoParam:["id", "name"],    
	            dataType: "json",//默认text  
	            type:"get"//默认post  
	        }  
	        ,callback:{  
	            onClick:ClickQueryMenuTreeNodeFunc
	        }  
	    };    
	 	   
     
     var zNodes=[];  
     
     var  zTreeObj  =  $.fn.zTree.init($("#QueryMenuTree"), setting, zNodes); 
	     
}

//点击树的目录时得到点击目录的名称
function ClickQueryMenuTreeNodeFunc(event, treeId, treeNode, clickFlag){   
	 $("#tmp_menuParentId").val(treeNode.id);
	 $("#tmp_menuParentName").val(treeNode.name);
 } 

//点击目录后把名称放到框中
function SetQueryMenuTreeParam(){ 
	 $("#qry_menuParentId").val( $("#tmp_menuParentId").val());
	 $("#qry_menuParentName").val( $("#tmp_menuParentName").val());
	 var index = layer.index; //获取当前弹层的索引号
	 layer.close(index); //关闭当前弹层
 }


