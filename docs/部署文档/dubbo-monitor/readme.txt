使用版本：V2.6.0

1、上传解压
2、复制配置文件到dubbo-monitor-simple-2.6.0/conf目录下
3、复制start.sh到 /bin/start.sh
	注意：dubbo monitor需要修改start.sh的内存分配-Xmx2g -Xms2g -Xmn256m
4、开启自启
	vi /etc/rc.local
	/home/husen/dubbo/monitor/dubbo-monitor-simple-2.6.0/bin/start.sh