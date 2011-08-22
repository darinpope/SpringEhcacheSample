package com.darinpope.model;

import java.io.Serializable;

public class Region implements Serializable {
  private Integer regionId;
  private String regionCode;
  private String localCode;
  private String regionName;
  private String continent;
  private String isoCountry;

  public Integer getRegionId() {
    return regionId;
  }

  public void setRegionId(Integer regionId) {
    this.regionId = regionId;
  }

  public String getRegionCode() {
    return regionCode;
  }

  public void setRegionCode(String regionCode) {
    this.regionCode = regionCode;
  }

  public String getLocalCode() {
    return localCode;
  }

  public void setLocalCode(String localCode) {
    this.localCode = localCode;
  }

  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }

  public String getIsoCountry() {
    return isoCountry;
  }

  public void setIsoCountry(String isoCountry) {
    this.isoCountry = isoCountry;
  }
}
