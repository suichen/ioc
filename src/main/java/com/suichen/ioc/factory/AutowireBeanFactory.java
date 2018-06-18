package com.suichen.ioc.factory;

import com.suichen.ioc.BeanDefinition;
import com.suichen.ioc.BeanReference;
import com.suichen.ioc.PropertyValue;

import java.lang.reflect.Field;

public class AutowireBeanFactory extends AbstractBeanFactory{
    @Override
    Object doCreate(BeanDefinition beanDefinition) throws Exception {
        Object bean = beanDefinition.getBeanClass().newInstance();
        addPropertyValue(bean, beanDefinition);
        return bean;
    }

    protected void addPropertyValue(Object bean, BeanDefinition beanDefinition) throws Exception {
        for (PropertyValue pv:beanDefinition.getPropertyValues().getPropertyValueList()) {
            Field declaredField = bean.getClass().getDeclaredField(pv.getName());
            declaredField.setAccessible(true);
            Object value = pv.getValue();

            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }

            declaredField.set(bean, value);
        }
    }
}
