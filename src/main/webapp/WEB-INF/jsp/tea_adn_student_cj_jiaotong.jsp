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
//查找学生
function doView() {
    $("#grid").datagrid('load', {
    	student_number : $("#number").val()      
    });

}
	// 工具栏
	var toolbar = [  { 
        text: '<input type="text" id="number"  class="easyui-searchbox" style="margin-top:-2px;" placeholder="学号"/>' 
    }, {
		id : 'button-view',	
		text : '查找',
		iconCls : 'icon-search',
		handler : doView
	},{
		id : 'button-add',
		text : '新增',
		iconCls : 'icon-add',
		handler : openAddDialog
	}, {
		id : 'button-edit',
		text : '修改',
		iconCls : 'icon-edit',
		handler : openModifyDialog
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
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
			url : "${pageContext.request.contextPath}/queryTeacher_Chengji_jiaotongList",
			idField : 'jiaotong_id', 
			frozenColumns : frozenColumns,
			columns : columns,
			pageList: [5,10,20],
			pagination : true,
			rownumbers:true,
		});
		
		$("body").css({visibility:"visible"});
		
	});
	

	//新增学生信息
	function openAddDialog() {
	    $("#dlg").dialog("open").dialog("setTitle", "添加学生");
	    resetValue();
	    $("#jiaotong_id").removeAttr("name");
	    var zhuanye_name="${sessionScope.user.teacher_remarks }";
	    $("#zhuanye_name").val(zhuanye_name);
	      url = "${pageContext.request.contextPath}/addTeacher_Chengji_jiaotong";
	}
	//修改学生信息
	function openModifyDialog() {
	    var selectedRows = $("#grid").datagrid("getSelections");
	    if (selectedRows.length != 1) {
	        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
	        return;
	    }
	    var row = selectedRows[0];
	    $("#dlg").dialog("open").dialog("setTitle", "编辑学生信息");
	    $("#fm").form("load", row);
	    url = "${pageContext.request.contextPath}/modifyTeacher_Chengji_jiaotong";
	}
	//新增与修改的统一按钮
	function saveStudent() {
	    $("#fm").form("submit", {
	        url : url,
	        success : function(result) {
	            if (result) {
	                $.messager.alert("系统提示", "保存成功！");
	                resetValue();
	                $("#dlg").dialog("close");
	                $("#grid").datagrid("reload");
	            } else {
	                $.messager.alert("系统提示", "保存失败！");
	                return;
	            }
	        }
	    });}
	//清空对话框
	function resetValue() {
	    $("#zhuanye_name").val("");
	    $("#student_number").val("");
	    $("#student_name").val("");
	    $("#kc_tlys").val("");
	    $("#kc_tlhy").val("");
	    $("#kc_jtgh").val("");
	    $("#kc_tlxc").val("");
	    $("#kc_tllc").val("");
	    $("#kc_tlhy_kcsj").val("");
	    $("#kc_jtys_sx").val("");
	    $("#kc_rjgc_sx").val("");
	    $("#cj_xueqi").datebox('setValue', '');
	}
	function doDelete() {
	    var selectedRows = $("#grid").datagrid("getSelections");
	    if (selectedRows.length == 0) {
	        $.messager.alert("系统提示", "请选择要删除的数据！");
	        return;
	    }
	  
	var strIds = [];
	for ( var i = 0; i < selectedRows.length; i++) {
	    strIds.push(selectedRows[i].jiaotong_id);
	}
	var ids = strIds.join(",");
	$.messager.confirm("系统提示", "您确定要删除这<font color=red>"
	        + selectedRows.length + "</font>条数据吗？", function(r) {
	    if (r) {
	        $.post("${pageContext.request.contextPath}/deleteTeacher_Chengji_jiaotong", {
	            ids : ids
	        }, function(result) {
	            if (result) {
	                $.messager.alert("系统提示", "数据已成功删除！");
	                $('#grid').datagrid('reload');
	        		$('#grid').datagrid('uncheckAll');
	            } else {
	                $.messager.alert("系统提示", "数据删除失败，请联系系统管理员！");
	            }
	        }, "json");
	    }
	});
	}
	
	
</script>		
</head>
<body class="easyui-layout" style="visibility:hidden;">
    <div region="center" border="false">
    	<table id="grid"></table>
	</div>
	
	<div id="dlg" class="easyui-dialog"
            style="width: 830px;height:400px;padding:10px 10px;" closed="true"
            buttons="#dlg-buttons"  >
            <form method="post" id="fm">
            <input type="hidden" id=jiaotong_id name="jiaotong_id" readonly="readonly">
            <table  cellspacing="20px">
            <tr>
             <td>学号：</td>
             <td><input type="text" id=student_number name="student_number" class="easyui-validatebox"  required="required" />&nbsp;
             <span style="color: red">*</span>
             </td>
             <td>姓名：</td>
             <td><input type="text" id="student_name" name="student_name" class="easyui-validatebox"  required="required" />&nbsp;
             <span style="color: red">*</span></td>
            </tr>
             <tr>
              <td>铁路运输专业英语：</td>
             <td><input type="text" id="kc_tlys" name="kc_tlys" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
             <td>铁路货运组织：</td>
             <td><input type="text" id="kc_tlhy" name="kc_tlhy" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
              </tr>         
            <tr>
            <td>交通规划理论与方法：</td>
             <td><input type="text" id="kc_jtgh" name="kc_jtgh" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
             <td>铁路行车规章：</td>
             <td><input type="text" id="kc_tlxc" name="kc_tlxc" class="easyui-validatebox" required="required"/>&nbsp;
             <span style="color: red">*</span></td>
            </tr>
            <tr>
            <td>铁路冷藏运输：</td>
             <td><input type="text" id="kc_tllc" name="kc_tllc" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
             <td>《铁路货运组织》课程设计：</td>
             <td><input type="text" id="kc_tlhy_kcsj" name="kc_tlhy_kcsj" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
            </tr>
            <tr>
            <td>交通运输综合实训：</td>
             <td><input type="text" id="kc_jtys_sx" name="kc_jtys_sx" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
             <td>软件工程实训(B)：</td>
             <td><input type="text" id="kc_rjgc_sx" name="kc_rjgc_sx" required="required" class="easyui-validatebox" />&nbsp;
             <span style="color: red">*</span></td>
            </tr>
              <tr>
             <td>学期：</td>
             <td><input type="text" id="cj_xueqi" name="cj_xueqi" class="easyui-datebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
             <td>专业：</td>
             <td><input type="text" id="zhuanye_name" name="zhuanye_name" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
            </tr>
            </table>
            </form>
    </div>
 <div id="dlg-buttons">
            <a href="javascript:saveStudent()" class="easyui-linkbutton"
                iconCls="icon-ok">保存</a>
 </div>
</body>
</html>