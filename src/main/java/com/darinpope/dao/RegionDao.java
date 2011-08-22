package com.darinpope.dao;

import com.darinpope.model.Region;

public interface RegionDao {
  Region getRegion(Integer regionId);
  void deleteRegion(Integer regionId);
  void clearRegionCache();
}
