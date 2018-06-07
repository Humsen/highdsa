#!/bin/sh

# ------------------------
# 安装fastdfs-nginx-module
# ------------------------

#变量定义
ORIGIN_DIR=/mnt/fastdfs #上传压缩包的源目录
STORE_DIR=/opt/fastdfs #解压存放的目录
AIMS_DIR=/home/husen/fastdfs #目标安装目录

cd $STORE_DIR
unzip $ORIGIN_DIR/fastdfs-nginx-module-master.zip 
tar -zxvf $ORIGIN_DIR/nginx-1.12.2.tar.gz

### 安装nginx

#安装依赖
yum -y install gcc pcre pcre-devel zlib zlib-devel openssl openssl-devel 

#配置依赖
cd nginx-1.12.2/
./configure --prefix=/home/husen/nginx --sbin-path=/usr/bin/nginx --add-module=$STORE_DIR/fastdfs-nginx-module-master/src

#编译安装
make && make install 
	
#增加配置文件
cp $ORIGIN_DIR/config/mod_fastdfs.conf /etc/fdfs/mod_fastdfs.conf
cd $STORE_DIR/fastdfs-master/conf/
cp http.conf mime.types /etc/fdfs/

#启动 自动创建目录
/etc/init.d/fdfs_trackerd start
echo "-------------  tracker 启动成功"

#启动nginx
cp $ORIGIN_DIR/config/nginx_tracker.conf /home/husen/nginx/conf/nginx.conf
nginx

echo "-------------  nginx 启动成功"
echo "-------------  安装完成  -------------------"