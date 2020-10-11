package com.wangwenjun.mockito.lesson04and05_stubbing;

import com.wangwenjun.mockito.quickstart.StubbingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StubbingTest {
    private List<String> list;

    @Before
    public void init() {
        this.list = mock(ArrayList.class);
    }

    @Test
    public void testUsingStubbing() {
        when(list.get(0)).thenReturn("first");//录制
        assertThat(list.get(0), equalTo("first"));//播放+断言   三步走
        //这句注释调单元测试就失败了，因为允许到fail了
        when(list.get(anyInt())).thenThrow(new RuntimeException());
        try {
            list.get(0);
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    /**
     * doxxx. when for void method and note void method
     * when(),thenxxx for not void method
     */
    @Test
    public void howToStubbingVoidMethod() {
        //when要求参数不空!!!
//        when(list.clear()).thenReturn(null);
        doNothing().when(list).clear();
        list.clear();
        list.clear();
        list.clear();
        verify(list, times(3)).clear();//验证某种行为发生了几次

        //mock 行为 of void method!
        doThrow(RuntimeException.class).when(list).clear();
        try {
            list.clear();
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void iterateStubbing() {
        //以下两种方式等价
        when(list.size()).thenReturn(1, 2, 3, 4);
        when(list.size()).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4);

        assertThat(list.size(), equalTo(1));
        assertThat(list.size(), equalTo(2));
        assertThat(list.size(), equalTo(3));
        assertThat(list.size(), equalTo(4));
        assertThat(list.size(), equalTo(4));
        assertThat(list.size(), equalTo(4));
    }

    @Test
    public void stubbingWithAnswer() {
        when(list.get(anyInt())).thenAnswer(invocationOnMock -> {
            //获取输入参数中第一个参数的值
            Integer index = invocationOnMock.getArgumentAt(0, Integer.class);
            //指定返回值
            return String.valueOf(index * 10);
        });
        assertThat(list.get(0), equalTo("0"));
        assertThat(list.get(999), equalTo("9990"));
    }

    @After
    public void destroy() {
        reset(this.list);
    }

    @Test
    public void stubbingWithRealCall(){
        StubbingService stub = mock(StubbingService.class);
        System.out.println(stub.getClass());//本质上是cglib帮助生成的类
        System.out.println("before record : stub.getI() = " + stub.getI());
        System.out.println("before record : stub.getS() = " + stub.getS());
        when(stub.getS()).thenReturn("Alex");
        when(stub.getI()).thenCallRealMethod();
        assertThat(stub.getS(), equalTo("Alex"));
        assertThat(stub.getI(),equalTo(10));
    }


}
