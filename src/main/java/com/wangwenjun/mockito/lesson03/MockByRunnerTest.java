package com.wangwenjun.mockito.lesson03;

import com.wangwenjun.mockito.common.Account;
import com.wangwenjun.mockito.common.AccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MockByRunnerTest {

    @Test
    public void testMock(){
        AccountDao accountDao=mock(AccountDao.class);
//        AccountDao accountDao=mock(AccountDao.class, Mockito.RETURNS_SMART_NULLS);//不用默认值以防变空！
        Account account = accountDao.findAccount("x", "y");
        System.out.println(account);
    }

}
