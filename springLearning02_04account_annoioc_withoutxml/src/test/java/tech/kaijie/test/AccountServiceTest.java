package tech.kaijie.test;

import config.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tech.kaijie.domain.Account;
import tech.kaijie.service.IAccountService;

import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 * Spring整合junit的配置
 *      1. 导入spring整合junit的jar（spring-test坐标）
 *      2. 使用Junit提供的一个注解把原有的main方法替换了，替换成spring提供的
 *          @Runwith
 *      3. 告知spring的运行器，spring和ioc创建是基于xml还是注解的，并且说明位置
 *          @ContextConfiguration
 *              locations：指定xml文件的位置，加上classpath关键字，表示在类路径下
 *              classes：指定注解类所在的位置
 *      注意：当我们使用spring5.x版本时，要求junit的jar必须是4.12及上
 *
 *
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {

    @Autowired
    private IAccountService as = null;

    @Test
    public void testFindAll() {
        // 3.执行方法
        List<Account> accounts = as.findAllAccount();
        for (Account account : accounts)
        {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        // 1.获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2.得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        // 3.执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        // 1.获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2.得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);

        Account account = new Account();
        account.setName("保存用户");
        account.setMoney((float) 10000);

        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        // 1.获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2.得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);

        Account account = as.findAccountById(4);
        account.setName("更新用户");
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        // 1.获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2.得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        as.deleteAccount(4);
    }
}
