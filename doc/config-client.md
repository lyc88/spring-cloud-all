# Spring Cloud Config Client



## 预备知识



### 发布/订阅模式

`java.util.Observable` 是一个发布者

`java.util.Observer` 是订阅者



发布者和订阅者：1 : N

发布者和订阅者：N : M



### 事件/监听模式

`java.util.EventObject` ：事件对象

​	* 事件对象总是关联着事件源（source）

`java.util.EventListener` ：事件监听接口（标记）



## Spring 事件/监听

`ApplicationEvent` : 应用事件

`ApplicationListener` : 应用监听器



### Spring Boot 事件/监听器



#### ConfigFileApplicationListener

管理配置文件，比如：`application.properties` 以及 `application.yaml`

`application-{profile}.properties`:

profile  = dev 、test

1. `application-{profile}.properties`
2. application.properties



Spring Boot 在相对于 ClassPath ： /META-INF/spring.factories



Java SPI : `java.util.ServiceLoader`

Spring SPI：

Spring Boot "/META-INF/spring.factories"

```properties
org.springframework.context.ApplicationListener=\
org.springframework.boot.ClearCachesApplicationListener,\
org.springframework.boot.builder.ParentContextCloserApplicationListener,\
org.springframework.boot.context.FileEncodingApplicationListener,\
org.springframework.boot.context.config.AnsiOutputApplicationListener,\
org.springframework.boot.context.config.ConfigFileApplicationListener,\
org.springframework.boot.context.config.DelegatingApplicationListener,\
org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicationListener,\
org.springframework.boot.logging.ClasspathLoggingApplicationListener,\
org.springframework.boot.logging.LoggingApplicationListener
```

##### 如何控制顺序

实现`Ordered` 以及 标记`@Order`

在 Spring 里面，数值越小，越优先



### Spring Cloud 事件/监听器

#### BootstrapApplicationListener



Spring Cloud "/META-INF/spring.factories":

```properties
# Application Listeners
org.springframework.context.ApplicationListener=\
org.springframework.cloud.bootstrap.BootstrapApplicationListener,\
org.springframework.cloud.bootstrap.LoggingSystemShutdownListener,\
org.springframework.cloud.context.restart.RestartListener
```



> 加载的优先级 高于 `ConfigFileApplicationListener`，所以 application.properties 文件即使定义也配置不到！
>
> 原因在于：
>
> `BootstrapApplicationListener ` 第6优先
>
> `ConfigFileApplicationListener` 第11优先

1. 负责加载`bootstrap.properties` 或者 `bootstrap.yaml`
2. 负责初始化 Bootstrap ApplicationContext ID = "bootstrap"

```java
ConfigurableApplicationContext context = builder.run();
```

Bootstrap 是一个根 Spring 上下文，parent = null

> 联想 ClassLoader：
>
> ExtClassLoader <- AppClassLoader <- System ClassLoader -> Bootstrap Classloader(null)



#### ConfigurableApplicationContext

标准实现类：`AnnotationConfigApplicationContext`



### Env 端点：`EnvironmentEndpoint`



`Environment` 关联多个带名称的`PropertySource`

可以参考一下Spring Framework 源码：

`AbstractRefreshableWebApplicationContext`

```java
protected void initPropertySources() {
  ConfigurableEnvironment env = getEnvironment();
  if (env instanceof ConfigurableWebEnvironment) {
    ((ConfigurableWebEnvironment) env).initPropertySources(this.servletContext, this.servletConfig);
  }
}
```



`Environment` 有两种实现方式：

普通类型：`StandardEnvironment`

Web类型：`StandardServletEnvironment`



`Environment`

 -  `AbstractEnvironment`

    ​	- `StandardEnvironment`



Enviroment 关联着一个`PropertySources` 实例

`PropertySources` 关联着多个`PropertySource`，并且有优先级

其中比较常用的`PropertySource` 实现：

Java System#getProperties 实现：  名称"systemProperties"，对应的内容 `System.getProperties()`

Java System#getenv 实现(环境变量）：  名称"systemEnvironment"，对应的内容 `System.getProperties()`



关于 Spring Boot 优先级顺序，可以参考：https://docs.spring.io/spring-boot/docs/2.0.0.BUILD-SNAPSHOT/reference/htmlsingle/#boot-features-external-config



#### 实现自定义配置

1. 实现`PropertySourceLocator`

2. 暴露该实现作为一个Spring Bean

3. 实现`PropertySource`:

   ```java
   public static class MyPropertySourceLocator implements PropertySourceLocator {

       @Override
       public PropertySource<?> locate(Environment environment) {
           Map<String, Object> source = new HashMap<>();
           source.put("server.port","9090");
           MapPropertySource propertySource =
                   new MapPropertySource("my-property-source", source);
           return propertySource;
       }
   }
   ```

4.  定义并且配置 /META-INF/spring.factories:

   ```properties
   org.springframework.cloud.bootstrap.BootstrapConfiguration=\
   com.gupao.springcloudconfigclient.SpringCloudConfigClientApplication.MyPropertySourceLocator
   ```



注意事项：

`Environment` 允许出现同名的配置，不过优先级高的胜出

内部实现：`MutablePropertySources` 关联代码：

```java
List<PropertySource<?>> propertySourceList = new CopyOnWriteArrayList<PropertySource<?>>();
```

propertySourceList FIFO，它有顺序

可以通过 MutablePropertySources#addFirst 提高到最优先，相当于调用：

`List#add(0,PropertySource);`







### 书籍推荐

翟永超《Spring Cloud 微服务实战》

