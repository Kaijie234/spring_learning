package tech.kaijie.dao.impl;

import org.springframework.stereotype.Repository;
import tech.kaijie.dao.IAccountDao;

/**
 * 账户持久层的实现类
 */
@Repository(value = "accountDao2")
public class AccountDaoImpl2 implements IAccountDao {
    public void saveAccount()
    {
        System.out.println("保存了账户2");
    }
}
