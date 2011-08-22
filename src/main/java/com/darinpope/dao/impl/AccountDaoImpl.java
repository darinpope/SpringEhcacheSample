package com.darinpope.dao.impl;

import com.darinpope.dao.AccountDao;
import com.darinpope.dao.DataCenterHealthDao;
import com.darinpope.model.Account;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

  private Ehcache localAccountCache;
  private Ehcache remoteAccountCache;
  private DataCenterHealthDao dataCenterHealthDao;

  @Autowired
  @Qualifier(value="localAccountCache")
  public void setLocalAccountCache(final EhCacheFactoryBean factoryBean) {
    localAccountCache = factoryBean.getObject();
  }

  @Autowired
  @Qualifier(value="remoteAccountCache")
  public void setRemoteAccountCache(final EhCacheFactoryBean factoryBean) {
    remoteAccountCache = factoryBean.getObject();
  }

  @Autowired
  @Qualifier("dataCenterHealthDao")
  public void setDataCenterHealthDao(final DataCenterHealthDao dataCenterHealthDao) {
    this.dataCenterHealthDao = dataCenterHealthDao;
  }

  public Account get(String accountId) {
    Element element = localAccountCache.get(accountId);
    if(element != null) {
      return (Account) element.getValue();
    }
    // go get the account from the system of record. For now, just make a fake one...
    Account account = new Account();
    account.setFirstName("John");
    account.setLastName("Doe");
    element = new Element("jdoe",account);
    localAccountCache.put(element);
    if(dataCenterHealthDao.isRemoteActive()) {
      remoteAccountCache.put(element);
    }
    return account;
  }

  public void remove(String accountId) {
    localAccountCache.remove(accountId);
    if(dataCenterHealthDao.isRemoteActive()) {
      remoteAccountCache.remove(accountId);
    }
  }

  public Account refresh(String accountId) {
    remove(accountId);
    return get(accountId);
  }
}
