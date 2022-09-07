package com.hundsun.jdk.util;



public class ClassLoaderUtil {


    public static Class loadClass(String className) {
        try {
            return getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not found '"+className+"'", e);
        }
    }
    public static ClassLoader getClassLoader() {
        return ClassLoaderUtil.class.getClassLoader();
    }
}
