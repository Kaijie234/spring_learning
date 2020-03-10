package tech.kaijie.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tech.kaijie.domain.Account;
import tech.kaijie.service.IAccountService;

import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 */
public class AccountServiceTest {
    @Test
    public void testFindAll() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2.得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
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
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2.得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        // 3.执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
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
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2.得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);

        Account account = as.findAccountById(4);
        account.setName("更新用户");
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2.得到业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        as.deleteAccount(4);
    }
}
