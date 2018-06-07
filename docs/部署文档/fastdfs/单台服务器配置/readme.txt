说明：
	这两个脚本执行完成之后，可以使用
	/etc/init.d/fdfs_storaged start
	/etc/init.d/fdfs_trackerd start
	/usr/bin/fdfs_upload_file  /etc/fdfs/client.conf  [file name]
	这三个命令分别启动storage、trackerd和client来上传、下载文件
	启动之后也可以只用java client操作

1、切换到 root 用户(需要使用root账户安装三方库)

2、root 创建/mnt/fastdfs目录,并赋予 777权限

3、将config复制到/mnt/fastdfs 目录下，修改storage.conf 118行的ip以及其他ip
   将此目录上一级目录的安装包文件下的4个文件复制到/mnt/fastdfs目录下

4、将两个脚本复制到/mnt/fastdfs 目录下，赋予脚本执行权限
	chmod +x 1_fastdfs.sh 2_copy-config.sh 3_fastdfs-nginx-module.sh

5、注意手动打开防火墙端口 t:22122  s:23000 8888 以及nginx开放的端口80等
	firewall-cmd --zone=public --add-port=80/tcp --permanent 
	firewall-cmd --reload
	firewall-cmd --zone=public --list-ports

6、执行脚本 