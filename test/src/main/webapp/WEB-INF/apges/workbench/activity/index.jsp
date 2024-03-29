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

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<link rel="stylesheet" type="text/css" href="bs_pagination-master/jquery.bs_pagination.min.css">
	<script type="text/javascript" src="bs_pagination-master/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="bs_pagination-master/localization/en.min.js"></script>


<script type="text/javascript">

	$(function(){
		//给导入按钮添加单机事件
		$("#importActivityBtn").click(function (){
			var filename=$("#activityFile").val()
			var suffix=filename.substr(filename.lastIndexOf(".")+1).toLocaleLowerCase()
			if (suffix!="xls"){
				alert("只支持excel文件")
				return;
			}
			var file=$("#activityFile")[0].files[0];
			if (file.size>50*1024*1024){
				alert("文件太大了")
				return;
			}
			//FromData获取二进制数据
			var fromdata=new FormData()
			fromdata.append("activityfile",file)
			$.ajax({
				url:"workbench/activity/uploadActivity.do",
				type:"post",
				data:fromdata,
				dataType:"json",
				processData:false,//设置ajax提交请求前是否将参数转成string
				contentType:false,//设置ajax提交请求时，是否把参数按urlencoded编码

				success:function (resp){
					if (resp.code==1){
						alert(resp.message)
						$("#importActivityModal").modal("hide")
						reloadac(1,$("#demo_pag1").bs_pagination('getOption', 'rowsPerPage'))
					}else {
						alert(resp.message)
						$("#importActivityModal").modal("show")
					}
				}
			})

		})
		//给选择导出按钮添加单机事件
		$("#exportActivityXzBtn").click(function (){
			//获取参数
			//获取被选中的checkbox的value
			var checkeds=$("#listB input[type='checkbox']:checked")

			if(checkeds.size()==0){
				alert("请选择要导出的市场活动")
				return;
			}else {
				var ids=""
				$.each(checkeds,function (){
					ids+="id="+this.value+"&"
				})
				ids=ids.substr(0,ids.length-1)
				window.location.href="workbench/activity/xzQueryActivity.do?"+ids
			}
		})
		//给批量导出按钮添加单机事件
		$("#exportActivityAllBtn").click(function (){
			window.location.href="workbench/activity/exportAllActivity.do"
		})
		//给更新按钮添加单机事件
		$("#saveactivity").click(function (){
			var id=$("#edit-id").val()
			var owner=$("#edit-marketActivityOwner").val()
			var name=$.trim($("#edit-marketActivityName").val())
			var start_date=$("#edit-startTime").val()
			var end_date=$("#edit-endTime").val()
			var cost=$.trim($("#edit-cost").val())
			var describe=$.trim($("#edit-describe").val())
			if (owner==""){
				alert("所有者不能为空")
				return;
			}else if(name==""){
				alert("名称不能为空")
				return;
			}
			if (start_date!=""&&end_date!=""){
				if(start_date>end_date){
					alert("开始时间不能大于结束时间")
					return;
				}
			}
			var resexp=/^(([1-9]\d*)|0)$/
			if(resexp.test(cost)==false){
				alert("开销只能为正整数")
				return;
			}
			$.ajax({
				url:"workbench/activity/savemodify.do",
				type:"post",
				data:{
					id:id,
					owner:owner,
					name:name,
					start_date:start_date,
					end_date:end_date,
					cost:cost,
					description:describe
				},
				dataType: "json",
				success:function (resp){
					if (resp.code==1){
						//关闭模态窗口
						$("#editActivityModal").modal("hide")
						reloadac($("#demo_pag1").bs_pagination('getOption', 'currentPage'),$("#demo_pag1").bs_pagination('getOption', 'rowsPerPage'))
					}else {
						alert(resp.message)
						$("#editActivityModal").modal("show")
					}
				}
			})

		})


		// 给修改按钮添加单机事件
		$("#xiugaibt").click(function (){
			// 获取被选中的checkbox
			var checkeds=$("#listB input[type='checkbox']:checked")

			if(checkeds.size() == 0){
				alert("请选择要修改的市场活动")
				return;
			}else if (checkeds.size()>1){
				alert('每次只能修改一条数据')
				return;
			}
			var idd=checkeds.val()
			$.ajax({
				url:"workbench/activity/modify.do",
				type:"post",
				data:{
					id:idd
				},
				success:function (resp){
					//把市场活动信息加载到模态窗口
					$("#edit-id").val(resp.id)
					$("#edit-marketActivityOwner").val(resp.owner)
					$("#edit-marketActivityName").val(resp.name)
					$("#edit-startTime").val(resp.start_date)
					$("#edit-endTime").val(resp.end_date)
					$("#edit-cost").val(resp.cost)
					$("#edit-describe").val(resp.description)
					//显示模态窗口
					$("#editActivityModal").modal("show")
				}
			})
		})
		//给删除按钮添加单机事件
		$("#actdel").click(function (){
			//获取参数
			//获取被选中的checkbox的value
			var checkeds=$("#listB input[type='checkbox']:checked")
			if(checkeds.size()==0){
				alert("请选择要删除的市场活动")
				return;
			}
			if(window.confirm("确定删除吗？")){
				var ids=""
				$.each(checkeds,function (){
					ids+="id="+this.value+"&"
				})
				ids=ids.substr(0,ids.length-1)
				$.ajax({
					url:"workbench/activity/delteAct.do",
					data:ids,
					type:"post",
					success:function (re){
						if(re.code=="1"){
							reloadac(1,$("#demo_pag1").bs_pagination('getOption', 'rowsPerPage'))
						}else {
							alert(re.message)
						}
					}
				})
			}
		})


		//为listB中的所有checkbox添加单机事件
		// $("#listB input[type='checkbox']").change(function (){
		// 	//如果listB下的选中框数组长度和选中状态下的选择框数组长度一样说明全选了
		// 	alert(123)
		// 	if($("#listB input[type='checkbox']").size()==$("#listB input[type='checkbox']:checked").size()){
		// 		$("#quanxuan").prop("checked",true)
		// 	}else {
		// 		$("#quanxuan").prop("checked",false)
		// 	}
		// })
		$("#listB").on("click","input[type='checkbox']",function (){
			if($("#listB input[type='checkbox']").size()==$("#listB input[type='checkbox']:checked").size()){
				$("#quanxuan").prop("checked",true)
			}else {
				$("#quanxuan").prop("checked",false)
			}
				}
		)



		//点击全选按钮
		$("#quanxuan").click(function (){
			// if (this.checked==true){
			// 	$("#listB input[type='checkbox']").prop("checkbox",true)//选择listB下的所有checkbox,将状态设置为选中
			// }else {
			// 	$("#listB input[type='checkbox']").prop("checkbox",false)//选择listB下的所有checkbox，将状态设置成取消选中
			// }
			$("#listB input[type='checkbox']").prop("checked",this.checked)
		})

		// 用户点击创建按钮后重置表单
		$("#createbt").click(function (){
			$("#createac")[0].reset()
			$("#createActivityModal").modal("show")

		})

		$(".dateclose").datetimepicker({
			language:'zh-CN',
			format:"yyyy-mm-dd",
			minView:"month",
			initData:new Date(),
			autoclose:true,
			todayBtn:true,
			clearBtn:true
		})
		// 创建新的市场活动
		$("#savebt").click(function (){
			var owner=$("#create-marketActivityOwner").val()
			var name=$.trim($("#create-marketActivityName").val())
			var start_date=$.trim($("#create-startTime").val())
			var end_date=$.trim($("#create-endTime").val())
			var cost=$.trim($("#create-cost").val())
			var message=$.trim($("#create-describe").val())
			if (owner==""){
				alert("所有者不能为空")
				return;
			}else if(name==""){
				alert("名称不能为空")
				return;
			}
			if (start_date!=""&&end_date!=""){
				if(start_date>end_date){
					alert("开始时间不能大于结束时间")
					return;
				}
			}
			var resexp=/^(([1-9]\d*)|0)$/
			if(resexp.test(cost)==false){
				alert("开销只能为正整数")
				return;
			}

			$.ajax({
				url:"workbench/activity/create.do",
				dataType:"json",
				type:"post",
				data:{
					owner:owner,
					name:name,
					start_date:start_date,
					end_date:end_date,
					cost:cost,
					description:message
				},
				success:function (resp){
						if(resp.code==1){
							alert(resp.message)
							$("#createActivityModal").modal("hide")
							reloadac(1,$("#demo_pag1").bs_pagination('getOption', 'rowsPerPage'))
						}else {
							alert(resp.message)
							$("#createActivityModal").modal("show")
						}

				}
			})
		})

		//加载完成后加载市场活动记录
		reloadac(1,10)
		//单机查询按钮后执行加载市场活动方法
		$("#chaxunbt").click(function (){
			reloadac(1,$("#demo_pag1").bs_pagination('getOption', 'rowsPerPage'))
		})


	});

	// 加载市场活动记录
	function reloadac(startflg,pagecout){
		var name=$("#query-name").val()
		var owner=$("#query-owner").val()
		var startdate=$("#query-startdate").val()
		var enddate=$("#query-enddate").val()
		$.ajax({
			url:"workbench/activity/chaxunac",
			type:"post",
			data:{
				name:name,
				owner:owner,
				start_date:startdate,
				end_date:enddate,
				start_flg:startflg,
				pageflg:pagecout
			},
			success:function (resp){
				//显示总条数
				//$("#ctB").text(resp.counts)

				var activityhtml=""
				$.each(resp.activityList,function (i,p){
					activityhtml+="<tr class=\"active\">--%>"
					activityhtml+="<td><input type=\"checkbox\" value=\""+p.id+"\"/></td>"
					activityhtml+="<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='workbench/activity/detailActivity.do?id="+p.id+"';\">"+p.name+"</a></td>"
					activityhtml+="<td>"+p.owner+"</td>"
					activityhtml+="<td>"+p.start_date+"</td>"
					activityhtml+="<td>"+p.end_date+"</td>"
					activityhtml+="</tr>"
				})
				$("#listB").html(activityhtml)

				//取消全选按钮
				$("#quanxuan").prop("checked",false)

				//计算总页数
				var zonyeshu=1
				//判断总条数除于每页条数是否可以整除
				if(resp.counts%pagecout==0){
					zonyeshu=resp.counts/pagecout
				}else {
					//将小数转换成整数
					zonyeshu=parseInt(resp.counts/pagecout)+1
				}
				//调用分页工具函数
				//当页面加载完成后创建分页插件
				$("#demo_pag1").bs_pagination({
					totalPages:zonyeshu,
					currentPage: startflg,
					rowsPerPage: pagecout,
					totalRows: resp.counts,
					visiblePageLinks: 5,//一组可以显示多少个卡片


					showGoToPage: true,//是否显示“跳转到”卡片
					showRowsPerPage: true,//是否显示“每页显示条数”
					showRowsInfo: true,//是否显示记录的信息
					//当用户切换页号，都会执行该函数
					onChangePage: function(event,pageObj) {
						// returns page_num and rows_per_page after a link has clicked
						// alert(pageObj.currentPage)//获取切换后的页号
						// alert(pageObj.rowsPerPage)//获取切换后的条数
						reloadac(pageObj.currentPage,pageObj.rowsPerPage)
					},
				});
			}
		})
	}
	
