package com.kaikeba.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SMSUtilTest {

    @Test
    public void sendSms(){
        boolean flag = SMSUtil.send("18516955565", "123456");
        System.out.println(flag);
    }

}