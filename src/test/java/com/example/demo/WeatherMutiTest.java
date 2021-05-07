package com.example.demo;

import com.example.demo.service.WeatherService;
import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

public class WeatherMutiTest extends DemoApplicationTests {

  private static int number;

  private static AtomicInteger count = new AtomicInteger();

  @Autowired private WeatherService weatherService;

  @Test
  public void testTetTemperatureTps100() {
    TestRunnable runner =
        new TestRunnable() {
          @Override
          public void runTest() throws Throwable {
            Assert.assertEquals("查询温度失败",(Integer)239, weatherService.getTemperature("10119", "04", "01").get());
            increment();
            number = getCount();
            System.out.println("计数："+number);
          }
        };
    //1秒
    int time = 1;
    int runnerCount = 100;
    TestRunnable[] trs = new TestRunnable[runnerCount];
    for (int i = 0; i < runnerCount; i++) {
      trs[i] = runner;
    }
    MultiThreadedTestRunner multiThreadedTestRunner = new MultiThreadedTestRunner(trs);
    try {
      long startTime = System.currentTimeMillis();
      System.out.println("开始时间戳"+startTime);
      while (true) {
        long endTime = System.currentTimeMillis();
        multiThreadedTestRunner.runTestRunnables();
        if ((endTime - startTime) > time * 1000) {
          System.out.println("结束时间戳"+endTime);
          System.out.println("计数器最大数"+number);
          break;
        }
      }
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testTetTemperatureTps101() {
    TestRunnable runner =
            new TestRunnable() {
              @Override
              public void runTest() throws Throwable {
                Assert.assertEquals("查询温度失败",(Integer)239, weatherService.getTemperature("10119", "04", "01").get());
                increment();
                number = getCount();
                System.out.println("计数："+number);
              }
            };
    //1秒
    int time = 1;
    int runnerCount = 101;
    TestRunnable[] trs = new TestRunnable[runnerCount];
    for (int i = 0; i < runnerCount; i++) {
      trs[i] = runner;
    }
    MultiThreadedTestRunner multiThreadedTestRunner = new MultiThreadedTestRunner(trs);
    try {
      long startTime = System.currentTimeMillis();
      System.out.println("开始时间戳"+startTime);
      while (true) {
        long endTime = System.currentTimeMillis();
        multiThreadedTestRunner.runTestRunnables();
        if ((endTime - startTime) > time * 1000) {
          System.out.println("结束时间戳"+endTime);
          System.out.println("计数器最大数"+number);
          break;
        }
      }
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  public void increment() {
    count.incrementAndGet();
  }

  public int getCount() {
    return count.get();
  }
}
