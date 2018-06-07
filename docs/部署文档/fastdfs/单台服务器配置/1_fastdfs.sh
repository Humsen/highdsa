#!/bin/sh

#变量定义
STORE_DIR=/opt/fastdfs #目标存放目录

#安装依赖
yum -y install make cmake gcc gcc-c++
yum -y install zip unzip
yum -y install perl

#安装libfatscommon
cd /opt/
mkdir -p fastdfs
cd fastdfs
unzip /mnt/fastdfs/libfastcommon-master.zip -d $STORE_DIR
cd libfastcommon-master/
./make.sh
./make.sh install

#安装FastDFS
cd ..
unzip /mnt/fastdfs/fastdfs-master.zip
cd fastdfs-master/
./make.sh
./make.sh install