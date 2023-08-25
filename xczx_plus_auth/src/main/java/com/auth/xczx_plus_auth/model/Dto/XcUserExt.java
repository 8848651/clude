package com.auth.xczx_plus_auth.model.Dto;
import com.auth.xczx_plus_auth.model.Dao.XcUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class XcUserExt extends XcUser {
    //用户权限
    List<String> permissions = new ArrayList<>();
}
