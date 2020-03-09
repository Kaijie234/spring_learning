package tech.kaijie.service.impl;

import tech.kaijie.dao.IAccountDao;
import tech.kaijie.dao.impl.AccountDaoImpl;
import tech.kaijie.factory.BeanFactory;
import tech.kaijie.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
    // 此处的依赖关系有待解决（需要new）
//    private IAccountDao accountDao = new AccountDaoImpl();
    private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");
    public void saveAccount()
    {
        accountDao.saveAccount();
    }
}
