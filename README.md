# **highdsa** -- 分布式系统架构
基于Spring、Dubbo、Spring MVC、ELK、ActiveMQ、Redis、FastDFS、Nginx、Zookeeper、RESTful、Maven、Mybatis、Druid等的一套高可用、高性能、高可扩展的分布式系统架构。

***
 
### 主要工程结构如下

``` lua
──highdsa -> 工程文件，所有模块的父模块
    │  README.md
    │ 
    ├─docs -> 说明文档，包括开发规范和部署内容
    │  │  RESTful API规范.txt
    │  │  前端命名规范.txt
    │  │  后端开发规范.txt
    │  │  
    │  ├─fastdfs
    │  │      readme.md
    │  │      
    │  ├─Java-mail
    │  │      readme.md
    │  │      
    │  ├─log4j
    │  │      readme.md
    │  │      
    │  ├─mybatis-generator
    │  │      generator-mysql.properties
    │  │      generatorConfig.xml
    │  │      readme.md
    │  │      
    │  └─sql
    ├─highdsa-api-dubbo -> 接口模块，dubbo的所有接口和其他普通接口 
    ├─highdsa-common -> 公共模块最终jar包，引入所有通用类jar包，以jar包形式存在
    ├─highdsa-common-parent -> 公共模块父模块，所有公共模块的父pom模块 
    │  ├─highdsa-common-entity -> POJO类,PO类与数据库对应,VO类与RESTful对应
    │  ├─highdsa-common-exception -> 异常体系公共模块 
    │  ├─highdsa-common-log4j2 -> 日志公共模块
    │  └─highdsa-common-utility -> 通用工具公共模块
    ├─highdsa-restful-app -> 移动端app后台模块，整合权限、分布式会话的dubbo消费者
    ├─highdsa-restful-email -> 邮件消费者模块，对外提供RESTful API 
    ├─highdsa-restful-fastdfs -> 文件存储消费者模块，对外提供RESTful API
    ├─highdsa-restful-message-> 短信和消息推送消费者模块，对外提供RESTful API
    ├─highdsa-restful-redis -> redis消费者模块，对外提供RESTful API
    ├─highdsa-restful-shiro -> 权限测试模块
    ├─highdsa-restful-test -> 消费者测试模块，以web形式整合所有RESTful API，方便测试
    ├─highdsa-security-cas -> 单点登录父模块
    │  ├─highdsa-cas-manager -> 单点登录管理web模块
    │  └─highdsa-cas-server -> 单点登录web模块
    ├─highdsa-security-shiro -> 权限管理父模块
    │  ├─highdsa-shiro-client -> 权限控制客户端
    │  │  ├─highdsa-client-cas -> cas客户端
    │  │  ├─highdsa-client-pac4j -> pac4j客户端
    │  │  └─highdsa-client-restful -> restful api客户端
    │  └─highdsa-shiro-manage -> 权限管理web模块
    │      ├─highdsa-manage-customer -> 系统用户权限管理配置
    │      └─highdsa-manage-sysuser -> 顾客权限管理配置
    ├─highdsa-service-activemq -> 消息队列父模块
    │  ├─highdsa-service-mqproducer -> 消息生产者模块
    │  └─highdsa-service-mqreceiver -> 消息接收者模块
    ├─highdsa-service-email -> 邮件服务提供者模块
    ├─highdsa-service-fastdfs -> 文件存储服务提供者模块
    ├─highdsa-service-message -> 短信和消息推送模块
    ├─highdsa-service-mybatis -> 数据库服务提供者模块
    └─highdsa-service-redis -> redis缓存服务提供者模块
```

***

### highdsa架构模型图如下
 
 ![image](https://github.com/CrazyHusen/highdsa/blob/master/docs/%E6%9E%B6%E6%9E%84%E6%A8%A1%E5%9E%8B%E5%9B%BE/%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84%E7%B2%BE%E7%AE%80%E5%9B%BE.jpg)

***

### highdsa架构图如下
 
 ![image](https://github.com/CrazyHusen/highdsa/blob/master/docs/%E6%9E%B6%E6%9E%84%E6%A8%A1%E5%9E%8B%E5%9B%BE/%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84%E5%9B%BE.jpg)
 
 ***
 
 ### 工程Maven模块依赖关系图如下
 
 ![image](https://github.com/CrazyHusen/highdsa/blob/master/docs/%E6%9E%B6%E6%9E%84%E6%A8%A1%E5%9E%8B%E5%9B%BE/Maven%E6%A8%A1%E5%9D%97%E4%BE%9D%E8%B5%96%E5%9B%BE.jpg)
 
 ***
 
 ### highdsa RESTful请求调用链如下
 
 ![image](https://github.com/CrazyHusen/highdsa/blob/master/docs/%E6%9E%B6%E6%9E%84%E6%A8%A1%E5%9E%8B%E5%9B%BE/%E8%AF%B7%E6%B1%82%E8%B0%83%E7%94%A8%E9%93%BE.jpg)
 
 ***
 
 ### highdsa系统交互图如下
 
 ![image](https://github.com/CrazyHusen/highdsa/blob/master/docs/%E6%9E%B6%E6%9E%84%E6%A8%A1%E5%9E%8B%E5%9B%BE/%E7%B3%BB%E7%BB%9F%E4%BA%A4%E4%BA%92%E5%9B%BE.jpg)

***
