package com.suichen.ioc.factory;

import com.suichen.ioc.BeanDefinition;

public interface BeanFactory {

    Object getBean(String name) throws Exception;
    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
