package com.darinpope.dao;

import com.darinpope.model.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCountryDao {
  private static final Logger logger = Logger.getLogger(TestCountryDao.class.getName());

  @Autowired
  @Qualifier(value="countryDao")
  private CountryDao countryDao;

  @Test
  public void testGetCountries() {
    for(int i=0;i<1000000;i++) {
    Country country = countryDao.getCountry(1);
    assertEquals("Country is not Andorra","Andorra",country.getName());
    }
  }
}
