## log4j 日志配置说明
***
### 文档说明
**log4j的依赖和配置文件全在highdsa-common-log4j2模块下。由于web工程日志输出路径需要在web根目录，所以通过web.xml里的配置参数webAppRootKey和webapp.root实现指定路径为根目录**

- log4j2 web模块配置文件log4j2-web.xml放在log4j2模块下，web项目可以直接引入   

- log4j web模块配置文件log4j-web.properties需要分别放在每个web模块的resources文件夹下，并改名为log4j.properties。因为
 - 1.web可以直接读取依赖的jar包类路径下的log4j.properties，但是这样就不能达到和非web模块配置文件分开而分别设置日志输出路径的目的；
 - 2.通过web.xml配置监听器，则无法直接读取jar包里的配置文件，需要通过maven 插件解压jar的配置文件至web工程中，比较麻烦；
 - 3.通过web.xml配置监听器，${webapp.root}未生效之前，会将${webapp.root}当作空，从而在系统根目录下生成临时文件夹（如C盘根目录）；
 
 综上所述，决定将log4j.properties分别放在每个web模块下

***
### 文件说明	 
> `log4j2.xml` -> log4j2 非web模块配置文件，无需移动   
> `log4j2-web.xml` -> log4j2 web模块配置文件，无需移动      
> `log4j.properties` -> log4j 非web服务模块配文件，无需移动   
> `log4j-web.properties` -> log4j web模块配置文件，需要分别放在每个web模块下，并改名为logj4.properties   

***