# springboot 2.0.5.RELEASE + spring-cloud Finchley版本 + Mysql8.0.11 + MyBatis + Atomikos 分布式事务
# spring-cloud 实现功能：
	注册中心 端口-8888 应用名称-register
	服务提供者 端口-8763 应用名称-client
	服务网关 端口-8080 应用名称-gateway
	Feign声明式HTTP客户端 端口-8081 应用名称-feign
	熔断器 在feign模块下
	配置中心 端口-8101 应用名称-config
	消息总线 配置在client、config模块下
	服务链路追踪 配置在client、gateway模块下