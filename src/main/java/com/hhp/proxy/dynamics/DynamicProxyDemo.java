package com.hhp.proxy.dynamics;

import com.hhp.proxy.IUserService;
import com.hhp.proxy.UserServiceImpl;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {

    public static void main(String[] args) {

        IUserService target = new UserServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(target);
        IUserService proxy = (IUserService) Proxy.newProxyInstance(DynamicProxyDemo.class.getClassLoader(),
                target.getClass().getInterfaces(), handler);
        proxy.add("zhangSan");
    }
}
