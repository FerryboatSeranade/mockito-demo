package com.wangwenjun.mockito.lesson03;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class Z_ShallowMockTest {
    @Mock
    private Z_Lesson03 lesson03;
    @Mock
    private Z_Lesson03Service lesson03Service;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Z_Lesson03Service lesson03Service_deep;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShallow(){
        //mock方法的返回值
        when(lesson03Service.get()).thenReturn(lesson03);
        Z_Lesson03 lesson03_bak=new Z_Lesson03();
        //这句注释的话，会抛异常，因为该方法抛出了异常
        //这句不注释的话，不会抛异常，因为该方法抛出了异常
        lesson03_bak = lesson03Service.get();
        lesson03_bak.foo();
    }

    @Test
    public void testDeep(){
        //可以明显感受到减少了when-thenReturn的频次
        lesson03Service_deep.get().foo();
    }

}
