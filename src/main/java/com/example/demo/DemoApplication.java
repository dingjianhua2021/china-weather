package com.example.demo;

import com.example.demo.config.WeatherConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 演示应用程序-以无数据库形式启动
 *
 * @author Peter.Ding
 * @date 2021/04/28
 */
@EnableConfigurationProperties({WeatherConfigProperties.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication {

  /**
   * 启动入口
   *
   * @param args arg
   */
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
