<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
</head>

<body>
	<c:forEach items="${navibar}" var="navi" varStatus="status">
		<div class="list-group">
			<a href="javascript:void(0);" class="list-group-item active"
				type="button" data-toggle="collapse"
				data-target="#navi${status.count}" aria-expanded="false"
				aria-controls="navi${status.count}"> ${navi.navigationName } </a>
			<div class="list-group collapse" id="navi${status.count}">
				<c:forEach items="${navi.childNavigations }" var="perm">
					<a
						href="${pageContext.request.contextPath}${perm.permissionUrl}.html"
						class="list-group-item">${perm.permissionDesc }</a>
				</c:forEach>
			</div>
		</div>
	</c:forEach>
</body>
</html>