package com.system.xczx_plus_system_service.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.xczx_plus_system_model.Dao.Dictionary;
import com.system.xczx_plus_system_service.mapper.DictionaryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Override
    public List<Dictionary> queryAll() {
        List<Dictionary> list = this.list();
        return list;
    }

    @Override
    public Dictionary getByCode(String code) {
        LambdaQueryWrapper<Dictionary> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dictionary::getCode, code);
        Dictionary dictionary = this.getOne(queryWrapper);
        return dictionary;
    }
}
