1、解压，在$TOMCAT_HOME/bin/catalina.sh最前面加一句
	
	if [[ "$JAVA_OPTS" != *-Djava.security.egd=* ]]; then
		JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/urandom"
	fi

2、删除webapps自带的web应用
3、开机启动
	修改配置文件里的toomcat8的JAVA_HOME和CATALANA_HOME变量，然后放到 /etc/init.d目录下
	chmod 755 tomcat8 #赋权限
	service tomcat8 start #启动检查
	chkconfig tomcat8 on 