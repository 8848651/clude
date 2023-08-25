package com.auth.xczx_plus_auth.model.Dao;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author  
 */
@Data
@TableName("xc_menu")
public class XcMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 父菜单ID
     */
    private String pId;

    /**
     * 名称
     */
    private String menuName;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 是否是菜单
     */
    private String isMenu;

    /**
     * 菜单层级
     */
    private Integer level;

    /**
     * 菜单排序
     */
    private Integer sort;

    private String status;

    private String icon;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
