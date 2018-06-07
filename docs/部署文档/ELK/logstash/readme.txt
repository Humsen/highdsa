使用版本：V2.4.1 或 V5.6.9

第一步，解压logstash.tar.gz 到/home/husen目录下。文件夹名称不变，仍然为Logstash+版本号
第二步，将config文件夹和service-logstash-版本号.sh文件复制到logstash同级目录下
第三步，给depoly脚本加执行权限，然后执行./logstash-5.6.9-depoly.sh start即可后台启动。

脚本执行参数详情见脚本，有start、stop、resatrt三种