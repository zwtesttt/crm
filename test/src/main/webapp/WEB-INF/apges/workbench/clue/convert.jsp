﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
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


<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
	$(function(){
		//给转换按钮添加单击事件
		$("#saveConvertBtn").click(function (){
			var clueId="${cluetra.id}"
			var money=$.trim($("#amountOfMoney").val())
			var name=$.trim($("#tradeName").val())
			var expectedDate=$("#expectedClosingDate").val()
			var stage=$("#stage").val()
			var activiId=$("#activityId").val()
			var isCreateTra=$("#isCreateTransaction").prop("checked");

			$.ajax({
				url: "workbench/clue/convertClue.do",
				type:"post",
				data: {
					clueId:clueId,
					money:money,
					name:name,
					expectedDate:expectedDate,
					stage:stage,
					activityId:activiId,
					isConvert:isCreateTra
				},
				dataType: "json",
				success:function (re){
					if (re.code=="1"){
						window.location.href="workbench/clue/index"
					}else {
						alert(re.message)
					}
				}
			})

		})


		// 给搜索市场活动模态窗口所有的单选按钮填加单击事件
		$("#selectTbodyBtn").on("click","input[type='radio']",function (){
			$("#selectInput").val("")
			$("#selectTbodyBtn").html("")
			// 获取被选中的市场活动id和name
			var id=this.value
			var activityName=$(this).attr("activiName")

			$("#activityId").val(id)
			$("#activityName").val(activityName)
			$("#searchActivityModal").modal("hide")

		})

		// 给搜索按钮添加单机事件
		$("#selectBtn").click(function (){
			$("#searchActivityModal").modal("show")
		})

		//给搜索框添加键盘弹起事件
		$("#selectInput").keyup(function (){
			var actitiName=$(this).val()
			var clueId="${cluetra.id}"

			$.ajax({
				url:"workbench/clue/returnActivityByClueId.do",
				type:"post",
				data:{
					actitiName:actitiName,
					clueId:clueId
				},
				dataType:"json",
				success:function (re){
					var text=""
					$.each(re,function (i,p){
							text+="<tr>"
							text+="<td><input value='"+p.id+"' type=\"radio\" activiName=\""+p.name+"\" name=\"activity\"/></td>"
							text+="<td>"+p.name+"</td>"
							text+="<td>"+p.start_date+"</td>"
							text+="<td>"+p.end_date+"</td>"
							text+="<td>"+p.owner+"</td>"
							text+="</tr>"
					})
					$("#selectTbodyBtn").html(text)
				}

			})
		})



		$("#isCreateTransaction").click(function(){
			if(this.checked){
				$("#create-transaction2").show(200);
			}else{
				$("#create-transaction2").hide(200);
			}
		});
	});
</script>

</head>
<body>
	
	<!-- 搜索市场活动的模态窗口 -->
	<div class="modal fade" id="searchActivityModal" role="dialog" >
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">搜索市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">


						    <input type="text" class="form-control" id="selectInput" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询">
						    <span class="glyphicon glyphicon-search form-control-feedback"></span>
						  </div>
						</form>
					</div>
					<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
								<td></td>
							</tr>
						</thead>
						<tbody id="selectTbodyBtn">
<%--							<tr>--%>
<%--								<td><input type="radio" name="activity"/></td>--%>
<%--								<td>发传单</td>--%>
<%--								<td>2020-10-10</td>--%>
<%--								<td>2020-10-20</td>--%>
<%--								<td>zhangsan</td>--%>
<%--							</tr>--%>
<%--							<tr>--%>
<%--								<td><input type="radio" name="activity"/></td>--%>
<%--								<td>发传单</td>--%>
<%--								<td>2020-10-10</td>--%>
<%--								<td>2020-10-20</td>--%>
<%--								<td>zhangsan</td>--%>
<%--							</tr>--%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="title" class="page-header" style="position: relative; left: 20px;">
		<h4>转换线索 <small>${cluetra.fullname}${cluetra.appellation}-${cluetra.company}</small></h4>
	</div>
	<div id="create-customer" style="position: relative; left: 40px; height: 35px;">
		新建客户：${cluetra.company}
	</div>
	<div id="create-contact" style="position: relative; left: 40px; height: 35px;">
		新建联系人：${cluetra.fullname}${cluetra.appellation}
	</div>
	<div id="create-transaction1" style="position: relative; left: 40px; height: 35px; top: 25px;">
		<input type="checkbox" id="isCreateTransaction"/>
		为客户创建交易
	</div>
	<div id="create-transaction2" style="position: relative; left: 40px; top: 20px; width: 80%; background-color: #F7F7F7; display: none;" >
	
		<form>
		  <div class="form-group" style="width: 400px; position: relative; left: 20px;">
		    <label for="amountOfMoney">金额</label>
		    <input type="text" class="form-control" id="amountOfMoney">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="tradeName">交易名称</label>
		    <input type="text" class="form-control" id="tradeName" value="${cluetra.company}-">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="expectedClosingDate">预计成交日期</label>
		    <input type="text" class="form-control" id="expectedClosingDate">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="stage">阶段</label>
		    <select id="stage"  class="form-control">
		    	<option></option>
				<c:forEach items="${stageList}" var="obj">
					<option value="${obj.id}">${obj.value}</option>

				</c:forEach>
		    </select>
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
			  <input type="hidden" id="activityId">
		    <label for="activityName">市场活动源&nbsp;&nbsp;<a href="javascript:void(0);" id="selectBtn" style="text-decoration: none;"><span class="glyphicon glyphicon-search"></span></a></label>
		    <input type="text" class="form-control" id="activityName" placeholder="点击上面搜索" readonly>
		  </div>
		</form>
		
	</div>
	
	<div id="owner" style="position: relative; left: 40px; height: 35px; top: 50px;">
		记录的所有者：<br>
		<b>${cluetra.owner}</b>
	</div>
	<div id="operation" style="position: relative; left: 40px; height: 35px; top: 100px;">
		<input class="btn btn-primary" id="saveConvertBtn" type="button" value="转换">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="btn btn-default" type="button" value="取消">
	</div>
</body>
</html>