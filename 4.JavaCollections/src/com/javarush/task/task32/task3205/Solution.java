package com.javarush.task.task32.task3205;

import java.io.Reader;
import java.lang.reflect.Proxy;

/*
Создание прокси-объекта
*/
public class Solution {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
        */
    }

    public static SomeInterfaceWithMethods getProxy() {
        SomeInterfaceWithMethodsImpl original = new SomeInterfaceWithMethodsImpl();
        CustomInvocationHandler handler = new CustomInvocationHandler(original);
        SomeInterfaceWithMethods reader = (SomeInterfaceWithMethods) Proxy.newProxyInstance(original.getClass().getClassLoader(), original.getClass().getInterfaces(), handler);
        return reader;
    }
}