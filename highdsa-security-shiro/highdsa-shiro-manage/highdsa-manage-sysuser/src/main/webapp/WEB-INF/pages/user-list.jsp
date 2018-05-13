<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>用户管理 - 用户列表</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/static/css/layout.css" rel="stylesheet">
  </head>

  <body>

    <!-- 头部 -->
    <jsp:include page="header.jsp"/>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <jsp:include page="navibar.jsp"/>
         </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">用户列表</h1>
          <div class="row placeholders">
          	<div>
                <button type="button" class="btn btn-warning delete-query" data-toggle="modal" data-target="#delete-confirm-dialog">删除所选</button>
                <!--  删除所选对话框 -->
                <div class="modal fade " id="delete-confirm-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" >警告</h4>
                      </div>
                      <div class="modal-body">
                        确认删除所选用户吗
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary delete-selected-confirm">确认</button>
                      </div>
                    </div>
                  </div>
                </div>
                <button type="button" class="btn btn-primary show-user-form" data-toggle="modal" data-target="#add-user-form">添加新用户</button>
                <!--添加新用户表单-->
                <div class="modal fade " id="add-user-form" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" >添加新用户</h4>
                      </div>
                      <div class="modal-body">
                      	<form class="user-form">
                          <div class="form-group">
                            <label for="userNameInput">用户名</label>
                            <input type="text" name="userName" class="form-control" id="userNameInput" placeholder="用户名">
                          </div>
                          <div class="form-group">
                            <label for="passwordInput">密码</label>
                            <input type="password" name="userPassword" class="form-control" id="passwordInput" placeholder="密码">
                          </div>
                          <label>角色选择</label>
                          <div class="roles-div">
                          </div>
                        </form>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary add-user-submit">添加 </button>
                      </div>
                    </div>
                  </div>
                </div>
            </div>
            <div class="space-div"></div>
            <table class="table table-hover table-bordered user-list">
            	<tr>
                	<td><input type="checkbox" class="select-all-btn"/></td>
                    <td>ID</td>
                    <td>用户名</td>
                    <td>拥有角色</td>
                    <td>操作</td>
                </tr>
                <!--  <tr>
                	<td><input type="checkbox" name="userIds" value="11"/></td>
                    <td class="userid">1</td>
                    <td class="username">sisu</td>
                    <td><a href="javascript:void(0);" class="show-user-roles" >查看所有角色</a></td>
                    <td>
                    	<a class="glyphicon glyphicon-wrench show-userrole-form" aria-hidden="true" title="修改所有角色" href="javascript:void(0);" data-toggle="modal" data-target="#update-userrole-dialog"></a>
                    	<a class="glyphicon glyphicon-remove delete-this-user" aria-hidden="true" title="删除用户" href="javascript:void(0);"></a>
                    </td>
                </tr>-->
                <c:forEach items="${users }" var="user">
                	<tr>
                		<td><input type="checkbox" name="userIds" value="${user.userId }"/></td>
                		<td class="userid">${user.userId }</td>
                		<td class="username">${user.userName }</td>
                		<td><a href="javascript:void(0);" class="show-user-roles" >显示所有角色</a></td>
	                    <td>
	                    	<a class="glyphicon glyphicon-wrench show-userrole-form" aria-hidden="true" title="修改所有角色" href="javascript:void(0);" data-toggle="modal" data-target="#update-userrole-dialog"></a>
	                    	<a class="glyphicon glyphicon-remove delete-this-user" aria-hidden="true" title="删除用户" href="javascript:void(0);"></a>
	                    </td>
                	</tr>
                </c:forEach>
            </table>
            <!--修改用户角色表单-->
            <div class="modal fade " id="update-userrole-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" >修改用户角色</h4>
                      </div>
                      <div class="modal-body">
                      	<form class="update-userrole-form">
                        	<input name="userId" type="hidden"/>
                        	<div class="roles-div"></div>
                        </form>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary update-userrole-submit">提交 </button>
                      </div>
                    </div>
                  </div>
            </div>
          </div>          
        </div>
    </div>
    <!-- 提示框 -->
	<div class="modal fade" id="op-tips-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
      <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
        	<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >提示信息</h4>
          </div>
          <div class="modal-body" id="op-tips-content">
          </div>
        </div>
      </div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script>
    	function getAllRoles(obj){
        	obj.html("");
    		$.ajax({
				url:"listRoles.html",
				type:"POST",
				dataType:"json",
				success:function(data){
					for(var i in data){
						obj.append("<input type='checkbox' name='roleIds' value='"+
								data[i].roleId+"'/>"+data[i].roleName+":"+data[i].roleDesc);
						obj.append("<br/>");
					}
				}
			});
       	}
       	function showTips(content){
       		$("#op-tips-content").html(content);
			$("#op-tips-dialog").modal("show");
       	}
    	$(".select-all-btn").change(function(){
			if($(this).is(":checked")){//$(this).prop("checked")
				$(".user-list input[type='checkbox']:gt(0)").prop("checked",true);
			}else{
				$(".user-list input[type='checkbox']:gt(0)").prop("checked",false);
			}
		});
		$(".delete-selected-confirm").click(function(){
			$("#delete-confirm-dialog").modal("hide");
			var cbs=$("input[name='userIds']:checked")
			if(cbs.length===0){
				showTips("没有选中任意项！");
			}else{
				var userids=new Array();
				$.each(cbs,function(i,cb){
					userids[i]=cb.value;
				});
				//请求删除所选用户
				$.ajax({
					url:"deletemore.html",
					data:{userIds:userids},
					type:"POST",
					traditional:true,
					success:function(){
						cbs.parent().parent().remove();
						showTips("删除所选成功！");
					}
				});
			}
		});
		$(".show-user-form").click(function(){
			getAllRoles($(".user-form .roles-div"));
		});
		$(".add-user-submit").click(function(){
			//请求添加新用户
			$.ajax({
				url:"add.html",
				data:$(".user-form").serialize(),
				type:"POST",
				dataType:"json",
				success:function(data){
					$("#add-user-form").modal("hide");
					showTips("添加成功！");

					var newTr="<tr><td><input type='checkbox' name='userIds' value="+data.userId+"/></td>"+
                		"<td class='userid'>"+data.userId+"</td>"+
                		"<td class='username'>"+data.userName+"</td>"+
                		"<td><a href='javascript:void(0);' class='show-user-roles' >显示所有角色</a></td>"+
	                    "<td>"+
	                    	"<a class='glyphicon glyphicon-wrench show-userrole-form' aria-hidden='true' title='修改所有角色' href='javascript:void(0);' data-toggle='modal' data-target='#update-userrole-dialog'></a> "+
	                    	"<a class='glyphicon glyphicon-remove delete-this-user' aria-hidden='true' title='删除用户' href='javascript:void(0);'></a>"+
	                    "</td></tr>";
					$(".user-list tr").eq(0).after(newTr);
				}
			});
		});
		function getRolesByUserName(username,doSuccess){
			$.ajax({
				url:"showroles.html",
				data:{userName:username},
				type:"POST",
				dataType:"json",
				success:function(data){
					doSuccess(data);
				}
			});
		}
		$(".user-list").on("click",".show-user-roles",function(){
			var username=$(this).parents("tr").find(".username").html();
			var urTd=$(this).parent();
			//请求查看用户角色
			/*$.ajax({
				url:"showroles.html",
				data:{userName:username},
				type:"POST",
				dataType:"json",
				success:function(data){
					for(var i in data){
						var role=data[i].roleName+":"+data[i].roleDesc+"<br/>";
						urObj.append(role);
					}
				}
			});*/
			getRolesByUserName(username,function(data){
				urTd.html("");
				for(var i in data){
					var role=data[i].roleName+":"+data[i].roleDesc+"<br/>";
					urTd.append(role);
				}
			});
		});
		$(".user-list").on("click",".show-userrole-form",function(){
			var userid=$(this).parents("tr").find(".userid").html();
			var username=$(this).parents("tr").find(".username").html();
			$(".update-userrole-form input[name='userId']").val(userid);
			//获取当前用户当前角色
			getAllRoles($(".update-userrole-form .roles-div"));
			getRolesByUserName(username,function(data){
				for(var i in data){
					$('.update-userrole-form input[name="roleIds"][value="'+data[i].roleId+'"]').prop("checked",true);
				}
			});
		});
		//确认修改用户角色
		$(".update-userrole-submit").click(function(){
			$.ajax({
				url:"corelationRole.html",
				data:$(".update-userrole-form").serialize(),
				type:"POST",
				success:function(){
					$("#update-userrole-dialog").modal("hide");
					showTips("更新成功！");
				}
			});
		});
		$(".user-list").on("click",".delete-this-user",function(){
			var userTr=$(this).parents("tr");
			var userid=userTr.find(".userid").html();
			if(confirm('确认删除ID为"'+userid+'"的用户吗？')){
				//请求删除该用户
				$.ajax({
					url:"delete.html",
					data:{userId:userid},
					type:"POST",
					success:function(){
						userTr.remove();
						showTips("删除成功！");
					}
				});
			}
		});
    </script>
  </body>
</html>