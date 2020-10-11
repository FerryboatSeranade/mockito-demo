package com.wangwenjun.mockito.lesson07_argumentMatchers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentMatcherTest {

    @Test
    public void basicTest() {
        List<Integer> list = mock(ArrayList.class);
        //以下两句完全等价
        when(list.get(0)).thenReturn(100);
        when(list.get(eq(0))).thenReturn(100);

        assertThat(list.get(0), equalTo(100));
        assertThat(list.get(1), nullValue());
    }

    @Test
    public void testComplex(){
        //============isA==============
        //如果argument is isA中指定类型，就thenxxx()
        //============isA==============
        Foo foo=mock(Foo.class);
        when(foo.function(isA(Parent.class))).thenReturn(100);//如果参数是Parent的实例，返回100
        int res = foo.function(new Child1());
        assertThat(res,equalTo(100));
        when(foo.function(isA(Child1.class))).thenReturn(-100);//如果参数是Parent的实例，返回100
        res = foo.function(new Child1());
        assertThat(res,equalTo(-100));

        //============any==============
        //如果argument 任意，只要通过编译，就thenxxx()
        //============any==============
        when(foo.function(any(Child1.class))).thenReturn(0);//啥也不干,就返回true!
        res = foo.function(new Child2());
        assertThat(res,equalTo(0));

    }
    static class Foo{
        int function(Parent p){
            return p.work();
        }
    }
    interface Parent{
        int work();
    }

    class Child1 implements Parent{
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }
    class Child2 implements Parent{
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }
}
