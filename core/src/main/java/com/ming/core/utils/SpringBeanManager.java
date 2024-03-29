package com.ming.core.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

/**
 * 对操作spring applicationcontext提供基本封装  方便使用
 * 使用order 最先加载
 * 预期在容器最开始加载 暂时这个无法生效  如果需要这个bean 优先加载 请调整依赖 来调整bean加载顺序
 *
 * @author ming
 * @date 11:00
 */
public class SpringBeanManager implements ApplicationContextAware, DisposableBean {


    /**
     * spring bean上下文
     *
     * @author ming
     * @date 11:00
     */
    private static ApplicationContext applicationContext;
    /**
     * 获取BeanFactory  进行动态注册bean 删除bean
     *
     * @author mingjie
     * @date 2017-11-10 15:51
     */
    private static DefaultListableBeanFactory defaultListableBeanFactory;

    /**
     * 手动注册的bean 名称列表  必须保证 每个使用它的地方 是一样的
     *
     * @author ming
     * @date 2017-11-10 15:54
     */
    private static volatile Map<String, Class<?>> manualRegisterBeanMap = Maps.newConcurrentMap();


    /**
     * 获取 手动注入的 bean名称
     *
     * @return Set<String>
     * @author ming
     * @date 2017-12-12 13:46
     */
    public static Set<String> getManualRegisterBeanNameSet() {
        return manualRegisterBeanMap.keySet();
    }

    /**
     * 动态注入bean
     *
     * @param beanName
     * @param beanClazz
     * @author ming
     * @date 2017-11-09 16:50
     */
    public static void registerBean(String beanName, Class<?> beanClazz) {
        assert StringUtils.isNotEmpty(beanName);
        assert beanClazz != null;
        checkDefaultListableBeanFactory();
        //创建beanBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz);
        //注册bean
        defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        //添加手工注册的beanName 到集合
        manualRegisterBeanMap.put(beanName, beanClazz);
    }

    /**
     * 删除bean
     *
     * @param beanName
     * @author ming
     * @date 2017-11-10 15:45
     */
    public static void removeBean(String beanName) {
        assert StringUtils.isNotEmpty(beanName);
        //当试图删除 非手动注册的bean的时候
        if (!manualRegisterBeanMap.keySet().contains(beanName)) {
            throw new UnsupportedOperationException("不能删除非手动注册的bean");
        }
        checkDefaultListableBeanFactory();
        defaultListableBeanFactory.removeBeanDefinition(beanName);
        manualRegisterBeanMap.remove(beanName);
    }

    /**
     * 根据名称 获取bean
     *
     * @param name 注册的bean名称
     * @return T
     * @author ming
     * @date 11:19
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 根据类型 获取bean
     *
     * @param clazz 注册bean的类型
     * @return T
     * @author ming
     * @date 11:20
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(beanName, clazz);
    }

    /**
     * 获取application中所有注册的bean 列表
     *
     * @return String[]
     * @author ming
     * @date 2017-08-28 16点
     */
    public static String[] getBeanDefinitionNames() {
        checkApplicationContext();
        return applicationContext.getBeanDefinitionNames();
    }

    /**
     * 统计application中所有bean的数量
     *
     * @return Integer
     * @author ming
     * @date 2017-08-28 17点
     */
    public static Integer getBeanDefinitionCount() {
        checkApplicationContext();
        return applicationContext.getBeanDefinitionCount();
    }

    /**
     * 根据注解获取 beanNameList
     *
     * @param annotation
     * @return String[]
     * @author ming
     * @date 2017-08-28 16点
     */
    public static String[] getBeanNameListByAnnotation(Class<? extends Annotation> annotation) {
        checkApplicationContext();
        return applicationContext.getBeanNamesForAnnotation(annotation);
    }

    /**
     * 根据bean类型获取所有的bean
     *
     * @param clazz
     * @return String[]
     * @author ming
     * @date 2017-08-28 16点
     */
    public static String[] getBeanNamesForType(Class clazz) {
        checkApplicationContext();
        return applicationContext.getBeanNamesForType(clazz);
    }

    /**
     * 根据类型 获取 所有这个类型的bean  map 键为bean名字  值为注册的bean
     *
     * @param clazz
     * @return Map
     * @author ming
     * @date 2017-08-28 16点
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBeansOfType(clazz);
    }

    /**
     * 检测applicationcontext是否可用
     *
     * @author ming
     * @date 2017-08-28 17点
     */
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new NullPointerException("spring applicationContext is null !!!");
        }
    }

    /**
     * 检测 defaultListableBeanFactory
     *
     * @author ming
     * @date 2017-11-10 15:47
     */
    private static void checkDefaultListableBeanFactory() {
        if (defaultListableBeanFactory == null) {
            throw new NullPointerException(" spring defaultListableBeanFactory is null !!!");
        }
    }

    private static void updateApplicationContext(ApplicationContext applicationContext) {
        SpringBeanManager.applicationContext = applicationContext;
    }

    private static void updateDefaultListableBeanFactory(DefaultListableBeanFactory defaultListableBeanFactory) {
        SpringBeanManager.defaultListableBeanFactory = defaultListableBeanFactory;
    }

    public static void publishEvent(final ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

    /**
     * 销毁方法
     *
     * @author ming
     * @date 2017-08-28 17点
     */
    @Override
    public void destroy() throws Exception {
        updateApplicationContext(null);
        updateDefaultListableBeanFactory(null);
    }

    /**
     * 设置上下文
     *
     * @author ming
     * @date 11:17
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        updateApplicationContext(applicationContext);
        //获取 bean factory
        updateDefaultListableBeanFactory((DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory());
    }
}
