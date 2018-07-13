function OrgOper(){
	
	this.addOrg = function(){
		var htmlStr = orgOper.initHtml();
		
		$.layer({
			type:1,
			title:false,
			area:['auto','auto'],
			page:{
				html:htmlStr
			}
		})
	}
	
	this.saveOrg = function(){
		var orgId = $("#orgId").val();
		
		var param = new Object();
		param.orgId = orgId;
		param.orgParentId = $("#orgParentId").val();
		param.orgName = $("#orgName").val();
		param.state = $("#state").val();
		param.dispIndex = $("#dispIndex").val();
		param.orgDesc = $("#orgDesc").val();
		
		//判断是做新增还是修改
		var url = "/org/addOrg";
		var desc="新增";
		if(orgId){
			url = "/org/updateOrg";
			desc="修改";
		}else{
			url = "/org/addOrg";
			desc="新增";
		}
		
		$.ajax({
			url:url,
			async:true,
			type:"POST",
			data:param,
			success:function(data){
				//isSuccess  true,false[{"isSuccess":"true"}]
				var obj = jQuery.parseJSON(data);
				if(obj.isSuccess==true){
					var alerts = layer.alert(desc+"成功",1,function(){
						layer.close(alerts);
						location.reload();
					});
				}else{
					var alerts = layer.alert(desc+"失败",5,function(){
						layer.close(alerts);
					});
				}
			}
		})
	}

    this.showOrg = function(orgId){
        if(orgId==1){
            var alerts= layer.alert("第一级组织没有详细内容！",1,function(){
                layer.close(alerts);
            });
            return null;
        }

        var htmlStr = orgOper.initHtml();

        $.layer({
            type:1,
            title:false,
            area:['auto','auto'],
            page:{
                html:htmlStr
            }
        })

        var param = new Object();
        param.orgId = orgId;

        $.getJSON("/org/queryOrgById",param,function(data){
            $("#orgId").val(data.org.orgId);
            $("#orgParentId").val(data.org.orgParentId);
            $("#orgParentName").val(data.org.orgParentName);
            $("#orgName").val(data.org.orgName);
            $("#state").val(data.org.state);
            $("#dispIndex").val(data.org.dispIndex);
            $("#orgDesc").val(data.org.orgDesc);
            $("#saveButton").hide();
        });

    }

    this.delOrg = function(orgId){
        var param = new Object();
        param.orgId = orgId;

        $.ajax({
            url:"/org/delOrg",
            async:true,
            type:"GET",
            data:param,
            success:function(data){
                //isSuccess  true,false[{"isSuccess":"true"}]
                var obj = jQuery.parseJSON(data);
                if(obj.isSuccess==true){
                    var alerts = layer.alert("删除成功",1,function(){
                        layer.close(alerts);
                        location.reload();
                    });
                }else{
                    var alerts = layer.alert("删除失败",5,function(){
                        layer.close(alerts);
                    });
                }
            }
        })
    }

    this.editOrg = function(orgId){
        if(orgId==1){
            var alerts= layer.alert("第一级组织没有详细内容！",1,function(){
                layer.close(alerts);
            });
            return null;
        }

        var htmlStr = orgOper.initHtml();

        $.layer({
            type:1,
            title:false,
            area:['auto','auto'],
            page:{
                html:htmlStr
            }
        })

        var param = new Object();
        param.orgId = orgId;


        $.getJSON("/org/queryOrgById",param,function(data){
            $("#orgId").val(data.org.orgId);
            $("#orgParentId").val(data.org.orgParentId);
            $("#orgParentName").val(data.org.orgParentName);
            $("#orgName").val(data.org.orgName);
            $("#state").val(data.org.state);
            $("#dispIndex").val(data.org.dispIndex);
            $("#orgDesc").val(data.org.orgDesc);
        });
    }
	
	this.initHtml = function(){		 
		var htmlStr = '';
		htmlStr += '<div style="width:600px;" >';
		htmlStr += '<div style=" line-height:30px; text-indent:10px; margin-bottom:20px; background-color:#eee; position:relative;">组织</div>';
		htmlStr += '<div style="display:block; padding-bottom:20px;" align="center"  >';		
		htmlStr += '<table border="0" >';		
		htmlStr += '<tr style="display:none">';
		htmlStr += '<td colspan="2">';
		htmlStr += '<span>组织ID:</span>';
		htmlStr += '<input type="text"  id="orgId" style="width:220px;">';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		htmlStr += '<tr>';
		htmlStr += '<td>';
		htmlStr += '<span>父组织:</span>';
		htmlStr += '<input type="hidden"  id="orgParentId" style="width:120px;">';
		htmlStr += '<input type="text"  id="orgParentName"  readonly="readonly" style="width:120px;">';
		htmlStr += '<input type="button"  value="父组织" onclick="AddOrgTreeLayer();" style="width:60px;">';		
		htmlStr += '</td>';
		htmlStr += '<td>';
		htmlStr += '<span>组织名称:</span>';
		htmlStr += '<input type="text"  id="orgName" style="width:220px;">';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		htmlStr += '<tr>';
		
		htmlStr += '<td>';
		htmlStr += '<span>组织状态:</span>';
		htmlStr += '<select id="state" style="width:220px;">';
		htmlStr += '<option value="">请选择</option>';
		htmlStr += '<option value="1">可用</option>';
		htmlStr += '<option value="2">不可用</option>';
		htmlStr += '</select>';
		htmlStr += '</td>';
		
		htmlStr += '<td>';
		htmlStr += '<span>显示顺序:</span>';
		htmlStr += '<input type="text"  id="dispIndex" style="width:220px;">';
		htmlStr += '</td>';

		htmlStr += '</tr>';
		htmlStr += '<tr>';
		htmlStr += '<td colspan="2" align="center" >';	 
		htmlStr += '<span>组织描述:</span>';
		htmlStr += '<textarea id="orgDesc" rows="3"   style="width:500px;"></textarea>';
		htmlStr += '</td>';
		htmlStr += '</tr>';
		htmlStr += '<tr id="saveOrgTr"><td colspan="2" align="center">';
		htmlStr += '<input id="saveButton"  type="button"  onclick="javascript:orgOper.saveOrg();" value="保存"  />';		
		htmlStr += '</td></tr>';
		htmlStr += '</table>';
		htmlStr += '</div>';
		htmlStr += '</div>';
		
		return htmlStr;
	}

}