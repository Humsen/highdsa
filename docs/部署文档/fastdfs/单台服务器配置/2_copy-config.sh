#!/bin/sh

#配置tracker
cp /mnt/fastdfs/config/tracker.conf /etc/fdfs/tracker.conf
mkdir -p /home/husen/fastdfs/tracker/
	
#配置storage
cp /mnt/fastdfs/config/storage.conf /etc/fdfs/storage.conf
mkdir -p /home/husen/fastdfs/storage
	
#配置client
cp /mnt/fastdfs/config/client.conf /etc/fdfs/client.conf
mkdir -p /home/husen/fastdfs/client