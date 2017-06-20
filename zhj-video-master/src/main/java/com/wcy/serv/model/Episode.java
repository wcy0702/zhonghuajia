package com.wcy.serv.model;

import lombok.Data;

/**
 * 集
 * Created by Silence on 2016/12/12.
 */
@Data
public class Episode {

  /* 第几集 */
  private String index;

  /* 视频源地址 */
  private String value;

public String getIndex() {
	return index;
}

public void setIndex(String index) {
	this.index = index;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

  
}