</script>
</head>
<body>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal"  role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form id="createac" class="form-horizontal" role="form">
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-marketActivityOwner">
								  <c:forEach items="${requestScope.userlist}" var="u">
									  <option value="${u.id}">${u.name}</option>
								  </c:forEach>
								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-marketActivityName">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control dateclose" id="create-startTime" readonly>
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control dateclose" id="create-endTime" readonly>
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-describe"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="savebt">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
						<input type="hidden" id="edit-id"/>
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">
									<c:forEach items="${requestScope.userlist}" var="u">
										<option value="${u.id}">${u.name}</option>
									</c:forEach>
								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-startTime" value="2020-10-10">
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-endTime" value="2020-10-20">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" value="5,000">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"  id="saveactivity"> 更新</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 导入市场活动的模态窗口 -->
    <div class="modal fade" id="importActivityModal" role="dialog">
        <div class="modal-dialog" role="document" style="width: 85%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
                </div>
                <div class="modal-body" style="height: 350px;">
                    <div style="position: relative;top: 20px; left: 50px;">
                        请选择要上传的文件：<small style="color: gray;">[仅支持.xls]</small>
                    </div>
                    <div style="position: relative;top: 40px; left: 50px;">
                        <input type="file" id="activityFile">
                    </div>
                    <div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;" >
                        <h3>重要提示</h3>
                        <ul>
                            <li>操作仅针对Excel，仅支持后缀名为XLS的文件。</li>
                            <li>给定文件的第一行将视为字段名。</li>
                            <li>请确认您的文件大小不超过5MB。</li>
                            <li>日期值以文本形式保存，必须符合yyyy-MM-dd格式。</li>
                            <li>日期时间以文本形式保存，必须符合yyyy-MM-dd HH:mm:ss的格式。</li>
                            <li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
                            <li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button id="importActivityBtn" type="button" class="btn btn-primary">导入</button>
                </div>
            </div>
        </div>
    </div>
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="query-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="query-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control" type="text" id="query-startdate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" id="query-enddate">
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="chaxunbt">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="createbt"  data-target="#createActivityModal"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#editActivityModal" id="xiugaibt"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="actdel"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
                    <button type="button" class="btn btn-default"  data-toggle="modal" data-target="#importActivityModal" ><span class="glyphicon glyphicon-import"></span> 上传列表数据（导入）</button>
                    <button id="exportActivityAllBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表数据（批量导出）</button>
                    <button id="exportActivityXzBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表数据（选择导出）</button>
                </div>
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="quanxuan"/></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="listB">
<%--						<tr class="active">--%>
<%--							<td><input type="checkbox" /></td>--%>
<%--							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>--%>
<%--                            <td>zhangsan</td>--%>
<%--							<td>2020-10-10</td>--%>
<%--							<td>2020-10-20</td>--%>
<%--						</tr>--%>
<%--                        <tr class="active">--%>
<%--                            <td><input type="checkbox" /></td>--%>
<%--                            <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>--%>
<%--                            <td>zhangsan</td>--%>
<%--                            <td>2020-10-10</td>--%>
<%--                            <td>2020-10-20</td>--%>
<%--                        </tr>--%>
					</tbody>
				</table>
				<div id="demo_pag1"></div>
			</div>

			
