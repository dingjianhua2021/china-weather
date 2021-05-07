package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.HttpStatusEnum;
import com.example.demo.common.ServiceException;
import com.example.demo.config.WeatherConfigProperties;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.Semaphore;

/**
 * 气象服务
 *
 * @author Peter.Ding
 * @date 2021/04/29
 */
@Service
public class WeatherService {

  /** 天气配置属性 */
  @Autowired
  private WeatherConfigProperties weatherConfigProperties;
  //信号量控制
  private static Semaphore semaphore = new Semaphore(100);
  /**
   * 得到温度
   *
   * @param province 省份
   * @param city 城市
   * @param county 县
   * @return {@link Optional<Integer>}
   */
  public Optional<Integer> getTemperature(String province, String city, String county) {
    if(!semaphore.tryAcquire()){
      throw new ServiceException("服务访问受限，请稍后再试");
    }
    if (!paramCheck(province, city, county)) {
      // 抛出业务异常，不再执行
      throw new ServiceException("请输入正确的查询参数");
    }
    Optional<Integer> temperature = Optional.empty();
    CloseableHttpClient myClient = HttpClientBuilder.create().build();
    HttpGet httpGet = new HttpGet(getUrl(province, city, county));
    CloseableHttpResponse response = null;
    int requestCount = 0;
    boolean needRetry = false;
    try {
      do {
        // 执行请求
        response = myClient.execute(httpGet);
        if (HttpStatusEnum.OK.code() == response.getStatusLine().getStatusCode()) {
          needRetry = false;
          HttpEntity responseEntity = response.getEntity();
          if (responseEntity != null) {
            // System.out.println("响应内容为:" + EntityUtils.toString(responseEntity,"utf-8"));
            JSONObject jsonObject =
                JSONObject.parseObject(EntityUtils.toString(responseEntity, "utf-8"));
            if (jsonObject.containsKey("weatherinfo")) {
              JSONObject weatherinfo = (JSONObject) jsonObject.get("weatherinfo");
              if (weatherinfo.containsKey("temp")) {
                String temp = (String) weatherinfo.get("temp");
                if (StringUtils.contains(temp, ".")) {
                  temp =
                      temp.substring(0, temp.indexOf(".")) + temp.substring(temp.indexOf(".") + 1);
                }
                // 带小数点形式，转化一下
                temperature = Optional.of(Integer.parseInt(temp));
              }
            }
          }
        } else {
          requestCount++;
          needRetry = true;
        }
      } while (requestCount < weatherConfigProperties.retryCounter && needRetry);
      if(requestCount>=weatherConfigProperties.retryCounter){
        throw new ServiceException("服务暂时不可用，请稍后重试");
      }
    } catch (ClientProtocolException e) {
      throw new ServiceException("服务暂时不可用，请稍后重试");
    } catch (IOException e) {
      throw new ServiceException("服务暂时不可用，请稍后重试");
    } finally {
      try {
        // 释放资源
        semaphore.release();
        if (myClient != null) {
          myClient.close();
        }
        if (response != null) {
          response.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return temperature;
  }

  /**
   * 获取url
   *
   * @param province 省
   * @param city 城市
   * @param county 县
   * @return {@link String}
   */
  private String getUrl(String province, String city, String county) {
    return weatherConfigProperties.getWeatherQueryUrl(province, city, county);
  }

  /**
   * 参数检查
   *
   * @param province 省
   * @param city 城市
   * @param county 县
   * @return boolean
   */
  private boolean paramCheck(String province, String city, String county) {
    if (checkProvince(province)
        && checkCity(province, city)
        && checkCounty(province, city, county)) {
      return true;
    }
    return false;
  }

  /**
   * 检查省
   *
   * @param province 省
   * @return boolean
   */
  public boolean checkProvince(String province) {
    boolean flag = true;
    int requestCount = 0;
    boolean needRetry = false;
    CloseableHttpClient myClient = HttpClientBuilder.create().build();
    HttpGet httpGet = new HttpGet(weatherConfigProperties.getProvinceQueryUrl());
    CloseableHttpResponse response = null;
    try {
      do {
        // 执行请求
        response = myClient.execute(httpGet);
        if (HttpStatusEnum.OK.code() == response.getStatusLine().getStatusCode()) {
          needRetry = false;
          HttpEntity responseEntity = response.getEntity();
          if (responseEntity != null) {
            JSONObject jsonObject =
                JSONObject.parseObject(EntityUtils.toString(responseEntity, "utf-8"));
            if (!jsonObject.containsKey(province)) {
              flag = false;
            }
          }
        } else {
          requestCount++;
          needRetry = true;
        }
      } while (requestCount < weatherConfigProperties.retryCounter && needRetry);
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        // 释放资源
        if (myClient != null) {
          myClient.close();
        }
        if (response != null) {
          response.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return flag;
  }

  /**
   * 检查城市
   *
   * @param province 省
   * @param city 城市
   * @return boolean
   */
  public boolean checkCity(String province, String city) {
    boolean flag = true;
    int requestCount = 0;
    boolean needRetry = false;
    CloseableHttpClient myClient = HttpClientBuilder.create().build();
    HttpGet httpGet = new HttpGet(weatherConfigProperties.getCityQueryUrl(province));
    CloseableHttpResponse response = null;
    try {
      do {
        // 执行请求
        response = myClient.execute(httpGet);
        if (HttpStatusEnum.OK.code() == response.getStatusLine().getStatusCode()) {
          needRetry = false;
          HttpEntity responseEntity = response.getEntity();
          if (responseEntity != null) {
            JSONObject jsonObject =
                JSONObject.parseObject(EntityUtils.toString(responseEntity, "utf-8"));
            if (!jsonObject.containsKey(city)) {
              flag = false;
            }
          }
        } else {
          requestCount++;
          needRetry = true;
        }
      } while (requestCount < weatherConfigProperties.retryCounter && needRetry);
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        // 释放资源
        if (myClient != null) {
          myClient.close();
        }
        if (response != null) {
          response.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return flag;
  }

  /**
   * 检查县
   *
   * @param province 省
   * @param city 城市
   * @param county 县
   * @return boolean
   */
  public boolean checkCounty(String province, String city, String county) {
    boolean flag = true;
    int requestCount = 0;
    boolean needRetry = false;
    CloseableHttpClient myClient = HttpClientBuilder.create().build();
    HttpGet httpGet = new HttpGet(weatherConfigProperties.getCountyQueryUrl(province, city));
    CloseableHttpResponse response = null;
    try {
      do {
        // 执行请求
        response = myClient.execute(httpGet);
        if (HttpStatusEnum.OK.code() == response.getStatusLine().getStatusCode()) {
          needRetry = false;
          HttpEntity responseEntity = response.getEntity();
          if (responseEntity != null) {
            JSONObject jsonObject =
                JSONObject.parseObject(EntityUtils.toString(responseEntity, "utf-8"));
            if (!jsonObject.containsKey(county)) {
              flag = false;
            }
          }
        } else {
          requestCount++;
          needRetry = true;
        }
      } while (requestCount < weatherConfigProperties.retryCounter && needRetry);
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        // 释放资源
        if (myClient != null) {
          myClient.close();
        }
        if (response != null) {
          response.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return flag;
  }
}
