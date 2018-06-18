package com.suichen.ioc.factory;

import com.suichen.ioc.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanFactory implements BeanFactory{
    private Map<String, BeanDefinition> map = new HashMap<>();

    abstract Object doCreate(BeanDefinition beanDefinition) throws Exception;

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = map.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named "+name+" is defined");
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            return doCreate(beanDefinition);
        }
        return bean;
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        Object bean = doCreate(beanDefinition);
        beanDefinition.setBean(bean);
        map.put(name, beanDefinition);
    }
}
