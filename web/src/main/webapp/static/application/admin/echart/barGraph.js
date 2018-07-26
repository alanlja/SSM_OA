$(document).ready(function() {
	// 把生成的图表挂载到barEchart的div中
	var myChart = echarts.init(document.getElementById("barEchart"));

	var legendData = [ '男', '女', '保密' ];// 图例
	var colors = [ '#5793f3', '#d14a61', '#675bba' ];// 图例颜色

	var option = {
		color : colors,
		// 移动到柱体时有阴影的效果
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		// 图表离右边框的距离
		grid : {
			right : '20%'
		},
		// 右边的三个小工具
		toolbox : {
			feature : {
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		// 图表上显示的分类信息
		legend : {
			data : []
		},
		// X轴显示的内容
		// option.xAxis[0].data
		xAxis : [ {
			type : 'category',
			axisTick : {
				alignWithLabel : false
			// 刻度与分类的位置
			},
			data : []
		} ],
		// Y轴显示的三个刻度条
		yAxis : [ {
			type : 'value',
			name : '',
			// min: 0,
			// max: 250,
			position : 'right',
			axisLine : {
				lineStyle : {
					color : colors[0]
				}
			},
			axisLabel : {
				formatter : '{value} 个'
			}
		}, {
			type : 'value',
			name : '',
			/*
			 * min: 0, max: 250,
			 */
			position : 'right',
			offset : 80,
			axisLine : {
				lineStyle : {
					color : colors[1]
				}
			},
			axisLabel : {
				formatter : '{value} 个'
			}
		}, {
			type : 'value',
			name : '',
			/*
			 * min: 0, max: 25,
			 */
			position : 'left',
			axisLine : {
				lineStyle : {
					color : colors[2]
				}
			},
			axisLabel : {
				formatter : '{value} 个'
			}
		} ],
		// 移动柱体时旁边小图框展示的数据
		series : [ {
			name : '',
			type : 'bar',// line
			data : []
		}, {
			name : '',
			type : 'bar',
			yAxisIndex : 1,
			data : []
		}, {
			name : '',
			type : 'bar',
			yAxisIndex : 2,
			data : []
		} ]
	};

	var param = new Object();
	$.getJSON("/user/getUserSexStaticties",param,function(data){
		//{"list":[{"girl":1,"provinceName":"北京市","secrity":0,"boy":0},{"girl":0,"provinceName":"广东省","secrity":0,"boy":2},{"girl":0,"provinceName":"福建省","secrity":0,"boy":1}],"isSuccess":true}
		if(data.isSuccess==true){
			var list = data.list;
			
			//装性别的最大的个数
			var max = 0;
			
			//定义数组来接收查询到的数据
			var xAxisData = [];//X轴的数据  放省份的名称 
			var seriesBoyData = [];//小提示框要展示的数据
			var seriesGirlData = [];
			var seriesSecrityData = [];
			
			for(var i=0;i<list.length;i++){
				var item = list[i];
				xAxisData[i] = item['provinceName'];
				seriesBoyData[i]= item['boy'];
				seriesGirlData[i]= item['girl'];
				seriesSecrityData[i]= item['secrity'];
				
				if(seriesBoyData[i]>max){
					max = seriesBoyData[i];
				}
				
				if(seriesGirlData[i]>max){
					max = seriesGirlData[i];
				}
				
				if(seriesSecrityData[i]>max){
					max = seriesSecrityData[i];
				}
			}
			//给图例赋值
			option.legend.data = legendData;
			//给X轴赋值
			option.xAxis[0].data = xAxisData;
			//给Y轴的名称和刻度的最大值赋值
			option.yAxis[0].name=legendData[0];
			option.yAxis[0].max=max;
			
			option.yAxis[1].name=legendData[1];
			option.yAxis[1].max=max;
			
			option.yAxis[2].name=legendData[2];
			option.yAxis[2].max=max;
			
			//给小提示框 赋值
			option.series[0].name=legendData[0];//男
			option.series[0].data=seriesBoyData;//0,2,1
			
			option.series[1].name=legendData[1];//女
			option.series[1].data=seriesGirlData;//1,0,0
			
			option.series[2].name=legendData[2];//保密
			option.series[2].data=seriesSecrityData;//0,0,0
			
			//把option信息添加到图表对象中
			myChart.setOption(option);
			
		}else{
			alert("连接服务器失败!!");
		}
	});
});