package com.cstsncv.gulimall.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Svc1 {

    @Autowired
    Svc2 svc2;

    public int add(int a, int b) {
        return svc2.add(a, b);
    }

    public int jian(int a, int b) {
        return a - b;
    }
}
