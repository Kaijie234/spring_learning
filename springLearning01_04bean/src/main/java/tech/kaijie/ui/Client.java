package tech.kaijie.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tech.kaijie.service.IAccountService;

/**
 * 模拟一个表现出，用于调用业务层
 */
public class Client {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // 1.获取核心容器对象
        // 这里使用了多态，即只能使用父类方法。
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2.根据id获取Bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService");

        as.saveAccount();

        // 手动关闭容器
        ac.close();
    }
}
