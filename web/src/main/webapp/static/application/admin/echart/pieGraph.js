$(document).ready(function() {

	var myChart = echarts.init(document.getElementById("pieEchart"));

	
	var option = {
		    title : {
		        text: '。。公司来源',
		        subtext: '示例',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: []
		    },
		    series : [
		        {
		            name: '员工来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 100,
		                    shadowOffsetX: 100,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		var param = new Object();  
		$.getJSON("/user/getProvincePersonStaticties",param,function(data){
			//{"list":[{"name":广东省,"value":2},{"name":广东省,"value":2},{"name":广东省,"value":2}],"isSuccess":true}
			if(data.isSuccess==true){
				var legendData = [];
				var list = data.list;
				for(var i=0;i<list.length;i++){
					var item = list[i];
					legendData[i] = item['name'];
				}
				//给图例赋值
				option.legend.data = legendData;
				//给series赋值
				option.series[0].data= list;
				myChart.setOption(option);
			}else{
				alert("连接服务器失败!");
			}
		})
});