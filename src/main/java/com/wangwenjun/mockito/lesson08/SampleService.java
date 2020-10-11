package com.wangwenjun.mockito.lesson08;

import java.io.Serializable;
import java.util.Collection;

public class SampleService {
    public int intMethod(int x, String s, Collection collection, Serializable serializable){
        throw  new RuntimeException();
    }
    public void voidMethod(int x, String s, Collection collection, Serializable serializable){
        throw  new RuntimeException();
    }
}
