**欢迎使用brief-tools工具包，该包现包括功能如下：**

- 打印方法调用信息的日志；
- 对调用的方法参数进行校验。

## 安装使用

前提：maven项目，Spring框架

在项目的`POM.xml`文件中添加依赖:

```xml
<dependency>
	<groupId>cn.followtry</groupId>
	<artifactId>brief-tools</artifactId>
	<version>0.0.2</version>
</dependency>
```

在Spring的springmvc配置文件中添加如下配置：

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	   xmlns:aop="http://www.springframework.org/schema/aop" 
	   xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
            http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
            http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
            http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task.xsd"
            >
	<!-- 该位置添加 cn.followtry，分号分隔-->
	<context:component-scan base-package="com.yonyou.esn;cn.followtry" />
	
	<!-- 使用cglib代理 -->
	<aop:aspectj-autoproxy proxy-target-class="true"/>
	<bean  class="cn.followtry.validation.base.logger.LoggingAspect"></bean>
	<bean class="cn.followtry.validation.base.DefaultAspectServiceChain"></bean>
</beans>
```

在需要显示调用日志的类上标注:`XComponent`,`XController`,`XRepository`,`XRestController`,`XService`等注解。

然后在应用启动并访问接口时就会对标注了如上所述注解的类实现调用日志的打印。




