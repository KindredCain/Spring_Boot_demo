# Spring_Boot_demo
随手写的SpringBoot模板  

SpringBoot + SpringMVC + SpringDataJpa + MySql  

***  
#### aspect包：AOP
#### controller：业务控制
#### enums包：返回信息枚举
#### exception包：自定义报错
#### handle包：捕捉错误
#### model包：数据库映射实体
#### repository包：数据库操作接口（继承SpringDataJpa）
#### service包：业务逻辑处理
#### util包：工具包（目前只有统一返回构造）  

***
返回Result：  
`{`  
`"code":（编号）,`   
`"msg":（消息）,`  
`"data":（有数据则为数据josn，没则为null）`  
`}`  

***
test下为单元测试，暂时没写，将来补充  

***
application.yml：配置文件  
application-dev.yml：开发用配置  
application-prod.yml：部署用配置  

***  
#### 附上服务器部署方法及后台持续运行启动结束脚本
1：`mvn spring-boot:run`  
2：`mvn install`编译jar，然后运行jar   
启动shell：  
`#!/bin/bash`  
`nohup java -jar name.jar &`   
结束shell：   
`#!/bin/bash`  
`PID=$(ps -ef | grep name.jar | grep -v grep | awk '{ print $2 }')`  
`if [ -z "$PID" ]`  
`then`  
`    echo Application is already stopped`  
`else`  
`    echo kill $PID`  
`    kill $PID`  
`fi`  
