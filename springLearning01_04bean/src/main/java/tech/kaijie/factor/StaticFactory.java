package tech.kaijie.factor;

import tech.kaijie.service.IAccountService;
import tech.kaijie.service.impl.AccountServiceImpl;

/**
 * 模拟一个工厂类（该类可能是存在于jar包中，我们无法通过修改源码的方式来提供默认构造函数）
 */
public class StaticFactory {
    // bean对象创建的第三种方式
    public static IAccountService getAccountService()
    {
        return new AccountServiceImpl();
    }
}
