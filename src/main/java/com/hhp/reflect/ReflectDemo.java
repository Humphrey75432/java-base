package com.hhp.reflect;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<ReflectDemo> formatter = ReflectDemo.class;
        Method method = formatter.getMethod("runWithResult", String.class);
        String aa = (String) method.invoke(new ReflectDemo(), "fuck");
        System.out.println("output: " + aa);
    }

    public void run(String obj) {
        System.out.println(obj + " reflect methods.");
    }

    public String runWithResult(String obj) {
        return "Hello " + obj + ", Welcome!";
    }
}
