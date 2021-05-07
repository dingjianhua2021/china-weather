package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.common.ServiceException;
import com.example.demo.service.WeatherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 天气查询控制器
 *
 * @author Peter.Ding
 * @date 2021/04/28
 */
@Controller
@RequestMapping(value = "/weather")
@Api("温度查询")
public class WeatherController {

  @Autowired
  private WeatherService weatherService;

  @GetMapping("/query/county")
  @ResponseBody
  public R getTemperature(
      @RequestParam String province, @RequestParam String city, @RequestParam String county) {
    try{
      Optional<Integer> temperature = weatherService.getTemperature(province, city, county);
      double fenmu = 10d;
      double result = temperature.get() / fenmu;
      return R.ok(result);
    }catch (ServiceException e){
      return R.failed(null,e.getValue());
    }
  }
}
