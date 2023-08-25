package com.system.xczx_plus_system_service;

import com.system.xczx_plus_system_model.Dao.Dictionary;
import com.system.xczx_plus_system_service.service.DictionaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class XczxPlusSystemServiceApplicationTests {

    @Autowired
    DictionaryService DS;

    @Test
    void contextLoads() {
        List<Dictionary> dictionaries = DS.queryAll();
        System.out.println(dictionaries);
    }

}
