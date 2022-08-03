<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%
    String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=path%>"/>
    <meta charset="UTF-8">
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <title>
        测试文件下载
    </title>
    <script type="text/javascript" >
        $(function (){
            $("#filedownloadbt").click(function (){
                window.location.href="workbench/activity/filedownload.do"
            })
        })
    </script>
</head>
<body>
<input type="button" value="下载" id="filedownloadbt">
</body>
</html>
