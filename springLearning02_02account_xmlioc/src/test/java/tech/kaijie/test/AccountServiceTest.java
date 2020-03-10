package tech.kaijie.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tech.kaijie.domain.Account;
import tech.kaijie.service.IAccountService;

import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {
    @Autowired
    private IAccountService as;

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
        // 3.执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {

        Account account = new Account();
        account.setName("保存用户");
        account.setMoney((float) 10000);

        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {

        Account account = as.findAccountById(4);
        account.setName("更新用户");
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        as.deleteAccount(4);
    }
}
