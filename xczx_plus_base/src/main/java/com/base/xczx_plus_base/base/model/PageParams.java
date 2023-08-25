package com.base.xczx_plus_base.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.java.Log;

/**
 * @description 分页查询通用参数
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageParams {
    //当前页码
    @ApiModelProperty("当前页码")
    private Long pageNo = 1L;
    //每页记录数默认值
    @ApiModelProperty("每页记录数默认值")
    private Long pageSize =10L;
}

