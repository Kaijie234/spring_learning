package tech.kaijie.service.impl;

import tech.kaijie.dao.IAccountDao;
import tech.kaijie.domain.Account;
import tech.kaijie.service.IAccountService;
import tech.kaijie.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 * 事务控制应该都是在业务层的
 */
public class AccountServiceImpl_OLD implements IAccountService {
    private IAccountDao accountDao;

    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
       try {
           // 1.开启事务
           txManager.beginTransaction();
           // 2.执行事务
           List<Account> accounts = accountDao.findAllAccount();
           // 3.提交事务
           txManager.commit();
           // 4.返回结果
           return accounts;
       }catch (Exception e){
           // 5.回滚操作
           txManager.rollback();
           throw new RuntimeException(e);
       }finally {
           // 6.释放连接
           txManager.release();
       }
    }

    public Account findAccountById(Integer accountId) {
        try {
            // 1.开启事务
            txManager.beginTransaction();
            // 2.执行事务
            Account account = accountDao.findAccountById(accountId);
            // 3.提交事务
            txManager.commit();
            // 4.返回结果
            return account;
        }catch (Exception e){
            // 5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            // 6.释放连接
            txManager.release();
        }
    }

    public void saveAccount(Account account) {
        try {
            // 1.开启事务
            txManager.beginTransaction();
            // 2.执行事务
           accountDao.saveAccount(account);
            // 3.提交事务
            txManager.commit();
            // 4.返回结果
        }catch (Exception e){
            // 5.回滚操作
            txManager.rollback();
        }finally {
            // 6.释放连接
            txManager.release();
        }
    }

    public void updateAccount(Account account) {
        try {
            // 1.开启事务
            txManager.beginTransaction();
            // 2.执行事务
            accountDao.updateAccount(account);
            // 3.提交事务
            txManager.commit();
            // 4.返回结果
        }catch (Exception e){
            // 5.回滚操作
            txManager.rollback();
        }finally {
            // 6.释放连接
            txManager.release();
        }
    }

    public void deleteAccount(Integer accountId) {
        try {
            // 1.开启事务
            txManager.beginTransaction();
            // 2.执行事务
            accountDao.deleteAccount(accountId);
            // 3.提交事务
            txManager.commit();
            // 4.返回结果
        }catch (Exception e){
            // 5.回滚操作
            txManager.rollback();
        }finally {
            // 6.释放连接
            txManager.release();
        }
    }

    public void transfer(String sourceName, String targetName, Float money) {
        try {
            // 1.开启事务
            txManager.beginTransaction();
            // 2.执行事务
            // 2.1.根据名称查询转出账户
            Account source = accountDao.findAccountByName(sourceName);
            // 2.2.根据名称查询转入账户
            Account target = accountDao.findAccountByName(targetName);
            // 2.3.转出账户减钱
            source.setMoney(source.getMoney() - money);
            // 2.4.转出账户加钱
            target.setMoney(target.getMoney() + money);
            // 2.5.更新转出账户
            accountDao.updateAccount(source);

            //手动加入异常
            int i = 1 / 0;

            // 2.6.更新转入账户
            accountDao.updateAccount(target);
            // 3.提交事务
            txManager.commit();
            // 4.返回结果
        }catch (Exception e){
            // 5.回滚操作
            txManager.rollback();
            e.printStackTrace();
        }finally {
            // 6.释放连接
            txManager.release();
        }
    }
}
