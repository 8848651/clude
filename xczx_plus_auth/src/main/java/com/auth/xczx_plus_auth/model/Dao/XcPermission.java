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
@TableName("xc_permission")
public class XcPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String roleId;

    private String menuId;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
