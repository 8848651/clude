package com.system.xczx_plus_system_service.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.system.xczx_plus_system_model.Dao.Dictionary;

import java.util.List;

public interface DictionaryService extends IService<Dictionary> {
    List<Dictionary> queryAll();
    Dictionary getByCode(String code);
}
