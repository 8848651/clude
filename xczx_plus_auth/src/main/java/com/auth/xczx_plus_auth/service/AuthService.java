package com.auth.xczx_plus_auth.service;

import com.auth.xczx_plus_auth.model.Dto.AuthParamsDto;
import com.auth.xczx_plus_auth.model.Dto.XcUserExt;

public interface AuthService {
 XcUserExt execute(AuthParamsDto authParamsDto);

}
