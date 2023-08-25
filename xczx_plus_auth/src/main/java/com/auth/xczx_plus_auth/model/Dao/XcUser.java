package com.auth.xczx_plus_auth.model.Dao;

import java.time.LocalDateTime;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
@Data
@TableName("xc_user")
public class XcUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    //账号
    private String username;

    private String password;

    private String salt;

   //////微信unionid
    private String wxUnionid;

    private String nickname;
    //真实姓名
    private String name;

    private String userpic;

    private String companyId;

    //用户类型
    private String utype;

    private LocalDateTime birthday;

    private String sex;

    private String email;

    private String cellphone;

    private String qq;

    //用户状态
    private String status;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
