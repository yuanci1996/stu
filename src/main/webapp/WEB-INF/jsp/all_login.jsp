<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css" />
</head>
<body>
<div class="main-inner" id="mainCnt">

<div class="header">
    <div class="page-logo">
        <h2><strong>学生综合测评管理系统</strong></h2>
        <p>Student Comprehensive survey management system</p>
    </div>
</div>
   <div id="loginBlock" class="login">
        <div class="loginFunc">
            <div id="lbNormal" class="loginFuncMobile">系统登录</div>
        </div>        <div class="loginForm">
            <form id="loginform" name="loginform" method="post" class="niceform"
                  action="${pageContext.request.contextPath}/student_login">
                <div id="idInputLine" class="loginFormIpt showPlaceholder"
                     style="margin-top: 5px;">
                    <input id="loginform:idInput" type="text" name="student_number"
                           class="loginFormTdIpt" maxlength="50" required="required" /> <label for="idInput"
                                                                           class="placeholder" id="idPlaceholder">帐号：</label>
                </div>
                <div class="forgetPwdLine"></div>
                <div id="pwdInputLine" class="loginFormIpt showPlaceholder">
                    <input id="loginform:pwdInput" class="loginFormTdIpt"
                           type="password" name="student_password" value=""  required="required"/> <label
                        for="pwdInput" class="placeholder" id="pwdPlaceholder">密码：</label>
                </div>
                <div class="loginFormIpt loginFormIptWiotTh"
                     style="margin-top:58px;">
                    <div style="margin-left:0px;margin-top:-40px;width:50px;">
                        <select onchange="return select_login();" id="select_l"><option value="s">学生</option>
                        <option value="t">老师</option> <option value="a">管理员</option></select>
                        <script type="text/javascript">
                        function select_login() {
                        	switch ($("#select_l").val()) {
							case "a":
								$("#loginform").attr("action","${pageContext.request.contextPath}/admin_login");
								$("#idInputLine input").attr("name","admin_account");
								$("#pwdInputLine input").attr("name","admin_password");
								break;
							case "t":
								$("#loginform").attr("action","${pageContext.request.contextPath}/teacher_login");
								$("#idInputLine input").attr("name","teacher_number");
								$("#pwdInputLine input").attr("name","teacher_password");
								break;
							case "s":
								$("#loginform").attr("action","${pageContext.request.contextPath}/student_login");
								$("#idInputLine input").attr("name","student_number");
								$("#pwdInputLine input").attr("name","student_password");
								break;
							default:
								break;
							}
                        	
                        }
                    </script>
                    </div>
                    <a class="loginFormTdIpt_a" onclick="mySubmit();" id="loginform:j_id19"
                       name="loginform:j_id19"> <script type="text/javascript">
                        function mySubmit() {
                            $("#loginform").submit();
                        }
                    </script> <span id="loginform:loginBtn" class="btn btn-login"
                                    style="margin-top:-36px;">登录</span>
                    </a>
                    <script type="text/javascript">
                    var Msg="${requestScope.Msg}";
                    if(Msg!=""){alert(Msg);}
                    </script>
                    <div>
                    
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>