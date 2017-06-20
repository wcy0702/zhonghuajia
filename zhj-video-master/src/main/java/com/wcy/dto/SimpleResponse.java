package com.wcy.dto;

import lombok.Data;

/**
 * 简易响应 DTO
 * Created by Silence on 2017/3/9.
 */
@Data
public class SimpleResponse {
  /**
   * 100 成功
   * 200 失败
   * 其它待添加
   */
  private int code;

  private String message;

public int getCode() {
	return code;
}

public void setCode(int code) {
	this.code = code;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
  
  

}
