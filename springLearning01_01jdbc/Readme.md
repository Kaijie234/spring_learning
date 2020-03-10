## Spring概述

### 什么是spring

Spring框架是 Java 平台的一个开源的全栈（Full-stack）应用程序框架和控制反转容器实现，一般被直接称为 Spring。该框架的一些核心功能理论上可用于任何 Java 应用，但 Spring 还为基于Java企业版平台构建的 Web 应用提供了大量的拓展支持。虽然 Spring 没有直接实现任何的编程模型，但它已经在 Java 社区中广为流行，基本上完全代替了企业级JavaBeans（EJB）模型。

Spring框架以 Apache License 2.0 开源许可协议的形式发布，该框架最初由 Rod Johnson 以及 Juergen Hoeller 等人开发。——[维基百科](https://zh.wikipedia.org/wiki/Spring_Framework)

`2017 年 9 月份发布了 spring 的最新版本 spring 5.0 通用版（GA）`

### Spring的优势

- 方便解耦，简化开发：通过 Spring 提供的 IoC 容器，可以将对象间的依赖关系交由 Spring 进行控制，避免硬编码所造成的过度程序耦合。用户也不必再为单例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注于上层的应用。
- AOP 编程的支持：通过 Spring 的 AOP 功能，方便进行面向切面的编程，许多不容易用传统 OOP 实现的功能可以通过 AOP 轻松应付。
- 声明式事务的支持：可以将我们从单调烦闷的事务管理代码中解脱出来，通过声明式方式灵活的进行事务的管理，提高开发效率和质量。
- 方便程序的测试：可以用非容器依赖的编程方式进行几乎所有的测试工作，测试不再是昂贵的操作，而是随手可做的事情。
- 方便集成各种优秀框架：Spring 可以降低各种框架的使用难度，提供了对各种优秀框架（Struts、Hibernate、Hessian、Quartz等）的直接支持。
- 降低 JavaEE API 的使用难度：Spring 对 JavaEE API（如 JDBC、JavaMail、远程调用等）进行了薄薄的封装层，使这些 API 的使用难度大为降低。
- Java 源码是经典学习范例：Spring 的源代码设计精妙、结构清晰、匠心独用，处处体现着大师对 Java 设计模式灵活运用以
及对 Java 技术的高深造诣。它的源代码无意是 Java 技术的最佳实践的范例。

![spring的体系结构]()

## IOC的概念和作用

### 程序的耦合和解耦

#### 什么是程序的耦合

程序间的耦合，简单来说就是**对象间的依赖关系**。解耦：降低对象间的依赖关系。如果模块间必须存在耦合，就尽量使用数据耦合，少用控制耦合，限制公共耦合的范围，尽量避免使用内容耦合。内聚标志一个模块内各个元素彼此结合的紧密程度。而在实际开发中，有些依赖关系是必须的，而有些依赖关系可以通过优化代码来解除。包括：类之间的依赖和方法间的依赖。划分模块的一个准则就是**高内聚低耦合**。实际开发中：应该做到，编译器不依赖，运行时才依赖。

### 耦合的场景

#### jdbc的注册驱动

早期我们的 JDBC 操作，注册驱动时代码如下所示：

```java
package tech.kaijie.jdbc;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        // 1.注册驱动
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        // 使用反射加载对象
        Class.forName("com.mysql.jdbc.Driver");
        // 2.获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql:///easy_spring", "root", "root");
        // 3.获取操作数据库的预处理对象
        java.sql.PreparedStatement pstm = conn.prepareStatement("SELECT * FROM account");
        // 4.执行SQL，得到结果集
        ResultSet rs = pstm.executeQuery();
        // 5.遍历结果集
        while (rs.next())
        {
            System.out.println(rs.getString("name"));
        }
        // 6. 释放资源
        rs.close();
        pstm.close();
        conn.close();

    }
}
```

我们为什么不使用 DriverManager 的 register 方法，而是采用 Class.forName 的方式？原因就是：如果我们使用前者用new关键字创建对象，会造成类间耦合性过强。解决的方式就是：**反射**。

同时，也产生了一个新的问题，mysql 驱动的全限定类名字符串是在 java 类中写死的，一旦要改还是要修改源码。**解决这个问题也很简单，使用配置文件配置。**

详细代码见：[springLearning01_01jdbc](https://github.com/Kaijie234/spring_learning)

#### 框架中的耦合

又如：在开发中，业务层经常要调用持久层，并且此时业务层在依赖持久层的接口和实现类。如果此时没有持久层实现类，编译将不能通过。这种编译期依赖关系，应该在我们开发中杜绝。我们需要优化代码解决。同理，在表现层调用业务层也是如此。

具体代码见[springLearning01_02factory](https://github.com/Kaijie234/spring_learning)

### 工厂模式解耦

在实际开发中我们可以**把三层的对象都使用配置文件配置起来，当启动服务器应用加载的时候，让一个类中的方法通过读取配置文件，把这些对象创建出来并存起来。**在接下来的使用的时候，直接拿过来用就好了。那么，这个读取配置文件，创建和获取三层对象的类就是**工厂**。


## Spring的IOC解决程序的耦合

由上文的两个示例中，解耦的方案可以总结：

- 第一步：使用反射来创建对象，而避免使用new关键字
- 第二步：通过读取配置文件来获取要创建的全限定类名

而Spring框架的IOC特性，就是用来解决程序见的耦合问题。

本章我们使用的案例是，账户的业务层和持久层的依赖关系解决。在开始 spring 的配置之前，我们要先准备一下环境。由于我们是使用 spring 解决依赖关系，并不是真正的要做增删改查操作，所以此时我们没必要写实体
类。并且我们在此处使用的是 java 工程，不是 java web 工程。
