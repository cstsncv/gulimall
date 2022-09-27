package com.cstsncv.gulimall.gateway;


import org.junit.jupiter.api.*;

public class Test1 {

    @BeforeAll
    static void beforeA(){
        System.out.println("BeforeAll");
    }

    @BeforeEach
    void each(){
        System.out.println("init");
    }

    @AfterEach
    void eacha(){
        System.out.println("after");
    }

    @Test
    void fun(){
        int res = 1 + 1;
        System.out.println(res);
        Assertions.assertEquals(21, res);
    }
}
