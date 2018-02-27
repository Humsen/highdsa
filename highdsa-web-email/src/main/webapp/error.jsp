<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isErrorPage="true"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="s_main_wrap s_no_top nicescroll">
		<div class="s_main_fixed_bg"></div>
		<div class="s_main">

			<div class="s_header_title">
				<span class="s_header_left"> <a href="#"
					onclick=(history.back())><i class="icon iconfont"></i></a>
				</span> <span class="s_f18">错误消息</span>
			</div>
			<!-- end header title -->

			<h1>抱 歉！</h1>
			<div style="padding: 2% 0; text-indent: 2em;">尊敬的用户：我们致力于提供更好的服务，但人算不如天算，有些错误发生了，希望是在控制的范围内……如果问题重复出现，请向系统管理员反馈。</div>
			<%-- 错误详情：<%=exception.getMessage()%> --%>
		</div>
	</div>
	<!-- end main -->
</body>
</html>
