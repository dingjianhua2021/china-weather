package com.example.demo;

import com.example.demo.service.WeatherService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class WeatherTest extends DemoApplicationTests {
  @Autowired
  private WeatherService weatherService;

  @Test
  public void testTetTemperature() {
    Assert.assertEquals("查询温度失败",(Integer)239, weatherService.getTemperature("10119", "04", "01").get());
  }

}
