package tech.kaijie.ui;

import tech.kaijie.factory.BeanFactory;
import tech.kaijie.service.IAccountService;
import tech.kaijie.service.impl.AccountServiceImpl;

/**
 * 模拟一个表现出，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        // 此处的依赖有待解决（需要new）
//        IAccountService as = new AccountServiceImpl();
        // 使用工厂模式来创建对象
        IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
        as.saveAccount();
    }
}
