package com.darinpope.dao.impl;

import com.darinpope.dao.DataCenterHealthDao;
import com.googlecode.ehcache.annotations.Cacheable;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.stereotype.Component;
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
    setActive("localDataCenterStatus","true");
  }

  public void setRemoteActive() {
    setActive("remoteDataCenterStatus","true");
  }

  public void setLocalInactive() {
    setActive("localDataCenterStatus","false");
  }

  public void setRemoteInactive() {
    setActive("remoteDataCenterStatus","false");
  }

  @Cacheable(cacheName="DataCenterHealthCache")
  private boolean isActive(String key) {
    return false;
  }

  private void setActive(String key, String status) {
    Element element = new Element(key,Boolean.valueOf(status));
    dataCenterHealthCache.put(element);
  }
}
