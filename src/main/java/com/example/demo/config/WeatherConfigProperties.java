package com.example.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 天气配置属性
 *
 * @author Peter.Ding
 * @date 2021/04/29
 */
@Data
@ConfigurationProperties(prefix = "weather.config", ignoreUnknownFields = true)
public class WeatherConfigProperties {

  /** 重试计数 */
  public int retryCounter;

  /** 省查询网址 */
  public String provinceQueryUrl;

  /** 城市查询网址 */
  public String cityQueryUrl;

  /** 县查询网址 */
  public String countyQueryUrl;

  /** 天气查询网址 */
  public String weatherQueryUrl;

  /**
   * 获得省查询网址
   *
   * @return {@link String}
   */
  public String getProvinceQueryUrl() {
    return provinceQueryUrl;
  }

  /**
   * 得到城市查询网址
   *
   * @param province 省
   * @return {@link String}
   */
  public String getCityQueryUrl(String province) {
    return new StringBuffer(cityQueryUrl).append(province).append(".html").toString();
  }

  /**
   * 得到县查询网址
   *
   * @param province 省
   * @param city 城市
   * @return {@link String}
   */
  public String getCountyQueryUrl(String province, String city) {
    return new StringBuffer(countyQueryUrl)
        .append(province)
        .append(city)
        .append(".html")
        .toString();
  }

  /**
   * 得到天气查询网址
   *
   * @param province 省
   * @param city 城市
   * @param county 县
   * @return {@link String}
   */
  public String getWeatherQueryUrl(String province, String city, String county) {
    return new StringBuffer(weatherQueryUrl)
        .append(province)
        .append(city)
        .append(county)
        .append(".html")
        .toString();
  }
}
