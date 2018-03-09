## JavaMail 保存邮件到”已发送”需要的jar包
***
### 文档说明
**这个是jdk导致的，jdk里面有一个jce的包，安全性机制导致的访问https会报错，官网上有替代的jar包，换掉就好了**   

将此目录下两个Jar包放到
目录 %JAVA_HOME%\jre\lib\security里的local_policy.jar,US_export_policy.jar（JDK8有可能没有这两个Jar包）

本地部署 :
	好像用java -jar ×.jar运行时，使用的是jre，所以jre（不是JDK下的那个，独立的那个）相应的目录下也要放这两个jar
	
***
### 文件说明	 
> 保存邮件到”已发送”需要的与安全机制相关的jar包

***