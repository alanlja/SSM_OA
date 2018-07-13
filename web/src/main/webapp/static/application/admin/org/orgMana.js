var pageSize = 3;
var totalCount;
var orgOper;
$(document).ready(function(){
	orgOper = new OrgOper();
	//加载分页数据
	loadOrgPage(0);
	
	//查询按钮
	$("#queryButton").bind("click",function(){
		loadOrgPage(0);
	});
	
	$("#resetButton").bind("click",function(){
		$("#qry_orgName").val('');
		$("#qry_state").val('');
		$("#qry_orgParentId").val('');
		$("#qry_orgParentName").val('');
		loadOrgPage(0);
	});
});

function loadOrgPage(startPage){
	var param = new Object();
	param.startIndex = startPage*pageSize; 
	param.pageSize = pageSize;
	param.orgName = $("#qry_orgName").val();
	param.state = $("#qry_state").val();
	param.orgParentId = $("#qry_orgParentId").val();
	
	$.ajax({
		url:"/org/orgPage",
		async:true,
		type:"GET",
		data:param,
		success:function(response){
			if(response!=""){
				$("#pageListContainer").empty();
				$("#pageListContainer").html(response);
				loadOrgPageNumber(startPage);
			}
		}
	});
}

function loadOrgPageNumber(startPage){
	var param = new Object();
	param.startIndex = startPage*pageSize; 
	param.pageSize = pageSize;
	param.total=totalCount;
	
	$.ajax({
		url:"/org/getPageNumber",
		async:true,
		type:"GET",
		data:param,
		success:function(response){
			if(response!=""){
				$("#pageNumberToolBar").empty();
				$("#pageNumberToolBar").html(response);
			}
		}
	});
	
}
