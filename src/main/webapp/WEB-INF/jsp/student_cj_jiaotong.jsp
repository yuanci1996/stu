<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	// 工具栏
	var toolbar = [  {
		id : 'button-export',
		text : '<a style="text-decoration:none;" href="${pageContext.request.contextPath}/erportTeacher_Chengji_jiaotongByExcel?zhuanye_id='+"${sessionScope.user.zhuanye_id }"+'">导出到Excel</a>',
		iconCls : 'icon-save'
	}];
	//定义冻结列
	var frozenColumns = [ [ {
		field : 'jiaotong_id',
		checkbox : true,
		rowspan : 2
	} ] ];
	// 定义标题栏
	var columns = [ [ {
		field : 'zhuanye_name',
		title : '专业',
		width : 160,
		rowspan : 2,
		align : 'center'
	}, {
		field : 'student_number',
		title : '学号',
		width : 160,
		rowspan : 2,
		align : 'center'
	}, {
		field : 'student_name',
		title : '学生姓名',
		width : 80,
		align : 'center'
	} , {
		field : 'kc_tlys',
		title : '铁路运输专业英语',
		width : 160,
		align : 'center'
	} , {
		field : 'kc_tlhy',
		title : '铁路货运组织',
		width : 160,
		align : 'center'
	},{
		field : 'kc_jtgh',
		title : '交通规划理论与方法',
		width : 160,
		align : 'center'
	}, {
		field : 'kc_tlxc',
		title : '铁路行车规章',
		width : 160,
		align : 'center'
	},{
		field : 'kc_tllc',
		title : '铁路冷藏运输',
		width : 160,
		align : 'center'
	},{
		field : 'kc_tlhy_kcsj',
		title : '《铁路货运组织》课程设计',
		width : 80,
		align : 'center'
	},{
		field : 'kc_jtys_sx',
		title : '交通运输综合实训',
		width : 120,
		align : 'center'
	},{
		field : 'kc_rjgc_sx',
		title : '软件工程实训(B)',
		width : 160,
		align : 'center'
	},{
		field : 'cj_xueqi',
		title : '学期',
		width : 100,
		align : 'center'
	}
	] ];
	$(function(){
		// 初始化 datagrid
		// 创建grid
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			 fitColumns:true,//宽度自适应
			striped : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/studentgradelist",
			idField : 'jiaotong_id', 
			frozenColumns : frozenColumns,
			columns : columns,
			pageList: [5,10,20],
			pagination : true,
			rownumbers:true,
		});
		
		$("body").css({visibility:"visible"});
		
	});
	
</script>		
</head>
<body class="easyui-layout" style="visibility:hidden;">
    <div region="center" border="false">
    	<table id="grid"></table>
	</div>
</body>
</html>