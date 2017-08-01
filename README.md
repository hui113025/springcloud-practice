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


#### **参考文档**
    MyBatis官方文档：http://www.mybatis.org/mybatis-3/zh/index.html
    程序猿DD博客：http://blog.didispace.com/Spring-Cloud基础教程/
    纯洁的虫纸博客：https://m.aliyun.com/yunqi/articles/91248

