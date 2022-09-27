package com.cstsncv.gulimall.gateway;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;



@SpringBootTest
public class Test2 {
    //    @Autowired
    @SpyBean
//    @MockBean
    Svc1 svc1;

    @SpyBean
    Svc2 svc2;

    @Test
    void t1() {
        Mockito.when(svc2.add(1, 2)).thenReturn(3);
        int res = svc1.add(1, 2);
        int res2 = svc1.jian(2, 1);
        Assertions.assertEquals(3, res);
        Assertions.assertEquals(1, res2);
    }
}