<%--			<div style="height: 50px; position: relative;top: 30px;">--%>
<%--				<div>--%>
<%--					<button type="button" class="btn btn-default" style="cursor: default;">共<b id="ctB">50</b>条记录</button>--%>
<%--				</div>--%>
<%--				<div class="btn-group" style="position: relative;top: -34px; left: 110px;">--%>
<%--					<button type="button" class="btn btn-default" style="cursor: default;">显示</button>--%>
<%--					<div class="btn-group">--%>
<%--						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">--%>
<%--							10--%>
<%--							<span class="caret"></span>--%>
<%--						</button>--%>
<%--						<ul class="dropdown-menu" role="menu">--%>
<%--							<li><a href="#">20</a></li>--%>
<%--							<li><a href="#">30</a></li>--%>
<%--						</ul>--%>
<%--					</div>--%>
<%--					<button type="button" class="btn btn-default" style="cursor: default;">条/页</button>--%>
<%--				</div>--%>
<%--				<div style="position: relative;top: -88px; left: 285px;">--%>
<%--					<nav>--%>
<%--						<ul class="pagination">--%>
<%--							<li class="disabled"><a href="#">首页</a></li>--%>
<%--							<li class="disabled"><a href="#">上一页</a></li>--%>
<%--							<li class="active"><a href="#">1</a></li>--%>
<%--							<li><a href="#">2</a></li>--%>
<%--							<li><a href="#">3</a></li>--%>
<%--							<li><a href="#">4</a></li>--%>
<%--							<li><a href="#">5</a></li>--%>
<%--							<li><a href="#">下一页</a></li>--%>
<%--							<li class="disabled"><a href="#">末页</a></li>--%>
<%--						</ul>--%>
<%--					</nav>--%>
<%--				</div>--%>
<%--			</div>--%>

		</div>
		
	</div>
</body>
</html>