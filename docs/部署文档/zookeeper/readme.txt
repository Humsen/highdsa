使用版本：V3.4.11

1、解压，修改配置文件zoo.cfg，将配置文件zoo.cfg复制到 $ZOOKEEPER_HOME/conf 目录下	

2、给脚本加执行权限，执行./zookeeper-deploy.sh start

3、开机启动
	注意：可能要给rc.local加权限 chmod +x /etc/rc.local

	vi /etc/rc.local 

	# java environment
	JAVA_HOME=/home/jdk/jdk1.8.0_151
	JRE_HOME=/home/jdk/jdk1.8.0_151/jre
	CLASS_PATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib
	PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin
	export JAVA_HOME JRE_HOME CLASS_PATH PATH

	/home/husen/zookeeper/zookeeper-deploy.sh start