package com.darinpope.dao.impl;

import com.darinpope.dao.RegionDao;
import com.darinpope.model.Region;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.googlecode.ehcache.annotations.TriggersRemove;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("regionDao")
public class RegionDaoImpl extends SimpleJdbcDaoSupport implements RegionDao {
  @Cacheable(cacheName="RegionCache",
         keyGenerator = @KeyGenerator(
                name = "HashCodeCacheKeyGenerator",
                properties = @Property( name="includeMethod", value="false" )
         )
  )
  public Region getRegion(Integer regionId) {
    String sql = "select id,code,local_code,region_name,continent,iso_country from region where id = ?";
    return this.getSimpleJdbcTemplate().queryForObject(sql, new RegionMapper(), regionId);
  }

  @TriggersRemove(cacheName="RegionCache",
         keyGenerator = @KeyGenerator(
                name = "HashCodeCacheKeyGenerator",
                properties = @Property( name="includeMethod", value="false" )
         )
  )
  public void deleteRegion(Integer regionId) {}

  @TriggersRemove(cacheName="RegionCache", removeAll=true)
  public void clearRegionCache() {}

  private static final class RegionMapper implements RowMapper<Region> {
    public Region mapRow(ResultSet rs, int rowNum) throws SQLException {
      Region region = new Region();
      region.setRegionId(rs.getInt("id"));
      region.setRegionCode(rs.getString("code"));
      region.setLocalCode(rs.getString("local_code"));
      region.setRegionName(rs.getString("region_name"));
      region.setContinent(rs.getString("continent"));
      region.setIsoCountry(rs.getString("iso_country"));
      return region;
    }
  }
}