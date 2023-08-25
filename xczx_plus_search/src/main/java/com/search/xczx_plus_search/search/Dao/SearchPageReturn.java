package com.search.xczx_plus_search.search.Dao;

import com.base.xczx_plus_base.base.model.PageResult;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
//返回类
public class SearchPageReturn<T> extends PageResult {

    //大分类列表
    List<String> mtList;
    //小分类列表
    List<String> stList;

    public SearchPageReturn(List<T> items, long counts, long page, long pageSize) {
        super(items, counts, page, pageSize);
    }

}
