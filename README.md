# **highdsa** -- 分布式系统架构
基于Dubbo、Spring MVC、ELK、ActiveMQ、Redis、FastDFS、Nginx等的一套高可用、高性能、高可扩展的分布式系统架构。

#### 主要工程结构如下

> `highdsa` -> 工程文件，所有模块的父模块
>> `docs` -> 说明文档，包括开发规范和部署内容  
>> `highdsa-api` -> 接口模块，dubbo的所有接口和其他普通接口  
>> `highdsa-common` -> 公共模块最终jar包，引入所有通用类jar包，以jar包形式存在  
>> `highdsa-common-parent` -> 公共模块父模块，所有公共模块的父模块
>>> `highdsa-common-exception` 异常体系公共模块  
>>> `highdsa-common-log4j2` 日志公共模块  
>>> `highdsa-common-utility` 使用工具公共模块  
>>
>> `highdsa-service-email` -> 邮件服务提供者模块  
>> `highdsa-service-fastdfs` -> 文件存储服务提供者模块  
>> `highdsa-service-redis` -> redis缓存服务提供者模块  
>> `highdsa-web-email` -> 邮件消费者模块，对外提供RESTful API  
>> `highdsa-web-fastdfs` -> 文件存储消费者模块，对外提供RESTful API  
>> `highdsa-web-test` -> 消费者测试模块，以web形式整合所有RESTful API，方便测试  
