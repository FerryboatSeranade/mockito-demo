package com.wangwenjun.mockito.lesson08;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentMatcherTest {
    @Mock
    private SampleService sampleService;

    @After
    public void destory() {
        reset(sampleService);
    }

    @Test
    public void wildcardMethod() {
        when(sampleService.intMethod(anyInt(),anyString(),anyCollection(),isA(Serializable.class))).thenReturn(100);
        int res= sampleService.intMethod(1,"Alex", Collections.emptyList(),"String implents Serializable");
        assertThat(res,equalTo(100));

    }
    @Test
    public void wildcardMethodWithSpecific(){
        //第一个when不要放在第三行，因为范围最大，会把前两句全部覆盖
        when(sampleService.intMethod(anyInt(),anyString(),anyCollection(),isA(Serializable.class))).thenReturn(123);
        when(sampleService.intMethod(anyInt(),eq("Alex"),anyCollection(),isA(Serializable.class))).thenReturn(100);
        when(sampleService.intMethod(anyInt(),eq("John"),anyCollection(),isA(Serializable.class))).thenReturn(200);
        int res= sampleService.intMethod(1,"Alex", Collections.emptyList(),"String implents Serializable");
        int res2= sampleService.intMethod(1,"John", Collections.emptyList(),"String implents Serializable");
        assertThat(res,equalTo(100));
        assertThat(res2,equalTo(200));
        int defaultRes=sampleService.intMethod(1,"shu", Collections.emptyList(),"String implents Serializable");
        assertThat(defaultRes,equalTo(123));
    }

    @Test
    public void wildcardMethod2() {
        doNothing().when(sampleService).voidMethod(anyInt(),anyString(),anyCollection(),isA(Serializable.class));
        List<Object> objects = Collections.emptyList();
        sampleService.voidMethod(1,"Alex", objects,"String implents Serializable");
        sampleService.voidMethod(18,"Alex", objects,"String implents Serializable");
        verify(sampleService,times(2)).voidMethod(anyInt(),eq("Alex"),anyCollection(),isA(Serializable.class));

    }


}
