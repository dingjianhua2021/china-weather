package com.example.demo.common;

/**
 * http状态枚举
 *
 * @author Peter.Ding
 * @date 2021/04/29
 */
public enum HttpStatusEnum {
  /** 继续 */
  CONTINUE(100, "Continue", "请继续发送请求的剩余部分"),
  /** 交换协议 */
  SWITCHING_PROTOCOLS(101, "Switching Protocols", "协议切换"),
  /** 处理 */
  PROCESSING(102, "Processing", "请求将继续执行"),
  /** 检查点 */
  CHECKPOINT(103, "Checkpoint", "可以预加载"),
  /** 好吧 */
  OK(200, "OK", "请求已经成功处理"),
  /** 创建 */
  CREATED(201, "Created", "请求已经成功处理，并创建了资源"),
  /** 接受 */
  ACCEPTED(202, "Accepted", "请求已经接受，等待执行"),
  /** 非权威的信息 */
  NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information", "请求已经成功处理，但是信息不是原始的"),
  /** 没有内容 */
  NO_CONTENT(204, "No Content", "请求已经成功处理，没有内容需要返回"),
  /** 重置内容 */
  RESET_CONTENT(205, "Reset Content", "请求已经成功处理，请重置视图"),
  /** 部分内容 */
  PARTIAL_CONTENT(206, "Partial Content", "部分Get请求已经成功处理"),
  /** 多状态 */
  MULTI_STATUS(207, "Multi-Status", "请求已经成功处理，将返回XML消息体"),
  /** 已经报道的 */
  ALREADY_REPORTED(208, "Already Reported", "请求已经成功处理，一个DAV的绑定成员被前一个请求枚举，并且没有被再一次包括"),
  /** 我使用 */
  IM_USED(226, "IM Used", "请求已经成功处理，将响应一个或者多个实例"),
  /** 多个选择 */
  MULTIPLE_CHOICES(300, "Multiple Choices", "提供可供选择的回馈"),
  /** 搬到永久 */
  MOVED_PERMANENTLY(301, "Moved Permanently", "请求的资源已经永久转移"),
  /** 发现 */
  FOUND(302, "Found", "请重新发送请求"),
  /** 看到其他 */
  // MOVED_TEMPORARILY(302, "Moved Temporarily", "") 已经过时
  SEE_OTHER(303, "See Other", "请以Get方式请求另一个URI"),
  /** 不修改 */
  NOT_MODIFIED(304, "Not Modified", "资源未改变"),
  /** 使用代理 */
  USE_PROXY(305, "Use Proxy", "请通过Location域中的代理进行访问"),
  /** 临时重定向 */
  // 306在新版本的规范中被弃用
  TEMPORARY_REDIRECT(307, "Temporary Redirect", "请求的资源临时从不同的URI响应请求"),
  /** 简历不完整 */
  RESUME_INCOMPLETE(308, "Resume Incomplete", "请求的资源已经永久转移"),
  /** 坏的请求 */
  BAD_REQUEST(400, "Bad Request", "请求错误，请修正请求"),
  /** 未经授权的 */
  UNAUTHORIZED(401, "Unauthorized", "没有被授权或者授权已经失效"),
  /** 付款要求 */
  PAYMENT_REQUIRED(402, "Payment Required", "预留状态"),
  /** 被禁止的 */
  FORBIDDEN(403, "Forbidden", "请求被理解，但是拒绝执行"),
  /** 没有找到 */
  NOT_FOUND(404, "Not Found", "资源未找到"),
  /** 方法不允许 */
  METHOD_NOT_ALLOWED(405, "Method Not Allowed", "请求方法不允许被执行"),
  /** 不能接受的 */
  NOT_ACCEPTABLE(406, "Not Acceptable", "请求的资源不满足请求者要求"),
  /** 代理身份验证要求 */
  PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required", "请通过代理进行身份验证"),
  /** 请求超时 */
  REQUEST_TIMEOUT(408, "Request Timeout", "请求超时"),
  /** 冲突 */
  CONFLICT(409, "Conflict", "请求冲突"),
  /** 走了 */
  GONE(410, "Gone", "请求的资源不可用"),
  /** 长度要求 */
  LENGTH_REQUIRED(411, "Length Required", "Content-Length未定义"),
  /** 失败的前提 */
  PRECONDITION_FAILED(412, "Precondition Failed", "不满足请求的先决条件"),
  /** 请求实体太大 */
  REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large", "请求发送的实体太大"),
  /** 请求uri太长 */
  REQUEST_URI_TOO_LONG(414, "Request-URI Too Long", "请求的URI超长"),
  /** 不支持的媒体类型 */
  UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type", "请求发送的实体类型不受支持"),
  /** 不可以满足的要求范围 */
  REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable", "Range指定的范围与当前资源可用范围不一致"),
  /** 期望失败 */
  EXPECTATION_FAILED(417, "Expectation Failed", "请求头Expect中指定的预期内容无法被服务器满足"),
  /** unprocessable实体 */
  UNPROCESSABLE_ENTITY(422, "Unprocessable Entity", "请求格式正确，但是由于含有语义错误，无法响应"),
  /** 锁着的 */
  LOCKED(423, "Locked", "当前资源被锁定"),
  /** 失败的依赖 */
  FAILED_DEPENDENCY(424, "Failed Dependency", "由于之前的请求发生错误，导致当前请求失败"),
  /** 升级需要 */
  UPGRADE_REQUIRED(426, "Upgrade Required", "客户端需要切换到TLS1.0"),
  /** 先决条件要求 */
  PRECONDITION_REQUIRED(428, "Precondition Required", "请求需要提供前置条件"),
  /** 太多的请求 */
  TOO_MANY_REQUESTS(429, "Too Many Requests", "请求过多"),
  /** 请求头字段太大 */
  REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large", "请求头超大，拒绝请求"),
  /** 内部服务器错误 */
  INTERNAL_SERVER_ERROR(500, "Internal Server Error", "服务器内部错误"),
  /** 没有实现 */
  NOT_IMPLEMENTED(501, "Not Implemented", "服务器不支持当前请求的部分功能"),
  /** 坏的网关 */
  BAD_GATEWAY(502, "Bad Gateway", "响应无效"),
  /** 服务不可用 */
  SERVICE_UNAVAILABLE(503, "Service Unavailable", "服务器维护或者过载，拒绝服务"),
  /** 网关超时 */
  GATEWAY_TIMEOUT(504, "Gateway Timeout", "上游服务器超时"),
  /** http版本不支持 */
  HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported", "不支持的HTTP版本"),
  /** 变体也协商 */
  VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates", "服务器内部配置错误"),
  /** 存储不足 */
  INSUFFICIENT_STORAGE(507, "Insufficient Storage", "服务器无法完成存储请求所需的内容"),
  /** 循环检测 */
  LOOP_DETECTED(508, "Loop Detected", "服务器处理请求时发现死循环"),
  /** 带宽限制超过 */
  BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded", "服务器达到带宽限制"),
  /** 不延长 */
  NOT_EXTENDED(510, "Not Extended", "获取资源所需的策略没有被满足"),
  /** 网络身份验证要求 */
  NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required", "需要进行网络授权");

