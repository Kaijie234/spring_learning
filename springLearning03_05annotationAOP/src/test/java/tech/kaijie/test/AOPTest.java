package tech.kaijie.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tech.kaijie.service.IAccountService;

/**
 * 测试AOP的配置
 * 最好使用环绕通知，因为如果使用其它顺序会有问题。
 */
public class AOPTest {
    public static void main(String[] args) {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2.获取对象
        IAccountService as = (IAccountService) ac.getBean("accountService");
        // 3.执行方法
        as.saveAccount();
    }
}
