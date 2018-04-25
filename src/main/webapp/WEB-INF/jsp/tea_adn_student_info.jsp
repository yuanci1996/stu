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
			url : "${pageContext.request.contextPath}/te_adn_queryStudentList",
			idField : 'student_number', 
			frozenColumns : frozenColumns,
			columns : columns,
			pageList: [5,10,20],
			pagination : true,
			rownumbers:true,
		});
		
		$("body").css({visibility:"visible"});
		
	});
	//导入面板
	function openStuExcelDialog() {
	    $("#excelDlg").dialog("open").dialog("setTitle", "导入Excel");  
	}
	function insertStuExcel() {
		var excelUrl = "${pageContext.request.contextPath}/importStuListByExcel";
	    $("#excelForm").form("submit", {
	    	url : excelUrl ,
	        success : function(result) {
	            var result = eval('(' + result + ')');
	            if (result) {
	                $.messager.alert("系统提示", "导入成功!");
	                $("#excelDlg").dialog("close");
	                $("#grid").datagrid("reload");
	            } else {
	                $.messager.alert("系统提示", "导入 失败!");
	                return;
	            }
	        }
	    });}

	//新增学生信息
	function openAddDialog() {
	    $("#dlg").dialog("open").dialog("setTitle", "添加学生");
	    resetValue();
	    $("#student_number").removeAttr("readonly");
	    $("#student_name").removeAttr("readonly");
	      url = "${pageContext.request.contextPath}/te_adn_addStudent";
	}
	//修改学生信息
	function openModifyDialog() {
	    var selectedRows = $("#grid").datagrid("getSelections");
	    if (selectedRows.length != 1) {
	        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
	        return;
	    }
	    $("#student_number").attr("readonly","readonly");
	    $("#student_name").attr("readonly","readonly");
	    var row = selectedRows[0];
	    $("#dlg").dialog("open").dialog("setTitle", "编辑学生信息");
	    $("#fm").form("load", row);
	    url = "${pageContext.request.contextPath}/te_adn_modifyStudent";
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
	    $("#class_id").val("");
	    $("#student_name").val("");
	    $("#student_jiguan").val("");
	    $("#student_bir").datebox('setValue', '');
	    $("#student_tel").val("");
	    $("#student_mianmao").val("");
	    $("#student_mingzu").val("");
	    $("#student_student_type").val("");
	    $("#student_xueyuan").val("");
	    $("#student_nianji").val("");
	    $("#student_zhuanye").val("");
	    $("#student_fudaoyuan").val("");
	}
	function doDelete() {
	    var selectedRows = $("#grid").datagrid("getSelections");
	    if (selectedRows.length == 0) {
	        $.messager.alert("系统提示", "请选择要删除的数据！");
	        return;
	    }
	  
	var strIds = [];
	for ( var i = 0; i < selectedRows.length; i++) {
	    strIds.push(selectedRows[i].student_number);
	}
	var ids = strIds.join(",");
	$.messager.confirm("系统提示", "您确定要删除这<font color=red>"
	        + selectedRows.length + "</font>条数据吗？", function(r) {
	    if (r) {
	        $.post("${pageContext.request.contextPath}/te_adn_deleteStudent", {
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
            style="width: 730px;height:420px;padding:10px 10px;" closed="true"
            buttons="#dlg-buttons"  >
            <form method="post" id="fm">
            <table  cellspacing="20px">
            <tr>
             <td>学号：</td>
             <td><input type="text" id=student_number name="student_number" class="easyui-validatebox" readonly="readonly" required="required" />&nbsp;
             <span style="color: red">*</span>
             </td>
             <td>姓名：</td>
             <td><input type="text" id="student_name" name="student_name" class="easyui-validatebox" readonly="readonly" required="required" />&nbsp;
             <span style="color: red">*</span></td>
            </tr>
            <tr>
            <td>性别：</td>
             <td>
             <input  type="radio" name="student_sex" value="男" checked="checked" style="margin-left: 30px;">男
                     <input type="radio" name="student_sex" value="女" style="margin-left: 70px;">女</td>
             <td>出生年月：</td>
             <td><input type="text" id="student_bir" name="student_bir" class="easyui-datebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
            </tr>
             <tr>
              <td>联系方式：</td>
             <td><input type="text" id="student_tel" name="student_tel" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
             <td>籍贯：</td>
             <td><input type="text" id="student_jiguan" name="student_jiguan" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
              </tr>         
            <tr>
            <td>民族：</td>
             <td><input type="text" id="student_mingzu" name="student_mingzu" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
             <td>政治面貌：</td>
             <td><input type="text" id="student_mianmao" name="student_mianmao" class="easyui-validatebox" required="required"/>&nbsp;
             <span style="color: red">*</span></td>
            </tr>
            <tr>
            <td>学院：</td>
             <td><input type="text" id="student_xueyuan" name="student_xueyuan" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
             <td>年级：</td>
             <td><input type="text" id="student_nianji" name="student_nianji" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
            </tr>
            <tr>
            <td>专业：</td>
             <td><input type="text" id="student_zhuanye" name="student_zhuanye" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
             <td>学生类别：</td>
             <td><input type="text" id="student_student_type" name="student_student_type" required="required" class="easyui-validatebox" />&nbsp;
             <span style="color: red">*</span></td>
            </tr>
              <tr>
             <td>班级：</td>
             <td><input type="text" id="class_id" name="class_id" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
            <td>辅导员：</td>
             <td><input type="text" id="student_fudaoyuan" name="student_fudaoyuan" class="easyui-validatebox" required="required" />&nbsp;
             <span style="color: red">*</span></td>
            </tr>
            </table>
            </form>
    </div>
 
 <div id="dlg-buttons">
            <a href="javascript:saveStudent()" class="easyui-linkbutton"
                iconCls="icon-ok">保存</a>
 </div>
     <div id="excelDlg" class="easyui-dialog" closed="true" style="width:400px"
            buttons="#excelDlg-buttons"  draggable="flase">
       <form id="excelForm"  method="post" enctype="multipart/form-data">
		<p style="font-weight:bold;font-size:150%;" >请选择导入文件:</p>
		<input type="file" name="file" accept="application/vnd.ms-excel" style="width:auto">
		<br/>
	  </form>
    </div>
        <div id="excelDlg-buttons">
            <a href="javascript:insertStuExcel()" class="easyui-linkbutton"
                iconCls="icon-ok">导入</a>
                 <a href="${pageContext.request.contextPath}/erportStudentsExcelMuBan" class="easyui-linkbutton"
                iconCls="icon-edit">模板下载</a>
   </div>
</body>
</html>