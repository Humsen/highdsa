使用版本：V5.15.3

1、tar -zxvf apache-activemq-5.15.3-bin.tar.gz
2、开启端口
	su
	firewall-cmd --zone=public --add-port=61616/tcp --permanent
	firewall-cmd --zone=public --add-port=8161/tcp --permanent
	firewall-cmd --reload
	检查：firewall-cmd --zone=public --list-ports

3、复制配置文件到/conf目录下
4、可选（自启），后期制作服务
	vi /etc/rc.local
	/home/husen/activemq/apache-activemq-5.15.3/bin/activemq start

5、启动
	./bin/activemq start


url：http://192.168.186.11:8161/