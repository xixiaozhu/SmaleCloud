                            SpringCloud

大纲导图: https://www.processon.com/mindmap/5e746eace4b08b61572aceda

更新笔记：

----------------------------  Nacos  ----------------------------

Nacos：快速实现动态服务发现、服务配置、服务元数据及流量管理
       可替代 Eureka 做服务注册中心
       可替代 Config 做服务配置中心
       ...
      
官网文档：https://nacos.io/zh-cn/docs/what-is-nacos.html






----------------------------  Sentinel  ----------------------------

Sentinel：为微服务架构提供服务熔断、降级、限流等解决方案
          可替代Hystrix

官方文档：https://github.com/alibaba/Sentinel/wiki/介绍






----------------------------  Seata  ----------------------------
  
分布式事务:  -->  
  产生问题举例：多个数据库需要保持同时更新成功or同时更新失败的应用场景

	没有100%解决的完美方式, 只有在系统中尽量提高事务成功率，提高系统健壮性

	跨数据库曾经解决思路是使用两阶段/三阶段式的提交方式
	即：	有一个事务管理器管理多条事务
		    多条sql执行后先不commit, 先提交到事务管理器中
		    然后由事务管理器一条一条提交事务

		因此可能出现事务管理器提交时候出状况导致事务不一致风险


	微服务的分布式事务处理大致流程

		阶段1:	更新数据前先做预准备工作,如更新数据状态,预加操作等

		阶段2:	确认资源再检查各种缓存和mq是否正常, 然后将预数据更改为生效数据更新至数据库

		阶段3:	如果失败则介入补偿或根据日志进行人工手动恢复数据


	阿里Seata分布式事务框架
  
  
  
  
  
  
