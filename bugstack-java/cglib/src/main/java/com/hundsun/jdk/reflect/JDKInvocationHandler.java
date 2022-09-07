package com.hundsun.jdk.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类反射调用
 */
public class JDKInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法名：" + method.getName());
        return "我被JDKProxy代理了";
    }
}
