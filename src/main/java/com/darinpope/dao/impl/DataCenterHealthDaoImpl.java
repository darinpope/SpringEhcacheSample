package com.darinpope.dao.impl;

import com.darinpope.dao.DataCenterHealthDao;
import com.googlecode.ehcache.annotations.Cacheable;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.stereotype.Repository;

@Repository("dataCenterHealthDao")
public class DataCenterHealthDaoImpl implements DataCenterHealthDao {

  private Ehcache dataCenterHealthCache;

  @Autowired
  @Qualifier(value="dataCenterHealthCache")
  public void setDataCenterHealthCache(final EhCacheFactoryBean factoryBean) {
    dataCenterHealthCache = factoryBean.getObject();
  }

  public boolean isLocalActive() {
    return isActive("localDataCenterStatus");
  }

  public boolean isRemoteActive() {
    return isActive("remoteDataCenterStatus");
  }

  public void setLocalActive() {
    setStatus("localDataCenterStatus", "true");
  }

  public void setRemoteActive() {
    setStatus("remoteDataCenterStatus", "true");
  }

  public void setLocalInactive() {
    setStatus("localDataCenterStatus", "false");
  }

  public void setRemoteInactive() {
    setStatus("remoteDataCenterStatus", "false");
  }

  @Cacheable(cacheName="DataCenterHealthCache")
  private boolean isActive(String key) {
    return false;
  }

  private void setStatus(String key, String status) {
    Element element = new Element(key,Boolean.valueOf(status));
    try {
      dataCenterHealthCache.put(element);
    } catch (CacheException e) {
    }
  }
}
