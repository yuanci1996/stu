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
    	student_number : $("#student_number").val()      
    });

}
	// 工具栏
	var toolbar = [  { 
        text: '<input type="text" id="number"  class="easyui-searchbox" style="margin-top:-2px;" placeholder="学号"/>' 
    }, {
		id : 'button-edit',
		text : '修改',
		iconCls : 'icon-edit',
		handler : openModifyDialog
	}, {
		id : 'button-export',
		text : '<a style="text-decoration:none;" href="${pageContext.request.contextPath}/erportStudentsExcel?zhuanye_id='+"${sessionScope.user.zhuanye_id }"+'">导出到Excel</a>',
		iconCls : 'icon-save'
	}, {
		id : 'button-daoru',
		text : '<a style="text-decoration:none;" href="javascript:openStuExcelDialog()">导入Excel</a>',
		iconCls : 'icon-edit'
	}];
	//定义冻结列
	var frozenColumns = [ [ {
		field : 'cc_id',
		checkbox : true,
		rowspan : 2
	} ] ];
	// 定义标题栏
	var columns = [ [ {
		field : 'student_number',
		title : '学号',
		width : 200,
		rowspan : 2,
		align : 'center'
	}, {
		field : 'name',
		title : '学生姓名',
		width : 80,
		align : 'center'
	} , {
		field : 'fosc',
		title : '辅导员评分',
		width : 100,
		align : 'center'
	} , {
		field : 'ftosc',
		title : '表彰得分',
		width : 100,
		align : 'center'
	},{
		field : 'ftwsc',
		title : '惩罚扣分',
		width : 100,
		align : 'center'
	}, {
		field : 'frsc',
		title : '体育成绩',
		width : 100,
		align : 'center'
	},{
		field : 'ffsc',
		title : '智育成绩',
		width : 100,
		align : 'center'
	},{
		field : 'fsc',
		title : '综测成绩',
		width : 80,
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
			url : "${pageContext.request.contextPath}/queryTeacherCchengjiList",
			idField : 'cc_id', 
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
	    $("#student_number").removeAttr("readonly");
	    $("#cc_id").removeAttr("name");
	    var student_number=" ";
	    $("#student_number").val(student_number);
	      url = "${pageContext.request.contextPath}/addTeacherCchengji";
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
	    url = "${pageContext.request.contextPath}/modifyTeacherCchengji";
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
	    $("#student_number").val("");
	    $("#name").val("");
	    $("#Fosc").val("");
	    $("#Ftosc").val("");
	    $("#Ftwsc").val("");
	    $("#Frsc").val("");
	    $("#Ffsc").val("");
	    $("#Fsc").val("");
	    $("#sco").val("");
	}
	function doDelete() {
	    var selectedRows = $("#grid").datagrid("getSelections");
	    if (selectedRows.length == 0) {
	        $.messager.alert("系统提示", "请选择要删除的数据！");
	        return;
	    }
	  
	var strIds = [];
	for ( var i = 0; i < selectedRows.length; i++) {
	    strIds.push(selectedRows[i].cc_id);
	}
	var ids = strIds.join(",");
	$.messager.confirm("系统提示", "您确定要删除这<font color=red>"
	        + selectedRows.length + "</font>条数据吗？", function(r) {
	    if (r) { 
	        $.post("${pageContext.request.contextPath}/deleteTeacherCchengji", {
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
            <input type="hidden" id=cc_id name="cc_id" readonly="readonly">
            <table  cellspacing="20px">
             <tr>
             <td>学号：</td>
             <td><input type="text" id=student_number name="student_number" class="easyui-validatebox" readonly="readonly"  required="required" />&nbsp;
             <span style="color: red">*</span>
             </td>
             <td>姓名：</td>
             <td><input type="text" id="name" name="name" class="easyui-validatebox" readonly="readonly"  required="required" />&nbsp;
             <span style="color: red">*</span></td>
            </tr>
             <tr>
              <td>辅导员评分：</td>
             <td><input type="text" id="Fosc" name="Fosc" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
             <td>表彰得分：</td>
             <td><input type="text" id="Ftosc" name="Ftosc" class="easyui-validatebox"  readonly="readonly" required="required" />&nbsp;
             <span style="color: red">*</span></td>
              </tr>         
            <tr>
            <td>惩罚扣分：</td>
             <td><input type="text" id="Ftwsc" name="Ftwsc" class="easyui-validatebox"  readonly="readonly" required="required" />&nbsp;
             <span style="color: red">*</span></td>
             <td>体育成绩:</td>
             <td><input type="text" id="Frsc" name="Frsc" class="easyui-validatebox"  readonly="readonly" required="required"/>&nbsp;
             <span style="color: red">*</span></td>
            </tr>
            <tr>
            <td>智育成绩：</td>
             <td><input type="text" id="Ffsc" name="Ffsc" class="easyui-validatebox"  readonly="readonly" required="required" />&nbsp;
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