var pageSize = 3;
var totalCount;
var userOper;
var emailOper;
$(document).ready(function () {
   userOper = new UserOper();
   emailOper = new EmailOper();
   loadUserPage(0);
   
   $("#queryButton").bind("click",function () {
       loadUserPage(0);
   });
   
   $("#resetButton").bind("click",function () {
       $("#qry_userChName").val('');
       $("#qry_mobilePhone").val('');
       $("#qry_provinceId").val('');
       $("#qry_cityId").val('');
       $("#qry_countryId").val('');
       $("#qry_orgId").val('');
       $("#qry_orgName").val('');
       loadUserPage(0);
   });
    //初始化区域信息
    CascadeArea(null,null,null,"qry_provinceId","qry_cityId","qry_countryId");
});

function loadUserPage(startPage){
    var param = new Object();
    param.startIndex = startPage*pageSize;
    param.pageSize = pageSize;
    param.userChName = $("#qry_userChName").val();
    param.mobilePhone = $("#qry_mobilePhone").val();
    param.provinceId = $("#qry_provinceId").val();
    param.cityId = $("#qry_cityId").val();
    param.countryId = $("#qry_countryId").val();
    param.orgId = $("#qry_orgId").val();

    $.ajax({
        url:"/user/userPage",
        async:true,
        data:param,
        type:"GET",
        success:function(data){
            if(data!=""){
                $("#pageListContainer").empty();
                $("#pageListContainer").html(data);
                loadUserPageNumber(startPage);
            }
        }
    });
}

function loadUserPageNumber(startPage){
    var param = new Object();
    param.startIndex = startPage*pageSize;
    param.pageSize = pageSize;
    param.total = totalCount;

    $.ajax({
        url:"/user/getUserPageNumber",
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