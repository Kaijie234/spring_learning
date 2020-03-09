package tech.kaijie.factor;

import tech.kaijie.service.IAccountService;
import tech.kaijie.service.impl.AccountServiceImpl;

/**
 * 模拟一个工厂类（该类可能是存在于jar包中，我们无法通过修改源码的方式来提供默认构造函数）
 */
public class InstanceFactory {
    // bean对象的第二种方式
    public IAccountService getAccountService()
    {
        return new AccountServiceImpl();
    }
}