  /** 代码 */
  private final int code;
  /** 原因的话我们 */
  private final String reasonPhraseUS;
  /** 原因短语cn */
  private final String reasonPhraseCN;

  /** 信息 */
  private static final int INFORMATIONAL = 1,
      SUCCESSFUL = 2,
      REDIRECTION = 3,
      CLIENT_ERROR = 4,
      SERVER_ERROR = 5;

  /**
   * http状态枚举
   *
   * @param code 代码
   * @param reasonPhraseUS 原因的话我们
   * @param reasonPhraseCN 原因短语cn
   */
  HttpStatusEnum(int code, String reasonPhraseUS, String reasonPhraseCN) {
    this.code = code;
    this.reasonPhraseUS = reasonPhraseUS;
    this.reasonPhraseCN = reasonPhraseCN;
  }

  /**
   * 代码
   *
   * @return int
   */
  public int code() {
    return code;
  }

  /**
   * 原因的话我们
   *
   * @return {@link String}
   */
  public String reasonPhraseUS() {
    return reasonPhraseUS;
  }

  /**
   * 原因短语cn
   *
   * @return {@link String}
   */
  public String reasonPhraseCN() {
    return reasonPhraseCN;
  }

  /**
   * 的价值
   *
   * @param code 代码
   * @return {@link HttpStatusEnum}
   */
  public static HttpStatusEnum valueOf(int code) {
    for (HttpStatusEnum httpStatus : values()) {
      if (httpStatus.code() == code) {
        return httpStatus;
      }
    }
    throw new IllegalArgumentException("No matching constant for [" + code + "]");
  }

  /**
   * is1xx信息
   *
   * @return boolean
   */
  public boolean is1xxInformational() {
    return type() == INFORMATIONAL;
  }

  /**
   * is2xx成功
   *
   * @return boolean
   */
  public boolean is2xxSuccessful() {
    return type() == SUCCESSFUL;
  }

  /**
   * is3xx重定向
   *
   * @return boolean
   */
  public boolean is3xxRedirection() {
    return type() == REDIRECTION;
  }

  /**
   * is4xx客户端错误
   *
   * @return boolean
   */
  public boolean is4xxClientError() {
    return type() == CLIENT_ERROR;
  }

  /**
   * is5xx服务器错误
   *
   * @return boolean
   */
  public boolean is5xxServerError() {
    return type() == SERVER_ERROR;
  }

  /**
   * 类型
   *
   * @return int
   */
  private int type() {
    return (int) code / 100;
  }
}
