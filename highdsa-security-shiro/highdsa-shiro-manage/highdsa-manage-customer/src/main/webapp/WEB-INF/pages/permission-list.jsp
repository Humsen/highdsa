<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%session.setAttribute("user-list",true);%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>用户管理</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css" rel="stylesheet">
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="header.jsp" />

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="navibar.jsp" />
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">权限列表</h1>

				<div class="row placeholders">
					<div>
						<button type="button" class="btn btn-warning delete-query" data-toggle="modal" data-target="#delete-confirm-dialog">删除所选</button>
						<!--  删除所选对话框 -->
						<div class="modal fade " id="delete-confirm-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-sm" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title">警告</h4>
									</div>
									<div class="modal-body">确认删除所选权限吗</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary delete-selected-confirm">确认</button>
									</div>
								</div>
							</div>
						</div>
						<button type="button" class="btn btn-default show-add-form" data-toggle="modal" data-target="#perm-form-div">添加新权限</button>
						<!--添加新权限表单-->
						<div class="modal fade " id="perm-form-div" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="perm-form-title"></h4>
									</div>
									<div class="modal-body">
										<form class="perm-form">
											<input type="hidden" name="permissionId" class="form-control" id="permIdInput">
											<div class="form-group">
												<label for="descInput">权限名称</label> <input type="text" name="permissionName" class="form-control" id="nameInput" placeholder="权限名称">
											</div>
											<div class="form-group">
												<label for="descInput">描述</label> <input type="text" name="permissionDesc" class="form-control" id="descInput" placeholder="权限描述">
											</div>
											<div class="form-group">
												<label for="urlInput">URL</label> <input type="text" name="permissionUrl" class="form-control" id="urlInput" placeholder="URL">
											</div>
											<div class="checkbox">
												<label> <input type="checkbox" name="permissionNavi" value="1">是否导航结点
												</label>
											</div>
											<div class="form-group">
												<label for="codeInput">权限代码</label> <input type="text" name="permissionCode" class="form-control" id="codeInput" placeholder="权限代码">
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary perm-submit"></button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="space-div"></div>
					<table class="table table-hover table-bordered perm-list">
						<tr>
							<td><input type="checkbox" class="select-all-btn" /></td>
							<td>ID</td>
							<td>描述</td>
							<td>URL</td>
							<td>是否导航结点</td>
							<td>权限代码</td>
							<td>操作</td>
						</tr>
						<!-- <tr>
                        <td><input type="checkbox" name="permIds"/></td>
                        <td class="permid">11</td>
                        <td>增加</td>
                        <td>/add</td>
                        <td>是</td>
                        <td>add</td>
                        <td><a class="glyphicon glyphicon-pencil show-perminfo-form" aria-hidden="true" title="修改权限信息" href="javascript:void(0);" data-toggle="modal" data-target="#perm-form"></a>
                    	<a class="glyphicon glyphicon-remove delete-this-perm" aria-hidden="true" title="删除权限" href="javascript:void(0);"></a></td>
                    </tr> -->
						<c:forEach items="${perms }" var="perm">
							<tr>
								<td><input type="checkbox" name="permIds" value="${perm.permissionId }" /></td>
								<td class="permid">${perm.permissionId }</td>
								<td>${perm.permissionDesc }</td>
								<td>${perm.permissionUrl }</td>
								<td>${perm.permissionNavi }</td>
								<td>${perm.permissionCode }</td>
								<td><a class="glyphicon glyphicon-pencil show-perminfo-form" aria-hidden="true" title="修改权限信息" href="javascript:void(0);"
									data-toggle="modal" data-target="#perm-form-div"></a> <a class="glyphicon glyphicon-remove delete-this-perm" aria-hidden="true" title="删除权限"
									href="javascript:void(0);"></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- 提示框 -->
	<div class="modal fade" id="op-tips-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">提示信息</h4>
				</div>
				<div class="modal-body" id="op-tips-content"></div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<script>
		function showTips(contents) {
			$("#op-tips-content").html(contents);
			$("#op-tips-dialog").modal("show");
		}
		function resetPermForm(title, button) {
			$(".perm-form input[type='text']").val("");
			$(".perm-form input[type='checkbox']").prop("checked", false);

			$(".perm-form-title").html(title);
			$(".perm-submit").html(button);
		}
		$(".select-all-btn").change(function() {
			if ($(this).is(":checked")) {//$(this).prop("checked")
				$(".perm-list input[type='checkbox']:gt(0)").prop("checked", true);
			} else {
				$(".perm-list input[type='checkbox']:gt(0)").prop("checked", false);
			}
		});
		$(".delete-selected-confirm").click(function() {
			$("#delete-confirm-dialog").modal("hide");
			var cbs = $("input[name='permIds']:checked")
			if (cbs.length === 0) {
				showTips("没有选中任意项！");
			} else {
				var permIds = new Array();
				$.each(cbs, function(i, cb) {
					permIds[i] = cb.value;
				});
				//请求删除所选角色
				$.ajax({
					url : "deletemore.html",
					data : {
						permIds : permIds
					},
					type : "POST",
					traditional : true,
					success : function() {
						cbs.parent().parent().remove();
						showTips("删除所选成功！");
					}
				});
			}
		});
		$(".show-add-form").click(function() {
			resetPermForm("添加新权限", "添加");
		});
		$(".perm-list").on("click", ".show-perminfo-form", function() {
			resetPermForm("更新权限信息", "更新");

			var permId = $(this).parents("tr").find(".permid").html();
			$.ajax({
				url : "getperm.html",
				data : {
					permId : permId
				},
				type : "POST",
				dataType : "json",
				success : function(data) {
					$(".perm-form input[name='permissionId']").val(permId);
					$(".perm-form input[name='permissionName']").val(data.permissionName);
					$(".perm-form input[name='permissionDesc']").val(data.permissionDesc);
					$(".perm-form input[name='permissionUrl']").val(data.permissionUrl);
					if (data.permissionNavi === 1) {
						$(".perm-form input[type='checkbox'][name='permissionNavi']").prop("checked", true);
					}
					$(".perm-form input[name='permissionCode']").val(data.permissionCode);
				}
			});
		});
		$(".perm-submit")
				.click(
						function() {
							if ($(this).html() === "添加") {
								//请求添加新权限
								$
										.ajax({
											url : "add.html",
											type : "POST",
											data : $(".perm-form").serialize(),
											dataType : "json",
											success : function(data) {
												$("#perm-form-div").modal("hide");
												showTips("添加成功！");

												var newTr = '<tr>'
														+ '<td><input type="checkbox" name="permIds" value="'+data.permissionId+'"/></td>'
														+ '<td class="permid">'
														+ data.permissionId
														+ '</td>'
														+ '<td>'
														+ data.permissionDesc
														+ '</td>'
														+ '<td>'
														+ data.permissionUrl
														+ '</td>'
														+ '<td>'
														+ data.permissionNavi
														+ '</td>'
														+ '<td>'
														+ data.permissionCode
														+ '</td>'
														+ '<td><a class="glyphicon glyphicon-pencil show-perminfo-form" aria-hidden="true" title="修改权限信息" href="javascript:void(0);" data-toggle="modal" data-target="#perm-form-div"></a> '
														+ '<a class="glyphicon glyphicon-remove delete-this-perm" aria-hidden="true" title="删除权限" href="javascript:void(0);"></a></td>' + '</tr>';
												$(".perm-list tr").eq(0).after(newTr);
											}
										});
							} else {
								//请求更新权限
								$.ajax({
									url : "update.html",
									data : $(".perm-form").serialize(),
									type : "POST",
									success : function() {
										$("#perm-form-div").modal("hide");
										showTips("更新成功！");
									}
								});
							}
						});
		$(".perm-list").on("click", ".delete-this-perm", function() {
			var permTr = $(this).parents("tr");
			var permId = permTr.find(".permid").html();
			if (confirm('确认删除ID为"' + permId + '"的角色吗？')) {
				//请求删除该权限
				$.ajax({
					url : "delete.html",
					data : {
						permId : permId
					},
					type : "POST",
					success : function() {
						permTr.remove();
						showTips("删除成功！");
					}
				});
			}
		});
	</script>
</body>
</html>
