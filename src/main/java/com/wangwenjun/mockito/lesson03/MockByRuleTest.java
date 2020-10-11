package com.wangwenjun.mockito.lesson03;

import com.wangwenjun.mockito.common.Account;
import com.wangwenjun.mockito.common.AccountDao;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MockByRuleTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void testMock() {
        AccountDao accountDao = mock(AccountDao.class);
        Account account = accountDao.findAccount("x", "y");
        System.out.println(account);
    }

}
