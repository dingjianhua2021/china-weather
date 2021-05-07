package com.example.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * 结果
 *
 * @author Peter.Ding
 * @date 2021/04/29
 */
@ApiModel("响应信息主体")
public class R<T> implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("返回标记：成功标记=0，失败标记=1")
  private int code;

  @ApiModelProperty("返回信息")
  private String message;

  @ApiModelProperty("数据")
  private T data;

  @ApiModelProperty("返回标记：成功标记=true，失败标记=false")
  private String result = "true";

  public static <T> R<T> ok(T data) {
    return restResult(data, CommonConstants.SUCCESS, "操作成功！");
  }

  public static <T> R<T> ok(T data, String message) {
    return restResult(data, CommonConstants.SUCCESS, message);
  }

  public static <T> R<T> failed(T data) {
    return restResult(data, CommonConstants.FAIL, "操作失败！");
  }

  public static <T> R<T> failed(T data, String message) {
    return restResult(data, CommonConstants.FAIL, message);
  }

  private static <T> R<T> restResult(T data, int code, String message) {
    return restResult(data, code, code == 0 ? "true" : "false", message);
  }

  private static <T> R<T> restResult(T data, int code, String result, String message) {
    R<T> apiResult = new R();
    apiResult.setCode(code);
    apiResult.setResult(result);
    apiResult.setData(data);
    apiResult.setMessage(message);
    return apiResult;
  }

  public static <T> R.RBuilder<T> builder() {
    return new R.RBuilder();
  }

  public R() {}

  public R(int code, String message, T data, String result) {
    this.code = code;
    this.message = message;
    this.data = data;
    this.result = result;
  }

  public int getCode() {
    return this.code;
  }

  public R<T> setCode(int code) {
    this.code = code;
    return this;
  }

  public String getMessage() {
    return this.message;
  }

  public R<T> setMessage(String message) {
    this.message = message;
    return this;
  }

  public T getData() {
    return this.data;
  }

  public R<T> setData(T data) {
    this.data = data;
    return this;
  }

  public String getResult() {
    return this.result;
  }

  public R<T> setResult(String result) {
    this.result = result;
    return this;
  }

  public static class RBuilder<T> {
    private int code;
    private String message;
    private T data;
    private String result;

    RBuilder() {}

    public R.RBuilder<T> code(int code) {
      this.code = code;
      return this;
    }

    public R.RBuilder<T> message(String message) {
      this.message = message;
      return this;
    }

    public R.RBuilder<T> data(T data) {
      this.data = data;
      return this;
    }

    public R.RBuilder<T> result(String result) {
      this.result = result;
      return this;
    }

    public R<T> build() {
      return new R(this.code, this.message, this.data, this.result);
    }
  }
}
