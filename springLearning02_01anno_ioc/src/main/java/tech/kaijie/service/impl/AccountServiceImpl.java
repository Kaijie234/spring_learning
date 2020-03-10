package tech.kaijie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tech.kaijie.dao.IAccountDao;
import tech.kaijie.dao.impl.AccountDaoImpl;
import tech.kaijie.service.IAccountService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 账户的业务层实现类
 * 曾经xml的配置：
 * <bean id="accountService" class="tech.kaijie.service.impl.AccountServiceImpl"
 *      scope="" init-method="" destroy-method="">
 *      <property name="" value="" ref=""></property>
 * </bean>
 *
 * 用于创建对象的注解
 *      他们的作用就和在xml配置文件中编写一个<bean>变迁实现的功能是一样的
 *      @Component:
 *          作用：用于把当前类对象存入spring容器中
 *          属性：
 *              value：用于指定bean的id。默认值是当前类名，且首字母改小写。
 *                      细节：如果注解中有且只有一个属性要赋值时，且名称是 value，value 在赋值是可以不写。
 *      @Controller：一般用于表现层（可以使用继承）
 *      @Service：一般用于业务层
 *      @Repository：一般用于持久层
 *      以上三个注解他们的作用和属性与Component是一模一样的。
 *      他们三个是spring框架为我们明确的三层使用的注解，使我们三册对象更加清晰。
 * 用于注入数据的
 *      他们的作用就和在xml配置中的bean标签中写一个properties作用是一样的
 *      @Autowired：
 *          作用：自动按照类型注入。只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，它就可以注入成功。
 *                  如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错。
 *                  如果Ioc容器中有多个类型匹配时：首先匹配类型，其次匹配变量名称。
 *          出现位置：
 *              可以是变量上，也可以是方法上
 *          细节：在使用注解注入时，set方法就不是必须的了。
 *
 *      @Qualifier：
 *          作用：在按照类型注入的基础之上再按照名称注入。它在给类成员注入时不能单独使用，但是在给方法参数注入时可以。
 *          属性：
 *              value：用于指定注入bean的id
 *      @Resource
 *          作用：直接按照bean的id注入，它可以独立使用。
 *          属性：
 *              name：用于指定bean的id
 *      以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现。
 *      另外，集合类型只能通过xml来实现。
 *
 *      @Value
 *          作用：用于注入基本类型和String类型的数据。
 *              属性：
 *                  value：用于指定数据的值，它可以使用spring中spEL（它就是spring的el表达式）
 *                          SpEl的写法：${表达式}
 * 用于改变作用范围的
 *      他们的作用就和在bean标签中使用scope属性实现的功能是一样的.
 *      @Scope
 *          作用：用于指定bean的作用范围
 *          属性：
 *              value：指定范围的取值。常用取值：singleTo（默认） prototype
 * 和生命周期相关的（了解）
 *      他们的作用就和在bean标签中使用init-method和destroy-method的作用是一样的
 *      @PreDestroy
 *          作用：用于指定销毁方法
 *      @PostConstruct
 *          作用：用于指定初始化方法
 *
 */
@Service(value = "accountService")
@Scope(value = "singleton")
public class AccountServiceImpl implements IAccountService {
//    @Autowired
//    @Qualifier("accountDao1")
    @Resource(name = "accountDao2")
    private IAccountDao accountDao;

    @PostConstruct
    public void init()
    {
        System.out.println("初始化方法执行了");
    }

    @PreDestroy
    public void detroy()
    {
        System.out.println("销毁方法执行了");
    }

    public void saveAccount()
    {
        accountDao.saveAccount();
    }
}
