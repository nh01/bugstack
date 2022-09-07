package com.hundsun.jdk.reflect;



import com.hundsun.jdk.util.ClassLoaderUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 定义代理类获取的服务
 */
public class JDKProxy {

    public static <T> T getProxy(Class<T> interfaceClass) {
        InvocationHandler handler = new JDKInvocationHandler();
        ClassLoader classLoader = ClassLoaderUtil.getClassLoader();
        T result = (T)Proxy.newProxyInstance(classLoader, new Class[]{interfaceClass}, handler);
        return result;
    }
}
