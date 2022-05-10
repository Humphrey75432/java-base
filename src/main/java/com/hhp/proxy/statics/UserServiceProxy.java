package com.hhp.proxy.statics;

import com.hhp.proxy.IUserService;

public class UserServiceProxy implements IUserService {
    private IUserService target;

    public UserServiceProxy(IUserService target) {
        this.target = target;
    }

    @Override
    public void add(String name) {
        System.out.println("准备向数据库中插入数据");
        target.add(name);
        System.out.println("插入数据库完成");
    }
}
