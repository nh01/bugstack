package com.hundsun;

import com.hundsun.jdk.reflect.JDKProxy;
import com.hundsun.jdk.util.ClassLoaderUtil;
import com.hundsun.proxy.cglib.CglibProxy;
import com.hundsun.service.IUserService;
import com.hundsun.service.impl.UserServiceImpl;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ApiTest {

    @Test
    public void test_proxy_jdk() {
        IUserService proxy = (IUserService)JDKProxy.getProxy(ClassLoaderUtil.loadClass("com.hundsun.service.IUserService"));
        String userName = proxy.queryUserNameById("1001");
        System.out.println(userName);

        String name = "ProxyUserService";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{IUserService.class});

        // 输出字节码
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(name + ".class");
            System.out.println((new File("")).getAbsoluteFile());
            out.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test_proxy_cglib() {
        CglibProxy proxy = new CglibProxy();
        UserServiceImpl userService = (UserServiceImpl) proxy.newInstall(new UserServiceImpl());
        String username = userService.queryUserNameById("1001");
        System.out.println(username);
    }
}
