package com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RepositoryInvocationHandler implements InvocationHandler {
    private final DatabaseConnection databaseConnection;
    private final Repository repository;
    public RepositoryInvocationHandler(Repository repository) {
        this.databaseConnection = new DatabaseConnection();
        this.repository = repository;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        try {
            databaseConnection.connect();
            Object invoke = method.invoke(repository, args);
            databaseConnection.close();
            return invoke;


        }catch (InvocationTargetException e){
            e.printStackTrace();
        }

        return null;
    }
}
