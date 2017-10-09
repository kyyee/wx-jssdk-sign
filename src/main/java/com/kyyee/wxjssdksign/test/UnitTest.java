/*
 * Copyright (c) 2017.  kyyee All rights reserved.
 */

package com.kyyee.wxjssdksign.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jm1138 on 2017/5/31.
 */
public class UnitTest {
    private static final Logger logger = LoggerFactory.getLogger(UnitTest.class);

    private void hasParameterTest() {
        System.out.println("UnitTest.hasParameterTest");
        String str1 = "Q：晚上在杨公桥吃串串，";
        String str2 = "A：你吃吗？";
        long stime1 = System.nanoTime();
        StringBuilder sb1;
        sb1 = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb1.append(str1)
                    .append(str2);
        }
        System.out.println("总时间s：" + Long.toString((System.nanoTime() - stime1) / 1000 / 1000));

        long stime2 = System.nanoTime();
        StringBuilder sb2;
        sb2 = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb2.append("Q：晚上在杨公桥吃串串，")
                    .append("A：你吃吗？");
        }
        System.out.println("总时间s：" + Long.toString((System.nanoTime() - stime2) / 1000 / 1000));
    }


    public static void main(String[] argus) {
        UnitTest test = new UnitTest();
        test.hasParameterTest();
    }
}
