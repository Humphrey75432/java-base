package com.hhp.proxy.statics;

import com.hhp.proxy.IUserService;
import com.hhp.proxy.UserServiceImpl;

public class StaticsProxyDemo {

    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        IUserService proxyService = new UserServiceProxy(userService);
        proxyService.add("zhangSan");
    }
}
