package com.hhp.proxy;

public class UserServiceImpl implements IUserService {
    @Override
    public void add(String name) {
        System.out.println("向数据库插入名为：" + name + "的用户");
    }
}
