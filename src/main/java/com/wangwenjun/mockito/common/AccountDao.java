package com.wangwenjun.mockito.common;

public class AccountDao {
    public Account findAccount(String username ,String password){
        throw new UnsupportedOperationException();//db 不可用
    }

}
