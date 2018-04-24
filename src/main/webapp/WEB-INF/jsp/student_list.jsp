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


	//定义冻结列
	var frozenColumns = [ [ {
		field : 'id',
		checkbox : true,
		rowspan : 2
	} ] ];
	// 定义标题栏
	var columns = [ [ {
		field : 'student_number',
		title : '学号',
		width : 160,
		align : 'center'
	}, {
		field : 'student_name',
		title : '姓名',
		width : 60,
		align : 'center'
	}, {
		field : 'student_sex',
		title : '性别',
		width : 60,
		align : 'center'
	} , {
		field : 'student_bir',
		title : '出生年月',
		width : 100,
		align : 'center'
	} , {
		field : 'student_jiguan',
		title : '籍贯',
		width : 160,
		align : 'center'
	}, {
		field : 'student_mianmao',
		title : '政治面貌',
		width : 60,
		align : 'center'
	},{
		field : 'student_mingzu',
		title : '民族',
		width : 60,
		align : 'center'
	},{
		field : 'student_xueyuan',
		title : '学院',
		width : 60,
		align : 'center'
	},{
		field : 'student_nianji',
		title : '年级',
		width : 60,
		align : 'center'
	},{
		field : 'student_zhuanye',
		title : '专业',
		width : 160,
		align : 'center'
	},{
		field : 'class_id',
		title : '班级',
		width : 60,
		align : 'center'
	},{
		field : 'student_student_type',
		title : '学生类别',
		width : 60,
		align : 'center'
	},{
		field : 'student_tel',
		title : '联系方式',
		width : 100,
		align : 'center'
	},{
		field : 'student_fudaoyuan',
		title : '辅导员',
		width : 60,
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
			url : "${pageContext.request.contextPath}/studentlist",
			idField : 'student_number', 
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