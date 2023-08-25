package com.search.xczx_plus_search.search.Dao;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
//条件类
public class SearchQuery {
  //关键字
  private String keywords;
  //大分类
  private String mt;
  //小分类
  private String st;
  //难度等级
  private String grade;
}
