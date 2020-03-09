package tech.kaijie.service.impl;

import tech.kaijie.dao.IAccountDao;
import tech.kaijie.dao.impl.AccountDaoImpl;
import tech.kaijie.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
    // 此处的依赖关系有待解决（需要new）
    private IAccountDao accountDao = new AccountDaoImpl();

    // 测试ApplicationContext和BeanFactory创建对象的方式
    public AccountServiceImpl()
    {
        System.out.println("对象创建了");
    }
    public void saveAccount()
    {
        accountDao.saveAccount();
    }
}
