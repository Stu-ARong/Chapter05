package com.bdqn.chapter05.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: 赖榕
 * @date: 2019/10/14
 * @description: javabean工具类
 * @version: 1.0
 * @since: JDK1.8
 * @packageName: com.bdqn.chapter05.utils
 */
public class BeanUtils {

    /**
     * 向指定对象中的制定属性注入值
     *
     * @param obj       对象
     * @param fieldName 属性名称
     * @param value     值
     * @throws Exception
     */
    public static void property(Object obj, String fieldName, Object value) throws Exception {

        Class clazz = obj.getClass();

        //  获取对象的属性
        Field field = clazz.getDeclaredField(fieldName);

        // 根据setter机制获取属性对应的setter方法名称
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        //根据方法名称获取method对象
        Method method = clazz.getDeclaredMethod(methodName, field.getType());

        // 根据包装类型对象的String构造函数创建对象并赋值给指定属性
        method.invoke(obj, field.getType().getDeclaredConstructor(String.class).newInstance(value));

    }

    ;

}
