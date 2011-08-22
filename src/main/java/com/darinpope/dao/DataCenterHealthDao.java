package com.darinpope.dao;

public interface DataCenterHealthDao {
  boolean isLocalActive();
  boolean isRemoteActive();
  void setLocalActive();
  void setRemoteActive();
  void setLocalInactive();
  void setRemoteInactive();
}
