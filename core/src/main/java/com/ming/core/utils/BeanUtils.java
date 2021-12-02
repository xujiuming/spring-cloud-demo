package com.ming.core.utils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.InvocationTargetException;

/**
 * 基于cglib的bean  copy
 * <p>
 * 依赖 guava的 table结构 做缓存
 *
 * @author ming
 * @date 2021-07-28 16:00:29
 */
@Slf4j
public class BeanUtils {

    /**
     * copier 缓存
     */
    private static final Table<Class<?>, Class<?>, BeanCopier> BEAN_COPIER_TABLE = HashBasedTable.create();

    /**
     * 获取 bean copier
     *
     * @param sourceClazz sourceClazz
     * @param targetClazz targetClazz
     * @return BeanCopier
     * @author ming
     * @date 2021-07-28 16:09:17
     */
    private static BeanCopier getBeanCopier(Class<?> sourceClazz, Class<?> targetClazz) {
        if (BEAN_COPIER_TABLE.contains(sourceClazz, targetClazz)) {
            return BEAN_COPIER_TABLE.get(sourceClazz, targetClazz);
        }
        BeanCopier beanCopier = BeanCopier.create(sourceClazz, targetClazz, false);
        BEAN_COPIER_TABLE.put(sourceClazz, targetClazz, beanCopier);
        return beanCopier;
    }

    /**
     * 复制对象
     *
     * @param source 源对象
     * @param target 复制对象
     * @author ming
     * @date 2021-07-28 16:32:37
     */
    public static <T1, T2> void copy(T1 source, T2 target) {
        getBeanCopier(source.getClass(), target.getClass()).copy(source, target, null);
    }

    /**
     * 复制bean 返回一个新的bean
     *
     * @param source      源对象
     * @param targetClazz 复制对象的class
     * @return R  复制对象
     * @author ming
     * @date 2021-07-28 16:35:03
     */
    public static <T, R> R copy(T source, Class<R> targetClazz) {
        try {
            R result = targetClazz.getConstructor().newInstance();
            copy(source, result);
            return result;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("复制对象失败!", e);
            throw new RuntimeException("复制对象失败!");
        }
    }
}
