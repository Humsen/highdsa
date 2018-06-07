1、切换到 root 用户(需要使用root账户安装三方库)

2、root 创建/mnt/fastdfs目录,并赋予 777权限

3、将此目录上一级目录的安装包文件下的4个文件复制到/mnt/fastdfs目录下
   将config复制到/mnt/fastdfs 目录下，修改mod_fastdfs 40行的tracker_server其他ip	

4、赋予脚本执行权限

5、注意手动打开防火墙端口 t:22122  s:23000 8888 以及nginx开放的端口80等
	firewall-cmd --zone=public --add-port=80/tcp --permanent 
	firewall-cmd --reload
	firewall-cmd --zone=public --list-ports

6、执行三个脚本
	1、2和4