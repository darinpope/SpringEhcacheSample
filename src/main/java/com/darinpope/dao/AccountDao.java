package com.darinpope.dao;

import com.darinpope.model.Account;

public interface AccountDao {
  Account get(String accountId);
  void remove(String accountId);
  Account refresh(String accountId);
}
