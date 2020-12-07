# Spring
## Spring 的特点
- 方便解耦，简化开发。使用了Spring提供的IoC容器，对象之间的依赖关系交由Spring进行控制。用户不必再为单实例模式类，属性文件解析写代码
- AOP编程支持。通过Spring的AOP功能，可以进行面向切面的编程，可以实现传统OOP不容易实现的方法
- 声明式事物的支持。
- 方便程序的测试。可以使用非容器依赖的编程方式进行几乎所有的测试工作。在Spring里面，可以使用junit4
- 方便集成各种优秀的框架
- 降低java EE API的使用难度
## spring组织架构
spring, Spring Data, Spring MVC, Spring Boot, Spring Cloud
- Data Access / Integration
  - JDBC
  - ORM object relation mapping
  - OXM object xml mapping
  - JMS java message service
  - Transaction
- Web
  - WebSocket 实现了浏览器和服务器全双工通信(full-duplex)。一开始的握手需要借助HTTP请求完成。Socket是传输控制层协议，WebSocket是应用层协议。
  - Servlet
  - Web
  - Portlet 是一种Web组件-就像servlets-是专为将合成页面里的内容聚集在一起而设计的。通常请求一 个portal页面会引发多个portlets被调用。每个portlet都会生成标记段，并与别的portlets生成的标记段 组合在一起嵌入到portal页面的标记内
- AOP
- Aspects
- Instrumention
- Messaging
- Core Container
  - Beans
  - Core
  - Context
  - SpEL
- Test
#### spring的核心模块
- spring-core ：依赖注入IOC与DI的最基本实现
- spring-beans ：Bean工厂与bean的装饰
- spring-context ：spring的context上下文即IOC容器
- spring-context-support
- spring-expression ：spring表达式语言
  
## spring中的IOC
IOC(inverse of control)：控制反转，降低对象之间的耦合关系的设计思想；  
DI(Dependency Injection)：依赖注入，创建对象实例的时候，同时为这个对象注入它所依赖的属性。  
- 在application.xml里面创建对象，ioc将对象统一管理，统一分配。当容器给属性调值时，默认使用setter方法
- 从配置文件中得到已经创建好的对象
  1. ApplicationContext app=new ClassPathXmlApplicationContext("spring.xml");
  2. Users users=(Users)app.getBean("u1");
- spring中默认使用单例模式创建对象
- bean标签的属性介绍
  - class：指定bean对应类的全路径
  - name：name是bean对应对象的一个标识
  - scope：执行bean对象创建模式和生命周期,有两种选择singleton 和 prototype
  - id：bean对象的唯一标识，不能添加特别的字符
  - lazy-init：是否延时加载，默认是false。true 延迟加载对象,当对象被调用的时候才会加载，测试 的时候，通过getbean()方法获得对象。lazy-init="false" 默认值，不延迟，无论对象 是否被使用，都会立即创建对象,测试时只需要加载配置文件即可。注意:测试的时候 只留下id,class属性
  - init-method：只需要加载配置文件即可对象初始化方法
  - destroy-method：对象销毁方法
- 对象创建的方式
  - 给属性赋值时，对象类型选择ref，非对象类型选择value
  - 无参构造
  - 有参构造 `<bean name="person" class="com.xzk.spring.bean.Person"> <constructor-arg name="name" value="rose"/> <constructor-arg name="car" ref="car"/></bean>`
  - 静态方法创建对象`<bean name="pf" class="com.xzk.PersonFactory" factory-method="createPerson" />`
  - 非静态工厂方法`<bean id="u2" class="com.bean.Users"></bean> <bean id="u3" factory-method="createPerson1" factory-bean="u2"></bean>`
- springBean的生命周期
  - 实例化bean -> 设置属性 -> 调用BeanNameAware的setBeanName()方法 -> 调用BeanFactoryAware的setBeanFactory()方法 -> 调用ApplicationContextAware的setApplicationContextAware()方法 -> 调用BeanPostProcessor的预初始化方法 -> 调用InitializingBean的afterPropertiesSet()方法 -> 调用定制的初始化方法 -> 调用BeanPostProcessor的后初始化方法 -> prototype 将准备就绪的bean交给调用者 or singleton Spring缓存池中准备就绪的Bean -> 调用DisposableBean的destory()方法 or 调用destory-method属性配置的销毁方法
  1. 根据配置情况调用 Bean 构造方法或工厂方法实例化 Bean。
  2. 利用依赖注入完成 Bean 中所有属性值的配置注入。
  3. 如果 Bean 实现了 BeanNameAware 接口，则 Spring 调用 Bean 的 setBeanName() 方法传入当前 Bean 的 id 值。
  4. 如果 Bean 实现了 BeanFactoryAware 接口，则 Spring 调用 setBeanFactory() 方法传入当前工厂 实例的引用。
  5. 如果 Bean 实现了 ApplicationContextAware 接口，则 Spring 调用 setApplicationContext() 方法 传入当前 ApplicationContext 实例的引用。
  6. 如果 BeanPostProcessor 和 Bean 关联，则 Spring 将调用该接口的预初始化方法 postProcessBeforeInitialzation() 对 Bean 进行加工操作，此处非常重要，Spring 的 AOP 就是利用它 实现的。
  7. 如果 Bean 实现了 InitializingBean 接口，则 Spring 将调用 afterPropertiesSet() 方法。初始化 bean的时候执行，可以针对某个具体的bean进行配置。afterPropertiesSet 必须实现 InitializingBean 接口。实现 InitializingBean接口必须实现afterPropertiesSet方法。
  8. 如果在配置文件中通过 init-method 属性指定了初始化方法，则调用该初始化方法。
  9. 如果 BeanPostProcessor 和 Bean 关联，则 Spring 将调用该接口的初始化方法postProcessAfterInitialization()。此时，Bean 已经可以被应用系统使用了。
  10. 如果在 中指定了该 Bean 的作用范围为 scope="singleton"，则将该 Bean 放入 Spring IoC 的缓 存池中，将触发 Spring 对该 Bean 的生命周期管理;如果在 中指定了该 Bean 的作用范围为 scope="prototype"，则将该 Bean 交给调用者，调用者管理该 Bean 的生命周期，Spring 不再管理该 Bean。
  11. 如果 Bean 实现了 DisposableBean 接口，则 Spring 会调用 destory() 方法将 Spring 中的 Bean 销毁;如果在配置文件中通过 destory-method 属性指定了 Bean 的销毁方法，则 Spring 将调用该方 法对 Bean 进行销毁。

