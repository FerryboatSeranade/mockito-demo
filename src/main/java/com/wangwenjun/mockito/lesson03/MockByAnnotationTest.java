package com.wangwenjun.mockito.lesson03;

import com.wangwenjun.mockito.common.Account;
import com.wangwenjun.mockito.common.AccountDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MockByAnnotationTest {

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private AccountDao accountDao;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMock(){
        Account account = accountDao.findAccount("x", "y");
        System.out.println(account);
    }

}
