# springcloud-practice
### springcloud 搭建的java分布式项目
- 分布式配置中心git -> 注册与发现服务（eureka注册中心） -> 服务A -> ribbon消费、hystrix熔断器 -> Hystrix监控面板 -> Hystrix监控数据聚合(turbine) -> amq
- 启动config-server-git服务 -> 服务A（微服务端映射配置）
  需要配置两个配置文件，application.properties和bootstrap.properties,或者application.yml
  bootstrap.properties的加载也是先于application.properties。

- 注册中心：
    http://zhenghui-pc:1001
- 服务A：
    http://zhenghui-pc:2001/dc
- ribbon消费：
    http://zhenghui-pc:2101/consumer
- Hystrix监控面板：
    http://zhenghui-pc:1301/hystrix
- 监控数据聚合：
    http://zhenghui-pc:2101/hystrix.stream
    http://localhost:8989/turbine.stream
- 加路由gateway
    http://zhenghui-pc:1101/api-a/dc
    http://zhenghui-pc:1101/api-b/consumer
- 加token认证t
    http://zhenghui-pc:1101/api-b/consumer?accessToken
- 手动更新机制（@RefreshScope） post方式模拟手动请求一次，不启动服务可获取最新值
    http://localhost:2001/refresh
- Spring cloud bus 消息总线 自动刷新配置获取最新值
    https://m.aliyun.com/yunqi/articles/100719

##springcloud
eureka-server：注册中心
eureka-client：服务A
eureka-ribbon-hystrix：负载均衡+熔断器
eureka-api-gateway：网管，访问用
eureka-server-> eureka-client-> eureka-ribbon-hystrix ->eureka-api-gateway

##远程配置、自动刷新配置
eureka-server：注册中心
config-repo：远程仓库内容
config-client：服务A，是可以从远程仓库获取数据的服务
eureka-bus-client：服务A，config-client升级，自动刷新配置获取最新值
config-server-git：spring.cloud.config.discovery.service-id：指定配置中心的service-id，便于扩展为高可用配置集群。
config-repo -> config-client
config-repo -> config-server-git -> eureka-bus-client
#### **参考文档**
    MyBatis官方文档：http://www.mybatis.org/mybatis-3/zh/index.html
    程序猿DD博客：http://blog.didispace.com/Spring-Cloud基础教程/
    纯洁的虫纸博客：https://m.aliyun.com/yunqi/articles/91248

