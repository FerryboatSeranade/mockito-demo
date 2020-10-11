package com.wangwenjun.mockito.common;

import javax.servlet.http.HttpServletRequest;

public class AccountLoginContoller {
    private final AccountDao accountDao;

    public AccountLoginContoller(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public String login(HttpServletRequest request){
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        try {

            Account account=accountDao.findAccount(username,password);
            if(account==null){
                return "/login";
            }else {
                return "/index";
            }
        }catch (Exception e){//模拟db不可用
            return "/505";
        }
    }
}
