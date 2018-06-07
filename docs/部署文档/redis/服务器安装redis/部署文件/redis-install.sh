#!/bin/sh

## -------------------- description --------------------
## redis 自动安装脚本,需要在root下执行
## -----------------------------------------------------

FILE_NAME=redis-4.0.8
PACKAGE_NAME=$FILE_NAME.tar.gz 

#安装
tar -zxvf $PACKAGE_NAME
cd $FILE_NAME
make MALLOC=libc
make test
make install

#启动
cd ..
cp -f redis.conf $FILE_NAME/redis.conf
#$FILE_NAME/src/redis-server $FILE_NAME/redis.conf

#配置开机启动
cp -f redis-server.service /etc/systemd/system/redis-server.service
systemctl daemon-reload 
systemctl start redis-server.service 
systemctl enable redis-server.service

#创建客户端命令软连接
ln -s /home/husen/redis/redis-4.0.8/src/redis-cli /usr/bin/redis