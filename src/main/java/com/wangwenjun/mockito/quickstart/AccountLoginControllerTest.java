package com.wangwenjun.mockito.quickstart;

import com.wangwenjun.mockito.common.Account;
import com.wangwenjun.mockito.common.AccountDao;
import com.wangwenjun.mockito.common.AccountLoginContoller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountLoginControllerTest {

    private AccountDao accountDao;

    private HttpServletRequest request;

    private AccountLoginContoller accountLoginContoller;

    @Before
    public void setup() {
        this.accountDao = Mockito.mock(AccountDao.class);
        this.request = Mockito.mock(HttpServletRequest.class);
        this.accountLoginContoller = new AccountLoginContoller(accountDao);
    }

    @Test
    public void testLoginSuccess() {
        Account account = new Account();
        //mockRequest
        when(request.getParameter("username")).thenReturn("alex");
        when(request.getParameter("password")).thenReturn("123456");
        //mockAccountDao的方法
        when(accountDao.findAccount(anyString(), anyString())).thenReturn(account);
        assertThat(accountLoginContoller.login(request), equalTo("/index"));

    }

    @Test
    public void testLoginFail() {
        //mockRequest
        when(request.getParameter("username")).thenReturn("alex");
        when(request.getParameter("password")).thenReturn("123456");
        //mockAccountDao的方法
        when(accountDao.findAccount(anyString(), anyString())).thenReturn(null);
        assertThat(accountLoginContoller.login(request), equalTo("/login"));
    }

    @Test
    public void testDBError505() {
        Account account = new Account();
        //mockRequest
        when(request.getParameter("username")).thenReturn("alex");
        when(request.getParameter("password")).thenReturn("123456");
        //mockAccountDao的方法
        when(accountDao.findAccount(anyString(), anyString())).thenThrow(UnsupportedOperationException.class);
        assertThat(accountLoginContoller.login(request), equalTo("/505"));
    }

}
