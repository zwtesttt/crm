<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=path%>"/>
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="application/javascript">
		$(window).keydown(function (e){
			if(e.keyCode==13){
				$("#bt1").click()
			}
		})
		$(function (){
			$("#bt1").click(function (){
				var username=$.trim($("#username").val())
				var passwd=$.trim($("#passwd").val())
				var islogin=$("#cb").prop("checked")
				if (username==""){
					$("#msg").text("用户名不能为空")
					return;
				}
				if (passwd==""){
					$("#msg").text("密码不能为空")
					return;
				}
				$.ajax({
					url:"settings/qx/user/login.do",
					type:"get",
					data:{
						islogin:islogin,
						username:username,
						passwd:passwd
					},
					dataType:"json",
					success:function (resp){
						if (resp.code=="1"){
							window.location.href="workbench/index";
						}else {
							$("#msg").html(resp.message)
						}
					},
					beforeSend:function (){
						$("#msg").text("正在努力验证")
					}
				})
			})
		})
	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2019&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="settings/qx/user/login.do" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input id="username" class="form-control" type="text" value="${cookie.username.value}" placeholder="用户名" name="username">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input id="passwd" class="form-control" type="password" value="${cookie.passwd.value}" placeholder="密码" name="passwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						<label>
							<c:if test="${not empty cookie.username.value and not empty cookie.passwd.value}">
								<input id="cb" type="checkbox" checked> 十天内免登录
							</c:if>
							<c:if test="${empty cookie.username.value or empty cookie.passwd.value}">
								<input id="cb" type="checkbox"> 十天内免登录
							</c:if>
						</label>
						&nbsp;&nbsp;
						<span id="msg"></span>
					</div>
					<button type="button" id="bt1" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>