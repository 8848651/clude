package com.example.demo;

import com.example.demo.CS.CS;
import com.example.demo.CS.Customer;
import com.example.demo.CS.CustomerMappers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private CustomerMappers CM;

    @Autowired
    private CS SS;


    @Test
    void contextLoads1() {
        System.out.println(CM.selectOne("8848"));
    }

    @Test
    void contextLoads2() {
        System.out.println(CM.update("5000", "8848"));
    }

    @Test
    void contextLoads3() throws InterruptedException {
        SS.CS1();
    }


}