## DI注入值
- set注入值
  - 基本类型值注入`<property name="name" value="jeck" />`
  - 引用类型值注入 `<property name="car" ref="car"></property>`
- 构造注入
- spring表达式
- p命名空间注入值
- 复杂类型注入
- 自动注入
  - no 不自动装配(默认值)
  - byName 属性名=id名 ，调取set方法赋值
  - byType 属性的类型和id对象的类型相同，当找到多个同类型的对象时报错，调取set方法赋值
  - constructor 构造方法的参数类型和id对象的类型相同,当没有找到时，报错。调取构造方法赋值

## 注解实现IOC
注解实现IOC的时候，不一定要set方法
-  配置文件中添加约束
  xmlns:context="http://www.springframework.org/schema/context"  
  http://www.springframework.org/schema/context   
  http://www.springframework.org/schema/context/spring-context.xsd
-  配置注解扫描：指定扫描包下所有类中的注解，扫描包的时候会扫描所有的子包`<context:component-scan base-package="com.xzk.spring.bean"></context:component- scan>`
-  注解
   - 添加在类名上
     - @Component(“对象名”) 都可以用
     - @Service("person") // service层
     - @Controller("person") // controller层 
     - @Repository("person") // dao层
     - @Scope(scopeName="singleton") // 单例对象
     - @Scope(scopeName="prototype") // 多例对象
   - 添加在属性上
     - @value("属性名")
     - @Autowired  @Qualifier("bean name")
     - @Resource(name="对象名")
   - 添加在方法上
     - @PostConstruct
     - @PreDestroy
  
## AOP介绍
AOP(Aspect Oriented Programming)即面向切面编程，在不改变原程序的基础上，为代码增加新的功能。应用在权限认证，日志，事务。
- jdk的动态代理：针对实现了接口的类产生代理。InvocationHandler接口
  - 创建接口和对应实现类
  - 创建动态代理类，实现InvocationHandler接口
- CGlib的动态代理：针对没有实现接口的类产生代理，应用的是底层的字节码增强的技术，生成当前类的子类对象，MethodInterceptor接口
- 区别：
  1. jdk动态代理生成的代理类和委托类实现了相同的接口
  2. cglib生成的字节码更复杂，生成的代理类是委托类的子类，且不能处理被final关键字修饰的方法
  3. jdk采用反射机制调用委托类的方法，cglib采用类似索引的方式直接调用委托类方法
#### spring中使用aop
1. 添加jar包
2. 添加项目原有的调取过程
3. 创建增强类
   - 前置通知：目标方法运行前通知 aop:before
   - 后置通知：目标方法运行后通知 aop:after-returning
   - 环绕通知：在目标方法之前和之后都调用 aop:around
   - 最终通知：在目标方法运行之后调用 aop:after
   - 异常增强：程序出现异常的时候执行 aop:after-throwing
4. 添加aop命名空间
5. 设置配置文件
##### 增强的顺序
1. AOP
2. around
3. before
4. method
5. arounf
6. after
7. afterReturning (when normal)
8. afterThrowing (when unnormal)

## 切入点方法的定义
- 表达式匹配规则举例
  - public * addUser(com.pb.entity.User):匹配所有类型的返回值
  - public void * (com.pb.entity.User)：匹配所有的方法名
  - public void addUser(..): 匹配所有参数个数和类型
  - \* com.pb.service.*.*(..): 匹配com.pb.service包下所有类的所有方法
  - \* com.pb.service..*(..): 匹配com.pb.service包及子包下所有类的所有方法

## 如何让获取切入点信息
通过jointpoint对象获取信息

## 特殊的前置增强类（Advisor前置增强实现步骤）
- 创建增强类，要求该类实现MethodBeforeAdvice接口
- 修改applicationContext.xml文件
  - 创建增强类对象
  - 定义增强和切入点的关系

