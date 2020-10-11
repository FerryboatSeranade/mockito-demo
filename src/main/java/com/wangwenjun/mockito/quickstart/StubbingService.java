package com.wangwenjun.mockito.quickstart;

public class StubbingService {
    public Integer getI(){
        System.out.println("getI.........");
        return 10;
    }
    public String getS(){
        System.out.println("getS.........");
        throw new RuntimeException();
    }
}
