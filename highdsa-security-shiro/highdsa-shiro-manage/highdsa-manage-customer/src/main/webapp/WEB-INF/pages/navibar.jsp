<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
</head>

<body>
	<ul id="menunav" class="nav nav-tabs nav-stacked">
		<li class="active"><a href="#"><i class="glyphicon glyphicon-th-large"></i> 菜单栏</a></li>
		<c:forEach items="${navibar}" var="navi" varStatus="status">
			<li><a href="#navi${status.count}" class="nav-header collapsed" data-toggle="collapse" aria-expanded="false"><i class="glyphicon glyphicon-cog"></i>
					${navi.navigationName} <span class="pull-right glyphicon glyphicon-chevron-down"></span>
			</a>
				 <%
				 	String in = "";
				 	String userListShow = String.valueOf(session.getAttribute("user-list"));
				 
				 	if(userListShow != null && userListShow != ""){
				 		in = "in";
				 	}else{
				 		in = "";
				 	}
				 %>
				<ul id="navi${status.count}" class="nav-list collapse <%=in%>">
					<c:forEach items="${navi.childNavigations }" var="perm">
						<li><a href="${pageContext.request.contextPath}${perm.permissionUrl}.html"><i class="glyphicon glyphicon-user"></i> ${perm.permissionDesc}</a></li>
					</c:forEach>
				</ul></li>
		</c:forEach>
	</ul>
</body>
</html>