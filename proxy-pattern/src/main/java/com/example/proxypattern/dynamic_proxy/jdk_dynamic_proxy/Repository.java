package com.example.proxypattern.dynamic_proxy.jdk_dynamic_proxy;

import java.util.List;

public interface Repository<T, ID>{

    T findById(ID id);

    List<T> findAll();
}
