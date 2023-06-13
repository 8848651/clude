package com.example.demo.CS;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class CS {

    @Autowired
    private CustomerMappers CM;

    public void CS1() throws InterruptedException {
        Customer user9 = CM.selectOne("8848");
        System.out.println(user9.toString());

        Thread.sleep(60000);

        Customer user9s = CM.selectOne("8848");
        System.out.println(user9s.toString());
    }

    public void CS2() throws InterruptedException {
        Customer user9 = CM.selecttwo("8848");
        System.out.println(user9.toString());

        Thread.sleep(60000);

        Customer user9s = CM.selecttwo("8848");
        System.out.println(user9s.toString());
    }
}
